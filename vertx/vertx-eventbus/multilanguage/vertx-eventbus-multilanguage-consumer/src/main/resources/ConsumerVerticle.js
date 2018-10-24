console.log("ConsumerVerticle.js verticle has started.");

var eventBus = vertx.eventBus();

eventBus.consumer("string.multilanguage.address", function (message) {
    console.log("JS Worker consumed data: " + message.body())
})

console.log("ConsumerVerticle.js completed");
