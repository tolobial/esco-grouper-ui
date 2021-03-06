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
package org.esco.grouperui.web.beans.dynamicgrouptree;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Operation class. Requirement(s) : [RECIA-ESCO-L2-001], [RECIA-ESCO-L2-002]
 * 
 * @author oFages
 */
@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Operation {

    /** The type property. */
    private String                   type;

    /** the list of members. */
    private Collection < Member >    listOfMember;

    /** The list of operations from the node request. */
    private Collection < Operation > listOfOperation;

    /**
     * Default constructor.
     */
    public Operation() {

    }

    /**
     * Constructor.
     * 
     * @param theType
     *            the type of the operation
     */
    public Operation(final String theType) {
        super();
        this.type = theType;
    }

    /**
     * Getter for type.
     * 
     * @return the type to get.
     */
    @XmlAttribute(name = "type")
    public final String getType() {
        return this.type;
    }

    /**
     * Setter for type.
     * 
     * @param theType
     *            the type to set.
     */
    public final void setType(final String theType) {
        this.type = theType;
    }

    /**
     * Getter for listOfMember.
     * 
     * @return the listOfMember to get.
     */
    @XmlElement(name = "member", type = Member.class)
    public final Collection < Member > getListOfMember() {
        return this.listOfMember;
    }

    /**
     * Setter for listOfMember.
     * 
     * @param theListOfMember
     *            the listOfMember to set.
     */
    public final void setListOfMember(final Collection < Member > theListOfMember) {
        this.listOfMember = theListOfMember;
    }

    /**
     * Getter for listOfOperation.
     * 
     * @return the listOfOperation to get.
     */
    @XmlElement(name = "operation", type = Operation.class)
    public final Collection < Operation > getListOfOperation() {
        return this.listOfOperation;
    }

    /**
     * Setter for listOfOperation.
     * 
     * @param theListOfOperation
     *            the listOfOperation to set.
     */
    public final void setListOfOperation(final Collection < Operation > theListOfOperation) {
        this.listOfOperation = theListOfOperation;
    }

}
