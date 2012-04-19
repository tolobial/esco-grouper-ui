/**
 * Copyright (C) 2009 GIP RECIA http://www.recia.fr
 * @Author (C) 2009 GIP RECIA <contact@recia.fr>
 * @Contributor (C) 2009 SOPRA http://www.sopragroup.com/
 * @Contributor (C) 2011 Pierre Legay <pierre.legay@recia.fr>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esco.grouperui.web.utils;

import javax.faces.context.FacesContext;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.esupportail.commons.services.i18n.I18nService;

/**
 * @author sopragroup
 */
public class I18nAdapter extends XmlAdapter < String, String > {

    /**
     * {@inheritDoc}
     */
    @Override
    public String marshal(final String theValueToConvert) throws Exception {

        I18nService i18nService = (I18nService) FaceContextUtils.getValueFromContext(FacesContext
                .getCurrentInstance(), "i18nService");

        return i18nService.getString(theValueToConvert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String unmarshal(final String theArg0) throws Exception {
        throw new IllegalAccessError();
    }

}
