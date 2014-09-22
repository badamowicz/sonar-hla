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
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;

/**
 * Test cases for {@link DefaultSonarConverter} for creating CSV based on aggregated projects. See also
 * {@link DefaultSonarConverterTest} for more tests.
 * 
 * @author bernd
 *
 */
public class DefaultSonarConverterAggregatedTest extends ProjectAggregatedHelper {

    private static final Logger   LOG            = Logger.getLogger(DefaultSonarConverterAggregatedTest.class);

    private DefaultSonarConverter converter      = null;

    private static final String   CSV_EXPECTED   = "Project;Lines of Code;Coverage [%];Complexity;Duplicate Lines;Issues;Blocker;Critical;Major;Minor;Info\nUnit test;200;50.0;50.0;200;200;200;200;200;200;200\n";
    private static final String   CSV_INCOMPLETE = "Project;Complexity;Duplicate Lines;Issues;Blocker;Critical;Major;Minor\nincomplete;43.333333333333336;212;300;300;300;300;300\n";

    private File                  csvFile        = null;
    private static final String   CSV_FILE_NAME  = "aggr-test.csv";

    @BeforeClass
    public void beforeClass() {

        prepare();
        converter = new DefaultSonarConverter();
        csvFile = new File(FileUtils.getTempDirectory(), CSV_FILE_NAME);
    }

    @AfterClass
    public void cleanUp() {

        FileUtils.deleteQuietly(csvFile);
    }

    @Test
    public void getCSVData() {

        String csv = null;

        csv = converter.getCSVData(projectAgg, Arrays.asList(HLAMeasure.values()));
        assertEquals(csv, CSV_EXPECTED, "CSV data not created as expected!");

        LOG.debug("Got CSV data:");
        LOG.debug("\n" + csv);
    }

    @Test
    public void getCSVDataIncomplete() {

        String csv = null;

        csv = converter.getCSVData(projectAggIncomplete, Arrays.asList(HLAMeasure.values()));
        assertEquals(csv, CSV_INCOMPLETE, "CSV data not created as expected!");

        LOG.debug("Got CSV data:");
        LOG.debug("\n" + csv);
    }

    @Test
    public void getCSVDataAsFile() throws IOException {

        String csvContent = null;

        csvFile = converter.getCSVDataAsFile(csvFile.getAbsolutePath(), projectAgg, Arrays.asList(HLAMeasure.values()));
        assertNotNull(csvFile, "No CSV file generated!");
        assertTrue(csvFile.exists() && csvFile.isFile(), "Generated file does not exist!");

        csvContent = FileUtils.readFileToString(csvFile);
        assertNotNull(csvContent, "No CSV data written to file!");
        assertFalse(csvContent.isEmpty(), "Written CSV does not contain any data!");
        assertEquals(csvContent, CSV_EXPECTED, "CSV data not created as expected!");
    }

    @Test
    public void getCSVDataAsStream() {

        InputStream is = null;
        StringWriter sw = null;

        try {

            is = converter.getCSVDataAsStream(projectAgg, Arrays.asList(HLAMeasure.values()));
            assertNotNull(is, "No input stream generated!");

            sw = new StringWriter();
            IOUtils.copy(is, sw);
            assertEquals(sw.toString(), CSV_EXPECTED, "Stream does not contain same content as generated data!");

        } catch (Exception e) {

            fail("Failed creating input stream from CSV data!", e);

        } finally {

            IOUtils.closeQuietly(is);
        }
    }
}
