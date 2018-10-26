package es.msanchez.spring.cache.dao;

import es.msanchez.spring.cache.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Implementation to simulate a Database request
 */
@Slf4j
@Component
public class BookRepositoryImpl implements BookRepository {

  @Override public Book getByIsbn(final String isbn) {
    final StopWatch watch = new StopWatch();
    watch.start();

    this.simulateDatabaseAccess();
    final Book book = new Book(isbn, "this is a book title");

    watch.stop();
    log.debug("Retrieved book: {} in: {} millis", book, watch.getTotalTimeMillis());
    return book;
  }

  private void simulateDatabaseAccess() {
    try {
      Thread.sleep(3000L);
    }catch(final InterruptedException ex) {
      log.error("There was an error on waiting for a Thread", ex);
    }
  }

}
