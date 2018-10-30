package es.msanchez.spring.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {

  private String isbn;
  private String title;

  @Override public String toString() {
    return String.format("Book { isbn='%s' title='%s'}", isbn, title);
  }
}
