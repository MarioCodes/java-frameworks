package es.msanchez.spring.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Book implements Serializable {

  private String isbn;
  private String title;

  @Override public String toString() {
    return String.format("Book { isbn='%s' title='%s'}", isbn, title);
  }
}
