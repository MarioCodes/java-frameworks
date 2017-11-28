package examples.junit.serviceclass.dto;

import lombok.Data;

@Data
public class AccountDto {
	private String name;
	private String description;
	private Transaction transaction;
}
