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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class JsonDeserializer<T> {

	protected Class<T> typeOfT;
	private ArrayList<T> entitiesOfTypeT;
	private T entityOfTypeT;
	private String jsonFileName;

	public JsonDeserializer() {
	}

	public JsonDeserializer(final Class<T> typeOfT, final String jsonFileName) {
		this.typeOfT = typeOfT;
		this.jsonFileName = jsonFileName;
	}

	public T deserializeJsonObject() {
		prepareEntity(getEntityJsonObject(getJsonReader()));
		return this.entityOfTypeT;
	}

	private void prepareEntity(final JsonObject aJsonEntity) {
		this.entityOfTypeT = new Gson().fromJson(aJsonEntity, this.typeOfT);
	}

	public ArrayList<T> deserializeJsonArray() {
		prepareEntityList(getEntityJsonArray(getJsonReader()));
		return this.entitiesOfTypeT;
	}

	private void prepareEntityList(final JsonArray jsonEntities) {
		final Gson gson = new Gson();
		this.entitiesOfTypeT = new ArrayList<T>();
		for (final JsonElement aJsonEntity : jsonEntities) {
			final T anEntityOfTypeT = gson.fromJson(aJsonEntity, this.typeOfT);
			this.entitiesOfTypeT.add(anEntityOfTypeT);
		}
	}

	private JsonArray getEntityJsonArray(final JsonReader reader) {
		return new JsonParser().parse(reader).getAsJsonArray();
	}

	private JsonObject getEntityJsonObject(final JsonReader reader) {
		return new JsonParser().parse(reader).getAsJsonObject();
	}

	private JsonReader getJsonReader() {
		if (this.jsonFileName != null && !this.jsonFileName.isEmpty()) {
			try {
				return new JsonReader(new InputStreamReader(new FileInputStream(this.jsonFileName)));
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
