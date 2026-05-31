#!/usr/bin/env bash
#
# Regenere les captures "Resultat attendu" des apps GUI du TP4 (branche
# solution) dans .github/assets/apercu-*.png.
#
# Outil de MAINTENANCE (enseignant) : a relancer apres modification des
# exercices, pour que les visuels du README restent a jour. Les etudiants
# n'en ont pas besoin.
#
# Prerequis : xvfb (xvfb-run) + ImageMagick (import, convert) + xdotool pour
# les captures pilotees (formulaires remplis). Tout tourne en headless ; chaque
# app est lancee via `./mvnw javafx:run` en surchargeant le mainClass, capturee,
# puis arretee.
#
# Usage (depuis la racine du TP) :
#   ./.github/assets/capture-screenshots.sh            # toutes les captures
#   ./.github/assets/capture-screenshots.sh ex5        # une seule
#   ./.github/assets/capture-screenshots.sh ex3 ex3rempli
#   Cles : lanceur ex1 ex2 ex3 ex3rempli ex4 ex4rempli ex5 ex7 bonus8 bonus9
#   (l'exercice 6 n'a pas de fenetre : pas de capture)
#
set -uo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
cd "$ROOT"
ASSETS=.github/assets
SCREEN="-screen 0 1366x900x24"
WAIT=24   # secondes : demarrage de Maven + JavaFX avant la capture

# cle -> "mainClass|fichier de sortie (sans .png)"
declare -A APP=(
  [lanceur]="tp4.javafx/fr.univ_amu.iut.App|apercu-lanceur"
  [ex1]="tp4.javafx/fr.univ_amu.iut.exercice1.MessageApp|apercu-ex1-message"
  [ex2]="tp4.javafx/fr.univ_amu.iut.exercice2.CompteurApp|apercu-ex2-compteur"
  [ex3]="tp4.javafx/fr.univ_amu.iut.exercice3.FormulaireConnexionApp|apercu-ex3-formulaire"
  [ex3rempli]="tp4.javafx/fr.univ_amu.iut.exercice3.FormulaireConnexionApp|apercu-ex3-formulaire-rempli"
  [ex4]="tp4.javafx/fr.univ_amu.iut.exercice4.ConnexionApp|apercu-ex4-connexion"
  [ex4rempli]="tp4.javafx/fr.univ_amu.iut.exercice4.ConnexionApp|apercu-ex4-connexion-rempli"
  [ex5]="tp4.javafx/fr.univ_amu.iut.exercice5.PokemonApp|apercu-ex5-pokedex"
  [ex7]="tp4.javafx/fr.univ_amu.iut.exercice7.Qualification|apercu-ex7-qualification"
  [bonus8]="tp4.javafx/fr.univ_amu.iut.bonus8.MemeApp|apercu-bonus8-meme"
  [bonus9]="tp4.javafx/fr.univ_amu.iut.bonus9.NoteTerrainApp|apercu-bonus9-note"
)
ORDER=(lanceur ex1 ex2 ex3 ex3rempli ex4 ex4rempli ex5 ex7 bonus9 bonus8)  # bonus8 en dernier (il seme/restaure une source)

# Lance une app et capture sa fenetre. $1 = mainClass, $2 = nom de sortie, $3 = cle.
# Certaines cles (ex3rempli, ex4rempli) pilotent l'app via xdotool avant la
# capture (remplir le formulaire + valider). Variables passees par
# l'environnement -> bash -c en quotes simples (pas d'echappement).
capture() {
  local main="$1" out="$2" key="${3:-}"
  MAIN="$main" OUT="$out" KEY="$key" WAITS="$WAIT" \
    xvfb-run -a --server-args="$SCREEN" bash -c '
      ./mvnw -q -Djavafx.mainClass="$MAIN" javafx:run >"/tmp/cap-$OUT.log" 2>&1 &
      M=$!
      sleep "$WAITS"
      if { [ "$KEY" = ex3rempli ] || [ "$KEY" = ex4rempli ]; } && command -v xdotool >/dev/null 2>&1; then
        # Remplit identifiant + mot de passe (compte de demo marie/chiro2026)
        # puis valide -> bouton vert actif + statut "Bienvenue marie !".
        WID=$(xdotool search --onlyvisible --name "Exercice" 2>/dev/null | head -1)
        if [ -n "$WID" ]; then
          eval "$(xdotool getwindowgeometry --shell "$WID" 2>/dev/null | grep -E "^(X|Y|WIDTH)=")"
          xdotool mousemove $((X + WIDTH/2)) $((Y + 99)) click 1; sleep 0.3
          xdotool type --delay 60 "marie"
          xdotool mousemove $((X + WIDTH/2)) $((Y + 165)) click 1; sleep 0.3
          xdotool type --delay 60 "chiro2026"
          xdotool key Tab; sleep 0.2; xdotool key space; sleep 0.7
        fi
      fi
      import -window root "/tmp/$OUT-raw.png" 2>/dev/null
      ap=$(pgrep -f "enable-native-access=javafx.graphics --module-path" | head -1)
      [ -n "$ap" ] && kill -9 "$ap" 2>/dev/null
      kill -9 "$M" 2>/dev/null
      exit 0
    '
  convert "/tmp/$out-raw.png" -trim +repage "$ASSETS/$out.png"
  echo "  $out.png  ($(identify -format '%wx%h' "$ASSETS/$out.png" 2>/dev/null))"
}

