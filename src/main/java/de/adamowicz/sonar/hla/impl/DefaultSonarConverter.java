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
package de.adamowicz.sonar.hla.impl;

import java.util.Iterator;
import java.util.List;

import de.adamowicz.sonar.hla.api.IProject;
import de.adamowicz.sonar.hla.api.ISonarConverter;
import de.adamowicz.sonar.hla.api.HLAMeasure;

/**
 * Default implementation for {@link ISonarConverter}.
 * 
 * Created Aug 11, 2014 5:03:39 PM by bernd
 */
public class DefaultSonarConverter implements ISonarConverter {

    private static final String BREAK = "\n";
    private static final String SEP   = ",";

    @Override
    public String getCSVData(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleaned) {

        StringBuffer buff = null;
        HLAMeasure currMeasure = null;
        Iterator<HLAMeasure> iterMeasure = null;

        buff = new StringBuffer("Project");
        buff.append(SEP);
        iterMeasure = hlaMeasure.iterator();

        while (iterMeasure.hasNext()) {

            currMeasure = iterMeasure.next();
            buff.append(currMeasure.getHeaderName());

            if (iterMeasure.hasNext())
                buff.append(SEP);
        }

        buff.append(BREAK);

        for (IProject currProj : projects) {

            buff.append(currProj.getId());
            buff.append(SEP);

            iterMeasure = hlaMeasure.iterator();

            while (iterMeasure.hasNext()) {

                currMeasure = iterMeasure.next();
                buff.append(currProj.getMeasureValue(currMeasure, cleaned));

                if (iterMeasure.hasNext())
                    buff.append(SEP);
            }

            buff.append(BREAK);
        }

        return buff.toString();
    }
}
