/**
 * @author Gregorio Lozada
 *
 * Authors contains fields based on the authors table in the books database and implements the
 * DatabaseObjects interface
 */

package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Authors implements DatabaseObjects {

  Integer authorID;
  String firstName;
  String lastName;

  Authors() {
    authorID = null;
    firstName = "";
    lastName = "";
  }

  Authors(int aID, String fN, String lN) {
    authorID = aID;
    firstName = fN;
    lastName = lN;
  }

  /***
   * Get a set of columns and set their cell value factory to the field names of Authors
   * @param tableColumns the columns to set their cell factories
   */
  public static void setTableColumns(TableColumn[] tableColumns) {
    tableColumns[0].setCellValueFactory(new PropertyValueFactory<>("authorID"));
    tableColumns[1].setCellValueFactory(new PropertyValueFactory<>("firstName"));
    tableColumns[2].setCellValueFactory(new PropertyValueFactory<>("lastName"));
  }

  //Getters and setters
  public Integer getAuthorID() {
    return authorID;
  }

  public void setAuthorID(int authorID) {
    this.authorID = authorID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Fields are set to the data passed in Order of fields to set will be: authorID, firstName,
   * lastName
   *
   * @param data the data a specific field will be set to
   * @return false if all fields have been set, true if some are still null or empty
   */
  public boolean gettingResultSetData(String data) {
    if (authorID == null) {
      authorID = Integer.parseInt(data);
      return true;
    } else if (firstName.equals("")) {
      firstName = data;
      return true;
    } else {
      lastName = data;
      return false;
    }
  }
}
