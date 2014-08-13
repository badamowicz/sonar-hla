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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sonar.wsclient.services.Measure;
import org.sonar.wsclient.services.Resource;

import de.adamowicz.sonar.hla.api.HLAMeasure;
import de.adamowicz.sonar.hla.api.IProject;
import de.adamowicz.sonar.hla.helper.Converter;

/**
 * Represents a single project within SonarQube.
 * 
 * @author Bernd Adamowicz
 * 
 */
public class Project implements IProject {

    static final Logger             LOG                 = Logger.getLogger(Project.class);

    private static final String     VALUE_NOT_AVAILABLE = "n/a";
    private String                  id                  = null;
    private List<HLAMeasure>        hlaMeasure          = null;
    private Map<HLAMeasure, String> values              = null;
    private Resource                resource            = null;

    private Project() {

    }

    /**
     * Initializes the necessary fields for this type.
     * 
     * @param id The unique project id.
     * @param The resource which must describe a project from SonarQube.
     */
    public Project(String id, Resource resource) {

        this();
        this.id = id;
        this.resource = resource;
        init();
    }

    /**
     * Initialize the internal values.
     */
    private void init() {

        Measure currSonarMeasure = null;
        String currValue = null;

        setValues(new HashMap<HLAMeasure, String>());
        setMeasures(new ArrayList<HLAMeasure>());

        for (HLAMeasure currMeasure : HLAMeasure.values()) {

            LOG.debug("Analyzing measure " + currMeasure.getSonarName());
            currSonarMeasure = getResource().getMeasure(currMeasure.getSonarName());

            if (currSonarMeasure != null) {

                LOG.debug("Found measure for " + currMeasure.getSonarName());
                currValue = currSonarMeasure.getFormattedValue();
                LOG.debug("Value is: " + currValue);

                getValues().put(currMeasure, currValue != null ? currValue : VALUE_NOT_AVAILABLE);
                getMeasures().add(currMeasure);
            }
        }
    }

    @Override
    public String getId() {

        return id;
    }

    @Override
    public List<HLAMeasure> getMeasures() {

        return hlaMeasure != null ? hlaMeasure : new ArrayList<HLAMeasure>();
    }

    @Override
    public String getMeasureValue(HLAMeasure measure, boolean cleaned) {

        String value = null;

        value = values.get(measure);

        if (value != null && cleaned) {

            value = Converter.getCleanValue(value);
        }

        return value != null ? value : VALUE_NOT_AVAILABLE;
    }

    Resource getResource() {

        return resource;
    }

    Map<HLAMeasure, String> getValues() {

        return values;
    }

    private void setValues(Map<HLAMeasure, String> values) {

        this.values = values;
    }

    private void setMeasures(List<HLAMeasure> hlaMeasure) {

        this.hlaMeasure = hlaMeasure;
    }
}
