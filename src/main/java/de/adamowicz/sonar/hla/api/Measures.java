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

/**
 * Represents the measures of a project inside SonarQube and provides additional meta information for every measure.
 * 
 * Currently this is a small excerpt from the metrics found here: http://docs.codehaus.org/display/SONAR/Metric+definitions
 * 
 * @author Bernd Adamowicz
 * 
 */
public enum Measures {

    LOCS("ncloc", "Lines of Code"), COVERAGE("coverage", "Coverage [%]"), CMPLX("complexity", "Complexity"), DUPLINES(
            "duplicated_lines", "Duplicate Lines");

    private String sonarName  = null;
    private String headerName = null;

    /**
     * Creates a new enum of this type.
     * 
     * @param sonarName This is the measure name as it must be given when accessing SonarQube's web service.
     * @param headerName A header name for convenience. Might be used when generating log output or creating CSV data.
     */
    private Measures(String sonarName, String headerName) {

        this.sonarName = sonarName;
        this.headerName = headerName;
    }

    public String getSonarName() {

        return sonarName;
    }

    public String getHeaderName() {

        return headerName;
    }
}
