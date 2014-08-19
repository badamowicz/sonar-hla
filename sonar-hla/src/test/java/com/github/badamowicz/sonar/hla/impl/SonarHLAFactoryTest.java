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

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.badamowicz.sonar.hla.api.ISonarConverter;
import com.github.badamowicz.sonar.hla.api.ISonarExtractor;
import com.github.badamowicz.sonar.hla.impl.SonarHLAFactory;


/**
 * UTs for {@link SonarHLAFactory}.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class SonarHLAFactoryTest {

    private static final String PASSWORD   = "secret";
    private static final String USER       = "user";
    private static final String SOME_URL   = "/some/url";
    private ISonarExtractor     extractor1 = null;
    private ISonarExtractor     extractor2 = null;
    private ISonarConverter     converter  = null;

    @BeforeClass
    public void beforeClass() {

        extractor1 = SonarHLAFactory.getExtractor(SOME_URL);
        assertNotNull(extractor1, "Extractor not initialized!");

        extractor2 = SonarHLAFactory.getExtractor(SOME_URL, USER, PASSWORD);
        assertNotNull(extractor2, "Extractor not initialized!");

        converter = SonarHLAFactory.getConverterInstance();
        assertNotNull(converter, "Converter not initialized!");
    }

    @Test
    public void testExtractorNotSame() {

        assertNotSame(SonarHLAFactory.getExtractor(SOME_URL), extractor1, "Factory must not return singletons for converter!");
        assertNotSame(SonarHLAFactory.getExtractor(SOME_URL, USER, PASSWORD), extractor2,
                "Factory must not return singletons for converter!");
    }

    @Test
    public void testConverterSingleton() {

        assertSame(converter, SonarHLAFactory.getConverterInstance(), "Factory did not return a singleton for converter!");
    }
}
