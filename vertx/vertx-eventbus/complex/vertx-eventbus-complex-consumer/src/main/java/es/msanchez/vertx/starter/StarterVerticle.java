package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.address.EventBusAddresses;
import es.msanchez.vertx.codec.CustomMessageCodec;
import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.dto.CustomMessage;
import es.msanchez.vertx.handler.CustomMessageHandler;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class StarterVerticle extends AbstractVerticle {

  private AnnotationConfigApplicationContext springContext;

  private final SpringRegister register = new SpringRegister();

  private CustomMessageHandler handler;

  @Override
  public void start() {
    this.springContext = this.register.iniSpringConfig(SpringConfig.class);
    this.handler = this.springContext.getBean(CustomMessageHandler.class);
    final EventBus bus = this.springContext.getBean(EventBus.class);
    bus.registerDefaultCodec(CustomMessage.class, new CustomMessageCodec());
    bus.consumer(EventBusAddresses.STRING_ADDRESS.toString(), this.handler);
  }
}
