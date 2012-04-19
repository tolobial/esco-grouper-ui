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

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.esupportail.commons.aop.AopUtils;

/**
 * A monitoring method call.
 */
public class MonitoringMethodCall implements Comparable<MonitoringMethodCall> {

	/** The class called. */
	private String className;

	/** The method called. */
	private String methodName;
	
	/** The complete call string. */
	private String fullString;
	
	/** The time spent. */
	private List<Long> times;
	
	/**
	 * Constructor.
	 * @param joinPoint
	 * @param time
	 */
	public MonitoringMethodCall(
			final ProceedingJoinPoint joinPoint,
			final long time) {
		super();
		this.className = joinPoint.getSignature().getDeclaringTypeName();
		this.methodName = joinPoint.getSignature().getName();
		this.fullString = AopUtils.getLogSignature(joinPoint);
		this.times = new ArrayList<Long>();
		addTime(time);
	}
	
	/**
	 * Add an execution time.
	 * @param time
	 */
	public void addTime(final long time) {
		times.add(time);
	}
	
	/**
	 * @param o 
	 * @return an int
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(final MonitoringMethodCall o) {
		int classVal = className.compareTo(o.className);
		if (classVal != 0) {
			return classVal;
		}
		if (o.times.size() != times.size()) {
			return o.times.size() - times.size();
		}
		int methodVal = methodName.compareTo(o.methodName);
		if (methodVal != 0) {
			return methodVal;
		}
		return fullString.compareTo(o.fullString);
	}
	
	/** @return the className */
	public String getClassName() {
		return className;
	}
	
	/** @return the methodName */
	public String getMethodName() {
		return methodName;
	}
	
	/** @return the times */
	public List<Long> getTimes() {
		return times;
	}
	
	/** @return the fullString */
	public String getFullString() {
		return fullString;
	}

}