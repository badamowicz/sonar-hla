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
 * Instances of this type represent a project in terms of what is called a project inside SonarQube. It provides getter methods
 * for all analyzed data like complexity, DCOM or LOC. The values returned by the getter methods in this class will always return
 * string objects! It's up to implementing code to turn those strings into other data types.
 * 
 * @author Bernd Adamowicz
 * 
 */
public interface IProject {

    /**
     * Retrieve the ID of a project as it is defined within SonarQube.
     * 
     * @return The ID of the project as it is named inside Sonar. Example: <i>com.github.badamowicz.sonar.hla:sonar-hla</i>
     */
    public String getId();

    /**
     * Retrieve the list of internal measures which are available for this project.
     * 
     * @return The list of available {@link HLAMeasure} or an empty list if none are available.
     */
    public List<HLAMeasure> getMeasures();

    /**
     * Method will try to retrieve the value for the given measure. If no value is available, a default string will be returned
     * indicating that there is no such measure.
     * 
     * @param measure The {@link HLAMeasure} to be returned.
     * @param cleaned If true, all non-numeric characters like e.g. '%' will be removed from the returned string in order to
     *        convert the value into an appropriate Java type.
     * @return The value or a default string.
     */
    public String getMeasureValue(HLAMeasure measure, boolean cleaned);
}
