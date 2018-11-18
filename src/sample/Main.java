package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public enum TABLES {
    AUTHORS,
    TITLES,
  }

  public static TABLES tableToShow;

  public static Stage stage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    tableToShow = TABLES.AUTHORS;

    Parent root = FXMLLoader.load(getClass().getResource("SelectTable.fxml"));
    primaryStage.setTitle("SELECT TABLE");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

    primaryStage.setResizable(false);

    stage = primaryStage;
  }


  public static void main(String[] args) {
    launch(args);
  }
}
