package es.msanchez.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * If I want to deploy this Verticle, as it contains blocking code, I have to either do it
 *    the way of {@link NormalVerticleUnblocked#start() this verticle}, or deploy it with
 *    {@link io.vertx.core.DeploymentOptions#setWorker(boolean) setWorker} true, as a Worker Verticle.
 *
 * @author msanchez
 */
@Slf4j
@Component
public class NormalVerticleBlocked extends AbstractVerticle {

  @Override
  public void start() {
      log.info("Started NormalVerticleBlocked.");
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException ex) {
        log.error("Blocking normal verticle. Error on Thread sleep");
      }
      log.info("Ended NormalVerticleBlocked.");
  }
}
