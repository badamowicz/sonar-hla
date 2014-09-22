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
package com.github.badamowicz.sonar.hla.api;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Instances of this class are capable of converting extracted objects from SonarQube (see {@link ISonarExtractor}) to some other
 * format like XML or CSV.
 * 
 * @author Bernd Adamowicz
 * 
 */
public interface ISonarConverter {

    /**
     * Create CSV data based on the given aggregated project.
     * 
     * @param projectAgg The aggregated project.
     * @param hlaMeasures The measures to be used for output.
     * @param surroundFields If true, every single field of data will be surrounded with quotation marks.
     * @return The string containing the CSV data.
     */
    public String getCSVData(IProjectAggregated projectAgg, List<HLAMeasure> hlaMeasures, boolean surroundFields);

    /**
     * Create CSV data as input stream based on the given aggregated project.
     * 
     * @param projectAgg The aggregated project.
     * @param hlaMeasures The measures to be used for output.
     * @param surroundFields If true, every single field of data will be surrounded with quotation marks.
     * @return The {@link InputStream} containing the CSV data.
     */
    public InputStream getCSVDataAsStream(IProjectAggregated projectAgg, List<HLAMeasure> hlaMeasures, boolean surroundFields);

    /**
     * Create CSV data as a file based on the given aggregated project.
     * 
     * @param fileName The name / path of the file to be generated.
     * @param projectAgg The aggregated project.
     * @param hlaMeasures The measures to be used for output.
     * @param surroundFields If true, every single field of data will be surrounded with quotation marks.
     * @return The {@link File} containing the CSV data.
     */
    public File getCSVDataAsFile(String fileName, IProjectAggregated projectAgg, List<HLAMeasure> hlaMeasures,
            boolean surroundFields);

    /**
     * Convert the given projects into a string of CSV data. The fields will not be surrounded with any quotation marks.
     * 
     * @param projects A list of projects.
     * @param hlaMeasure The measures to use.
     * @param cleanValues When set to true, no non-numeric characters will be contained.
     * @return The string containing the CSV data.
     */
    public String getCSVData(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues);

    /**
     * Convert the given projects into a string of CSV data.
     * 
     * @param projects A list of projects.
     * @param hlaMeasure The measures to use.
     * @param cleanValues When set to true, no non-numeric characters will be contained.
     * @param surroundFields If true, every single field of data will be surrounded with quotation marks.
     * @return The string containing the CSV data.
     */
    public String getCSVData(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues, boolean surroundFields);

    /**
     * Convert the given projects into an {@link InputStream} of CSV data. The fields will not be surrounded with any quotation
     * marks.
     * 
     * @param projects A list of projects.
     * @param hlaMeasure The measures to use.
     * @param cleanValues When set to true, no non-numeric characters will be contained.
     * @return The {@link InputStream} containing the CSV data.
     */
    public InputStream getCSVDataAsStream(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues);

    /**
     * Convert the given projects into an {@link InputStream} of CSV data.
     * 
     * @param projects A list of projects.
     * @param hlaMeasure The measures to use.
     * @param cleanValues When set to true, no non-numeric characters will be contained.
     * @param surroundFields If true, every single field of data will be surrounded with quotation marks.
     * @return The {@link InputStream} containing the CSV data.
     */
    public InputStream getCSVDataAsStream(List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues,
            boolean surroundFields);

    /**
     * Convert the given projects into CSV data and write them to a file object. The fields will not be surrounded with any
     * quotation marks.
     * 
     * @param fileName A relative or absolute file name.
     * @param projects A list of projects.
     * @param hlaMeasure The measures to use.
     * @param cleanValues When set to true, no non-numeric characters will be contained.
     * @return The {@link File} object containing the CSV data.
     */
    public File getCSVDataAsFile(String fileName, List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues);

    /**
     * Convert the given projects into a {@link File} object which contains the CSV data.
     * 
     * @param fileName A relative or absolute file name.
     * @param projects A list of projects.
     * @param hlaMeasure The measures to use.
     * @param cleanValues When set to true, no non-numeric characters will be contained.
     * @param surroundFields If true, every single field of data will be surrounded with quotation marks.
     * @return The {@link File} object containing the CSV data.
     */
    public File getCSVDataAsFile(String fileName, List<IProject> projects, List<HLAMeasure> hlaMeasure, boolean cleanValues,
            boolean surroundFields);

    /**
     * This is a convenience method and a kind of shortcut to write CSV data which has already been generated to the given file.
     * It might be useful for implementing classes which have already generated the CSV data <b>and</b> also need these data
     * written to a file. Using this method will avoid processing the request to a SonarQube host again.
     * 
     * @param fileName A relative or absolute file name.
     * @param csvData The CSV data to be written to the file. Actually this method will <b>not</b> check if this string really
     *        contains CSV data. It will simply write what is given in the string object.
     */
    public void writeCSVDataToFile(String fileName, String csvData);
}
