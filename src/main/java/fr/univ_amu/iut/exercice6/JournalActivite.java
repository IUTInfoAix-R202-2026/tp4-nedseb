package fr.univ_amu.iut.exercice6;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Journal des actions de l'application, partagé par tous les composants.
 *
 * <p>Annoté {@code @Singleton} : Guice n'en crée qu'UNE seule instance pour toute l'application.
 * Tous les composants qui l'injectent partagent donc le même journal. Sans cette annotation, Guice
 * créerait une nouvelle instance à chaque injection (comportement par défaut), et les événements
 * seraient éparpillés dans des journaux distincts.
 */
@Singleton
public class JournalActivite {

  private final List<String> evenements = new ArrayList<>();

  public void enregistrer(String evenement) {
    evenements.add(evenement);
  }

  public List<String> evenements() {
    return List.copyOf(evenements);
  }

  public int taille() {
    return evenements.size();
  }
}
