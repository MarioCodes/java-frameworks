package impl.javafx.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Files {
	PATH_MAIN("fxml/main/"),
	PATH_OVERVIEWS("fxml/overviews/"),

	ROOT_LAYOUT(PATH_MAIN + "RootLayout.fxml"),
	DASHBOARD_OVERVIEW(PATH_MAIN + "DefaultOverview.fxml"),

	SECOND_OVERVIEW(PATH_OVERVIEWS + "SecondOverview.fxml");

	private final String address;

	@Override
	public String toString() {
		return this.address;
	}
}
