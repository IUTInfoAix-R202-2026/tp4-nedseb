package fr.univ_amu.iut.exercice6;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Service qui surveille des événements : il les inscrit au {@link JournalActivite} (partagé) et les
 * relaie à un {@link Notifieur}.
 *
 * <p>Deux choses intéressantes à l'injection :
 *
 * <ul>
 *   <li>{@code @Named("console")} : parmi les implémentations de {@link Notifieur}, Guice injecte
 *       précisément celle liée au nom "console" dans {@link Exercice6Module} ;
 *   <li>{@link JournalActivite} étant un singleton, ce service partage le même journal que tous les
 *       autres composants de l'application.
 * </ul>
 */
public class ServiceSurveillance {

  private final Notifieur notifieur;
  private final JournalActivite journal;

  @Inject
  public ServiceSurveillance(@Named("console") Notifieur notifieur, JournalActivite journal) {
    this.notifieur = notifieur;
    this.journal = journal;
  }

  public void signaler(String evenement) {
    journal.enregistrer(evenement);
    notifieur.notifier(evenement);
  }
}
