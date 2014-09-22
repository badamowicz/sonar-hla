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
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;

/**
 * Test cases for {@link ProjectAggregated} type.
 * 
 * @author bernd
 *
 */
public class ProjectAggregatedTest {

    /**
     * For the ease of testing we use simply one integer for all integer values.
     */
    private static final int         INT_VALUE            = 100;

    private static final int         INT_RESULT           = INT_VALUE * 2;

    private ProjectAggregated        projectAgg           = null;
    private ProjectAggregated        projectAggIncomplete = null;
    private ProjectMock              project1             = null;
    private ProjectMock              project2             = null;
    private ProjectMock              projectIncomplete    = null;

    private Map<HLAMeasure, Integer> valuesIntP1          = null;
    private Map<HLAMeasure, Double>  valuesDoubleP1       = null;
    private Map<HLAMeasure, Integer> valuesIntP2          = null;
    private Map<HLAMeasure, Double>  valuesDoubleP2       = null;
    private Map<HLAMeasure, Integer> valuesIntP3          = null;
    private Map<HLAMeasure, Double>  valuesDoubleP3       = null;
    private List<IProject>           projects             = null;
    private List<IProject>           projectsIncomplete   = null;
    private static final String      NAME                 = "Unit test";

    @BeforeClass
    public void beforeClass() {

        valuesDoubleP1 = new HashMap<HLAMeasure, Double>();
        valuesDoubleP1.put(HLAMeasure.CMPLX, 70.0);
        valuesDoubleP1.put(HLAMeasure.COVERAGE, 60.0);

        valuesIntP1 = new HashMap<HLAMeasure, Integer>();
        valuesIntP1.put(HLAMeasure.DUPLINES, INT_VALUE);
        valuesIntP1.put(HLAMeasure.ISSUES_ALL, INT_VALUE);
        valuesIntP1.put(HLAMeasure.ISSUES_BLOCKER, INT_VALUE);
        valuesIntP1.put(HLAMeasure.ISSUES_CRITICAL, INT_VALUE);
        valuesIntP1.put(HLAMeasure.ISSUES_INFO, INT_VALUE);
        valuesIntP1.put(HLAMeasure.ISSUES_MAJOR, INT_VALUE);
        valuesIntP1.put(HLAMeasure.ISSUES_MINOR, INT_VALUE);
        valuesIntP1.put(HLAMeasure.LOCS, INT_VALUE);

        valuesDoubleP2 = new HashMap<HLAMeasure, Double>();
        valuesDoubleP2.put(HLAMeasure.CMPLX, 30.0);
        valuesDoubleP2.put(HLAMeasure.COVERAGE, 40.0);

        valuesIntP2 = new HashMap<HLAMeasure, Integer>();
        valuesIntP2.put(HLAMeasure.DUPLINES, INT_VALUE);
        valuesIntP2.put(HLAMeasure.ISSUES_ALL, INT_VALUE);
        valuesIntP2.put(HLAMeasure.ISSUES_BLOCKER, INT_VALUE);
        valuesIntP2.put(HLAMeasure.ISSUES_CRITICAL, INT_VALUE);
        valuesIntP2.put(HLAMeasure.ISSUES_INFO, INT_VALUE);
        valuesIntP2.put(HLAMeasure.ISSUES_MAJOR, INT_VALUE);
        valuesIntP2.put(HLAMeasure.ISSUES_MINOR, INT_VALUE);
        valuesIntP2.put(HLAMeasure.LOCS, INT_VALUE);

        valuesDoubleP3 = new HashMap<HLAMeasure, Double>();
        valuesDoubleP3.put(HLAMeasure.CMPLX, 30.0);

        valuesIntP3 = new HashMap<HLAMeasure, Integer>();
        valuesIntP3.put(HLAMeasure.DUPLINES, 12);
        valuesIntP3.put(HLAMeasure.ISSUES_ALL, INT_VALUE);
        valuesIntP3.put(HLAMeasure.ISSUES_BLOCKER, INT_VALUE);
        valuesIntP3.put(HLAMeasure.ISSUES_CRITICAL, INT_VALUE);
        valuesIntP3.put(HLAMeasure.ISSUES_INFO, null);
        valuesIntP3.put(HLAMeasure.ISSUES_MAJOR, INT_VALUE);
        valuesIntP3.put(HLAMeasure.ISSUES_MINOR, INT_VALUE);
        valuesIntP3.put(HLAMeasure.LOCS, null);

        project1 = new ProjectMock("1", valuesIntP1, valuesDoubleP1);
        project2 = new ProjectMock("2", valuesIntP2, valuesDoubleP2);
        projectIncomplete = new ProjectMock("incomplete", valuesIntP3, valuesDoubleP3);

        projects = new ArrayList<IProject>();
        projects.add(project1);
        projects.add(project2);

        projectsIncomplete = new ArrayList<IProject>();
        projectsIncomplete.add(project1);
        projectsIncomplete.add(project2);
        projectsIncomplete.add(projectIncomplete);

        projectAgg = new ProjectAggregated(NAME, projects);
        projectAggIncomplete = new ProjectAggregated("incomplete", projectsIncomplete);
    }

