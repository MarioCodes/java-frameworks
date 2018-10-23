### Standard vs Worker Verticle
When created, a _standard verticle_ will have one Event-Loop assigned to it (it'll always use the same) and the start method it's called within that Event-Loop.
If it calls other Handlers, it can guarantee that they'll be executed in the same Event-Loop  
Meanwhile, a _worker verticle_ will have a different Thread assigned to it, everytime it wants to perform a Job.  
If you're able to use a standard verticle for _non-blocking_ jobs, you'll save resources every time you execute code with it.  

A standard verticle runs in an Event-Loop thread (one of many). If they're completely blocked, **the whole Program will be blocked and it will just halt.**
On the other side, the worker verticles run on a different Thread than the main event-loop, so they're perfect to execute blocking code (another option is an inline _.executeBlocking()_ call).  

The downside of using always workers, is that the max. concurrency achievable is much lesser than using normal verticles + workers. With a lot of blocking tasks, you may create a processing queue.

#### References  
https://vertx.io/docs/vertx-core/java/#_verticle_types  
https://groups.google.com/forum/#!topic/vertx/4HdQvi2jIJ8
