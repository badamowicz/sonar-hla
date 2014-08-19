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
package com.github.badamowicz.sonar.hla.impl;

import static org.testng.Assert.fail;

import java.util.regex.Pattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.impl.DefaultSonarExtractor;

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
