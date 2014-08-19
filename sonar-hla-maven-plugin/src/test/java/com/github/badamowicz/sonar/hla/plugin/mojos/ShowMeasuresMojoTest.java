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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.plugin.mojos.ShowMeasuresMojo;

/**
 * Test cases for {@link ShowMeasuresMojo}. Admittedly no real tests here.
 * 
 * Created Aug 19, 2014 11:06:32 AM by bernd
 */
public class ShowMeasuresMojoTest {

    private ShowMeasuresMojo mojo = null;

    @BeforeClass
    public void beforeClass() {

        mojo = new ShowMeasuresMojo();
    }

    /**
     * Simply issue information by executing <i>execute()</i>. No exception -> test OK.
     */
    @Test
    public void execute() throws MojoExecutionException, MojoFailureException {

        mojo.execute();
    }
}
