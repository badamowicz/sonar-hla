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

import com.github.badamowicz.sonar.hla.api.ISonarConverter;
import com.github.badamowicz.sonar.hla.api.ISonarExtractor;

/**
 * Factory class for {@link ISonarExtractor} and {@link ISonarConverter} instances.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class SonarHLAFactory {

    private static ISonarConverter converterInstance = null;

    /**
     * All static here. So don't use.
     */
    private SonarHLAFactory() {

    }

    /**
     * Retrieve an instance of an {@link ISonarConverter} object.
     * 
     * @return A singleton instance of {@link ISonarConverter}.
     */
    public static ISonarConverter getConverterInstance() {

        if (converterInstance == null) {

            synchronized (DefaultSonarConverter.class) {

                if (converterInstance == null)
                    converterInstance = new DefaultSonarConverter();
            }
        }

        return converterInstance;
    }

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
