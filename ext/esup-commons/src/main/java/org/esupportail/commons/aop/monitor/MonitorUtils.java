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
/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.commons.aop.monitor;

/**
 * Monitoring utilities.
 */
public abstract class MonitorUtils {

	/**
	 * Constructor.
	 */
	private MonitorUtils() {
		super();
	}

	/**
	 * Log the method calls.
	 * @param startTime
	 * @param message 
	 */
	public static void log(
			final long startTime,
			final String message) {
		(new MonitoringMethodInterceptor()).log(startTime, message);
	}
	
	/**
	 * Clear.
	 */
	public static void clear() {
		(new MonitoringMethodInterceptor()).clear();
	}
	
}