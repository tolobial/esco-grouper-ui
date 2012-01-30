package org.esco.grouperui.services.grouper.internal.enums;

/**
 * <b>Enumeration for query.</b><br/>
 * Requirements: [RECIA-ESCO-L1-001]
 * 
 * @author SopraGroup
 */
public enum SearchParametersEnum {

    /** Search by group UUID. **/
    FIELD("field"),
    /** Search by group name exact. **/
    TYPE("type"),
    /** Search by stem name. **/
    PATH("path"),
    /** Search by stem UUID. **/
    TERM("term"),
    /** Search by approximate attribute. **/
    SUBJECT("subject");

    /**
     * The value of the enum.
     */
    private final String value;

    /**
     * Default constructor.
     * 
     * @param value
     *            : the value of the enum
     */
    private SearchParametersEnum(final String value) {
        this.value = value;
    }

    /**
     * Getter for the value.
     * 
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

}