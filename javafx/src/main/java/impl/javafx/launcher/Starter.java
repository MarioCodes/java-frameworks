package impl.javafx.launcher;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import impl.javafx.config.Files;
import impl.javafx.config.SpringConfig;
import impl.javafx.controllers.overviews.ControllerOverview;
import impl.javafx.controllers.root.ControllerDashboard;
import impl.javafx.controllers.root.ControllerRoot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Starter extends Application {
	private BorderPane rootLayout;

	private FXMLLoader fxmlLoader;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		loadRoot(primaryStage);
		prepareDashboard();
	}

	public void prepareDashboard() {
		this.prepareFxmlLoader(Files.DASHBOARD_OVERVIEW.toString());
		final AnchorPane root = this.prepareAnchorPane();
		this.rootLayout.setCenter(root);
		this.setControllerDashboard();
	}

	public void prepareSecondOverview() {
		this.prepareFxmlLoader(Files.SECOND_OVERVIEW.toString());
		final AnchorPane root = this.prepareAnchorPane();
		this.rootLayout.setCenter(root);

		ControllerOverview overview = this.fxmlLoader.getController();
		overview.setStarter(this);
	}

	private void loadRoot(Stage primaryStage) {
		prepareFxmlLoader(Files.ROOT_LAYOUT.toString());
		prepareRootLayout();
		manipulateScene(primaryStage);
		setControllerRoot();
	}

	private AnchorPane prepareAnchorPane() {
		try {
			final AnchorPane root = this.fxmlLoader.load();
			return root;
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private void setControllerDashboard() {
		final ControllerDashboard dashboard = this.fxmlLoader.getController();
		dashboard.setStarter(this);
	}

	private void prepareFxmlLoader(final String fxmlPath) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfig.class);
		this.fxmlLoader = context.getBean(FXMLLoader.class);
		this.fxmlLoader.setControllerFactory(context::getBean);
		this.fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxmlPath));
	}

	private void prepareRootLayout() {
		try {
			this.rootLayout = this.fxmlLoader.load();
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	private void manipulateScene(final Stage primaryStage) {
		final Scene scene = new Scene(this.rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setControllerRoot() {
		final ControllerRoot root = this.fxmlLoader.getController();
		root.setStarter(this);
	}
}
