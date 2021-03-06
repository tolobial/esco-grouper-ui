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
package org.esco.grouperui.services.application.filters;

import org.apache.commons.lang.Validate;

/**
 * <b>Enumeration for privileges.</b>
 * 
 * @author SopraGroup
 */
public enum ScopeEnum {

    /** immediate privileges. **/
    IMMEDIATE(0, "IMMEDIATE"),
    /** effectives privileges. **/
    EFFECTIVE(1, "EFFECTIVE"),
    /** all. **/
    ALL(2, "ALL");

    /** The code of the enum. */
    private final Integer code;

    /** The value of the enum. */
    private final String  value;

    /**
     * Default constructor.
     * 
     * @param code
     *            : the code.
     * @param value
     *            : the value.
     */
    private ScopeEnum(final Integer code, final String value) {
        Validate.notNull(code, "Enum code must be defined");
        Validate.notNull(value, "Enum value must be defined");

        this.code = code;
        this.value = value;
    }

    /**
     * Getter for attribute <b>code</b>.
     * 
     * @return the code
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * Getter for attribute <b>value</b>.
     * 
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Test if the value is immediate.
     * 
     * @return true if the value is immediate.
     */
    public Boolean isImmediate() {
        return this.getCode().equals(IMMEDIATE.getCode());
    }

    /**
     * Test if the value is effective.
     * 
     * @return true if the value is effective.
     */
    public Boolean isEffective() {
        return this.getCode().equals(EFFECTIVE.getCode());
    }

    /**
     * Test if the value is all.
     * 
     * @return true if the value is all.
     */
    public Boolean isAll() {
        return this.getCode().equals(ALL.getCode());
    }

    /**
     * Get enum from it value.
     * 
     * @param value
     *            : the value of the enum.
     * @return the enum matching value.
     */
    public static ScopeEnum getFromValue(final String value) {

        ScopeEnum retour = null;

        for (ScopeEnum en : ScopeEnum.values()) {
            if (en.getValue().equals(value)) {
                retour = en;
                break;
            }
        }

        return retour;

    }
}
