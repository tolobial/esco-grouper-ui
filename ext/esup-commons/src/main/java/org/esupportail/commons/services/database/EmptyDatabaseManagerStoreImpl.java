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
package org.esupportail.commons.services.database;

import java.util.List;


/**
 * An empty implementation of the database manager store.
 */
public class EmptyDatabaseManagerStoreImpl extends AbstractDatabaseManagerStore {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -7507435502515600809L;

	/**
	 * Bean constructor.
	 */
	public EmptyDatabaseManagerStoreImpl() {
		super();
	}

	/**
	 * @see org.esupportail.commons.services.database.AbstractDatabaseManagerStore#getDatabaseManagers()
	 */
	@Override
	protected List<DatabaseManager> getDatabaseManagers() {
		return null;
	}

}
