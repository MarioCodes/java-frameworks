package impl.javafx.controllers.overviews;

import org.springframework.stereotype.Component;

import impl.javafx.launcher.Starter;
import javafx.fxml.FXML;
import lombok.Setter;

@Component
public class ControllerOverview {
	@Setter
	private Starter starter;

	@FXML
	private void prepareDashboard() {
		this.starter.prepareDashboard();
	}
}
