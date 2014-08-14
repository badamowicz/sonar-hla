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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * UTs for {@link Converter} class.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class ConverterTest {

    @DataProvider(name = "decimalProvider")
    public static Object[][] decimalProvider() {

        return new Object[][] { { "78.9%", "78.9" }, { "100%", "100" }, { "78,978", "78978" }, { "-78,990", "-78990" } };
    }

    /**
     * Method simply tests by string comparison. See also {@link #testCreateDecimalType(String, String)}.
     * 
     * @param value The value to be converted.
     * @param cleanValue The expected value.
     */
    @Test(dataProvider = "decimalProvider")
    public void testGetCleanValue(String value, String cleanValue) {

        assertEquals(Converter.getCleanValue(value), cleanValue, "The decimal value " + value + " was not convertet as expected!");
    }

    /**
     * Test if the converted value is really convertible into the appropriate Java type.
     * 
     * @param value The value to be converted.
     * @param cleanValue Not used here.
     */
    @Test(dataProvider = "decimalProvider")
    public void testCreateDecimalType(String value, String cleanValue) {

        try {

            new Double(Converter.getCleanValue(value));

        } catch (NumberFormatException e) {

            fail("Could not convert " + value + " into appropriate data type.");
        }
    }
}
