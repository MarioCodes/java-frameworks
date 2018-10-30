package es.msanchez.spring.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
@ComponentScan(basePackages = { "es.msanchez.spring.cache.**" })
public class SpringConfig {

  @Bean
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(this.ehCacheManager().getObject());
  }

  @Bean
  public EhCacheManagerFactoryBean ehCacheManager() {
    final EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
    factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
    factory.setShared(true);
    return factory;
  }

}
