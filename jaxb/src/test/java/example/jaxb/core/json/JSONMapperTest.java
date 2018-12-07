package example.jaxb.core.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import example.jaxb.dto.LodgingDto;
import example.jaxb.dto.RoomDto;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class JSONMapperTest {

    private final String path = "src/test/resources/mappedJSON.json";

    @InjectMocks
    private JSONMapper mapper;

    @BeforeClass
    public static void setClassUp() {
        // Without this line, as of 7.12.2018 it won't be able to compile anymore and will throw
        // an obscure exception when trying to load JSON property.
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }

    @Before
    public void setUp() {
        this.deleteFileIfPresent(this.path);
    }

    @After
    public void tearDown() {
        // this.deleteFileIfPresent(this.path);
    }

    private void deleteFileIfPresent(final String path) {
        final File file = new File(path);
        if (file.exists()) {
            if (file.delete())
                log.info("Deleted path: {}", path);
            else
                log.error("Could not delete path: {}", path);
        }
    }

    @Test
    public void testMap() {
        // Given
        final File file = new File(this.path);
        final LodgingDto dto = prepareDto();

        // When
        this.mapper.map(dto, file);

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
