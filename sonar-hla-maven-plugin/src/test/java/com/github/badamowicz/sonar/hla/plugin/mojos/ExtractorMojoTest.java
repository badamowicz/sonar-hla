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
package com.github.badamowicz.sonar.hla.plugin.mojos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.apache.maven.plugin.MojoFailureException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.plugin.mojos.ExtractorMojo;


/**
 * UTs for {@link ExtractorMojo}.
 * 
 * Created Aug 14, 2014 11:32:05 AM by bernd
 */
public class ExtractorMojoTest {

    private static final String  URL         = "http://somehost:9090";
    private static final String  USER        = "Heinz";
    private static final String  PASSWORD    = "Ketchup";
    private static final String  PROJECT_KEY = "some:key";
    private static final String  PATTERN     = "some:.*";
    private static final String  MEASURES    = "ncloc,coverage,duplicated_lines";
    private static final Boolean CLEAN_VALUE = true;
    private static final Boolean SURROUND    = true;

    private ExtractorMojo        mojo        = null;
    private ExtractorMojo        mojoEmpty   = null;

    @BeforeClass
    public void beforeClass() {

        mojo = new ExtractorMojo();
        mojo.setCleanValues(CLEAN_VALUE);
        mojo.setHostUrl(URL);
        mojo.setMeasures(MEASURES);
        mojo.setPassword(PASSWORD);
        mojo.setSurroundFields(SURROUND);
        mojo.setUserName(USER);
        mojo.setProjectKeyPattern(PATTERN);
        mojo.setProjectKey(PROJECT_KEY);

        mojoEmpty = new ExtractorMojo();
    }

    @Test
    public void prepare() {

        mojo.prepare();
        assertNotNull(mojo.getConverter(), "No converter initialized!");
        assertNotNull(mojo.getExtractor(), "No extractor initialized!");
    }

    @Test
    public void prepareMeasures() {

        List<HLAMeasure> measureObjects = null;

        mojoEmpty.setMeasures(null);
        mojoEmpty.prepare();
        measureObjects = mojoEmpty.getMeasureObjects();
        assertEquals(measureObjects.size(), HLAMeasure.values().length, "Not all measure objects initialized!");

        mojoEmpty.setMeasures("ncloc");
        mojoEmpty.prepare();
        measureObjects = mojoEmpty.getMeasureObjects();
        assertEquals(measureObjects.size(), 1, "Only one measure object expected!");
    }

    @Test
    public void getHostUrl() {

        assertEquals(mojo.getHostUrl(), URL, "Host URL not as expected!");
    }

    @Test
    public void getMeasures() {

        assertEquals(mojo.getMeasures(), MEASURES, "Measures not as expected!");
    }

    @Test
    public void getPassword() {

        assertEquals(mojo.getPassword(), PASSWORD, "Password not as expected!");
    }

    @Test
    public void getProjectKey() {

        assertEquals(mojo.getProjectKey(), PROJECT_KEY, "Project key not as expected!");
    }

    @Test
    public void getProjectKeyPattern() {

        assertEquals(mojo.getProjectKeyPattern(), PATTERN, "Project key pattern not as expected!");
    }

    @Test
    public void getUserName() {

        assertEquals(mojo.getUserName(), USER, "User name not as expected!");
    }

    @Test
    public void isCleanValues() {

        assertEquals(mojo.isCleanValues(), CLEAN_VALUE, "Value for clean values not as expected!");
    }

    @Test
    public void isSurroundFields() {

        assertEquals(mojo.isSurroundFields(), SURROUND, "Value for surrounding values not as expected!");
    }

    @Test(expectedExceptions = MojoFailureException.class)
    public void validate() throws MojoFailureException {

        mojo.validate();
    }
}
