package fr.univ_amu.iut.exercice7;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée du capstone : l'écran de vérification d'une nuit d'enregistrement, en MVVM complet
 * câblé par Guice.
 *
 * <p>Mêmes ingrédients que les exercices précédents : un injecteur créé à partir d'un module,
 * branché comme fabrique de contrôleurs sur le FXMLLoader. Toute la chaîne Contrôleur -> ViewModel
 * -> ServiceNuits est résolue automatiquement.
 */
public class Qualification extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Injector injector = Guice.createInjector(new NuitModule());

    FXMLLoader loader = new FXMLLoader(getClass().getResource("QualificationView.fxml"));
    loader.setControllerFactory(injector::getInstance);
    Parent racine = loader.load();

    stage.setTitle("VigieChiro - Vérifier l'enregistrement");
    stage.setScene(new Scene(racine));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
