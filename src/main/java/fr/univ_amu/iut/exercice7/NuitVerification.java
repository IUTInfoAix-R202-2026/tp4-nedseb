package fr.univ_amu.iut.exercice7;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Modèle agrégé d'une nuit de capture en cours de vérification (parcours P3 du brief SAE 2.01).
 *
 * <p>Une nuit contient une liste observable de {@link Sequence}, un verdict global ({@code "OK"},
 * {@code "Douteux"} ou {@code "À jeter"}) et un commentaire libre. C'est un modèle pur : il ne
 * connaît ni la vue ni le ViewModel.
 */
public class NuitVerification {

  private final ObservableList<Sequence> sequences = FXCollections.observableArrayList();
  private final StringProperty verdictGlobal = new SimpleStringProperty(this, "verdictGlobal", "");
  private final StringProperty commentaire = new SimpleStringProperty(this, "commentaire", "");

  public ObservableList<Sequence> getSequences() {
    return sequences;
  }

  public StringProperty verdictGlobalProperty() {
    return verdictGlobal;
  }

  public String getVerdictGlobal() {
    return verdictGlobal.get();
  }

  public void setVerdictGlobal(String value) {
    verdictGlobal.set(value);
  }

  public StringProperty commentaireProperty() {
    return commentaire;
  }

  public String getCommentaire() {
    return commentaire.get();
  }

  public void setCommentaire(String value) {
    commentaire.set(value);
  }
}
