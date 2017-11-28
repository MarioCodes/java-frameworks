package example.jaxb.core.xml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import example.jaxb.core.xml.XMLMapper;
import example.jaxb.dto.LodgingDto;
import example.jaxb.dto.RoomDto;

@RunWith(MockitoJUnitRunner.class)
public class XMLMapperTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private final String MAPPED_XML_NAME = "mappedXML.xml";

	@InjectMocks
	private XMLMapper jaxb;

	@Before
	public void setUp() {
		XMLMapper.setXmlPath(this.MAPPED_XML_NAME);
	}

	@Test
	public void testCaseCorrectFileIsCreated() throws Exception {
		// Given
		LodgingDto lodgingDto = prepareDto();

		// When
		this.jaxb.map(lodgingDto);

		// Then
		File file = new File(this.MAPPED_XML_NAME);
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
