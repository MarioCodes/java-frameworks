package example.jaxb.core.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import example.jaxb.dto.LodgingDto;
import example.jaxb.dto.RoomDto;

@RunWith(MockitoJUnitRunner.class)
public class JSONParserTest {
	private final String path = "mappedJSON.json";

	@InjectMocks
	private JSONParser parser;

    @BeforeClass
    public static void setClassUp() {
        // Without this line, as of 7.12.2018 it won't be able to compile anymore and will throw
        // an obscure exception when trying to load JSON property.
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }

	@Before
	public void setUp() {
		JSONParser.setFILE(this.path);
	}

	@Test
	public void testParse() {
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
