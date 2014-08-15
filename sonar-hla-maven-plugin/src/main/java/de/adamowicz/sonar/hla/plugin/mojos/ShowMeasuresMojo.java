package de.adamowicz.sonar.hla.plugin.mojos;

import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import de.adamowicz.sonar.hla.api.HLAMeasure;
import de.adamowicz.sonar.hla.plugin.helper.LogHelper;

/**
 * This Mojo actually performs no actions at all. It simply displays all available measures inside Sonar-HLA. Those measures can
 * then be used to configure {@link ExtractorMojo}.
 * <p>
 * Created Aug 14, 2014 11:12:38 AM by bernd
 * 
 * @goal showMeasures
 * @requiresProject false
 */
public class ShowMeasuresMojo extends AbstractMojo {

    private static final Logger LOG_INFO = Logger.getLogger("USER_DATA");

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        LOG_INFO.info("\nAvailable measures are:");
        LOG_INFO.info("======================");

        for (HLAMeasure currMeasure : HLAMeasure.values()) {

            LOG_INFO.info(currMeasure.getSonarName());
        }

        LogHelper.moo(LOG_INFO);
    }

}
