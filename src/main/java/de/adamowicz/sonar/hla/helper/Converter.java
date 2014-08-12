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
 package de.adamowicz.sonar.hla.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * Some helper methods for converting values retrieved from SonarQube.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class Converter {

    public static final String              EMPTY                = "";
    public static final Map<String, String> CHARS_TO_BE_REPLACED = new HashMap<String, String>() {

                                                                     private static final long serialVersionUID = 7701067419464874851L;

                                                                     {
                                                                         put("%", EMPTY);
                                                                         put(",", ".");
                                                                     }
                                                                 };

    /**
     * All static here. So don't use.
     */
    private Converter() {

    }

    /**
     * Method will remove any non-numeric characters like '%' from the given string. With a cleaned value it will be possible to
     * convert those into an appropriate Java type like {@link Integer} or {@link Double} using their given constructors like
     * {@link Integer#Integer(String)} or static methods like {@link Double#parseDouble(String)}.
     * 
     * @param value A string representing a value along with its unit. Example: <i>78.9%</i>.
     * @return The cleaned value without any unit characters. Example: <i>78.9%</i> will turn into <i>78.9</i>.
     */
    public static String getCleanValue(String value) {

        String cleanValue = null;

        cleanValue = value;

        for (String currChar : CHARS_TO_BE_REPLACED.keySet())
            cleanValue = cleanValue.replaceAll(currChar, CHARS_TO_BE_REPLACED.get(currChar));

        return cleanValue;
    }

}
