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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;

/**
 * Just a helper mock for testing aggregated projects. Beware this type is only for unit testing purposes. Not all methods will be
 * implemented and will simply return null!
 * 
 * @author bernd
 *
 */
public class ProjectMock implements IProject {

    private Map<HLAMeasure, Integer> valuesInt    = null;
    private Map<HLAMeasure, Double>  valuesDouble = null;
    private Map<HLAMeasure, String>  allValues    = null;
    private String                   id           = null;
    private List<HLAMeasure>         measures     = null;

    public ProjectMock(String id, Map<HLAMeasure, Integer> valuesInt, Map<HLAMeasure, Double> valuesDouble) {

        this.id = id;
        this.valuesInt = valuesInt;
        this.valuesDouble = valuesDouble;

        allValues = new HashMap<HLAMeasure, String>();

        for (HLAMeasure currIntMeasure : valuesInt.keySet())
            if (valuesInt.get(currIntMeasure) != null)
                allValues.put(currIntMeasure, valuesInt.get(currIntMeasure).toString());

        for (HLAMeasure currDoubleMeasure : valuesDouble.keySet())
            if (valuesDouble.get(currDoubleMeasure) != null)
                allValues.put(currDoubleMeasure, valuesDouble.get(currDoubleMeasure).toString());

        measures = new ArrayList<HLAMeasure>();
        measures.addAll(valuesInt.keySet());
        measures.addAll(valuesDouble.keySet());
    }

    @Override
    public String getId() {

        return id;
    }

    @Override
    public String getVersion() {

        return "mock";
    }

    @Override
    public List<HLAMeasure> getMeasures() {

        return measures;
    }

    @Override
    public Double getMeasureDoubleValue(HLAMeasure measure) {

        return valuesDouble.get(measure);
    }

    @Override
    public Integer getMeasureIntValue(HLAMeasure measure) {

        return valuesInt.get(measure);
    }

    @Override
    public String toString() {

        return "ProjectMock: " + getId();
    }

    @Override
    public String getMeasureValue(HLAMeasure measure, boolean cleaned) {

        String value = null;

        value = allValues.get(measure);

        return value != null ? value : Project.VALUE_NOT_AVAILABLE;
    }

}
