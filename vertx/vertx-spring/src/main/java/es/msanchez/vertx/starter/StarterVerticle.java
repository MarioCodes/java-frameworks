package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.config.SpringConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class StarterVerticle extends AbstractVerticle {

  protected AnnotationConfigApplicationContext applicationContext;

  @Override
  public void start(final Future<Void> startFuture) throws Exception {
    this.initSpringApplicationContext(SpringConfig.class);

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

  private void initSpringApplicationContext(final Class<?> configClass) {
    if (this.applicationContext != null)
      this.applicationContext = null;

    final AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
    springContext.register(configClass);
    springContext.refresh();
    this.applicationContext = springContext;
  }
}
