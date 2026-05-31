package fr.univ_amu.iut.bonus8;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée du bonus 8.
 *
 * <p>{@link MemeViewModel} est une classe concrète sans dépendance : Guice la construit sans module
 * (comme à l'exercice 5).
 */
public class MemeApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Injector injector = Guice.createInjector();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("MemeView.fxml"));
    loader.setControllerFactory(injector::getInstance);
    Parent racine = loader.load();

    stage.setTitle("Bonus 8 - Générateur de mème");
    stage.setScene(new Scene(racine));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
