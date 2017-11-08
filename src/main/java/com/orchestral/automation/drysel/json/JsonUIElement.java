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

import com.orchestral.automation.dryselcore.model.UIElement;

public class JsonUIElement {

	private String key;
	private String elementID;
	private String inputEventName;
	private String outputEventName;
	// we will create a UIElement from this, JsonUIElement and have an ability
	// to return it from this class in the form of "getUIElement"
	private UIElement uiElement;

	public JsonUIElement() {
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getElementID() {
		return this.elementID;
	}

	public void setElementID(final String elementID) {
		this.elementID = elementID;
	}

	public String getInputEventName() {
		return this.inputEventName;
	}

	public void setInputEventName(final String inputEventName) {
		if (this.outputEventName != null && !this.outputEventName.isEmpty()) {
			this.inputEventName = inputEventName;
		}

	}

	public String getOutputEventName() {
		return this.outputEventName;
	}

	public void setOutputEventName(final String outputEventName) {
		if (outputEventName != null && !outputEventName.isEmpty()) {
			this.outputEventName = outputEventName;
		}
	}

	public JsonUIElement(final String key, final String elementID, final String inputEventName,
			final String outputEventName) {
		setKey(key);
		setElementID(elementID);
		setInputEventName(inputEventName);
		setOutputEventName(outputEventName);
	}


	public UIElement getUIElement() {
		this.uiElement = new UIElement(this.key, this.elementID);
		setInputEvent();
		setOutputEvent();
		return this.uiElement;
	}

	private void setInputEvent() {
		if (getInputEventName() != null && !getInputEventName().isEmpty()) {
			final TestEventReflector testEventFactory = new TestEventReflector();
			this.uiElement.setInputEvent(testEventFactory.getTestEvent(this.inputEventName));
		}
	}

	private void setOutputEvent() {
		if (getOutputEventName() != null && !getOutputEventName().isEmpty()) {
			final TestEventReflector testEventFactory = new TestEventReflector();
			this.uiElement.setOutputEvent(testEventFactory.getTestEvent(this.outputEventName));
		}
	}

}
