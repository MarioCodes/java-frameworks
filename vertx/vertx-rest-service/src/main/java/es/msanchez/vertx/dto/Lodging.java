package es.msanchez.vertx.dto;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

@Data
public class Lodging {
	private static final AtomicInteger COUNTER = new AtomicInteger();

	private final int id;

	private String name;

	private String country;

	public Lodging(String name, String country) {
		this.id = COUNTER.getAndIncrement();
		this.name = name;
		this.country = country;
	}

	public Lodging() {
		this.id = COUNTER.getAndIncrement();
	}
}
