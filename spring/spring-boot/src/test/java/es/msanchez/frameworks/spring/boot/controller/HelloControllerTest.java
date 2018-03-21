package es.msanchez.frameworks.spring.boot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloController.class, secure = false)
public class HelloControllerTest {

  @Autowired
  private HelloController controller;

  @Before
  public void setUp() {
  }

  @Test
  public void givenMocksWhenGetThenObtainGreetings() {
    // Given

    // When

    // Then
  }

}
