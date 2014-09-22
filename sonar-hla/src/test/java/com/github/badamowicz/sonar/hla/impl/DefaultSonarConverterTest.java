/*  _______________________________________
 * < Sonar High Level API                  >
 * < Copyright 2014 Bernd Adamowicz        >
 * < mailto:info AT bernd-adamowicz DOT de >
 *  ---------------------------------------
 *  \
 *   \   \_\_    _/_/
 *    \      \__/
 *           (oo)\_______
 *           (__)\       )\/\
 *               ||----w |
 *               ||     ||
 *
 * Sonar-HLA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.badamowicz.sonar.hla.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.sonar.wsclient.services.Measure;
import org.sonar.wsclient.services.Resource;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;
import com.github.badamowicz.sonar.hla.exceptions.SonarProcessingException;

/**
 * Test cases for {@link DefaultSonarExtractor}.
 * 
 * Created Aug 12, 2014 12:08:53 PM by bernd
 */
public class DefaultSonarConverterTest {

    static final Logger           LOG                    = Logger.getLogger(DefaultSonarConverterTest.class);

    private Measure               sonarMeasure1          = null;
    private Measure               sonarMeasure2          = null;
    private Measure               sonarMeasure3          = null;
    private List<Measure>         measureList            = null;
    private Resource              resource1              = null;
    private Resource              resource2              = null;
    private Project               project1               = null;
    private Project               project2               = null;
    private List<IProject>        projectList            = null;
    private DefaultSonarConverter converter              = null;
    private String                csvData                = null;
    private String                csvDataSurrounded      = null;
    private File                  csvFile                = null;
    private File                  csvShortcutFile        = null;

    private static final String   KEY_1                  = "some.project:key1";
    private static final String   KEY_2                  = "some.project:key2";
    private static final String   SEPARATOR              = ",";
    private static final String   QUOT                   = "\"";
    private static final Pattern  PATTERN_ID             = Pattern.compile("DefaultSonarConverter with CSV separator.*");
    private static final String   VERSION                = "4711";
    private static final String   CSV_FILE_NAME          = "unittest.csv";
    private static final String   CSV_SHORTCUT_FILE_NAME = "unittest-shortcut.csv";

    @BeforeClass
    public void beforeClass() {

        sonarMeasure1 = new Measure();
        sonarMeasure1.setMetricKey("coverage");
        sonarMeasure1.setFormattedValue("86.8%");

        sonarMeasure2 = new Measure();
        sonarMeasure2.setMetricKey("ncloc");
        sonarMeasure2.setFormattedValue("16,754");

        sonarMeasure3 = new Measure();
        sonarMeasure3.setMetricKey("violations");
        sonarMeasure3.setFormattedValue("19");

        measureList = new ArrayList<Measure>();
        measureList.add(sonarMeasure1);
        measureList.add(sonarMeasure2);
        measureList.add(sonarMeasure3);

        resource1 = new Resource();
        resource1.setKey(KEY_1);
        resource1.setMeasures(measureList);
        resource1.setVersion(VERSION);

        resource2 = new Resource();
        resource2.setKey(KEY_2);
        resource2.setMeasures(measureList);
        resource2.setVersion(VERSION);

        project1 = new Project(KEY_1, resource1);
        project2 = new Project(KEY_2, resource2);

        projectList = new ArrayList<IProject>();
        projectList.add(project1);
        projectList.add(project2);

        converter = new DefaultSonarConverter();

        prepareCSVFile();
    }

    private void prepareCSVFile() {

        csvFile = new File(FileUtils.getTempDirectory(), CSV_FILE_NAME);
        assertNotNull(csvFile, "Could not prepare CSV file for testing!");

        csvShortcutFile = new File(FileUtils.getTempDirectory(), CSV_SHORTCUT_FILE_NAME);
        assertNotNull(csvShortcutFile, "Could not prepare CSV shortcut file for testing!");
    }

    @AfterClass
    public void cleanUp() {

        FileUtils.deleteQuietly(csvFile);
        FileUtils.deleteQuietly(csvShortcutFile);
    }

