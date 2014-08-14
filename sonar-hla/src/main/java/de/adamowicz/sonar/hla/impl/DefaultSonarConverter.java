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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import de.adamowicz.sonar.hla.api.HLAMeasure;
import de.adamowicz.sonar.hla.api.IProject;
import de.adamowicz.sonar.hla.api.ISonarConverter;

/**
 * Default implementation for {@link ISonarConverter}.
 * 
 * Created Aug 11, 2014 5:03:39 PM by bernd
 */
public class DefaultSonarConverter implements ISonarConverter {

    private static final Logger LOG       = Logger.getLogger(DefaultSonarConverter.class);

    private static final String BREAK     = "\n";
    private static final String QUOTATION = "\"";

    /**
     * Don't use constructor. Obtain instances of this type using {@link SonarHLAFactory} methods.
     */
    DefaultSonarConverter() {

        LOG.debug("New DefaultSonarConverter instance created.");
    }

    @Override
    public String getCSVData(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleaned) {

        StringBuilder builder = null;
        HLAMeasure currMeasure = null;
        Iterator<HLAMeasure> iterMeasure = null;

        builder = new StringBuilder("Project");
        builder.append(SEP);
        iterMeasure = hlaMeasure.iterator();

        while (iterMeasure.hasNext()) {

            currMeasure = iterMeasure.next();
            builder.append(currMeasure.getHeaderName());

            if (iterMeasure.hasNext())
                builder.append(SEP);
        }

        builder.append(BREAK);

        for (IProject currProj : projects) {

            builder.append(currProj.getId());
            builder.append(SEP);

            iterMeasure = hlaMeasure.iterator();

            while (iterMeasure.hasNext()) {

                currMeasure = iterMeasure.next();
                builder.append(currProj.getMeasureValue(currMeasure, cleaned));

                if (iterMeasure.hasNext())
                    builder.append(SEP);
            }

            builder.append(BREAK);
        }

        return builder.toString();
    }

    @Override
    public InputStream getCSVDataAsStream(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues) {

        return new ByteArrayInputStream(getCSVData(projects, hlaMeasure, cleanValues).getBytes());
    }

    @Override
    public String getCSVData(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues, boolean surroundFields) {

        String csvData = null;

        csvData = getCSVData(projects, hlaMeasure, cleanValues);

        if (surroundFields)
            csvData = surroundFields(csvData);

        return csvData;
    }

    /**
     * Surround the given CSV data with quotations for every field.
     * 
     * @param csvData The original data without quotations.
     * @return A new string object with all fields being quoted.
     */
    private String surroundFields(String csvData) {

        StringBuilder surroundedCSV = null;
        StringTokenizer currTokenizer = null;

        surroundedCSV = new StringBuilder();

        for (String currLine : csvData.split(BREAK)) {

            currTokenizer = new StringTokenizer(currLine, SEP);

            while (currTokenizer.hasMoreTokens()) {

                surroundedCSV.append(QUOTATION).append(currTokenizer.nextToken()).append(QUOTATION);

                if (currTokenizer.hasMoreTokens())
                    surroundedCSV.append(SEP);
            }

            surroundedCSV.append(BREAK);
        }

        return surroundedCSV.toString();
    }

    @Override
    public InputStream getCSVDataAsStream(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues,
            boolean surroundFields) {

        return new ByteArrayInputStream(getCSVData(projects, hlaMeasure, cleanValues, surroundFields).getBytes());
    }

    @Override
    public String toString() {

        return "DefaultSonarConverter with CSV separator: " + SEP;
    }
}
