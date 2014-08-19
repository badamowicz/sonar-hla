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
package com.github.badamowicz.sonar.hla.plugin.mojos;

import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.plugin.helper.LogHelper;


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

        LogHelper.logMeasures(HLAMeasure.values(), LOG_INFO);
    }

}
