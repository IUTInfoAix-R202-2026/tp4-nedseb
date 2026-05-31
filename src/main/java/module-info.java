/**
 * Module JavaFX pour le TP TP4.
 *
 * <p>Ce module exporte les paquetages nécessaires pour les exercices. Ajoutez les exports des
 * nouveaux paquetages d'exercices au fur et à mesure.
 */
open module tp4.javafx {
  // JavaFX dependencies
  requires transitive javafx.base;
  requires transitive javafx.controls;
  requires transitive javafx.graphics;
  requires transitive javafx.fxml;

  // Injection de dependances (Guice 7)
  requires com.google.guice;

  // Composant audio de la SAE 2.01 (sonogramme / spectrogramme) - integre a l'exercice 7
  requires fr.nedjar.vigiechiro.audio;

  // Export base package
  exports fr.univ_amu.iut;

// ========== EXERCICES - Ajouter les exports ici ==========
// exports fr.univ_amu.iut.exercice1;
// exports fr.univ_amu.iut.exercice2;
// ...
}
