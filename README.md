# java-frameworks-implementations
Personal Testing-zone and notes for java frameworks.

## Spring
### Spring Context
#### Spring Cache  
A cache itself may be imagined as a key-value map. For a basic Cache we need:  
* `@EnableCaching` tag in `@Configuration` class  
* Declare a `CacheManager` Bean  
* Tag the method to cache w. `@Cacheable`   
* Create a method with `@CacheEvict`

We may declare +1 cache(s) at the cache manager and select the one we want to use in the method we tag. As key for the cache we may use any conjunction of the parameters given to the method, or if this is a class, any of it's accessible variables. The cache will only be triggered when the exact key is given again. **Then the method won't be executed** and the value will be directly given from the cache. If the parameters don't match any key, the method will be executed as normal and then the value will be saved into the cache to be returned the next time.  

Caution with logs in big apps as they need to be written accordingly.  
The hard part is not knowing when to cache something, but to know when to Evict the cache.  

#### Default Cache  
Spring provides `Cache` and `CacheManager` as main abstractions for the caching logic. They do not provide the actual storage to store data. For that we have some options out of the box on the JDK such as `SimpleCacheManager`. It's based on `ConcurrentMap` and it's useful when we need a really basic Cache, but it does not support the eviction or persistence of the Cache.  

#### EHCache
The entities to save, have to implement `Serializable` interface. If we don't do that, it'll throw a `NotSerializableException`.  
The config for the several caches is specified in `ehcache.xml`

#### Infinispan  
It's an in-memory, highly concurrent Cache. It has built-in Eviction. It may be deployed in local mode but it's a best choice for its cluster mode (distributed or replicated).

##### References
https://spring.io/guides/gs/caching/  
https://www.baeldung.com/spring-cache-tutorial  
http://websystique.com/spring/spring-4-cache-tutorial-with-ehcache/  
https://blog.infinispan.org/2010/02/infinispan-as-local-cache.html  
http://infinispan.org/docs/stable/user_guide/user_guide.html#clustering

## Vertx
### Compile & execute:  
```
mvn clean install  
java -jar target/[substitute_with_name]-fat.jar -cluster
```

### Standard vs Worker Verticle
Concurrency is handled completely by Vert.x  

When created, a _standard verticle_ will have one Event-loop assigned to it (it'll always use the same) and the start method it's called within that Event-loop.
If it calls other Handlers, it can guarantee that they'll be executed in the same Event-Loop  
Meanwhile, a _worker verticle_ will have a different Thread assigned to it, everytime it wants to perform a Job.  
If you're able to use a standard verticle for _non-blocking_ jobs, you'll save resources every time you execute code with it.  

A standard verticle runs in an Event-Loop thread (one of many). If they're completely blocked, **the whole Program will be blocked and it will just halt.**
On the other side, the worker verticles run on a different Thread than the main event-loop, so they're perfect to execute blocking code (another option is an inline _.executeBlocking()_ call). They will never be executed by more than one Thread simultaneously, but they may be executed each time by different Threads.

The downside of using always workers, is that the max. concurrency achievable is much lesser than using normal verticles + workers. With a lot of blocking tasks, you may create a processing queue.

### Multi-threaded worker verticles  
It can be executed by more than one Thread concurrently. Standard Java techniques for concurrency will be needed when programming. It's an advanced feature and they're not supported through all of Vert.x parts.   

#### References  
https://vertx.io/docs/vertx-core/java/#_verticle_types  
https://groups.google.com/forum/#!topic/vertx/4HdQvi2jIJ8

### Polyglot Verticles  
_(For the example, I've used JS)_  
It needs a new dependency in our _pom.xml_
```
<dependency>
    <groupId>io.vertx</groupId>
    <artifactId>vertx-lang-js</artifactId>
    <version>3.0.0</version>
</dependency>
```  
Watch out as by default `mvn clean install` does not pack _*.js_ files into a _-fat.jar_ if they're in a default java package. I've solved this, by writting the _.js_ verticle into the _/resources_ folder.  
Also, for the _.js_ case, it may be needed to install _npm_ and _vertx-3_ dependencies for it to work.  
```
sudo apt-get install npm
npm install vertx3-min
```  
#### Reference  
https://github.com/vert-x3/vertx-examples/tree/master/core-examples/src/main/js

## Codearte's Catch exception
The only thing it does, it's to do a bit easier to test and assert for exceptions in a Test Driven Development-like way. **To use together with AssertJ.** It only has two methods which are useful to me:
```
// When
BDDCatchException.when(this.instance).methodWhichThrowsException();

// Then
Assertions.assertThat(BDDCatchException.caughtException())
            .isNotNull()
            .isExactlyInstanceOf(IndexOutOfBoundsException.class);
```

#### Reference
https://github.com/Codearte/catch-exception

## AssertJ  
Outside of the basic `Assertions.assertThat(x).isEqualTo(y);` there is:

### Soft Assertions  
Used to assert DTOs. With the basic Assertion when the first fails, it stops there and only shows that error. With soft assertions it executes all and shows all where there was an error.
```
SoftAssertions.assertSoftly(soft -> {
    soft.assertThat(dto.getName()).isEqualTo("nme");
    soft.assertThat(dto.getAge()).isEqualTo(90);
    soft.assertThat(dto.getRace()).isEqualTo(Race.HUMAN);
});
```

### Assert Collections
Assertions if the original Beans are accessible to compare against
```
// Given
final TolkienCharacter aragorn = new TolkienCharacter("aragon", 200, Race.HUMAN);
final TolkienCharacter frodo = new TolkienCharacter("frodo", 30, Race.HOBBIT);
final TolkienCharacter sam = new TolkienCharacter("sam", 30, Race.HOBBIT);
final List<TolkienCharacter> fellowship = Lists.newArrayList(aragorn, frodo, sam);

// When

// Then
Assertions.assertThat(fellowship)
        .filteredOn(character -> character.getName().contains("a"))
        .hasSize(2)
        .containsOnly(aragorn, sam);
```  

Assertions if the original Beans are not accessible, or it woul be too much work to create them.
```
// Given
final List<TolkienCharacter> fellowship = this.prepareFellowship();

// When

// Then
Assertions.assertThat(fellowship)
        .extracting("name", String.class)
        .contains("aragorn", "frodo", "sam")
        .doesNotContain("sauron");
```
#### Reference
https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html
