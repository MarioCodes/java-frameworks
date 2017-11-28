package es.msanchez.implementations.vertx.starter;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class StarterVerticleTest {
	private static int PORT = 8092;
	private Vertx vertx;

	@Before
	public void setUp(TestContext context) throws IOException {
		prepareVertxWithConfigOptions(context);
	}

	private void prepareVertxWithConfigOptions(TestContext context) {
		vertx = Vertx.vertx();
		DeploymentOptions options = new DeploymentOptions()
				.setConfig(new JsonObject().put("http.port", PORT));
		vertx.deployVerticle(StarterVerticle.class.getName(), options,
				context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void givenVertxContextWhenCreateClientThenAssertConnectsToServer(TestContext context) {
		// Given
		Async async = context.async();

		// When
		vertx.createHttpClient().getNow(PORT, "localhost", "/", response -> {
			response.handler(body -> {
				// Then
				context.assertTrue(body.toString().contains("Heyo!"));
				async.complete();
			});
		});
	}
}
