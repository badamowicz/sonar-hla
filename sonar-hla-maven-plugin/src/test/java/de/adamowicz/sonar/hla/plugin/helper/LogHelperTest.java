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
package de.adamowicz.sonar.hla.plugin.helper;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import de.adamowicz.sonar.hla.api.HLAMeasure;

/**
 * Some (more or less) useful tests for {@link LogHelper}. Main intention of tests here is to manually check what and how this log
 * helper produces output. So you most probably won't find any assertions here.
 * 
 * Created Aug 14, 2014 4:57:08 PM by bernd
 */
public class LogHelperTest {

    private static final Logger LOG = Logger.getLogger("USER_DATA");
    private static final String CSV = "header1,header2,header3\ndata1,data2,data3\ndata4,data5,data6";

    @Test
    public void logMeasures() {

        LogHelper.logMeasures(HLAMeasure.values(), LOG);
    }

    @Test
    public void logCSV() {

        LogHelper.logCSV(CSV, LOG);
    }

    @Test
    public void moo() {

        LogHelper.moo(LOG);
    }
}
