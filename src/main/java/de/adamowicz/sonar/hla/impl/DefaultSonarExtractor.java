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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;

import de.adamowicz.sonar.hla.api.IProject;
import de.adamowicz.sonar.hla.api.ISonarExtractor;
import de.adamowicz.sonar.hla.api.Measures;

/**
 * Concrete implementation of the {@link ISonarExtractor}. Accesses SonarQube's web services.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class DefaultSonarExtractor implements ISonarExtractor {

    static final Logger LOG      = Logger.getLogger(DefaultSonarExtractor.class);

    private Sonar       sonar    = null;
    private String      hostURL  = null;
    private String      userName = null;
    private String      password = null;

    /**
     * Don't use constructor.
     */
    private DefaultSonarExtractor() {

        LOG.debug("DefaultSonarExtractor initializing.");
    }

    DefaultSonarExtractor(String hostURL) {

        this();
        this.hostURL = hostURL;
        init();
    }

    DefaultSonarExtractor(String hostURL, String userName, String password) {

        this.hostURL = hostURL;
        this.userName = userName;
        this.password = password;
        init();
    }

    private void init() {

        if (getUserName() != null && !getUserName().isEmpty())
            sonar = Sonar.create(getHostURL(), getUserName(), getPassword());
        else
            sonar = Sonar.create(getHostURL());

        setSonar(sonar);
        LOG.debug("New Sonar object initialized: " + getSonar().toString());
        LOG.debug("SonarQube host: " + getHostURL() + " Username: " + (getUserName() != null ? getUserName() : "none"));
    }

    public String getHostURL() {

        return hostURL;
    }

    public String getUserName() {

        return userName;
    }

    String getPassword() {

        return password;
    }

    /**
     * <pre>
     * GET /sonartest/api/resources?resource=de.arbeitsagentur.sea.spu.sonar.ojaudit%3Asonar-baojaudit-plugin&metrics=lines,coverage&verbose=false& HTTP/1.1[\r][\n]
     * </pre>
     */
    public IProject getProject(String projectKey) {

        Project project = null;
        Resource projResource = null;
        ResourceQuery query = null;

        query = ResourceQuery.createForMetrics(projectKey, Measures.LOCS.getSonarName(), Measures.COVERAGE.getSonarName(),
                Measures.DUPLINES.getSonarName(), Measures.CMPLX.getSonarName());
        projResource = getSonar().find(query);
        project = new Project(projectKey, projResource);

        LOG.debug("Retrieved project:\n" + project);

        return project;
    }

    public List<IProject> getAllProjects() {

        ResourceQuery queryAll = null;
        List<Resource> projectsSonar = null;
        List<IProject> projects = null;

        queryAll = ResourceQuery.createForMetrics(null);
        projectsSonar = getSonar().findAll(queryAll);
        LOG.debug("Found " + projectsSonar.size() + " projects in Sonar.");

        projects = new ArrayList<IProject>();

        for (Resource currResource : projectsSonar) {

            projects.add(getProject(currResource.getKey()));
            LOG.debug("New project added with key: " + currResource.getKey());
        }

        return projects;
    }

    public List<IProject> getProjects(String projectKeyPattern) {

        Pattern pattern = null;
        Matcher matcher = null;
        List<IProject> allProjects = null;
        List<IProject> matchingProjects = null;

        matchingProjects = new ArrayList<IProject>();
        allProjects = getAllProjects();
        pattern = Pattern.compile(projectKeyPattern);

        for (IProject currProject : allProjects) {

            matcher = pattern.matcher(currProject.getId());

            if (matcher.matches())
                matchingProjects.add(currProject);
        }

        LOG.debug("Found " + matchingProjects.size() + " projects matching pattern " + projectKeyPattern);
        return matchingProjects;
    }

    Sonar getSonar() {

        return sonar;
    }

    private void setSonar(Sonar sonar) {

        this.sonar = sonar;
    }

}
