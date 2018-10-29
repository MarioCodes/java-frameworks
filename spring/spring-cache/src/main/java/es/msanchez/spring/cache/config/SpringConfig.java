package es.msanchez.spring.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
@ComponentScan(basePackages = { "es.msanchez.spring.cache.**" })
public class SpringConfig {

  @Bean
  public CacheManager cacheManager() {
    final SimpleCacheManager manager = new SimpleCacheManager();
    manager.setCaches(Arrays.asList(new ConcurrentMapCache("books")));
    return manager;
  }

}