# Capture une cle (gere le cas special du bonus 8).
capture_key() {
  local key="$1" entry main out
  entry="${APP[$key]:-}"
  if [ -z "$entry" ]; then echo "Cle inconnue : $key (attendu : ${ORDER[*]})" >&2; return 1; fi
  main="${entry%%|*}"; out="${entry##*|}"
  if [ "$key" = bonus8 ]; then
    # Le Canvas est vide au demarrage : on seme un texte de demo le temps de la
    # capture (puis on restaure la source, garanti par le trap).
    local MEME=src/main/java/fr/univ_amu/iut/bonus8/MemeViewModel.java
    (
      BAK=$(mktemp); cp "$MEME" "$BAK"
      # Sauvegarde/restauration par copie (et non git checkout) : preserve
      # d'eventuelles modifs non commitees de la source.
      trap 'cp "$BAK" "$MEME"; rm -f "$BAK"; ./mvnw -q compile >/dev/null 2>&1' EXIT
      sed -i 's#texteHaut = new SimpleStringProperty("")#texteHaut = new SimpleStringProperty("Quand le PR détecte")#' "$MEME"
      sed -i 's#texteBas = new SimpleStringProperty("")#texteBas = new SimpleStringProperty("une pipistrelle")#' "$MEME"
      ./mvnw -q compile
      capture "$main" "$out" "$key"
    )
  elif [ "$key" = bonus9 ]; then
    # Le ViewModel demarre vide : on seme une note de demo le temps de la capture
    # (-> apercu en direct + compteur synchronises), puis on restaure.
    local NVM=src/main/java/fr/univ_amu/iut/bonus9/NoteTerrainViewModel.java
    (
      BAK=$(mktemp); cp "$NVM" "$BAK"
      trap 'cp "$BAK" "$NVM"; rm -f "$BAK"; ./mvnw -q compile >/dev/null 2>&1' EXIT
      sed -i 's#note = new SimpleStringProperty("")#note = new SimpleStringProperty("Pipistrelle commune vers 21h15, ciel clair.")#' "$NVM"
      ./mvnw -q compile
      capture "$main" "$out" "$key"
    )
  elif [ "$key" = ex7 ]; then
    # Pre-selectionne la 1re sequence le temps de la capture : le panneau de
    # droite se remplit (description + bouton Écouter actif), puis on restaure.
    local QC=src/main/java/fr/univ_amu/iut/exercice7/QualificationController.java
    (
      BAK=$(mktemp); cp "$QC" "$BAK"
      trap 'cp "$BAK" "$QC"; rm -f "$BAK"; ./mvnw -q compile >/dev/null 2>&1' EXIT
      sed -i 's#\(labelVerdictGlobal.textProperty().bind(viewModel.verdictGlobalLibelleProperty());\)#\1\n    tableSequences.getSelectionModel().selectFirst();#' "$QC"
      ./mvnw -q compile
      capture "$main" "$out" "$key"
    )
  elif [ "$key" = ex5 ]; then
    # Le champ de recherche est vide au demarrage : on seme un nom de demo
    # (-> bouton "Ajouter" vert/actif) le temps de la capture, puis on restaure.
    local PVM=src/main/java/fr/univ_amu/iut/exercice5/PokemonViewModel.java
    (
      BAK=$(mktemp); cp "$PVM" "$BAK"
      trap 'cp "$BAK" "$PVM"; rm -f "$BAK"; ./mvnw -q compile >/dev/null 2>&1' EXIT
      sed -i 's#recherche = new SimpleStringProperty("")#recherche = new SimpleStringProperty("Dracaufeu")#' "$PVM"
      ./mvnw -q compile
      capture "$main" "$out" "$key"
    )
  else
    capture "$main" "$out" "$key"
  fi
}

echo "Pre-compilation..."
./mvnw -q compile

if [ "$#" -ge 1 ]; then
  echo "Captures demandees : $* -> $ASSETS/"
  for k in "$@"; do capture_key "$k"; done
else
  echo "Toutes les captures -> $ASSETS/"
  for k in "${ORDER[@]}"; do capture_key "$k"; done
fi

# Filet : tuer un eventuel process d'app residuel.
pgrep -f "enable-native-access=javafx.graphics --module-path" | xargs -r kill -9 2>/dev/null
echo "Termine."
