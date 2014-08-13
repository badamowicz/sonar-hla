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
package de.adamowicz.sonar.hla.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.sonar.wsclient.services.Measure;
import org.sonar.wsclient.services.Resource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.adamowicz.sonar.hla.api.HLAMeasure;

/**
 * Test cases for {@link Project} type.
 * 
 * Created Aug 11, 2014 3:28:48 PM by bernd
 */
public class ProjectTest {

    private Resource             resource       = null;
    private Measure              measure1       = null;
    private Measure              measure2       = null;
    private List<Measure>        measureList    = null;
    private Project              project        = null;

    private static final String  ID             = "some:id";
    private static final String  VALUE_LOCS     = "4567";
    private static final String  VALUE_COVERAGE = "75.0%";
    private static final Pattern PATTERN_ID     = Pattern.compile("Project.*ID.*");

    @BeforeClass
    public void beforeClass() {

        measure1 = new Measure();
        measure1.setMetricKey(HLAMeasure.COVERAGE.getSonarName());
        measure1.setFormattedValue(VALUE_COVERAGE);

        measure2 = new Measure();
        measure2.setMetricKey(HLAMeasure.LOCS.getSonarName());
        measure2.setFormattedValue(VALUE_LOCS);

        measureList = new ArrayList<Measure>();
        measureList.add(measure1);
        measureList.add(measure2);

        resource = new Resource();
        resource.setMeasures(measureList);

        project = new Project(ID, resource);
    }

    @Test
    public void getId() {

        assertEquals(project.getId(), ID, "ID of project not as expected!");
    }

    @Test
    public void getMeasureValue() {

        assertEquals(project.getMeasureValue(HLAMeasure.COVERAGE, false), VALUE_COVERAGE, "Value for coverage not as expected!");
        assertEquals(project.getMeasureValue(HLAMeasure.LOCS, false), VALUE_LOCS, "Value for LOC not as expected!");
    }

    @Test
    public void getMeasures() {

        assertNotNull(project.getMeasures(), "HLAMeasure not initialized!");
        assertEquals(project.getMeasures().size(), measureList.size(), "Amount of measures differs!");
    }

    @Test
    public void getResource() {

        assertSame(project.getResource(), resource, "Expected the same Resource object!");
    }

    @Test
    public void getValues() {

        assertNotNull(project.getValues(), "Internal list of values not initialized!");
    }

    @Test
    public void getMeasureValueClean() {

        assertEquals(project.getMeasureValue(HLAMeasure.COVERAGE, true),
                VALUE_COVERAGE.substring(0, VALUE_COVERAGE.length() - 1), "Cleaned value not returned as expected!");
    }

    /**
     * We just expect any none-empty string here.
     */
    @Test
    public void getMeasureValueNotAvail() {

        String notAvailable = null;

        notAvailable = project.getMeasureValue(HLAMeasure.CMPLX, false);
        assertTrue(notAvailable != null && !notAvailable.isEmpty(), "No default string returned for non existing value!");
    }

    @Test
    public void testToString() {

        if (!PATTERN_ID.matcher(project.toString()).matches())
            fail("Project does not identify as expected!");
    }
}
