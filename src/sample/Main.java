package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Stage stage;

  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("SelectTable.fxml"));
    primaryStage.setTitle("SELECT TABLE");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

    primaryStage.setResizable(false);

    //Spot bugs reported this as dodgy code
    stage = primaryStage;
  }

  /**
   * Change the primary stage's scene
   *
   * @param title title of the stage
   * @param root root of the scene
   */
  public static void changeScene(String title, Parent root){
    stage.setTitle(title);
    stage.setScene(new Scene(root));
  }

  public static void main(String[] args) {
    launch(args);
  }
}
