package fr.univ_amu.iut.exercice5;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée de l'exercice 5.
 *
 * <p>Ici, aucun module Guice n'est nécessaire : {@link PokemonService} et {@link PokemonViewModel}
 * sont des classes concrètes que Guice sait construire seul (constructeur sans argument ou
 * {@code @Inject}). On ne déclare un binding que pour relier une interface à une implémentation
 * (exercice 4). On crée donc l'injecteur SANS module.
 */
public class PokemonApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Injector injector = Guice.createInjector();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("PokemonView.fxml"));
    loader.setControllerFactory(injector::getInstance);
    Parent racine = loader.load();

    stage.setTitle("Exercice 5 - Pokedex MVVM");
    stage.setScene(new Scene(racine));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
