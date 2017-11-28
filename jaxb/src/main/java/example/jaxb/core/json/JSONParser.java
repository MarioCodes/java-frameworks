package example.jaxb.core.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import example.jaxb.dto.LodgingDto;
import lombok.Setter;

public class JSONParser {
	@Setter
	private static String FILE;

	/**
	 * Parses from a valid JSON file into a POJO.
	 * 
	 * @return POJO correctly parsed.
	 */
	public LodgingDto parse() {
		try {
			Unmarshaller unmarshaller = prepareUnmarshaller();
			loadProperties(unmarshaller);
			return parse(unmarshaller);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	private LodgingDto parse(Unmarshaller unmarshaller) throws JAXBException {
		return (LodgingDto) unmarshaller.unmarshal(getClass().getClassLoader().getResourceAsStream(FILE));
	}

	private void loadProperties(Unmarshaller unmarshaller) throws PropertyException {
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
	}

	private Unmarshaller prepareUnmarshaller() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(LodgingDto.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller;
	}

}
