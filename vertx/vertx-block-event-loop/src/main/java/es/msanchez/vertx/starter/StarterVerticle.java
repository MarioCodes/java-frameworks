package es.msanchez.vertx.starter;

import es.msanchez.vertx.verticles.BlockingHandlerVerticle;
import es.msanchez.vertx.verticles.ProducerVerticle;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * @author msanchez
 */
@Slf4j public class StarterVerticle extends AbstractStarterVerticle {

	@Override protected void startVerticleInstances() {
		deployVerticle(BlockingHandlerVerticle.class);
		deployVerticle(ProducerVerticle.class);
	}

	@Override public void stop(final Future<Void> stopFuture) {
		setApplicationContext(null);
		stopFuture.complete();
	}
}
