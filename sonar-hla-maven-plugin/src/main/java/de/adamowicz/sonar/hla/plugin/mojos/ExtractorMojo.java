package de.adamowicz.sonar.hla.plugin.mojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import de.adamowicz.sonar.hla.api.HLAMeasure;
import de.adamowicz.sonar.hla.api.IProject;
import de.adamowicz.sonar.hla.api.ISonarConverter;
import de.adamowicz.sonar.hla.api.ISonarExtractor;
import de.adamowicz.sonar.hla.impl.SonarHLAFactory;
import de.adamowicz.sonar.hla.plugin.helper.LogHelper;

/**
 * Mojo is capable of extracting resource data from a SonarQube server. It will perform all necesary steps from extracting the
 * data up to providing those in an appropriate way.
 * <p>
 * Created Aug 14, 2014 10:46:39 AM by bernd
 * 
 * @goal extract
 * @requiresProject false
 */
public class ExtractorMojo extends AbstractMojo {

    private static final Logger LOG               = Logger.getLogger(ExtractorMojo.class);
    private static final Logger LOG_INFO          = Logger.getLogger("USER_DATA");

    /**
     * The URL pointing to the SonarQube host.
     * 
     * @parameter property="hostUrl" default-value="http://localhost:9000"
     * @required
     */
    private String              hostUrl           = null;

    /**
     * The user name for accessing the SonarQube host.
     * 
     * @parameter property="userName"
     */
    private String              userName          = null;

    /**
     * The password for a given user. Beware this password will not be encrypted in any way!
     * 
     * @parameter property="password"
     */
    private String              password          = null;

    /**
     * For retrieving a single project, just give the project's key here. Example: <i>de.adamowicz.sonar.hla:sonar-hla</i>. This
     * property is mutual exclusive with the property <i>projectKeyPattern</i>! If both properties are given, an exception will be
     * thrown. If none of these properties is given, then all projects will be retrieved.
     * 
     * @parameter property="projectKey"
     */
    private String              projectKey        = null;

    /**
     * For retrieving a set of projects matching the given pattern. This property is mutual exclusive with the property
     * <i>projectKey</i>! If both properties are given, an exception will be thrown. If none of these properties is given, then
     * all projects will be retrieved.
     * 
     * @parameter property="projectKeyPattern"
     */
    private String              projectKeyPattern = null;

    /**
     * When set to true, no non-numeric characters will be contained in the generated data.
     * 
     * @parameter property="cleanValues" default-value=false
     */
    private Boolean             cleanValues       = false;

    /**
     * If true, every single field of data will be surrounded with quotation marks.
     * 
     * @parameter property="surroundFields" default-value=false
     */
    private Boolean             surroundFields    = false;

    /**
     * A comma separated list of names of measures which will be used to retrieve the data. To see which measures are available
     * inside Sonar-HLA, just execute the goal <i>showMeasures</i>.
     * <p>
     * Example: <i>ncloc,coverage,duplicated_lines</i>
     * <p>
     * If no measures are given, all available measures will be retrieved.
     * 
     * @parameter property="measures"
     */
    private String              measures          = null;

    /**
     * The internal list of {@link HLAMeasure} used for initializing Sonar-HLA. Will be filled accordingly to what is given in
     * parameter {@link #measures}.
     */
    private List<HLAMeasure>    measureObjects    = null;

    /**
     * The converter used for processing.
     */
    private ISonarConverter     converter         = null;

    /**
     * The extractor used for processing.
     */
    private ISonarExtractor     extractor         = null;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        String csvData = null;

        try {

            validate();
            prepare();
            csvData = createCSV();
            LogHelper.logCSV(csvData, LOG_INFO);

        } catch (Exception e) {

            throw new MojoFailureException("Failed extracting data from Sonar host!", e);
        }
    }

    /**
     * Performs all the steps necessary for retrieving Sonar data and convert them into CSV.
     * 
     * @return The string containing all the CSV data according to the configuration of this plugin.
     */
    private String createCSV() {

        List<IProject> projects = null;
        IProject project = null;
        String csvData = null;

        if (isProjectKeyProvided()) {

            project = getExtractor().getProject(getProjectKey());
            projects = new ArrayList<IProject>();
            projects.add(project);

        } else if (isProjectKeyPatternProvided()) {

            projects = getExtractor().getProjects(getProjectKeyPattern());

        } else {

            projects = getExtractor().getAllProjects();
        }

        LOG.debug("Retrieved projects.");

        csvData = getConverter().getCSVData(projects, getMeasureObjects(), isCleanValues(), isSurroundFields());
        LOG.debug("Retrieved CSV data.");

        return csvData;
    }

    private boolean isProjectKeyPatternProvided() {

        return getProjectKeyPattern() != null && !getProjectKeyPattern().isEmpty();
    }

    private boolean isProjectKeyProvided() {

        return getProjectKey() != null && !getProjectKey().isEmpty();
    }

    /**
     * Prepare and initialize necessary internal values before actually accessing Sonar-HLA.
     */
    void prepare() {

        if (getMeasures() == null || getMeasures().isEmpty())
            setMeasureObjects(Arrays.asList(HLAMeasure.values()));
        else
            setMeasureObjects(HLAMeasure.convert(getMeasures()));

        if (getUserName() != null && !getUserName().isEmpty())
            setExtractor(SonarHLAFactory.getExtractor(getHostUrl(), getUserName(), getPassword()));
        else
            setExtractor(SonarHLAFactory.getExtractor(getHostUrl()));

        setConverter(SonarHLAFactory.getConverterInstance());

        LOG.info("Initialized " + getMeasureObjects().size() + " measures.");
    }

    /**
     * Validate given Mojo properties and throw exception if things are wrong.
     * 
     * @throws MojoFailureException if the given Mojo parameters are incorrect.
     */
    void validate() throws MojoFailureException {

        if (isProjectKeyProvided() && isProjectKeyPatternProvided())
            throw new MojoFailureException(
                    "You must not give values for both 'projectKey' and 'projectKeyPattern'!\n Use either only one of it or none at all.");
    }

    public String getHostUrl() {

        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {

        this.hostUrl = hostUrl;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getProjectKey() {

        return projectKey;
    }

    public void setProjectKey(String projectKey) {

        this.projectKey = projectKey;
    }

    public String getProjectKeyPattern() {

        return projectKeyPattern;
    }

    public void setProjectKeyPattern(String projectKeyPattern) {

        this.projectKeyPattern = projectKeyPattern;
    }

    public Boolean isCleanValues() {

        return cleanValues;
    }

    public void setCleanValues(Boolean cleanValues) {

        this.cleanValues = cleanValues;
    }

    public Boolean isSurroundFields() {

        return surroundFields;
    }

    public void setSurroundFields(Boolean surroundFields) {

        this.surroundFields = surroundFields;
    }

    public String getMeasures() {

        return measures;
    }

    public void setMeasures(String measures) {

        this.measures = measures;
    }

    List<HLAMeasure> getMeasureObjects() {

        return measureObjects;
    }

    private void setMeasureObjects(List<HLAMeasure> measureObjects) {

        this.measureObjects = measureObjects;
    }

    ISonarConverter getConverter() {

        return converter;
    }

    private void setConverter(ISonarConverter converter) {

        this.converter = converter;
    }

    ISonarExtractor getExtractor() {

        return extractor;
    }

    private void setExtractor(ISonarExtractor extractor) {

        this.extractor = extractor;
    }
}
