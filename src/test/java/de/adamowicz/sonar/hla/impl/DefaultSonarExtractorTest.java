package de.adamowicz.sonar.hla.impl;

import static org.testng.Assert.fail;

import java.util.regex.Pattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * UTs for {@link DefaultSonarExtractor}. See also {@link DefaultSonarExtractorIT} for more tests.
 * 
 * Created Aug 13, 2014 3:58:29 PM by bernd
 */
public class DefaultSonarExtractorTest {

    private static final String   URL        = "/some/url";
    private static final Pattern  PATTERN_ID = Pattern.compile("DefaultSonarExtractor.*Host.*" + URL + ".*User.*");
    private DefaultSonarExtractor extractor  = null;

    @BeforeClass
    public void beforeClass() {

        extractor = new DefaultSonarExtractor(URL);
    }

    @Test
    public void testToString() {

        if (!PATTERN_ID.matcher(extractor.toString()).matches())
            fail("Extractor does not identify as expected!");
    }
}
