/*
 * Copyright (c) Orchestral Developments Ltd and the Orion Health group of companies (2001 - 2017).
 * Author: Kuldeep Sinh Chauhan (@KuldeepSinhC)
 * emails: kuldeeps@orionhealth.com, chauhan.kuldeep.sinh@gmail.com
 *
 * This file is provided to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an  "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,  either express or implied.  See the License for the specific language governing    
 * permissions and limitations under the License.
 */
package com.orchestral.automation.drysel.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.orchestral.automation.dryselcore.eventlib.TestEvent;

public class TestEventReflector {

	public TestEventReflector() {
	}

	public TestEvent getTestEvent(final String testEventName) {
		return createInstance(getConstructor(getClassFromName(testEventName)));
	}

	private TestEvent createInstance(final Constructor<? extends TestEvent> constructor) {
		constructor.setAccessible(true);
		try {
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Constructor<? extends TestEvent> getConstructor(final Class<? extends TestEvent> aClass) {
		try {
			return aClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<? extends TestEvent> getClassFromName(final String className) {
		try {
			return Class.forName(determineFullyQualifiedClassName(className)).asSubclass(TestEvent.class);
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String determineFullyQualifiedClassName(final String nameToSplit) {
		final String[] nameParts = splitStringAtPeriods(nameToSplit);
		if (nameParts.length == 1) {
			return TestEvent.class.getPackage().getName() + "." + nameToSplit.trim();
		} else {
			return nameToSplit.trim();
		}
	}

	private String[] splitStringAtPeriods(final String nameToSplit) {
		final String[] nameParts = nameToSplit.split("\\.", 0);
		return nameParts;
	}
}
