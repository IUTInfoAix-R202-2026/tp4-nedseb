package fr.univ_amu.iut.bonus8;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel du bonus 8 : un générateur de mème.
 *
 * <p>L'utilisateur saisit un texte du haut et un texte du bas ; le mème les affiche en MAJUSCULES
 * (convention du genre). Le ViewModel expose les textes saisis (lecture/écriture) et leurs versions
 * majuscules <b>dérivées</b> (lecture seule), que la vue dessinera sur un {@code Canvas}.
 *
 * <p>Aucune dépendance JavaFX d'interface : la logique de transformation reste testable en JUnit.
 */
public class MemeViewModel {

  private final StringProperty texteHaut = new SimpleStringProperty("");
  private final StringProperty texteBas = new SimpleStringProperty("");
  private final StringProperty texteHautAffiche = new SimpleStringProperty();
  private final StringProperty texteBasAffiche = new SimpleStringProperty();

  public MemeViewModel() {
    // TODO bonus 8 : lier les versions affichées (MAJUSCULES) aux textes saisis.
    // Astuce : Bindings.createStringBinding(() -> texteHaut.get().toUpperCase(Locale.FRENCH),
    //          texteHaut).
  }

  public StringProperty texteHautProperty() {
    return texteHaut;
  }

  public StringProperty texteBasProperty() {
    return texteBas;
  }

  public ReadOnlyStringProperty texteHautAfficheProperty() {
    return texteHautAffiche;
  }

  public ReadOnlyStringProperty texteBasAfficheProperty() {
    return texteBasAffiche;
  }
}
