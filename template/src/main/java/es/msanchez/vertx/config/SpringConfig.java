package es.msanchez.vertx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @author msanchez@neusta.de
 * @since Nov 28, 2017 - 1.0.0
 */
@Configuration
@ComponentScan(basePackages = { "" })
public class SpringConfig {
  @Bean
  public Vertx vertx() {
    return Vertx.vertx();
  }

  @Bean
  public EventBus eventBus() {
    return this.vertx().eventBus();
  }
}
