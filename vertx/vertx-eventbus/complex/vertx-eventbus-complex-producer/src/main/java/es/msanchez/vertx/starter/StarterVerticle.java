package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.address.EventBusAddresses;
import es.msanchez.vertx.codec.CustomMessageCodec;
import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.dto.CustomMessage;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarterVerticle extends AbstractVerticle {

  private AnnotationConfigApplicationContext context;

  private final SpringRegister register = new SpringRegister();

  @Override
  public void start() {
    this.context = this.register.iniSpringConfig(SpringConfig.class);

    final EventBus bus = this.context.getBean(EventBus.class);
    bus.registerDefaultCodec(CustomMessage.class, new CustomMessageCodec());
    final CustomMessage message = new CustomMessage(200, "a00001", "message sent from publisher");
    this.vertx.setPeriodic(1000, event -> {
      bus.send(EventBusAddresses.STRING_ADDRESS.toString(), message);
      log.info("Sent POJO {}", message);
    });
  }
}
