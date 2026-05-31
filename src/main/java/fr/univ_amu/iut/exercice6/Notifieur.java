package fr.univ_amu.iut.exercice6;

/**
 * Service de notification de l'exercice 6.
 *
 * <p>Une même interface peut avoir plusieurs implémentations. Guice permet de choisir laquelle
 * injecter grâce à une annotation {@code @Named} (voir {@link Exercice6Module}).
 */
public interface Notifieur {

  void notifier(String message);
}
