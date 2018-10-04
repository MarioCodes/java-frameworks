package es.msanchez.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SyncVerticle extends AbstractVerticle {

  /**
   * As this executes blocking code, it should be deployed as a worker or with
   *  {@link io.vertx.core.Vertx#executeBlocking(Handler, Handler) executeBlocking}.
   *
   *  Once this method has completed, the verticle will be considered started.
   */
  @Override
  public void start() throws InterruptedException {
    log.info("NormalVerticle deployed.");
    Thread.sleep(5000L);
    log.info("NormalVerticle completed its blocking work.");
  }

}
