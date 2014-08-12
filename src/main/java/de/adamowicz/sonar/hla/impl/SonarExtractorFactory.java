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

import de.adamowicz.sonar.hla.api.ISonarExtractor;

/**
 * Factory class for {@link ISonarExtractor} instances.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class SonarExtractorFactory {

    /**
     * Create an instance of an {@link ISonarExtractor} type.
     * 
     * @param hostURL The host URL pointing to SonarQube.
     * @return A <b>new</b> instance of {@link ISonarExtractor}.
     */
    public static ISonarExtractor getExtractor(String hostURL) {

        return new DefaultSonarExtractor(hostURL);
    }

    /**
     * Create an instance of an {@link ISonarExtractor} type.
     * 
     * @param hostURL The host URL pointing to SonarQube.
     * @param userName The user name.
     * @param password The password for the given user. Beware that it will not be handled in a safe way. So better use a kind of
     *        technical user or, if possible, use {@link #getExtractor(String)} which does not require any credentials.
     * @return A <b>new</b> instance of {@link ISonarExtractor}.
     */
    public static ISonarExtractor getExtractor(String hostURL, String userName, String password) {

        return new DefaultSonarExtractor(hostURL, userName, password);
    }
}
