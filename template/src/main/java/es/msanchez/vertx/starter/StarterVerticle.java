package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class StarterVerticle extends AbstractVerticle {

	private AnnotationConfigApplicationContext applicationContext;

	private SpringRegister register = new SpringRegister();

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		applicationContext = register.initSpringApplicationContext(SpringConfig.class);

		//
		// Here goes our custom code for this application.
		//

		startFuture.complete();
	}

	@Override
	public void stop(final Future<Void> stopFuture) throws Exception {
		this.applicationContext = null;
		stopFuture.complete();
	}
}
