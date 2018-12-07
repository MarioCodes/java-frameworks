package example.jaxb.core.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import example.jaxb.dto.LodgingDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Usage example of JAXB to map a DTO into XML and parse it back to DTO.
 * 
 * @author msánchez
 * @since 15/09/2017
 */
@Slf4j
public class XMLMapper {
	@Setter
	private static String xmlPath;

	/**
	 * @param dto
	 */
	public void map(LodgingDto dto) {
		try {
			File fileToWriteIn = new File(xmlPath);
			Marshaller marshaller = prepareMarshaller();
			marshaller.marshal(dto, fileToWriteIn);
		} catch (JAXBException ex) {
			log.error("Error on parse xml to POJO. {}", ex);
		}
	}

	private Marshaller prepareMarshaller() throws JAXBException, PropertyException {
		JAXBContext context = JAXBContext.newInstance(LodgingDto.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		return marshaller;
	}
}
