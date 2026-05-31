package fr.univ_amu.iut.exercice6;

/**
 * Implémentation silencieuse : n'affiche rien (utile par exemple en mode "ne pas déranger"). Sert à
 * illustrer qu'une même interface peut avoir plusieurs implémentations interchangeables.
 */
public class NotifieurSilencieux implements Notifieur {

  @Override
  public void notifier(String message) {
    // Volontairement vide : aucune notification.
  }
}
