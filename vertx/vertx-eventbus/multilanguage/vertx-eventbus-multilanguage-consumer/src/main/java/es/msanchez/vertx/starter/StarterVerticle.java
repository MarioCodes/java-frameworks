package es.msanchez.vertx.starter;

import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class StarterVerticle extends AbstractVerticle {

	@Override
	public void start() {
		final SpringRegister register = new SpringRegister();
		final AnnotationConfigApplicationContext context = register.iniSpringConfig(SpringConfig.class);

		final Vertx vertx = context.getBean(Vertx.class);
		vertx.deployVerticle("ConsumerVerticle.js", res -> {
			if(res.succeeded()) {
				log.info("JS verticle succeded");
			} else {
				log.error("JS verticle failed: ", res.cause());
			}
		});
	}
}
