package fr.univ_amu.iut.exercice2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Modèle de l'exercice 2 : un compteur d'entiers.
 *
 * <p>Ce modèle utilise une {@link IntegerProperty} : une propriété observable fait partie de {@code
 * javafx.base} et n'est PAS un composant d'interface. Un modèle MVVM a donc parfaitement le droit
 * d'exposer des propriétés observables ; ce qui lui est interdit, c'est de connaître des composants
 * graphiques ({@code Label}, {@code Button}...).
 *
 * <p>Vous avez déjà croisé ce compteur au TP1 et au TP2 : ici il devient le modèle d'une
 * architecture MVVM.
 */
public class Compteur {

  private final IntegerProperty valeur = new SimpleIntegerProperty(0);

  public IntegerProperty valeurProperty() {
    return valeur;
  }

  public int getValeur() {
    return valeur.get();
  }

  public void incrementer() {
    valeur.set(valeur.get() + 1);
  }

  public void decrementer() {
    valeur.set(valeur.get() - 1);
  }

  public void reinitialiser() {
    valeur.set(0);
  }
}
