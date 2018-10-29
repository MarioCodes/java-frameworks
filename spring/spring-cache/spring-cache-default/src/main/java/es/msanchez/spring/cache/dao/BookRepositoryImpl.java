package es.msanchez.spring.cache.dao;

import es.msanchez.spring.cache.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Implementation to simulate a Database request.
 */
@Slf4j
@Component
public class BookRepositoryImpl implements BookRepository {

  @Override
  @Cacheable(value = "books", key = "#isbn")
  public Book getByIsbn(final String isbn) {
    this.simulateDatabaseAccess();
    return new Book(isbn, "this is a book title");
  }

  private void simulateDatabaseAccess() {
    try {
      Thread.sleep(3000L);
    }catch(final InterruptedException ex) {
      log.error("There was an error on waiting for a Thread", ex);
    }
  }

}
