package de.adamowicz.sonar.hla.plugin.helper;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Some (more or less) useful tests for {@link LogHelper}.
 * 
 * Created Aug 14, 2014 4:57:08 PM by bernd
 */
public class LogHelperTest {

    private static final Logger LOG = Logger.getLogger(LogHelper.class);

    @Test
    public void moo() {

        LogHelper.moo(LOG);
    }
}
