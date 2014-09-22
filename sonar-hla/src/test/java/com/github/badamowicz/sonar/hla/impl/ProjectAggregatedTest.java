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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;

/**
 * Test cases for {@link ProjectAggregated} type.
 * 
 * @author bernd
 *
 */
public class ProjectAggregatedTest extends ProjectAggregatedHelper {

    @BeforeClass
    public void beforeClass() {

        prepare();
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
