package es.msanchez.spring.cache.dao;

import es.msanchez.spring.cache.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {

  public Book getByIsbn(final String isbn);

  public void refreshAllBooks();

}
