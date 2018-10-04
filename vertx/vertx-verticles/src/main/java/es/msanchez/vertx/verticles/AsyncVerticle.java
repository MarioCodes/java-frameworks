package es.msanchez.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncVerticle extends AbstractVerticle {

  /**
   * Even thought the method has returned, it won't be considered deployed until the
   *    async code has been executed and {@link Future#complete() complete} has been called.
   *
   * @param startFuture -
   * @throws Exception -
   */
  @Override
  public void start(final Future<Void> startFuture) {
    log.info("Started async Verticle");

    this.vertx.executeBlocking(handler -> { // Async work
      try {
        Thread.sleep(4000L);
        log.info("AsyncVerticle completed it's blocking code.");
        startFuture.complete();
      } catch(InterruptedException ex) {
        log.error("oh no!", ex);
        startFuture.fail(ex);
      }
    }, future -> {
      if(future.succeeded())
        log.info("AsyncVerticle's future completed.");
      else
        log.error("Oh no! Verticle failed.");
    });

    log.debug("Async exited it's method start call.");
  }

}
