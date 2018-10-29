package es.msanchez.spring.cache.starter;

import es.msanchez.spring.cache.config.SpringConfig;
import es.msanchez.spring.cache.dao.BookRepository;
import es.msanchez.spring.cache.entity.Book;
import es.msanchez.spring.cache.utilities.SpringRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

@Slf4j
public class Main {

  public static void main(String[] args) {
    log.info("Application started");
    final AnnotationConfigApplicationContext context = prepareSpring();
    log.info("Configuration is ready");

    final BookRepository bookRepository = context.getBean(BookRepository.class);
    retrieveBook(bookRepository, "1234");
    retrieveBook(bookRepository, "1234");
    retrieveBook(bookRepository, "5678");
    retrieveBook(bookRepository, "9012");
    retrieveBook(bookRepository, "9012");

    log.info("Application is done");
  }

  private static AnnotationConfigApplicationContext prepareSpring() {
    final SpringRegister register = new SpringRegister();
    return register.initSpringApplicationContext(SpringConfig.class);
  }

  /**
   * In a normal Program this wouldn't be here on Main. But I want to measure the time it takes to
   * retrieve a Book with(out) cache, and when the cache kicks in {@link BookRepository#getByIsbn(String)}
   * won't executed.
   *
   * @param repository -
   * @param isbn       -
   * @return -
   */
  private static Book retrieveBook(final BookRepository repository, final String isbn) {
    final StopWatch watch = new StopWatch();
    watch.start();

    final Book book = repository.getByIsbn(isbn);

    watch.stop();
    log.debug("Retrieved book: {} in: {} millis", book, watch.getTotalTimeMillis());
    return book;
  }
}
