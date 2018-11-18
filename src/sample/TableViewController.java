/**
 * @author Gregorio Lozada
 *
 * This is the controller for the TableScreen scene. Shows the table the user selected to view from
 * the SelectTable screen, as well as return to said screen to select a new table to view.
 */

package sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.ResizeFeatures;
import javafx.util.Callback;

public class TableViewController {

  @FXML
  private TableView<?> tableView;

  private ArrayList<DatabaseObjects> databaseObjects = new ArrayList<>();

  private ObservableList observableList = FXCollections.observableArrayList();

  private TableColumn[] tableColumns;

  @FXML
  private void initialize() {
    final String DATABASE_URL = "jdbc:derby:lib\\books";
    final String SELECT_QUERY;

    //Depending on what table the user wants to see set the SELECT_QUERY
    switch (Main.tableToShow) {
      case AUTHORS:
        SELECT_QUERY = "SELECT authorID, firstName, lastName FROM authors";
        break;
      case TITLES:
        SELECT_QUERY = "SELECT isbn, title, editionNumber, copyright FROM titles";
        break;
      default:
        SELECT_QUERY = "";
        break;
    }

    // use try-with-resources to connect to and query the database
    try (Connection connection = DriverManager.getConnection(DATABASE_URL, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
      // get ResultSet's meta data
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();

      //Set the size of tableColumns to the number of columns in resultSet
      tableColumns = new TableColumn[numberOfColumns];

      // set the names of the table columns to the names of the columns in the ResultSet
      for (int i = 1; i <= numberOfColumns; i++) {
        tableColumns[i - 1] = new TableColumn(metaData.getColumnName(i));
      }

      //Add all table columns to the table view
      tableView.getColumns().addAll(tableColumns);

      //Set databaseObject depending on what table will be shown
      DatabaseObjects databaseObject = getDataBaseObjectType();

      while (resultSet.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
          //If databaseObject is no longer getting data from the result set
          if (!databaseObject.gettingResultSetData(resultSet.getString(i))) {
            //Add databaseObject to the databaseObjects list
            databaseObjects.add(databaseObject);
            //Set databaseObject to a new object
            databaseObject = getDataBaseObjectType();
          }
        }
      }

      //Add all database objects to the observable list
      observableList.addAll(databaseObjects);

      //Set the table view's columns depending on what table to show
      switch (Main.tableToShow) {
        case AUTHORS:
          Authors.setTableColumns(tableColumns);
          break;
        case TITLES:
          Titles.setTableColumns(tableColumns);
          break;
      }

      //Set tableView's items
      tableView.setItems(observableList);
      //Set tableView's resize policy to false aka no resizing table
      tableView.setColumnResizePolicy(new Callback<ResizeFeatures, Boolean>() {
        @Override
        public Boolean call(ResizeFeatures param) {
          return false;
        }
      });
    } // AutoCloseable objects' close methods are called now
    catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  @FXML
  private void onSelectTableButtonClicked() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("SelectTable.fxml"));
    Main.stage.setTitle("SELECT TABLE");
    Main.stage.setScene(new Scene(root));
  }

  /**
   * Depending on what table user wants to see return a new object that implements DatabaseObjects
   *
   * @return new databaseObjects object
   */
  private DatabaseObjects getDataBaseObjectType() {
    switch (Main.tableToShow) {
      case AUTHORS:
        return new Authors();
      case TITLES:
        return new Titles();
      default:
        return null;
    }
  }
}

