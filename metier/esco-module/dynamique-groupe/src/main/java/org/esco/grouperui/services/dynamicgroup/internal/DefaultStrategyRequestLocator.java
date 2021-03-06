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
package org.esco.grouperui.services.dynamicgroup.internal;

import java.util.HashMap;
import java.util.Map;

import org.esco.grouperui.domaine.beans.Group;
import org.esco.grouperui.domaine.beans.Person;
import org.esco.grouperui.domaine.beans.Stem;
import org.esco.grouperui.exceptions.ESCOTechnicalException;
import org.esco.grouperui.services.dynamicgroup.IStrategyRequestFactory;
import org.esco.grouperui.services.dynamicgroup.IStrategyRequestLocator;
import org.esco.grouperui.services.strategy.IExpressionEvaluator;
import org.esco.grouperui.tools.log.ESCOLoggerFactory;
import org.esco.grouperui.tools.log.IESCOLogger;

/**
 * Default locator.
 * 
 * @author dMoulron
 */
public class DefaultStrategyRequestLocator implements IStrategyRequestLocator {

    /**
     * the logger for this class.
     */
    private static final IESCOLogger                LOGGER = ESCOLoggerFactory
                                                                   .getLogger(DefaultStrategyRequestLocator.class);

    /** constant for object person can be used in evaluator. */
    private static final String                     PERSON = "person";

    /** constant for object group can be used in evaluator. */
    private static final String                     GROUP  = "group";

    /** constant for object stem can be used in evaluator. */
    private static final String                     STEM   = "stem";

    /** Strategies. **/
    private Map < String, IStrategyRequestFactory > strategies;

    /** Evaluator. **/
    private IExpressionEvaluator                    evaluator;

    /**
     * Default constructor.
     */
    public DefaultStrategyRequestLocator() {
    }

    /**
     * setter for property evaluator.
     * 
     * @param theEvaluator
     *            the evaluator to set
     */
    public void setEvaluator(final IExpressionEvaluator theEvaluator) {
        this.evaluator = theEvaluator;
    }

    /**
     * setter for property strategies.
     * 
     * @param theStrategies
     *            the strategies to set
     */
    public void setStrategies(final Map < String, IStrategyRequestFactory > theStrategies) {
        this.strategies = theStrategies;
    }

    /**
     * {@inheritDoc}
     */
    public IStrategyRequestFactory locate(final Person thePerson, final Group theGroup, final Stem theStem) {

        DefaultStrategyRequestLocator.LOGGER
                .debug("locate(final Person thePerson, final Group theGroup, final Stem theStem) - start");

        final Map < String, Object > parameters = new HashMap < String, Object >();

        parameters.put("evaluator", this.evaluator);

        parameters.put(DefaultStrategyRequestLocator.PERSON, thePerson);
        parameters.put(DefaultStrategyRequestLocator.GROUP, theGroup);
        parameters.put(DefaultStrategyRequestLocator.STEM, theStem);

        IStrategyRequestFactory strategy = null;

        try {
            for (Object expression : this.strategies.keySet()) {
                if (this.evaluator.evaluate(expression.toString(), parameters)) {
                    strategy = this.strategies.get(expression);
                    DefaultStrategyRequestLocator.LOGGER.debug("Strategy found:" + strategy.toString());
                    break;
                }
            }
        } catch (final Exception e) {
            throw new ESCOTechnicalException("No strategy found", e);
        }

        DefaultStrategyRequestLocator.LOGGER
                .debug("locate(final Person thePerson, final Group theGroup, final Stem theStem) - end");

        return strategy;
    }
}
