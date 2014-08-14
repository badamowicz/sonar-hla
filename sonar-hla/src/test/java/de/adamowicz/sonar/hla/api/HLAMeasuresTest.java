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
package de.adamowicz.sonar.hla.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

/**
 * Test cases for {@link HLAMeasure}.
 * 
 * Created Aug 11, 2014 1:48:02 PM by bernd
 */
public class HLAMeasuresTest {

    @Test
    public void convert() {

        List<HLAMeasure> measures = null;

        measures = HLAMeasure.convert("ncloc,coverage,duplicated_lines");
        assertNotNull(measures, "No list returned though names are given!");
        assertEquals(measures.size(), 3, "Wrong amount of converted measure objects!");
        assertTrue(measures.contains(HLAMeasure.LOCS), "No object for 'ncloc' found!");
        assertTrue(measures.contains(HLAMeasure.COVERAGE), "No object for 'coverage' found!");
        assertTrue(measures.contains(HLAMeasure.DUPLINES), "No object for 'duplicated_lines' found!");
    }

    @Test
    public void convertNoneFound() {

        List<HLAMeasure> measures = null;

        measures = HLAMeasure.convert("none,existing,sonar,names");
        assertNotNull(measures, "An empty list must be returned!");
        assertEquals(measures.size(), 0, "Size must be 0 for non existing names!");
    }

    @Test
    public void getHeaderName() {

        assertEquals(HLAMeasure.COVERAGE.getHeaderName(), "Coverage [%]", "Header not retrieved as expected!");
    }

    @Test
    public void getSonarName() {

        assertEquals(HLAMeasure.COVERAGE.getSonarName(), "coverage", "Sonar name not retrieved as expected!");
    }

    @Test
    public void getSonarNamesAsList() {

        List<String> sonarNames = null;

        sonarNames = HLAMeasure.getSonarNamesAsList();
        assertNotNull(sonarNames, "No Sonar names retrieved!");
        assertEquals(sonarNames.size(), HLAMeasure.values().length, "Different amount of Sonar names in list detected!");
    }

    @Test
    public void getSonarNames() {

        String[] sonarNames = null;

        sonarNames = HLAMeasure.getSonarNames();
        assertNotNull(sonarNames, "No Sonar names retrieved!");
        assertEquals(sonarNames.length, HLAMeasure.values().length, "Different amount of Sonar names in array detected!");
    }
}
