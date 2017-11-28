package es.msanchez.vertx.rest.starter;

import java.util.LinkedHashMap;
import java.util.Map;

import es.msanchez.vertx.dto.Lodging;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class StarterVerticle extends AbstractVerticle {

	private Map<Integer, Lodging> registry = new LinkedHashMap<>();

	private Router router = Router.router(vertx);

	@Override
	public void start(Future<Void> future) {
		createDummyData();
		route();
		prepareHttpServer(future);
	}

	private void createDummyData() {
		Lodging first = new Lodging("Las Rosas", "Spain");
		Lodging second = new Lodging("Los Puentes", "Mexico");
		registry.put(first.getId(), first);
		registry.put(second.getId(), second);
	}

	/**
	 * Bind / to the hello message - so it's still compatible.
	 */
	private void route() {
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response
					.putHeader("content-type", "text/html")
					.end("<h1>Heyo!</h1>");
		});

		router.route("/assets/*").handler(StaticHandler.create("assets"));
	}

	private void prepareHttpServer(Future<Void> future) {
		vertx
				.createHttpServer()
				.requestHandler(router::accept)
				.listen(
						config().getInteger("http.port", 8080),
						result -> {
							if (result.succeeded())
								future.complete();
							else
								future.fail(future.cause());
						});
	}
}
