/**
 * @author Gregorio Lozada
 *
 * Titles contains fields based on the titles table in the books database and implements the
 * DatabaseObjects interface
 */

package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Titles implements DatabaseObjects {

  String isbn;
  String title;
  Integer editionNumber;
  String copyright;

  Titles() {
    isbn = "";
    title = "";
    editionNumber = null;
    copyright = "";
  }

  Titles(String isbn, String title, Integer eN, String cr) {
    this.isbn = isbn;
    this.title = title;
    editionNumber = eN;
    copyright = cr;
  }

  /***
   * Get a set of columns and set their cell value factory to the field names of Authors
   * @param tableColumns the columns to set their cell factories
   */
  public static void setTableColumns(TableColumn[] tableColumns) {
    tableColumns[0].setCellValueFactory(new PropertyValueFactory<>("isbn"));
    tableColumns[1].setCellValueFactory(new PropertyValueFactory<>("title"));
    tableColumns[2].setCellValueFactory(new PropertyValueFactory<>("editionNumber"));
    tableColumns[3].setCellValueFactory(new PropertyValueFactory<>("copyright"));
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getEditionNumber() {
    return editionNumber;
  }

  public void setEditionNumber(Integer editionNumber) {
    this.editionNumber = editionNumber;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  /**
   * Fields are set to the data passed in Order of fields to set will be: isbn, title,
   * editionNumber, copyright
   *
   * @param data the data a specific field will be set to
   * @return false if all fields have been set, true if some are still null or empty
   */
  public boolean gettingResultSetData(String data) {
    if (isbn.equals("")) {
      isbn = data;
      return true;
    } else if (title.equals("")) {
      title = data;
      return true;
    } else if (editionNumber == null) {
      editionNumber = Integer.parseInt(data);
      return true;
    } else {
      copyright = data;
      return false;
    }
  }
}
