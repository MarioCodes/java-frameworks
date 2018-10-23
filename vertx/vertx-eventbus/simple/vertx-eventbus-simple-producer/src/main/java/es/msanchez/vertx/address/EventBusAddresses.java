package es.msanchez.vertx.address;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventBusAddresses {

	STRING_ADDRESS("string.address");

	private String address;

	@Override
	public String toString() {
		return address;
	}
}
