package es.msanchez.vertx.starter;

import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@Getter
public abstract class AbstractStarterVerticle extends AbstractVerticle {

  @Setter private AnnotationConfigApplicationContext applicationContext;

  private SpringRegister register = new SpringRegister();

  protected void deployVerticle(final Class<? extends Verticle> workerClass) {
    DeploymentOptions options = new DeploymentOptions();
    Verticle verticle = applicationContext.getBean(workerClass);
    Vertx vertx = applicationContext.getBean(Vertx.class);
    vertx.deployVerticle(verticle, options, stringAsyncResult -> {
      if (stringAsyncResult.succeeded())
        log.info("Deployment succeded!");
      else
        log.info("Oh no! Deployment failed.");
    });
  }

  @Override public void start() {
    applicationContext = register.initSpringApplicationContext(SpringConfig.class);
    startVerticleInstances();
  }

  protected abstract void startVerticleInstances();

}
