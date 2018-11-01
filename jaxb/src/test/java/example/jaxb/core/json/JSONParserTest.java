package example.jaxb.core.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import example.jaxb.dto.LodgingDto;
import example.jaxb.dto.RoomDto;

@RunWith(MockitoJUnitRunner.class)
public class JSONParserTest {
	private String file = "mappedJSON.json";

	@InjectMocks
	JSONParser parser;

	@Before
	public void setUp() {
		JSONParser.setFILE(this.file);
	}

	@Test
	@Ignore
	public void testParse() throws Exception {
		// Given
		LodgingDto dto;
		RoomDto roomDto;

		// When
		dto = this.parser.parse();
		roomDto = dto.getRoomDto();

		// Then
		assertionsDtoIsCorrectlyParsed(dto, roomDto);
	}

	private void assertionsDtoIsCorrectlyParsed(LodgingDto dto, RoomDto roomDto) {
		assertThat(dto).isNotNull();
		assertThat(roomDto).isNotNull();
		assertThat(dto.getRoomDto()).isNotNull();
		assertThat(dto.getAddress()).isEqualTo("Reg. Str.");
		assertThat(dto.getCity()).isEqualTo("Bremerhaven");
		assertThat(dto.getCountry()).isEqualTo("Deutschland");
		assertThat(dto.getCounty()).isEqualTo("Baja Sajonia");
		assertThat(dto.getId()).isEqualTo(50);
		assertThat(dto.getPhoneNumber()).isEqualTo("645525555");
		assertThat(dto.getState()).isEqualTo("Madrid");
		assertThat(roomDto.getExtras()).isEqualTo("Cuarto de baño");
		assertThat(roomDto.getId()).isEqualTo(20);
		assertThat(roomDto.getType()).isEqualTo("Individual");
	}
}
