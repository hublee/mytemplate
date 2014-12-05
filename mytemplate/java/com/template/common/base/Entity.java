package com.template.common.base;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class Entity extends JSONObject {

	private static final long serialVersionUID = 1L;
	
	public Entity() {
		super();
	}

	public Entity(Map<String, Object> map) {
		super(map);
	}

	public Entity getEntity(String key) {
		Object value = this.get(key);
		if (value instanceof Entity) {
			return (Entity) value;
		}

		JSONObject jobj = null;

		if (value instanceof JSONObject) {
			jobj = (JSONObject) value;
		} else {
			jobj = (JSONObject) toJSON(value);
		}

		return jobj == null ? null : new Entity(jobj);
	}

	public Entity set(String key, Object value, boolean force) {
		if (force || value != null) {
			super.put(key, value);
		}
		return this;
	}

	public Entity set(String key, Object value) {
		return this.set(key, value, true);
	}

	public Entity setAll(Map<? extends String, ? extends Object> m) {
		super.putAll(m);
		return this;
	}

	public static Entity err(int errCode) {
		return new Entity().set("errCode", errCode);
	}

	public static Entity err(int errCode, String errMsg) {
		return new Entity().set("errCode", errCode).set("errMsg", errMsg);
	}
}
