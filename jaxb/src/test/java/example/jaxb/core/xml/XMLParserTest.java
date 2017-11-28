package example.jaxb.core.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import example.jaxb.core.xml.XMLParser;
import example.jaxb.dto.LodgingDto;
import example.jaxb.dto.RoomDto;

@RunWith(MockitoJUnitRunner.class)
public class XMLParserTest {
	@InjectMocks
	private XMLParser parser;

	private final String PARSED_XML_FILE = "lodgings-test.xml";

	@Before
	public void setUp() {
		this.parser = new XMLParser();
		XMLParser.setXmlPath(this.PARSED_XML_FILE);
	}

	@Test
	public void testCaseLodgingDtoIsCorrectlyParsed() throws Exception {
		// Given
		LodgingDto lodgingDto = null;

		// When
		lodgingDto = this.parser.parse();

		// Then
		assertThat(lodgingDto).isNotNull();
		assertThat(lodgingDto.getId()).isEqualTo(1);
		assertThat(lodgingDto.getAddress()).isEqualTo("Calle torreciudad");
		assertThat(lodgingDto.getCity()).isEqualTo("Barbastro");
		assertThat(lodgingDto.getCounty()).isEqualTo("Huesca");
		assertThat(lodgingDto.getState()).isEqualTo("Aragón");
		assertThat(lodgingDto.getPhoneNumber()).isEqualTo("+34974315856");
		assertThat(lodgingDto.getCountry()).isEqualTo("Spain");
	}

	@Test
	public void testCaseRoomDtoIsCorrectlyParsed() throws Exception {
		// Given
		RoomDto roomDto = null;

		// When
		roomDto = this.parser.parse().getRoomDto();

		// Then
		assertThat(roomDto).isNotNull();
		assertThat(roomDto.getExtras()).isEqualTo("Personal Sauna");
		assertThat(roomDto.getId()).isEqualTo(1);
		assertThat(roomDto.getType()).isEqualTo("Individual");
	}

	@Test
	public void testCasePathIsNullEmptyObjectIsGivenBack() throws Exception {
		// Given
		XMLParser.setXmlPath(null);

		// When
		LodgingDto dto = this.parser.parse();

		// Then
		assertThat(dto).isNull();

	}

	@Test
	public void testCasePathIsEmptyNullObjectIsGivenBack() {
		// Given
		XMLParser.setXmlPath("");

		// When
		LodgingDto dto = this.parser.parse();

		// Then
		assertThat(dto).isNull();
	}
}