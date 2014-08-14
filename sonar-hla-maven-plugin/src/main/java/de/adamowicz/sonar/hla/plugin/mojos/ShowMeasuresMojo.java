package de.adamowicz.sonar.hla.plugin.mojos;

import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import de.adamowicz.sonar.hla.api.HLAMeasure;

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

    static final Logger LOG = Logger.getLogger(ShowMeasuresMojo.class);

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        LOG.info("\nAvailable measures are:");

        for (HLAMeasure currMeasure : HLAMeasure.values()) {

            LOG.info(currMeasure.getSonarName());
        }
    }

}
