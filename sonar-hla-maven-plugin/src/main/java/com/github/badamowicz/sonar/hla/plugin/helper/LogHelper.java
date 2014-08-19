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
package com.github.badamowicz.sonar.hla.plugin.helper;

import org.apache.log4j.Logger;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;


/**
 * Some helper stuff for providing pretty log printing.
 * 
 * Created Aug 14, 2014 4:50:50 PM by bernd
 */
public class LogHelper {

    /**
     * All static here! Don't use.
     */
    private LogHelper() {

    }

    /**
     * Log the given array of measure objects in some readable way.
     * 
     * @param measures The array of measures.
     * @param log A {@link Logger} which will be used for output.
     */
    public static void logMeasures(HLAMeasure[] measures, final Logger log) {

        log.info("\nAvailable measures are:");
        log.info("======================");

        for (HLAMeasure currMeasure : measures) {

            log.info(" - " + currMeasure.getSonarName());
        }

        LogHelper.moo(log);
    }

    /**
     * Log CSV data with some additional information.
     * 
     * @param csvData A string expected to contain CSV data. Actually no checks are done if this is really CSV.
     * @param log A {@link Logger} which will be used for output.
     */
    public static void logCSV(String csvData, final Logger log) {

        log.info("");
        log.info("**** Here we go with CSV:");
        log.info("");
        log.info(csvData);
        log.info("");
        log.info("**** End of CSV data. Have a nice day!");
        log.info("");
        LogHelper.moo(log);
    }

    /**
     * Make 'moo'. ;-)
     */
    public static void moo(final Logger log) {

        log.info(" _____________________________");
        log.info("< Sonar High Level API - Moo! >");
        log.info(" -----------------------------");
        log.info("  \\");
        log.info("   \\   \\_\\_    _/_/");
        log.info("    \\      \\__/");
        log.info("           (oo)\\_______");
        log.info("           (__)\\       )\\/\\");
        log.info("               ||----w | °");
        log.info("               ||     || °°");

    }
}
