package es.msanchez.vertx.starter;

import es.msanchez.vertx.verticles.AsyncVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * I call the Verticles without using {@link AbstractStarterVerticle#deployVerticle(Class) deployVerticle}
 *    as I want to try manually.
 *
 * @author msanchez
 */
@Slf4j
public class StarterVerticle extends AbstractStarterVerticle {

  @Override
  protected void startVerticleInstances() {
    log.info("Started main verticle.");

    DeploymentOptions options = new DeploymentOptions();
    options.setWorker(true);

    Vertx vertx = this.getApplicationContext().getBean(Vertx.class);
    // vertx.deployVerticle(SyncVerticle.class, options);
    vertx.deployVerticle(AsyncVerticle.class, options);
  }

  @Override
  public void stop(final Future<Void> stopFuture) {
    setApplicationContext(null);
    stopFuture.complete();
  }
}
