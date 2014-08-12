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
package de.adamowicz.sonar.hla.api;

import java.util.List;

/**
 * Base class which serves as an entry point for accessing SonarQube resources.
 * 
 * @author Bernd Adamowicz
 * 
 */
public interface ISonarExtractor {

    /**
     * Retrieve the host URL this connector is connected to.
     * 
     * @return An URL to Sonar host.
     */
    public String getHostURL();

    /**
     * Get the user name for this connection.
     * 
     * @return The user name or <b>null</b> if none available.
     */
    public String getUserName();

    /**
     * Retrieve the project defined by the given arguments.
     * 
     * @param projectKey The project's key.
     * @return An initialized {@link IProject} or null if none found.
     */
    public IProject getProject(String projectKey);

    /**
     * Get all available Projects on this particular SonarQube.
     * 
     * @return A list of all projects or an empty list if none found.
     */
    public List<IProject> getAllProjects();

    /**
     * Retrieve all projects matching the given patterns.
     * 
     * @param projectKeyPattern A pattern matching the desired project names or null.
     * @return A list of all projects or an empty list if matching projects found.
     */
    public List<IProject> getProjects(String projectKeyPattern);
}
