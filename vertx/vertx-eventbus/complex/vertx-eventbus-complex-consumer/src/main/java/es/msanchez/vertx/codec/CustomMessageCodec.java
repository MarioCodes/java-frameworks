package es.msanchez.vertx.codec;

import es.msanchez.vertx.dto.CustomMessage;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;

public class CustomMessageCodec implements MessageCodec<CustomMessage, CustomMessage> {
  private final int GET_INT_JUMP = 4;
  private final byte SYSTEM_CODEC_ID = -1;

  @Override
  public void encodeToWire(final Buffer buffer,
                           final CustomMessage customMessage) {
    final JsonObject jsonToEncode = this.obtainFilledJson(customMessage);
    final String jsonString = jsonToEncode.encode();
    final int jsonLength = jsonString.getBytes().length; // Is NOT characters count.
    buffer.appendInt(jsonLength);
    buffer.appendString(jsonString);
  }

  private JsonObject obtainFilledJson(final CustomMessage message) {
    final JsonObject jsonToEncode = new JsonObject();
    jsonToEncode.put("statusCode", message.getStatusCode());
    jsonToEncode.put("resultCode", message.getResultCode());
    jsonToEncode.put("summary", message.getSummary());
    return jsonToEncode;
  }

  @Override
  public CustomMessage decodeFromWire(final int position,
                                      final Buffer buffer) {
    int bufferPosition = position;
    final int jsonLength = buffer.getInt(bufferPosition);

    final String jsonString = buffer.getString(bufferPosition += this.GET_INT_JUMP,
        bufferPosition += jsonLength);
    final JsonObject jsonObject = new JsonObject(jsonString);

    final int statusCode = jsonObject.getInteger("statusCode");
    final String resultCode = jsonObject.getString("resultCode");
    final String summary = jsonObject.getString("summary");

    return new CustomMessage(statusCode, resultCode, summary);
  }

  /**
   * Used to identify a codec when sending a message for unregistrering codecs.
   */
  @Override
  public String name() {
    return this.getClass().getSimpleName();
  }

  @Override
  public byte systemCodecID() {
    return this.SYSTEM_CODEC_ID;
  }

  @Override
  public CustomMessage transform(final CustomMessage customMessage) {
    return customMessage;
  }

}
