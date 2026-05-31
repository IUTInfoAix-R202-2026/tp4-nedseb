package fr.univ_amu.iut.exercice7;

import java.time.LocalTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modèle d'une séquence d'écoute issue d'une nuit de capture VigieChiro (déjà rencontré au TP3).
 *
 * <p>Une séquence représente un extrait audio de 5 secondes ralenti ×10 (parcours utilisateur P3 du
 * brief SAE 2.01). Ses attributs sont exposés sous forme de propriétés JavaFX pour que la vue
 * puisse s'y lier directement via les colonnes du TableView.
 */
public class Sequence {

  private final ObjectProperty<LocalTime> horodatage;
  private final DoubleProperty frequenceDominanteKHz;
  private final IntegerProperty dureeSecondes;
  private final StringProperty statut;

  /** Nom de la ressource audio (WAV) associée à la séquence, ex. {@code "seq-1.wav"}. */
  private final String audioRessource;

  /** Construit une séquence avec un statut initial "À écouter". */
  public Sequence(
      LocalTime horodatage,
      double frequenceDominanteKHz,
      int dureeSecondes,
      String audioRessource) {
    this.horodatage = new SimpleObjectProperty<>(this, "horodatage", horodatage);
    this.frequenceDominanteKHz =
        new SimpleDoubleProperty(this, "frequenceDominanteKHz", frequenceDominanteKHz);
    this.dureeSecondes = new SimpleIntegerProperty(this, "dureeSecondes", dureeSecondes);
    this.statut = new SimpleStringProperty(this, "statut", "À écouter");
    this.audioRessource = audioRessource;
  }

  public ObjectProperty<LocalTime> horodatageProperty() {
    return horodatage;
  }

  public LocalTime getHorodatage() {
    return horodatage.get();
  }

  public DoubleProperty frequenceDominanteKHzProperty() {
    return frequenceDominanteKHz;
  }

  public double getFrequenceDominanteKHz() {
    return frequenceDominanteKHz.get();
  }

  public IntegerProperty dureeSecondesProperty() {
    return dureeSecondes;
  }

  public int getDureeSecondes() {
    return dureeSecondes.get();
  }

  public StringProperty statutProperty() {
    return statut;
  }

  public String getStatut() {
    return statut.get();
  }

  public void setStatut(String value) {
    statut.set(value);
  }

  /** Nom de la ressource audio associée (ex. {@code "seq-1.wav"}). */
  public String getAudioRessource() {
    return audioRessource;
  }
}
