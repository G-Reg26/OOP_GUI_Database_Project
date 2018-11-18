/**
 * @author Gregorio Lozada
 *
 * This is the controller for the SelectTable scene. The user can select which table to view from a
 * drop down menu and click a button to view said table
 */

package sample;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;

public class SelectTableController {

  public enum TABLES {
    AUTHORS,
    TITLES,
  }

  static TABLES tableToShow;

  @FXML
  private MenuButton menuButton;

  @FXML
  private void initialize() {
    tableToShow = TABLES.AUTHORS;
    menuButton.setText(tableToShow.toString());
  }

  /**
   * Set menu button text to AUTHORS if menu item is clicked
   */
  @FXML
  void AuthorsMenuItemClicked() {
    tableToShow = TABLES.AUTHORS;
    menuButton.setText("AUTHORS");
  }

  /**
   * Set menu button text to TITLES if menu item is clicked
   */
  @FXML
  void TitlesMenuItemClicked() {
    tableToShow = TABLES.TITLES;
    menuButton.setText("TITLES");
  }

  /**
   * Show the table selected
   *
   * @throws IOException
   */
  @FXML
  void onShowTableButtonClicked() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("TableScreen.fxml"));
    Main.changeScene(menuButton.getText(), root);
  }

}

