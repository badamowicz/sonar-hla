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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.sonar.wsclient.services.ResourceQuery;

/**
 * Represents the measures of a project inside SonarQube and provides additional meta information for every measure.
 * <p>
 * This will always be only a subset from the metrics found here: http://docs.codehaus.org/display/SONAR/Metric+definitions since
 * it is not the intention of this project to mimic all the possible metrics.
 * 
 * @author Bernd Adamowicz
 * 
 */
public enum HLAMeasure {

    LOCS("ncloc", "Lines of Code"),
    COVERAGE("coverage", "Coverage [%]"),
    CMPLX("complexity", "Complexity"),
    DUPLINES("duplicated_lines", "Duplicate Lines"),
    ISSUES_ALL("violations", "Issues"),
    ISSUES_BLOCKER("blocker_violations", "Blocker"),
    ISSUES_CRITICAL("critical_violations", "Critical"),
    ISSUES_MAJOR("major_violations", "Major"),
    ISSUES_MINOR("minor_violations", "Minor"),
    ISSUES_INFO("info_violations", "Info");

    private String sonarName  = null;
    private String headerName = null;

    /**
     * Creates a new enum of this type.
     * 
     * @param sonarName This is the measure name as it must be given when accessing SonarQube's web service.
     * @param headerName A header name for convenience. Might be used when generating log output or creating CSV data.
     */
    private HLAMeasure(String sonarName, String headerName) {

        this.sonarName = sonarName;
        this.headerName = headerName;
    }

    public String getSonarName() {

        return sonarName;
    }

    public String getHeaderName() {

        return headerName;
    }

    /**
     * Retrieve all the Sonar names inside this type as an array. This is a convenience method which may for example be used
     * inside {@link ResourceQuery#createForMetrics(String, String...)}.
     * 
     * @return The array containing all Sonar names for measures defined in this type.
     */
    public static String[] getSonarNames() {

        return getSonarNamesAsList().toArray(new String[getSonarNamesAsList().size()]);
    }

    /**
     * Retrieve all the Sonar names for measures available in this enum type.
     * 
     * @return A list of all Sonar names.
     */
    public static List<String> getSonarNamesAsList() {

        List<String> sonarNames = null;

        sonarNames = new ArrayList<String>();

        for (HLAMeasure currMeasure : HLAMeasure.values())
            sonarNames.add(currMeasure.getSonarName());

        return sonarNames;
    }

    /**
     * Convenience method for converting a comma separated string of Sonar metric names into the appropriate {@link HLAMeasure}
     * objects.
     * 
     * @param sonarNames A list of metric names. Example: <i>ncloc,coverage,duplicated_lines</i>.
     * @return The list of {@link HLAMeasure} objects or an empty list if no name matched any measure.
     */
    public static List<HLAMeasure> convert(String sonarMetricNames) {

        List<HLAMeasure> measures = null;
        StringTokenizer tokenizer = null;
        String currToken = null;

        measures = new ArrayList<HLAMeasure>();
        tokenizer = new StringTokenizer(sonarMetricNames, ",");

        while (tokenizer.hasMoreTokens()) {

            currToken = tokenizer.nextToken();

            for (HLAMeasure currMeasure : HLAMeasure.values())
                if (currMeasure.getSonarName().equals(currToken))
                    measures.add(currMeasure);
        }

        return measures;
    }
}
