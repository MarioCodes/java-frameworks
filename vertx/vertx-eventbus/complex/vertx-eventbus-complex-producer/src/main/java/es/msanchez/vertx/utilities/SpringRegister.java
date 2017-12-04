package es.msanchez.vertx.utilities;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringRegister {
	public AnnotationConfigApplicationContext iniSpringConfig(Class<?> configClass) {
		AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
		springContext.register(configClass);
		springContext.refresh();
		return springContext;
	}
}
