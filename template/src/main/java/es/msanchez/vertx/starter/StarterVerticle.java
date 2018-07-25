package es.msanchez.vertx.starter;

import es.msanchez.vertx.addresses.EventBusAddresses;
import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.utilities.SpringRegister;
import es.msanchez.vertx.verticles.MessageHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author msanchez
 */
@Slf4j
public class StarterVerticle extends AbstractVerticle {

	private AnnotationConfigApplicationContext applicationContext;

	private SpringRegister register = new SpringRegister();

	@Override public void start(final Future<Void> startFuture)
			throws Exception {
		applicationContext = register.initSpringApplicationContext(SpringConfig.class);

		MessageHandler handler = applicationContext.getBean(MessageHandler.class);
		EventBus bus = applicationContext.getBean(EventBus.class);

		bus.consumer(EventBusAddresses.MESSAGE_ADDRESS.toString(), handler);

		startFuture.complete();
	}

	private void deployHandler(final Class<? extends Verticle> workerClass) {
		DeploymentOptions options = new DeploymentOptions();
		options.setWorker(true);
		Verticle verticle = applicationContext.getBean(workerClass);
		Vertx vertx = applicationContext.getBean(Vertx.class);
		vertx.deployVerticle(verticle, options, stringAsyncResult -> {
			if (stringAsyncResult.succeeded())
				log.info("Deployment succeded!");
			else
				log.info("Oh no! Deployment failed.");
		});
	}

	@Override public void stop(final Future<Void> stopFuture)
			throws Exception {
		this.applicationContext = null;
		stopFuture.complete();
	}
}
