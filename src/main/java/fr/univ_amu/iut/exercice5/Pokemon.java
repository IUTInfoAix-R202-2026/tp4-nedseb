package fr.univ_amu.iut.exercice5;

/**
 * Modèle de l'exercice 5 : un Pokémon.
 *
 * <p>C'est un {@code record} : un porteur de données immuable, parfait pour un modèle simple. Les
 * accesseurs s'appellent {@code numero()}, {@code nom()}, {@code type()}.
 */
public record Pokemon(int numero, String nom, String type) {}
