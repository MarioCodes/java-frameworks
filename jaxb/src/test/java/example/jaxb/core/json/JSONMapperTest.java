package example.jaxb.core.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import example.jaxb.dto.LodgingDto;
import example.jaxb.dto.RoomDto;

@RunWith(MockitoJUnitRunner.class)
public class JSONMapperTest {
	private String file = "src/test/resources/mappedJSON.json";

	@InjectMocks
	private JSONMapper mapper;

	@Before
	public void setUp() {
		JSONMapper.setFILE(this.file);
	}

	@Test
	public void testMap() throws Exception {
		// Given
		File file = new File(this.file);
		LodgingDto dto = prepareDto();

		// When
		this.mapper.map(dto);

		// Then
		assertThat(file.exists()).isTrue();
		assertThat(file.isFile()).isTrue();
	}

	private LodgingDto prepareDto() {
		LodgingDto lodgingDto = new LodgingDto();
		lodgingDto.setId(50);
		lodgingDto.setAddress("Reg. Str.");
		lodgingDto.setCity("Bremerhaven");
		lodgingDto.setCounty("Baja Sajonia");
		lodgingDto.setCountry("Deutschland");
		lodgingDto.setPhoneNumber("645525555");
		lodgingDto.setState("Madrid");

		RoomDto roomDto = new RoomDto();
		roomDto.setId(20);
		roomDto.setExtras("Cuarto de baño");
		roomDto.setType("Individual");

		lodgingDto.setRoomDto(roomDto);
		return lodgingDto;
	}
}
