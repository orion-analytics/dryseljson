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

import java.util.ArrayList;
import java.util.List;

import com.orchestral.automation.drysel.json.model.JsonUIElement;
import com.orchestral.automation.dryselcore.model.TestDatum;
import com.orchestral.automation.dryselcore.model.UIElement;

public class TestObjectFactory {

	public TestObjectFactory() {
	}

	public List<UIElement> getUIElements(final String pathOfJsonFileContainingUIElements) {
		return getListOfUIElementsFromTheListOfJsonUIElements(deserializeJsonUIElementFile(pathOfJsonFileContainingUIElements));
	}

	private List<JsonUIElement> deserializeJsonUIElementFile(final String pathOfJsonFileContainingUIElements) {
		return new JsonDeserializer<JsonUIElement>(JsonUIElement.class, pathOfJsonFileContainingUIElements).deserializeJsonArray();
	}

	private List<UIElement> getListOfUIElementsFromTheListOfJsonUIElements(final List<JsonUIElement> jsonUIElements) {
		final List<UIElement> uiElements = new ArrayList<UIElement>();
		for (final JsonUIElement jsonUIElement : jsonUIElements) {
			uiElements.add(jsonUIElement.getUIElement());
		}
		return uiElements;
	}

	public List<TestDatum> getTestData(final String pathOfJsonFileContainingTestData) {
		return new JsonDeserializer<TestDatum>(TestDatum.class, pathOfJsonFileContainingTestData).deserializeJsonArray();
	}
}