    @Test
    public void writeCSVDataToFile() throws IOException {

        converter.writeCSVDataToFile(csvShortcutFile.getAbsolutePath(), "sillydata");
        assertNotNull(csvShortcutFile, "No CSV data written to file!");
        assertFalse(FileUtils.readFileToString(csvShortcutFile).isEmpty(), "Written CSV does not contain any data!");
    }

    @Test(expectedExceptions = SonarProcessingException.class)
    public void writeCSVDataToFileFail() {

        converter.writeCSVDataToFile(null, "sillydata");
    }

    @Test
    public void getCSVDataAsFile() throws IOException {

        String csvContent = null;

        csvFile = converter.getCSVDataAsFile(csvFile.getAbsolutePath(), projectList, Arrays.asList(HLAMeasure.values()), false);
        csvContent = FileUtils.readFileToString(csvFile);
        assertNotNull(csvContent, "No CSV data written to file!");
        assertFalse(csvContent.isEmpty(), "Written CSV does not contain any data!");
    }

    @Test(expectedExceptions = SonarProcessingException.class)
    public void getCSVDataAsFileFail() {

        csvFile = converter.getCSVDataAsFile(null, projectList, Arrays.asList(HLAMeasure.values()), false);
    }

    /**
     * Check if there is CSV data returned at all and perform some plausibility checks.
     */
    @Test
    public void getCSVData() {

        csvData = converter.getCSVData(projectList, Arrays.asList(HLAMeasure.values()), false);
        assertNotNull(csvData, "No CSV data created!");

        LOG.debug("Generated CSV data is:\n" + csvData);

        for (String currLine : csvData.split("\\n")) {

            assertFalse(currLine.startsWith(SEPARATOR), "Separator character must not be at the start of a line!");
            assertFalse(currLine.endsWith(SEPARATOR), "Separator character must not be at the end of a line!");
        }
    }

    /**
     * Check if there is CSV data returned at all and perform some plausibility checks.
     */
    @Test
    public void getCSVDataSurrounded() {

        csvDataSurrounded = converter.getCSVData(projectList, Arrays.asList(HLAMeasure.values()), true, true);
        assertNotNull(csvDataSurrounded, "No CSV data created!");

        LOG.debug("Generated surrounded CSV data is:\n" + csvDataSurrounded);

        for (String currLine : csvDataSurrounded.split("\\n")) {

            assertFalse(currLine.startsWith(SEPARATOR), "Separator character must not be at the start of a line!");
            assertFalse(currLine.endsWith(SEPARATOR), "Separator character must not be at the end of a line!");

            assertTrue(currLine.startsWith(QUOT), "No quote found at beginning of line!");
            assertTrue(currLine.endsWith(QUOT), "No quote found at end of line!");
        }
    }

    @Test(dependsOnMethods = { "getCSVDataSurrounded" })
    public void getCSVDataAsStreamSurrounded() {

        InputStream is = null;
        StringWriter sw = null;

        try {

            is = converter.getCSVDataAsStream(projectList, Arrays.asList(HLAMeasure.values()), true, true);
            assertNotNull(is, "No input stream generated!");

            sw = new StringWriter();
            IOUtils.copy(is, sw);
            assertEquals(sw.toString(), csvDataSurrounded, "Stream does not contain same content as generated data!");

        } catch (Exception e) {

            fail("Failed creating input stream from CSV data!", e);

        } finally {

            IOUtils.closeQuietly(is);
        }
    }

    @Test(dependsOnMethods = { "getCSVData" })
    public void getCSVDataAsStream() {

        InputStream is = null;
        StringWriter sw = null;

        try {

            is = converter.getCSVDataAsStream(projectList, Arrays.asList(HLAMeasure.values()), false);
            assertNotNull(is, "No input stream generated!");

            sw = new StringWriter();
            IOUtils.copy(is, sw);
            assertEquals(sw.toString(), csvData, "Stream does not contain same content as generated data!");

        } catch (Exception e) {

            fail("Failed creating input stream from CSV data!", e);

        } finally {

            IOUtils.closeQuietly(is);
        }
    }

    @Test
    public void testToString() {

        if (!PATTERN_ID.matcher(converter.toString()).matches())
            fail("Converter does not identify as expected!");
    }
}
