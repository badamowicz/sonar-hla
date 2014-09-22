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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;

/**
 * Class provides some aggregated projects to be used in tests.
 * 
 * @author bernd
 *
 */
public class ProjectAggregatedHelper {

    /**
     * For the ease of testing we use simply one integer for all integer values.
     */
    protected static final int       INT_VALUE            = 100;
    protected static final int       INT_RESULT           = INT_VALUE * 2;
    protected ProjectAggregated      projectAgg           = null;
    protected ProjectAggregated      projectAggIncomplete = null;
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
    protected static final String    NAME                 = "Unit test";

    public void prepare() {

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

}