    @Test
    public void getProjectIDs() {

        assertEquals(projectAgg.getProjectIDs().size(), 2, "Wrong amount of project IDs caculated!");
        assertEquals(projectAggIncomplete.getProjectIDs().size(), 3, "Wrong amount of project IDs caculated!");
    }

    @Test
    public void getMeasureIntValueIncomplete() {

        assertTrue(projectAggIncomplete.getMeasureIntValue(HLAMeasure.LOCS) == null,
                "Measure LOC was incomplete and should not have been calculated!");
        assertTrue(projectAggIncomplete.getMeasureIntValue(HLAMeasure.ISSUES_INFO) == null,
                "Measure 'issues info' was incomplete and should not have been calculated!");

        assertTrue(projectAggIncomplete.getMeasureValue(HLAMeasure.LOCS, false) == null,
                "Measure LOC was incomplete and should not have been calculated!");
        assertTrue(projectAggIncomplete.getMeasureValue(HLAMeasure.ISSUES_INFO, false) == null,
                "Measure 'issues info' was incomplete and should not have been calculated!");
    }

    @Test
    public void getMeasureDoubleValue() {

        assertEquals(projectAgg.getMeasureDoubleValue(HLAMeasure.COVERAGE), Double.valueOf(50.0),
                "Wrong average for coverage calculated!");
        assertEquals(projectAgg.getMeasureDoubleValue(HLAMeasure.CMPLX), Double.valueOf(50.0),
                "Wrong average for complexity calculated!");

        assertEquals(projectAgg.getMeasureValue(HLAMeasure.COVERAGE, false), Double.toString(50.0),
                "Wrong average for coverage calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.CMPLX, false), Double.toString(50.0),
                "Wrong average for complexity calculated!");
    }

    @Test
    public void getMeasureDoubleValueIncomplete() {

        assertTrue(projectAggIncomplete.getMeasureDoubleValue(HLAMeasure.COVERAGE) == null,
                "Coverage was incomplete and should not have been calculated!");

        assertTrue(projectAggIncomplete.getMeasureValue(HLAMeasure.COVERAGE, false) == null,
                "Coverage was incomplete and should not have been calculated!");
    }

    @Test
    public void getMeasureIntValue() {

        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.LOCS), Integer.valueOf(INT_RESULT),
                "Wrong sum for locs calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.DUPLINES), Integer.valueOf(INT_RESULT),
                "Wrong sum for duplicate lines calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.ISSUES_ALL), Integer.valueOf(INT_RESULT),
                "Wrong sum for all issues calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.ISSUES_BLOCKER), Integer.valueOf(INT_RESULT),
                "Wrong sum for blocker issues calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.ISSUES_CRITICAL), Integer.valueOf(INT_RESULT),
                "Wrong sum for critical issues calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.ISSUES_INFO), Integer.valueOf(INT_RESULT),
                "Wrong sum for info issues calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.ISSUES_MAJOR), Integer.valueOf(INT_RESULT),
                "Wrong sum for major issues calculated!");
        assertEquals(projectAgg.getMeasureIntValue(HLAMeasure.ISSUES_MINOR), Integer.valueOf(INT_RESULT),
                "Wrong sum for minor issues calculated!");

        assertEquals(projectAgg.getMeasureValue(HLAMeasure.LOCS, false), Integer.toString(INT_RESULT),
                "Wrong sum for locs calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.DUPLINES, false), Integer.toString(INT_RESULT),
                "Wrong sum for duplicate lines calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.ISSUES_ALL, false), Integer.toString(INT_RESULT),
                "Wrong sum for all issues calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.ISSUES_BLOCKER, false), Integer.toString(INT_RESULT),
                "Wrong sum for blocker issues calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.ISSUES_CRITICAL, false), Integer.toString(INT_RESULT),
                "Wrong sum for critical issues calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.ISSUES_INFO, false), Integer.toString(INT_RESULT),
                "Wrong sum for info issues calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.ISSUES_MAJOR, false), Integer.toString(INT_RESULT),
                "Wrong sum for major issues calculated!");
        assertEquals(projectAgg.getMeasureValue(HLAMeasure.ISSUES_MINOR, false), Integer.toString(INT_RESULT),
                "Wrong sum for minor issues calculated!");

    }

    @Test
    public void getMeasures() {

        assertEquals(projectAgg.getMeasures().size(), 10, "Amount of calculated measures is wrong!");
        assertEquals(projectAggIncomplete.getMeasures().size(), 7, "Amount of calculated measures is wrong!");
    }

    @Test
    public void getId() {

        assertEquals(projectAgg.getId(), NAME, "Wrong ID returned!");
    }

    @Test
    public void getName() {

        assertEquals(projectAgg.getName(), NAME, "Wrong name returned!");
    }

    @Test
    public void getVersion() {

        assertTrue(projectAgg.getVersion() != null && !projectAgg.getVersion().isEmpty(), "No version returned!");
    }
}
