package example.jaxb.core.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import example.jaxb.dto.LodgingDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XMLParser {
	@Setter
	private static String xmlPath;

	/**
	 * Gets the .XML specified in the path of this class and converts its
	 * information into a LodgingDto POJO with its children.
	 * 
	 * @return
	 */
	public LodgingDto parse() {
		try {
			return parseToDto();
		} catch (JAXBException ex) {
			log.error("Error on parse of xml. {}", ex);
		}

		return null;
	}

	private LodgingDto parseToDto() throws JAXBException {
		Unmarshaller unmarshaller = prepareJAXB();

		if (pathIsValid())
			return (LodgingDto) unmarshaller.unmarshal(getClass().getClassLoader().getResourceAsStream(xmlPath));
		else
			return null;
	}

	private boolean pathIsValid() {
		return (XMLParser.xmlPath != null) && !XMLParser.xmlPath.isEmpty();
	}

	private Unmarshaller prepareJAXB() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(LodgingDto.class);
		return jaxbContext.createUnmarshaller();
	}
}
