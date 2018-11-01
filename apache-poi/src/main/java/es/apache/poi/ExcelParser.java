package es.apache.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Getter;

/**
 * Attention! There is a difference btw. excel files < 2007 and > 2007. Look the usage of POI for
 * both.
 *
 * @author msanchez
 * @since Oct 19, 2017 - 1.0.0
 */
public class ExcelParser {
  
  @Getter
  private FileInputStream inputStream;

  private Workbook workbook;

  @Getter
  private String output;

  /**
   * Saves the excel file contents into a FileInputStream.
   *
   * @param fileName
   *          Excel file which we want to parse.
   * @author msanchez
   * @since Oct 19, 2017
   */
  public void saveFileIntoStream(final String fileName) {
    try {
      final URL resource = this.getClass().getResource(fileName);
      final File file = new File(resource.toURI());
      this.inputStream = new FileInputStream(file);
    } catch (final Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Parses the contents of the file loaded into this parser instance.
   *
   * @throws IOException
   * @author msanchez
   * @since Oct 19, 2017 - 1.0.0
   */
  public void parseExcel() throws IOException {
    final Sheet firstSheet = this.setUp();
    this.workRowsInSheet(firstSheet);
    this.tearDown();
  }

  private Sheet setUp() throws IOException {
    this.output = new String();
    this.workbook = new XSSFWorkbook(this.inputStream);
    return this.workbook.getSheetAt(0); // Hardcoded! must change this so it parses all the sheets.
  }

  private void workRowsInSheet(final Sheet sheet) {
    for (final Row excelRow : sheet) {
      this.workSingleRow(excelRow);
    }
  }

  private void workSingleRow(final Row row) {
    for (final Cell cell : row) {
      this.workSingleCell(cell);
    }
    this.output += "\n";
  }

  private void workSingleCell(final Cell cell) {
    final String value = cell.toString();
    final String rgb = this.extractRGBColorFromCell(cell);
    this.output += value + ", " + rgb + "; ";
  }

  private String extractRGBColorFromCell(final Cell cell) {
    final Color color = cell.getCellStyle().getFillForegroundColorColor();
    return color == null ? "FFFFFFFF" : XSSFColor.toXSSFColor(color).getARGBHex();
  }

  private void tearDown() throws IOException {
    this.workbook.close();
  }
}
