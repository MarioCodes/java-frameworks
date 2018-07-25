package es.msanchez.vertx.starter;

import es.msanchez.vertx.verticles.MessageHandler;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * @author msanchez
 */
@Slf4j public class StarterVerticle extends AbstractStarterVerticle {

	@Override protected void startVerticleInstances() {
		deployVerticle(MessageHandler.class);
	}

	@Override public void stop(final Future<Void> stopFuture) {
		setApplicationContext(null);
		stopFuture.complete();
	}
}
