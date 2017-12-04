package es.msanchez.vertx.handler;

import org.springframework.stereotype.Component;

import es.msanchez.vertx.dto.CustomMessage;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomMessageHandler implements Handler<Message<CustomMessage>> {

  @Override
  public void handle(final Message<CustomMessage> event) {
    final CustomMessage message = event.body();
    log.info("Received POJO {}", message.toString());
  }

}
