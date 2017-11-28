package examples.junit.serviceclass.dto;

import lombok.Data;

@Data
public class Transaction {
	private int id;
	private float amount;
	private String type;
}
