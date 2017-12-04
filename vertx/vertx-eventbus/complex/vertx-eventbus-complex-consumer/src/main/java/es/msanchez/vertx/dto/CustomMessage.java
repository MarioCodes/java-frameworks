package es.msanchez.vertx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomMessage {
  private final int statusCode;
  private final String resultCode;
  private final String summary;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CustomMessage {");
    sb.append("status code = ").append(this.statusCode);
    sb.append(", resultCode='").append(this.resultCode).append('\'');
    sb.append(", summary='").append(this.summary).append('\'');
    sb.append("}");
    return sb.toString();
  }
}
