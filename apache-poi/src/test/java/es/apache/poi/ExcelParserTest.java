package es.apache.poi;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExcelParserTest {
  
  private final String FILE_NAME = "excel-file.xlsx";

  @InjectMocks
  private ExcelParser parser;

  @Before
  public void setUp() {
    this.parser.saveFileIntoStream(this.FILE_NAME);
  }

  /**
   * @author msanchez
   * @since Oct 19, 2017 - 1.0.0
   */
  @Test
  @Ignore
  public void whenNeededThenShowDebugOutput() throws IOException {
    // Given

    // When
    this.parser.parseExcel();
    final String output = this.parser.getOutput();

    // Then
    assertThat(output).isNotEmpty();
    System.out.println(output);
  }

  /**
   * @author msanchez
   * @since Oct 19, 2017 - 1.0.0
   */
  @Test
  public void fileExists() {
    // Given
    final File file = new File(this.getClass().getResource(this.FILE_NAME).getPath());

    // When
    final boolean exists = file.exists();

    // Then
    assertThat(exists).isTrue();
  }

  /**
   * @author msanchez
   * @since Oct 19, 2017 - 1.0.0
   */
  @Test
  public void whenOpenFileThenInputStreamIsNotEmpty() throws IOException {
    // Given
    FileInputStream inputStream;

    // When
    inputStream = this.parser.getInputStream();

    // Then
    assertThat(inputStream.available()).isNotZero();
  }
}
