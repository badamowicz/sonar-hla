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
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;
import com.github.badamowicz.sonar.hla.api.ISonarConverter;

/**
 * Integration tests for {@link DefaultSonarExtractor}. This test needs a running SonarQube for working properly. See also
 * {@link DefaultSonarConverterTest} for UTs.
 * 
 * Created Aug 12, 2014 10:03:27 AM by bernd
 */
public class DefaultSonarExtractorIT {

    static final Logger           LOG               = Logger.getLogger(DefaultSonarExtractorIT.class);

    private DefaultSonarExtractor extractor         = null;
    private DefaultSonarExtractor extractorHostOnly = null;
    private ISonarConverter       converter         = null;

    private static final String   URL               = "http://localhost:9000";
    private static final String   USER              = "admin";
    private static final String   PASSWORD          = "admin";
    private static final String   PROJECT_KEY       = "com.github.badamowicz.sonar.hla:sonar-hla";
    private static final String   PROJECT_PATTERN   = ".*:sonar-hla.*";

    @BeforeClass
    public void beforeClass() {

        extractor = new DefaultSonarExtractor(URL, USER, PASSWORD);
        extractorHostOnly = new DefaultSonarExtractor(URL);
        converter = new DefaultSonarConverter();
    }

    @Test
    public void getHostURL() {

        assertEquals(extractor.getHostURL(), URL, "Host URL not as expected!");
    }

    @Test
    public void getPassword() {

        assertEquals(extractor.getPassword(), PASSWORD, "Password not as expected!");
    }

    @Test
    public void getProject() {

        IProject project = null;

        project = extractorHostOnly.getProject(PROJECT_KEY);
        assertNotNull(project, "Project not initialized!");
    }

    @Test
    public void getProjects() {

        List<IProject> projects = null;
        String csvData = null;

        projects = extractorHostOnly.getAllProjects();
        assertNotNull(projects, "Projects not initialized!");

        csvData = converter.getCSVData(projects, Arrays.asList(HLAMeasure.values()), true);
        assertNotNull(csvData, "CSV data not created!");
        LOG.debug("\n" + csvData);
    }

    @Test
    public void getProjectsByPattern() {

        List<IProject> foundProjects = null;

        foundProjects = extractorHostOnly.getProjects(PROJECT_PATTERN);
        assertNotNull(foundProjects, "No projects returned!");
        assertEquals(foundProjects.size(), 2, "Wrong amount of projects found!");
    }

    @Test
    public void getSonar() {

        assertNotNull(extractor.getSonar(), "Sonar connection not initialized!");
    }

    @Test
    public void getUserName() {

        assertEquals(extractor.getUserName(), USER, "User name not as expected!");
    }
}
