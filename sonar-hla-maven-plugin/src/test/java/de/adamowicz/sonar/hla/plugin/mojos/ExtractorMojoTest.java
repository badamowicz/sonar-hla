package de.adamowicz.sonar.hla.plugin.mojos;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.maven.plugin.MojoFailureException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.adamowicz.sonar.hla.api.HLAMeasure;

/**
 * UTs for {@link ExtractorMojo}.
 * 
 * Created Aug 14, 2014 11:32:05 AM by bernd
 */
public class ExtractorMojoTest {

    private static final String  URL         = "http://somehost:9090";
    private static final String  USER        = "Heinz";
    private static final String  PASSWORD    = "Ketchup";
    private static final String  PROJECT_KEY = "some:key";
    private static final String  PATTERN     = "some:.*";
    private static final String  MEASURES    = "ncloc,coverage,duplicated_lines";
    private static final Boolean CLEAN_VALUE = true;
    private static final Boolean SURROUND    = true;

    private ExtractorMojo        mojo        = null;
    private ExtractorMojo        mojoEmpty   = null;

    @BeforeClass
    public void beforeClass() {

        mojo = new ExtractorMojo();
        mojo.setCleanValues(CLEAN_VALUE);
        mojo.setHostUrl(URL);
        mojo.setMeasures(MEASURES);
        mojo.setPassword(PASSWORD);
        mojo.setSurroundFields(SURROUND);
        mojo.setUserName(USER);
        mojo.setProjectKeyPattern(PATTERN);
        mojo.setProjectKey(PROJECT_KEY);

        mojoEmpty = new ExtractorMojo();
    }

    @Test
    public void prepareMeasures() {

        List<HLAMeasure> measureObjects = null;

        mojoEmpty.setMeasures(null);
        mojoEmpty.prepare();
        measureObjects = mojoEmpty.getMeasureObjects();
        assertEquals(measureObjects.size(), HLAMeasure.values().length, "Not all measure objects initialized!");

        mojoEmpty.setMeasures("ncloc");
        mojoEmpty.prepare();
        measureObjects = mojoEmpty.getMeasureObjects();
        assertEquals(measureObjects.size(), 1, "Only one measure object expected!");
    }

    @Test
    public void getHostUrl() {

        assertEquals(mojo.getHostUrl(), URL, "Host URL not as expected!");
    }

    @Test
    public void getMeasures() {

        assertEquals(mojo.getMeasures(), MEASURES, "Measures not as expected!");
    }

    @Test
    public void getPassword() {

        assertEquals(mojo.getPassword(), PASSWORD, "Password not as expected!");
    }

    @Test
    public void getProjectKey() {

        assertEquals(mojo.getProjectKey(), PROJECT_KEY, "Project key not as expected!");
    }

    @Test
    public void getProjectKeyPattern() {

        assertEquals(mojo.getProjectKeyPattern(), PATTERN, "Project key pattern not as expected!");
    }

    @Test
    public void getUserName() {

        assertEquals(mojo.getUserName(), USER, "User name not as expected!");
    }

    @Test
    public void isCleanValues() {

        assertEquals(mojo.isCleanValues(), CLEAN_VALUE, "Value for clean values not as expected!");
    }

    @Test
    public void isSurroundFields() {

        assertEquals(mojo.isSurroundFields(), SURROUND, "Value for surrounding values not as expected!");
    }

    @Test(expectedExceptions = MojoFailureException.class)
    public void validate() throws MojoFailureException {

        mojo.validate();
    }
}
