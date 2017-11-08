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

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.orchestral.automation.dryselcore.TestScriptExecutor;
import com.orchestral.automation.dryselcore.eventlib.ElementNotEnabledException;
import com.orchestral.automation.dryselcore.model.TestDatum;
import com.orchestral.automation.dryselcore.model.UIElement;

public class JsonTestScriptExecutor {

	private List<UIElement> uiElements;
	private List<TestDatum> testData;
	private final TestScriptExecutor testScriptExecutor;

	public JsonTestScriptExecutor(final WebDriver webDriver) {
		this.testScriptExecutor = new TestScriptExecutor(webDriver);
	}

	public void executeInputScript(final String uiElementsFilePath, final String inputTestDataFilePath)
			throws ElementNotEnabledException {
		// de-serialize objects
		deserializeObjects(uiElementsFilePath, inputTestDataFilePath);
		// execute input test script
		executeInputTestScript();
	}

	public void executeOutputScript(final String uiElementsFilePath, final String outputTestDataFilePath)
			throws ElementNotEnabledException {
		// de-serialize objects
		deserializeObjects(uiElementsFilePath, outputTestDataFilePath);
		// execute input test script
		executeOutputTestScript();
	}

	private void deserializeObjects(final String uiElementsFilePath, final String testDataFilePath) {
		// de-serialize UIElements
		deserializeUIElements(uiElementsFilePath);
		// de-serialize TestData
		deserializeTestData(testDataFilePath);
	}

	private void deserializeUIElements(final String uiElementsFilePath) {
		if (uiElementsFilePath != null && !uiElementsFilePath.isEmpty()) {
			this.uiElements = new TestObjectFactory().getUIElements(uiElementsFilePath);
		}
	}

	private void deserializeTestData(final String testDataFilePath) {
		if (testDataFilePath != null && !testDataFilePath.isEmpty()) {
			this.testData = new TestObjectFactory().getTestData(testDataFilePath);
		}
	}

	private void executeInputTestScript() throws ElementNotEnabledException {
		if (this.testData != null) {
			this.testScriptExecutor.executeInputScript(this.uiElements, this.testData);
		}
	}

	private void executeOutputTestScript() throws ElementNotEnabledException {
		if (this.testData != null) {
			this.testScriptExecutor.executeOutputScript(this.uiElements, this.testData);
		}
	}
}
