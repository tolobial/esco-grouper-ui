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
package org.esupportail.commons.services.remote; 

import org.esupportail.commons.exceptions.EsupException;

/**
 * A class for web services authorization exceptions.
 */
public class WebServiceAuthorizationException extends EsupException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = 4118216554765981193L;

	/**
	 * @param message
	 */
	public WebServiceAuthorizationException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WebServiceAuthorizationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WebServiceAuthorizationException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
