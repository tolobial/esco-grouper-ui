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
package org.esupportail.commons.dao;

import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * An abstract DAO implementation.
 */
public abstract class AbstractGenericHibernateDaoService 
implements HibernateDaoService, InitializingBean {

	/**
	* A logger.
	*/
	private Logger logger = new LoggerImpl(getClass()); 

	/**
	 * Bean constructor.
	 */
	protected AbstractGenericHibernateDaoService() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public final void afterPropertiesSet() throws Exception {
		initDao();
	}

	/**
	 * Initialize.
	 * @throws Exception
	 */
	protected abstract void initDao() throws Exception;
	
	/**
	 * @return the Hibernate template used for database operations
	 */
	protected abstract HibernateTemplate getHibernateTemplate();

	/**
	 * @see org.esupportail.commons.dao.HibernateDaoService#getQuery(java.lang.String)
	 */
	public Query getQuery(
			final String hqlQuery) {
		return (Query) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(final Session session) throws HibernateException {
						return session.createQuery(hqlQuery);
					}
				}
		);
	}
	
	/**
	 * Count entries of the database.
	 * @param countQuery the query
	 * @return an integer.
	 */
	protected int getQueryIntResult(final String countQuery) {
		return DataAccessUtils.intResult(getHibernateTemplate().find(countQuery));
	}
	
	/**
	 * do updates in the database.
	 * @param queryString
	 */
	protected void executeUpdate(final String queryString) {
		getQuery(queryString).executeUpdate();
	}
	
	/**
	 * @see org.esupportail.commons.dao.HibernateDaoService#getSqlQuery(java.lang.String)
	 */
	public SQLQuery getSqlQuery(
			final String sqlQuery) {
		return (SQLQuery) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(final Session session) throws HibernateException {
						return session.createSQLQuery(sqlQuery);
					}
				}
		);
	}
	
	//////////////////////////////////////////////////////////////
	// misc
	//////////////////////////////////////////////////////////////

	/**
	 * Add an object into the database.
	 * @param object 
	 */
	protected void addObject(final Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("adding " + object + "...");
		}
		getHibernateTemplate().save(object);
		if (logger.isDebugEnabled()) {
			logger.debug("done.");
		}
	}

	/**
	 * Update an object in the database.
	 * @param object 
	 */
	protected void updateObject(final Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("merging " + object + "...");
		}
		Object merged = getHibernateTemplate().merge(object);
		if (logger.isDebugEnabled()) {
			logger.debug("done, updating " + merged + "...");
		}
		getHibernateTemplate().update(merged);
		if (logger.isDebugEnabled()) {
			logger.debug("done.");
		}
	}

	/**
	 * Delete an object from the database.
	 * @param object 
	 */
	protected void deleteObject(final Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("merging " + object + "...");
		}
		Object merged = getHibernateTemplate().merge(object);
		if (logger.isDebugEnabled()) {
			logger.debug("done, deleting " + merged + "...");
		}
		getHibernateTemplate().delete(merged);
		if (logger.isDebugEnabled()) {
			logger.debug("done.");
		}
	}

	/**
	 * Delete a list of objects from the database.
	 * @param objects 
	 */
	@SuppressWarnings("unchecked")
	protected void deleteObjects(final List objects) {
		getHibernateTemplate().deleteAll(objects);
	}

}
