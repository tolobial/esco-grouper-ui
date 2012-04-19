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
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/
package javax.faces.component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Iterator;

/**
 * A collection of static helper methods for locating UIComponents.
 * 
 * @author Manfred Geiler (latest modification by $Author: grantsmith $)
 * @version $Revision: 472555 $ $Date: 2006-11-08 18:30:58 +0100 (Mi, 08 Nov 2006) $
 */
class _ComponentUtils
{
    private _ComponentUtils() {}

    static UIComponent findParentNamingContainer(UIComponent component,
                                                 boolean returnRootIfNotFound)
    {
        UIComponent parent = component.getParent();
        if (returnRootIfNotFound && parent == null)
        {
            return component;
        }
        while (parent != null)
        {
            if (parent instanceof NamingContainer) return parent;
            if (returnRootIfNotFound)
            {
                UIComponent nextParent = parent.getParent();
                if (nextParent == null)
                {
                    return parent;  //Root
                }
                parent = nextParent;
            }
            else
            {
                parent = parent.getParent();
            }
        }
        return null;
    }

    static UIComponent getRootComponent(UIComponent component)
    {
        UIComponent parent;
        for(;;)
        {
            parent = component.getParent();
            if (parent == null) return component;
            component = parent;
        }
    }

    /**
     * Find the component with the specified id starting from the specified
     * component.
     * <p>
     * Param id must not contain any NamingContainer.SEPARATOR_CHAR characters
     * (ie ":"). This method explicitly does <i>not</i> search into any
     * child naming container components; this is expected to be handled
     * by the caller of this method.
     * <p>
     * For an implementation of findComponent which does descend into
     * child naming components, see org.apache.myfaces.custom.util.ComponentUtils.
     * 
     * @return findBase, a descendant of findBase, or null.
     */
    static UIComponent findComponent(UIComponent findBase, String id)
    {
        if (idsAreEqual(id,findBase))
        {
            return findBase;
        }

        for (Iterator it = findBase.getFacetsAndChildren(); it.hasNext(); )
        {
            UIComponent childOrFacet = (UIComponent)it.next();
            if (!(childOrFacet instanceof NamingContainer))
            {
                UIComponent find = findComponent(childOrFacet, id);
                if (find != null) return find;
            }
            else if (idsAreEqual(id,childOrFacet))
            {
                return childOrFacet;
            }
        }

        return null;
    }

    /*
     * Return true if the specified component matches the provided id.
     * This needs some quirks to handle components whose id value gets
     * dynamically "tweaked", eg a UIData component whose id gets
     * the current row index appended to it.
     */
    private static boolean idsAreEqual(String id, UIComponent cmp)
    {
        if(id.equals(cmp.getId()))
            return true;

        if(cmp instanceof UIData)
        {
            UIData uiData = ((UIData) cmp);

            if(uiData.getRowIndex()==-1)
            {
                return dynamicIdIsEqual(id,cmp.getId());
            }
            else
            {
                return id.equals(cmp.getId()+NamingContainer.SEPARATOR_CHAR+uiData.getRowIndex());
            }
        }

        return false;
    }

    private static boolean dynamicIdIsEqual(String dynamicId, String id)
    {
        return dynamicId.matches(id+":[0-9]*");
    }


    static void callValidators(FacesContext context, UIInput input, Object convertedValue)
    {
        // first invoke the list of validator components
        Validator[] validators = input.getValidators();
        for (int i = 0; i < validators.length; i++)
        {
            Validator validator = validators[i];
            try
            {
                validator.validate(context, input, convertedValue);
            }
            catch (ValidatorException e)
            {
                input.setValid(false);
                FacesMessage facesMessage = e.getFacesMessage();
                if (facesMessage != null)
                {
                    facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                    context.addMessage(input.getClientId(context), facesMessage);
                }
            }
        }

        // now invoke the validator method defined as a method-binding attribute
        // on the component
        MethodBinding validatorBinding = input.getValidator();
        if (validatorBinding != null)
        {
            try
            {
                validatorBinding.invoke(context,
                                        new Object[] {context, input, convertedValue});
            }
            catch (EvaluationException e)
            {
                input.setValid(false);
                Throwable cause = e.getCause();
                if (cause instanceof ValidatorException)
                {
                    FacesMessage facesMessage = ((ValidatorException)cause).getFacesMessage();
                    if (facesMessage != null)
                    {
                        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                        context.addMessage(input.getClientId(context), facesMessage);
                    }
                }
                else
                {
                    throw e;
                }
            }
        }
    }
    
    static String getStringValue(FacesContext context, ValueBinding vb)
    {
        Object value = vb.getValue(context);
        if(value == null)
        {
            return null;
        }
        return value.toString();
    }


}
