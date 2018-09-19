package es.msanchez.vertx.utilities;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringRegister {

	public AnnotationConfigApplicationContext initSpringApplicationContext(
			final Class<?> configClass) {
		final AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
		springContext.register(configClass);
		springContext.refresh();
		return springContext;
	}

}
