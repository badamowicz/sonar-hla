package de.adamowicz.sonar.hla.plugin.helper;

import org.apache.log4j.Logger;

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
