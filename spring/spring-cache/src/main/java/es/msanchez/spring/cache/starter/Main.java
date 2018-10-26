package es.msanchez.spring.cache.starter;

import es.msanchez.spring.cache.config.SpringConfig;
import es.msanchez.spring.cache.dao.BookRepository;
import es.msanchez.spring.cache.dao.BookRepositoryImpl;
import es.msanchez.spring.cache.utilities.SpringRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j public class Main {

  public static void main(String[] args) {
    log.info("Application started");
    final AnnotationConfigApplicationContext context = prepareSpring();
    log.info("Configuration is ready");

    final BookRepository bookRepository = context.getBean(BookRepositoryImpl.class);
    bookRepository.getByIsbn("1234");
    bookRepository.getByIsbn("1234");
    bookRepository.getByIsbn("5678");
    bookRepository.getByIsbn("9012");
    bookRepository.getByIsbn("9012");

    log.info("Application is done");
  }

  private static AnnotationConfigApplicationContext prepareSpring() {
    final SpringRegister register = new SpringRegister();
    return register.initSpringApplicationContext(SpringConfig.class);
  }

}
