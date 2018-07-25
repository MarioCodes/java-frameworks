package es.msanchez.vertx.addresses;

import lombok.AllArgsConstructor;

@AllArgsConstructor public enum EventBusAddresses {

  MESSAGE_ADDRESS("vertx.message.address");

  private String address;

  @Override public String toString() {
    return address;
  }
}
