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
     * Make 'moo'. ;-)
     */
    public static void moo(Logger log) {

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
