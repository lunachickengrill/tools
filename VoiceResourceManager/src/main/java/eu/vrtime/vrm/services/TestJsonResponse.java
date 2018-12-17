package eu.vrtime.vrm.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestJsonResponse implements Serializable {

	@JsonProperty
	public Map<String, String> numbers = new HashMap<>();

	@JsonProperty
	public String customerId;

	public TestJsonResponse(String customerId) {
		this.customerId = customerId;

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Map<String, String> getNumbers() {
		return numbers;
	}

	public void setNumbers(Map<String, String> numbers) {
		this.numbers = numbers;
	}

	public void addNumber(String k, String v) {
		numbers.put(k, v);
	}

	@Override
	public String toString() {
		return "TestJsonResponse [numbers=" + numbers + ", customerId=" + customerId + "]";
	}

}
