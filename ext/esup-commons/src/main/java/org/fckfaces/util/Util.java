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
package org.fckfaces.util;

import javax.faces.context.FacesContext;

public class Util {
	
//	public static final String FCK_FACES_RESOURCE_PREFIX = "/fckfaces";
	public static final String FCK_FACES_RESOURCE_PREFIX = "/media";
	
	public static final String internalPath(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + FCK_FACES_RESOURCE_PREFIX + path;
	}
	
	public static final String externalPath(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + path;
	}
}
