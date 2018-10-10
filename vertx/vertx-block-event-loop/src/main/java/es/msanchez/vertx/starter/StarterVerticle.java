package es.msanchez.vertx.starter;

import es.msanchez.vertx.verticles.NormalVerticleBlocked;
import es.msanchez.vertx.verticles.NormalVerticleUnblocked;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * @author msanchez
 */
@Slf4j
public class StarterVerticle extends AbstractStarterVerticle {

	@Override
	protected void startVerticleInstances() {
		log.info("Deploying normal verticle.");
		deployVerticle(NormalVerticleBlocked.class, false);
		log.info("Completed normal verticle.");
	}

	@Override
	public void stop(final Future<Void> stopFuture) {
		setApplicationContext(null);
		stopFuture.complete();
	}
}
