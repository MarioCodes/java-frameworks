package impl.javafx.controllers.root;

import org.springframework.stereotype.Component;

import impl.javafx.launcher.Starter;
import javafx.fxml.FXML;
import lombok.Setter;

@Component
public class ControllerDashboard {
	@Setter
	private Starter starter;

	@FXML
	private void prepareSecondOverview() {
		this.starter.prepareSecondOverview();
	}
}
