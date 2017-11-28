package impl.javafx.controllers.root;

import org.springframework.stereotype.Component;

import impl.javafx.launcher.Starter;
import javafx.fxml.FXML;
import lombok.Setter;

@Component
public class ControllerRoot {
	@Setter
	private Starter starter;

	@FXML
	private void shutdown() {
		System.exit(0);
	}
}
