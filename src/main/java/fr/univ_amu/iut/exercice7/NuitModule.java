package fr.univ_amu.iut.exercice7;

import com.google.inject.AbstractModule;

/**
 * Module Guice du capstone : relie le service de nuits à son implémentation de démonstration.
 *
 * <p>Comme à l'exercice 4, c'est ici (et seulement ici) qu'on choisit l'implémentation concrète.
 * Pour brancher de vraies données plus tard (TP5), il suffirait de changer cette unique ligne.
 */
public class NuitModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ServiceNuits.class).to(ServiceNuitsDemo.class);
  }
}
