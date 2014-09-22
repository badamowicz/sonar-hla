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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;
import com.github.badamowicz.sonar.hla.api.IProjectAggregated;

public class ProjectAggregated implements IProjectAggregated {

    private static final String           VERSION          = "Aggregated Project";

    private static final Logger           LOG              = Logger.getLogger(ProjectAggregated.class);

    private String                        name             = null;
    private List<IProject>                projects         = null;
    private List<HLAMeasure>              measures         = null;
    private List<String>                  projectIDs       = null;

    private Map<HLAMeasure, String>       values           = null;
    private Map<HLAMeasure, Integer>      valuesInt        = null;
    private Map<HLAMeasure, Double>       valuesDouble     = null;

    private static final List<HLAMeasure> MEASURES_AVERAGE = Arrays.asList(new HLAMeasure[] { HLAMeasure.CMPLX,
            HLAMeasure.COVERAGE                           });
    private static final List<HLAMeasure> MEASURES_SUM     = Arrays.asList(new HLAMeasure[] { HLAMeasure.LOCS,
            HLAMeasure.DUPLINES, HLAMeasure.ISSUES_ALL, HLAMeasure.ISSUES_BLOCKER, HLAMeasure.ISSUES_CRITICAL,
            HLAMeasure.ISSUES_INFO, HLAMeasure.ISSUES_MAJOR, HLAMeasure.ISSUES_MINOR });

    private int                           amountProjects   = -1;

    /**
     * Don't use constructor.
     */
    private ProjectAggregated() {

        LOG.debug("Start initializing aggregated project.");
    }

    /**
     * Constructor creates this aggregated project based on the given real projects.
     * 
     * @param name A name describing this project.
     * @param projects The list of projects which will be used for aggregating all internal values.
     */
    public ProjectAggregated(String name, List<IProject> projects) {

        this();
        setName(name);
        setProjects(projects);
        setAmountProjects(getProjects().size());

        values = new HashMap<HLAMeasure, String>();
        calculateAvailableMeasures();
        calculateSumMeasures();
        calculateAvgMeasures();
        initProjectIDs();
    }

    private void initProjectIDs() {

        projectIDs = new ArrayList<String>();

        for (IProject currProject : getProjects())
            projectIDs.add(currProject.getId());
    }

    private void calculateAvgMeasures() {

        double sum = 0.0;
        double avg = 0.0;

        valuesDouble = new HashMap<HLAMeasure, Double>();

        for (HLAMeasure currMeasure : MEASURES_AVERAGE) {

            sum = 0.0;

            for (IProject currProject : getProjects())
                if (getMeasures().contains(currMeasure))
                    sum += currProject.getMeasureDoubleValue(currMeasure).doubleValue();

            if (getMeasures().contains(currMeasure)) {

                avg = sum / getAmountProjects();
                valuesDouble.put(currMeasure, Double.valueOf(avg));
                values.put(currMeasure, Double.toString(avg));
                LOG.debug("Measure " + currMeasure.getSonarName() + " calculated to " + avg);
            }
        }
    }

    private void calculateSumMeasures() {

        int sum = 0;

        valuesInt = new HashMap<HLAMeasure, Integer>();

        for (HLAMeasure currMeasure : MEASURES_SUM) {

            sum = 0;

            for (IProject currProject : getProjects())
                if (getMeasures().contains(currMeasure))
                    sum += currProject.getMeasureIntValue(currMeasure).intValue();

            if (getMeasures().contains(currMeasure)) {

                valuesInt.put(currMeasure, Integer.valueOf(sum));
                values.put(currMeasure, Integer.toString(sum));
                LOG.debug("Measure " + currMeasure.getSonarName() + " calculated to " + sum);
            }
        }
    }

    /**
     * Create the list of measures which are available in <b>all</b> projects.
     */
    private void calculateAvailableMeasures() {

        boolean measureAvailable = true;

        measures = new ArrayList<HLAMeasure>();

        for (HLAMeasure currMeasure : HLAMeasure.values()) {

            measureAvailable = true;

            for (IProject currProject : getProjects()) {

                LOG.debug("Checking measure " + currMeasure.getSonarName() + " for project " + currProject);

                if (isNoMeasureAvailable(currMeasure, currProject)) {

                    measureAvailable = false;
                    break;
                }
            }

            if (measureAvailable)
                measures.add(currMeasure);
            else
                LOG.warn("Measure " + currMeasure.getSonarName() + " not available in all projects. Cannot calculate!");
        }

        LOG.debug(measures.size() + " measures are available in aggregated project " + getName());
    }

    /**
     * Check if the given measure is available inside the project <b>and</b> if it contains a value.
     * 
     * @param measure The measure to be checked.
     * @param project The project to be checked.
     * @return true, if <b>no</b> measure is available.
     */
    private boolean isNoMeasureAvailable(HLAMeasure measure, IProject project) {

        return !project.getMeasures().contains(measure) || project.getMeasureValue(measure, false) == Project.VALUE_NOT_AVAILABLE;
    }

    @Override
    public String getId() {

        return getName();
    }

    @Override
    public String getVersion() {

        return VERSION;
    }

    @Override
    public List<HLAMeasure> getMeasures() {

        return measures;
    }

    /**
     * Beware that for this aggregated project type the value for <i>cleaned</i> is not used. Values are always returned without
     * any additional characters.
     */
    @Override
    public String getMeasureValue(HLAMeasure measure, boolean cleaned) {

        return values.get(measure);
    }

    @Override
    public Double getMeasureDoubleValue(HLAMeasure measure) {

        return valuesDouble.get(measure);
    }

    @Override
    public Integer getMeasureIntValue(HLAMeasure measure) {

        return valuesInt.get(measure);
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public List<String> getProjectIDs() {

        return projectIDs;
    }

    List<IProject> getProjects() {

        return projects;
    }

    private void setProjects(List<IProject> projects) {

        this.projects = projects;
    }

    private void setName(String name) {

        this.name = name;
    }

    private int getAmountProjects() {

        return amountProjects;
    }

    private void setAmountProjects(int amountProjects) {

        this.amountProjects = amountProjects;
    }

}
