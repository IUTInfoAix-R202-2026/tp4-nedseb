package fr.univ_amu.iut.exercice7;

import java.time.LocalTime;
import java.util.Random;

/**
 * Implémentation de démonstration de {@link ServiceNuits}.
 *
 * <p>Génère une nuit factice de 10 séquences réparties régulièrement entre 20h00 et 06h00 (la nuit
 * type d'enregistrement d'un PR), avec des fréquences pseudo-aléatoires entre 20 et 120 kHz (bande
 * utile pour les chiroptères). Le seed est fixe pour rendre la démo et les tests reproductibles.
 */
public class ServiceNuitsDemo implements ServiceNuits {

  private static final int NOMBRE_SEQUENCES = 10;

  @Override
  public NuitVerification chargerNuit() {
    NuitVerification nuit = new NuitVerification();
    Random rng = new Random(42L);
    int debutMin = 20 * 60; // 20h00
    int finMin = 30 * 60; // 06h00 le lendemain (1800 min)
    int delta = (finMin - debutMin) / (NOMBRE_SEQUENCES - 1);
    for (int i = 0; i < NOMBRE_SEQUENCES; i++) {
      int min = (debutMin + i * delta) % (24 * 60);
      LocalTime t = LocalTime.of(min / 60, min % 60);
      double freq = 20.0 + rng.nextDouble() * 100.0;
      // On fait tourner 6 enregistrements de démonstration : deux lignes voisines
      // pointent toujours vers des fichiers différents (le composant recharge à la sélection).
      String audio = "seq-" + ((i % 6) + 1) + ".wav";
      nuit.getSequences().add(new Sequence(t, freq, 5, audio));
    }
    return nuit;
  }
}
