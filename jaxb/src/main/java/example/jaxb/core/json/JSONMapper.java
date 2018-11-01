package example.jaxb.core.json;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import example.jaxb.dto.LodgingDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONMapper {

    /**
     * Maps a POJO into JSON. The result is loaded into the FILE specified in this
     * class. We do need a jaxb.properties file in the same package as the POJOS to
     * map.
     *
     * @param dto  POJO we want to map.
     * @param file file to write to dto into.
     */
    void map(final LodgingDto dto,
             final File file) {
        try {
            Marshaller marshaller = prepareMarshaller();
            loadMarshallerProperties(marshaller);
            marshaller.marshal(dto, file);
        } catch (JAXBException e) {
            log.error("Error on map DTO to JSON. {}", e);
        }
    }

    private void loadMarshallerProperties(Marshaller marshaller) throws PropertyException {
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    private Marshaller prepareMarshaller() throws JAXBException {
        JAXBContext context = obtainContext();
        return context.createMarshaller();
    }

    private JAXBContext obtainContext() throws JAXBException {
        return JAXBContext.newInstance(LodgingDto.class);
    }

}
