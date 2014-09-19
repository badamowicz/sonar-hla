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
package com.github.badamowicz.sonar.hla.api;

import java.util.List;

/**
 * This is a special project type which operates on a set of projects. One may give for example a pattern to extract a set of
 * projects. All measures of these projects will then be aggregated and provided as a single value.
 * <p>
 * Aggregation will take place depending on the used measure as listed here:
 * <ul>
 * <li>{@link HLAMeasure#DUPLINES}: sum</li>
 * <li>{@link HLAMeasure#COVERAGE}: average</li>
 * <li>{@link HLAMeasure#CMPLX}: average</li>
 * <li>{@link HLAMeasure#LOCS}: sum</li>
 * <li>{@link HLAMeasure#ISSUES_MINOR}: sum</li>
 * <li>{@link HLAMeasure#ISSUES_MAJOR}: sum</li>
 * <li>{@link HLAMeasure#ISSUES_INFO}: sum</li>
 * <li>{@link HLAMeasure#ISSUES_CRITICAL}: sum</li>
 * <li>{@link HLAMeasure#ISSUES_BLOCKER}: sum</li>
 * <li>{@link HLAMeasure#ISSUES_ALL}: sum</li>
 * </ul>
 * 
 * @author bernd
 */
public interface IProjectAggregated extends IProject {

    /**
     * The name this project is assigned to. This will not be a name or ID from SonarQube, since projects of this type are
     * retrieved using a set of projects and hence will contain data from more than one project. Instead this will be something
     * user given or some automatically calculated value.
     * 
     * @return The name of this project.
     */
    public String getName();

    /**
     * Get all the projects which are part of this aggregated project.
     * 
     * @return The list of all project IDs used.
     */
    public List<String> getProjectIDs();
}
