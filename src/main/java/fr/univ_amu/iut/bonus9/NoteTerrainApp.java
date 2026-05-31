package fr.univ_amu.iut.bonus9;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Point d'entrée du bonus 9. */
public class NoteTerrainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Injector injector = Guice.createInjector();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("NoteTerrainView.fxml"));
    loader.setControllerFactory(injector::getInstance);
    Parent racine = loader.load();

    stage.setTitle("Bonus 9 - Note de terrain");
    stage.setScene(new Scene(racine));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
