package examples.junit.serviceclass.entity;

import lombok.Data;

@Data
public class Account {

	private String name;

	private String description;

	private Transaction transaction;

}
