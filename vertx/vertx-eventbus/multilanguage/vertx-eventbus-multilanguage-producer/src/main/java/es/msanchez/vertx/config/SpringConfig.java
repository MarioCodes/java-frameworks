package es.msanchez.vertx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

@Configuration
@ComponentScan(basePackages = {"es.msanchez.vertx.**" })
public class SpringConfig {

	@Bean
	public Vertx vertx() {
		return Vertx.currentContext().owner();
	}

	@Bean
	public EventBus eventBus() {
		return vertx().eventBus();
	}

}
