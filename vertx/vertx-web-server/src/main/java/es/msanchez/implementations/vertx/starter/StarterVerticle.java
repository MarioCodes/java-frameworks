package es.msanchez.implementations.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class StarterVerticle extends AbstractVerticle {
	/**
	 * To launch from .jar: java -jar xxx -conf
	 * src/main/resources/conf/${file.name}.json
	 */
	@Override
	public void start(Future<Void> future) {
		vertx.createHttpServer().requestHandler(r -> {
			r.response().end("<h1>Heyo!</h1>");
		}).listen(
				config().getInteger("http.port", 8080),
				result -> {
					if (result.succeeded())
						future.complete();
					else
						future.fail(result.cause());
				});
	}
}
