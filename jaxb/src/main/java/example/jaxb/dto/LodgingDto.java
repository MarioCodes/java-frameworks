package example.jaxb.dto;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "lodgingDto")
public class LodgingDto {
	private long id;
	private String address;
	private String city;
	private String county;
	private String state;
	private String country;
	private String phoneNumber;
	private RoomDto roomDto;
}
