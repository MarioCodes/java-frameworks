package impl.javafx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javafx.fxml.FXMLLoader;

/**
 * 
 * @author msanchez
 *
 */
@Configuration
@ComponentScan("impl.javafx.controllers.**")
public class SpringConfig {
	@Bean
	@Scope("prototype")
	FXMLLoader fxmlLoader() {
		return new FXMLLoader();
	}
}
