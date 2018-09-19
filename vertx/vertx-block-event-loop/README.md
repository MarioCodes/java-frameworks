What happens if I go against the advice on the web, and execute infinite-blocking code on all possible workers?
Also, experiment on the way to properly execute blocking code.

Results: If in the config when deploying a verticle (AbstractStarterVerticle.java), the Verticle it's set as options.setWorker(true), it won't be blocked and this is not needed. But, if the verticle is not set as worker, and inside the .handle() you want to execute possible blocking code, you can do it setting it inside vertx.executeBlocking(handler, future);

It's not really an easy task to completely block the worker's pool and / or the event loop. Mainly, as there's more than 1 event loop, and the max. number of the pool depends on the cores of your machine (it may be overriden though). To test more into it.
