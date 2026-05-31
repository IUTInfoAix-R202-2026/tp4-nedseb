package fr.univ_amu.iut.exercice7;

/**
 * Service fournissant une nuit de capture à vérifier.
 *
 * <p>Interface volontairement abstraite : en production, l'implémentation lirait les séquences sur
 * le disque (voir TP5 et persistance). Pour le TP, {@link ServiceNuitsDemo} génère une nuit
 * factice. Le ViewModel ne dépend que de cette interface : Guice fournit l'implémentation.
 */
public interface ServiceNuits {

  NuitVerification chargerNuit();
}
