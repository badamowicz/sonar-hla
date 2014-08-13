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

import java.io.InputStream;
import java.util.List;

/**
 * Instances of this class are capable of converting extracted objects from SonarQube (see {@link ISonarExtractor}) to some other
 * format like XML or CSV.
 * 
 * @author Bernd Adamowicz
 * 
 */
public interface ISonarConverter {

    /**
     * Convert the given projects into a string of CSV data.
     * 
     * @param projects A list of projects.
     * @param hlaMeasure The metrics to use.
     * @param When set to true, no non-numeric characters will be contained.
     * @return The string containing the CSV data.
     */
    public String getCSVData(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues);

    /**
     * Convert the given projects into an {@link InputStream} of CSV data.
     * 
     * @param projects A list of projects.
     * @param hlaMeasure The metrics to use.
     * @param When set to true, no non-numeric characters will be contained.
     * @return The {@link InputStream} containing the CSV data.
     */
    public InputStream getCSVDataAsStream(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues);
}
