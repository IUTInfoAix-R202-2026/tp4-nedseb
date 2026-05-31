package fr.univ_amu.iut.exercice5;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Service de données de l'exercice 5 : le "Pokédex national".
 *
 * <p>Joue le rôle d'une source de données (l'équivalent d'un DAO du TP5, mais en mémoire). Comme la
 * classe possède un constructeur sans argument, Guice sait la construire sans qu'on ait besoin de
 * déclarer un quelconque binding : on ne déclare des bindings que pour relier une <b>interface</b>
 * à une implémentation (cf. exercice 4).
 *
 * <p>Le catalogue contient les 151 Pokémon de la première génération. {@link #tousLesPokemons()}
 * renvoie un petit sous-ensemble "déjà capturé" (l'état de départ de l'écran) ; {@link
 * #chercherParNom(String)} permet d'en retrouver n'importe lequel par son nom, pour l'ajouter.
 */
public class PokemonService {

  private static final List<Pokemon> CATALOGUE =
      List.of(
          new Pokemon(1, "Bulbizarre", "Plante"),
          new Pokemon(2, "Herbizarre", "Plante"),
          new Pokemon(3, "Florizarre", "Plante"),
          new Pokemon(4, "Salamèche", "Feu"),
          new Pokemon(5, "Reptincel", "Feu"),
          new Pokemon(6, "Dracaufeu", "Feu"),
          new Pokemon(7, "Carapuce", "Eau"),
          new Pokemon(8, "Carabaffe", "Eau"),
          new Pokemon(9, "Tortank", "Eau"),
          new Pokemon(10, "Chenipan", "Insecte"),
          new Pokemon(11, "Chrysacier", "Insecte"),
          new Pokemon(12, "Papilusion", "Insecte"),
          new Pokemon(13, "Aspicot", "Insecte"),
          new Pokemon(14, "Coconfort", "Insecte"),
          new Pokemon(15, "Dardargnan", "Insecte"),
          new Pokemon(16, "Roucool", "Normal"),
          new Pokemon(17, "Roucoups", "Normal"),
          new Pokemon(18, "Roucarnage", "Normal"),
          new Pokemon(19, "Rattata", "Normal"),
          new Pokemon(20, "Rattatac", "Normal"),
          new Pokemon(21, "Piafabec", "Normal"),
          new Pokemon(22, "Rapasdepic", "Normal"),
          new Pokemon(23, "Abo", "Poison"),
          new Pokemon(24, "Arbok", "Poison"),
          new Pokemon(25, "Pikachu", "Électrik"),
          new Pokemon(26, "Raichu", "Électrik"),
          new Pokemon(27, "Sabelette", "Sol"),
          new Pokemon(28, "Sablaireau", "Sol"),
          new Pokemon(29, "Nidoran F", "Poison"),
          new Pokemon(30, "Nidorina", "Poison"),
          new Pokemon(31, "Nidoqueen", "Poison"),
          new Pokemon(32, "Nidoran M", "Poison"),
          new Pokemon(33, "Nidorino", "Poison"),
          new Pokemon(34, "Nidoking", "Poison"),
          new Pokemon(35, "Mélofée", "Fée"),
          new Pokemon(36, "Mélodelfe", "Fée"),
          new Pokemon(37, "Goupix", "Feu"),
          new Pokemon(38, "Feunard", "Feu"),
          new Pokemon(39, "Rondoudou", "Normal"),
          new Pokemon(40, "Grodoudou", "Normal"),
          new Pokemon(41, "Nosferapti", "Poison"),
          new Pokemon(42, "Nosferalto", "Poison"),
          new Pokemon(43, "Mystherbe", "Plante"),
          new Pokemon(44, "Ortide", "Plante"),
          new Pokemon(45, "Rafflesia", "Plante"),
          new Pokemon(46, "Paras", "Insecte"),
          new Pokemon(47, "Parasect", "Insecte"),
          new Pokemon(48, "Mimitoss", "Insecte"),
          new Pokemon(49, "Aéromite", "Insecte"),
          new Pokemon(50, "Taupiqueur", "Sol"),
          new Pokemon(51, "Triopikeur", "Sol"),
          new Pokemon(52, "Miaouss", "Normal"),
          new Pokemon(53, "Persian", "Normal"),
          new Pokemon(54, "Psykokwak", "Eau"),
          new Pokemon(55, "Akwakwak", "Eau"),
          new Pokemon(56, "Férosinge", "Combat"),
          new Pokemon(57, "Colossinge", "Combat"),
          new Pokemon(58, "Caninos", "Feu"),
          new Pokemon(59, "Arcanin", "Feu"),
          new Pokemon(60, "Ptitard", "Eau"),
          new Pokemon(61, "Têtarte", "Eau"),
          new Pokemon(62, "Tartard", "Eau"),
          new Pokemon(63, "Abra", "Psy"),
          new Pokemon(64, "Kadabra", "Psy"),
          new Pokemon(65, "Alakazam", "Psy"),
          new Pokemon(66, "Machoc", "Combat"),
          new Pokemon(67, "Machopeur", "Combat"),
          new Pokemon(68, "Mackogneur", "Combat"),
          new Pokemon(69, "Chétiflor", "Plante"),
          new Pokemon(70, "Boustiflor", "Plante"),
          new Pokemon(71, "Empiflor", "Plante"),
          new Pokemon(72, "Tentacool", "Eau"),
          new Pokemon(73, "Tentacruel", "Eau"),
          new Pokemon(74, "Racaillou", "Roche"),
          new Pokemon(75, "Gravalanch", "Roche"),
          new Pokemon(76, "Grolem", "Roche"),
          new Pokemon(77, "Ponyta", "Feu"),
          new Pokemon(78, "Galopa", "Feu"),
          new Pokemon(79, "Ramoloss", "Eau"),
          new Pokemon(80, "Flagadoss", "Eau"),
          new Pokemon(81, "Magnéti", "Électrik"),
          new Pokemon(82, "Magnéton", "Électrik"),
          new Pokemon(83, "Canarticho", "Normal"),
          new Pokemon(84, "Doduo", "Normal"),
          new Pokemon(85, "Dodrio", "Normal"),
          new Pokemon(86, "Otaria", "Eau"),
          new Pokemon(87, "Lamantine", "Eau"),
          new Pokemon(88, "Tadmorv", "Poison"),
          new Pokemon(89, "Grotadmorv", "Poison"),
          new Pokemon(90, "Kokiyas", "Eau"),
          new Pokemon(91, "Crustabri", "Eau"),
          new Pokemon(92, "Fantominus", "Spectre"),
          new Pokemon(93, "Spectrum", "Spectre"),
          new Pokemon(94, "Ectoplasma", "Spectre"),
          new Pokemon(95, "Onix", "Roche"),
          new Pokemon(96, "Soporifik", "Psy"),
          new Pokemon(97, "Hypnomade", "Psy"),
          new Pokemon(98, "Krabby", "Eau"),
          new Pokemon(99, "Krabboss", "Eau"),
          new Pokemon(100, "Voltorbe", "Électrik"),
          new Pokemon(101, "Électrode", "Électrik"),
          new Pokemon(102, "Noeunoeuf", "Plante"),
          new Pokemon(103, "Noadkoko", "Plante"),
          new Pokemon(104, "Osselait", "Sol"),
          new Pokemon(105, "Ossatueur", "Sol"),
          new Pokemon(106, "Kicklee", "Combat"),
          new Pokemon(107, "Tygnon", "Combat"),
          new Pokemon(108, "Excelangue", "Normal"),
          new Pokemon(109, "Smogo", "Poison"),
          new Pokemon(110, "Smogogo", "Poison"),
          new Pokemon(111, "Rhinocorne", "Sol"),
          new Pokemon(112, "Rhinoféros", "Sol"),
          new Pokemon(113, "Leveinard", "Normal"),
          new Pokemon(114, "Saquedeneu", "Plante"),
          new Pokemon(115, "Kangourex", "Normal"),
          new Pokemon(116, "Hypotrempe", "Eau"),
          new Pokemon(117, "Hypocéan", "Eau"),
          new Pokemon(118, "Poissirène", "Eau"),
          new Pokemon(119, "Poissoroy", "Eau"),
          new Pokemon(120, "Stari", "Eau"),
          new Pokemon(121, "Staross", "Eau"),
          new Pokemon(122, "M. Mime", "Psy"),
          new Pokemon(123, "Insécateur", "Insecte"),
          new Pokemon(124, "Lippoutou", "Glace"),
          new Pokemon(125, "Élektek", "Électrik"),
          new Pokemon(126, "Magmar", "Feu"),
          new Pokemon(127, "Scarabrute", "Insecte"),
          new Pokemon(128, "Tauros", "Normal"),
          new Pokemon(129, "Magicarpe", "Eau"),
          new Pokemon(130, "Léviator", "Eau"),
          new Pokemon(131, "Lokhlass", "Eau"),
          new Pokemon(132, "Métamorph", "Normal"),
          new Pokemon(133, "Évoli", "Normal"),
          new Pokemon(134, "Aquali", "Eau"),
          new Pokemon(135, "Voltali", "Électrik"),
          new Pokemon(136, "Pyroli", "Feu"),
          new Pokemon(137, "Porygon", "Normal"),
          new Pokemon(138, "Amonita", "Roche"),
          new Pokemon(139, "Amonistar", "Roche"),
          new Pokemon(140, "Kabuto", "Roche"),
          new Pokemon(141, "Kabutops", "Roche"),
          new Pokemon(142, "Ptéra", "Roche"),
          new Pokemon(143, "Ronflex", "Normal"),
          new Pokemon(144, "Artikodin", "Glace"),
          new Pokemon(145, "Électhor", "Électrik"),
          new Pokemon(146, "Sulfura", "Feu"),
          new Pokemon(147, "Minidraco", "Dragon"),
          new Pokemon(148, "Draco", "Dragon"),
          new Pokemon(149, "Dracolosse", "Dragon"),
          new Pokemon(150, "Mewtwo", "Psy"),
          new Pokemon(151, "Mew", "Psy"));

  /** Numéros des Pokémon déjà présents au démarrage de l'écran. */
  private static final List<Integer> DEPART = List.of(1, 4, 7, 25, 133, 143);

  /** Les Pokémon déjà capturés au démarrage (un sous-ensemble du catalogue). */
  public List<Pokemon> tousLesPokemons() {
    return CATALOGUE.stream().filter(p -> DEPART.contains(p.numero())).toList();
  }

  /**
   * Cherche un Pokémon du catalogue par son nom, sans tenir compte de la casse ni des accents
   * (ainsi "salameche" trouve "Salamèche"). Renvoie {@link Optional#empty()} si aucun ne
   * correspond.
   */
  public Optional<Pokemon> chercherParNom(String nom) {
    if (nom == null || nom.isBlank()) {
      return Optional.empty();
    }
    String cible = normaliser(nom);
    return CATALOGUE.stream().filter(p -> normaliser(p.nom()).equals(cible)).findFirst();
  }

  private static String normaliser(String s) {
    String sansAccents = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
    return sansAccents.toLowerCase(Locale.FRENCH).trim();
  }
}
