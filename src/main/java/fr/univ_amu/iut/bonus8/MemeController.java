package fr.univ_amu.iut.bonus8;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Contrôleur de vue du bonus 8.
 *
 * <p>Le contrôleur lie les deux champs de saisie au ViewModel (bidirectionnel) et redessine le
 * {@code Canvas} chaque fois qu'un texte affiché change : d'abord l'<b>image de fond</b> (le
 * template du mème), puis les deux légendes en MAJUSCULES, <b>blanches cernées de noir</b> (le look
 * classique des mèmes). Le dessin (image, polices, couleurs) est un détail de présentation : il a
 * sa place dans la vue, pas dans le ViewModel.
 */
public class MemeController {

  @Inject private MemeViewModel viewModel;

  @FXML private TextField champHaut;
  @FXML private TextField champBas;
  @FXML private Canvas canvas;

  /** Image de fond du mème (le template), chargée une fois depuis le paquet. */
  private final Image fond = new Image(getClass().getResourceAsStream("meme-fond.jpg"));

  @FXML
  private void initialize() {
    champHaut.textProperty().bindBidirectional(viewModel.texteHautProperty());
    champBas.textProperty().bindBidirectional(viewModel.texteBasProperty());

    // Redessiner à chaque changement des textes affichés.
    viewModel.texteHautAfficheProperty().addListener((o, a, n) -> dessiner());
    viewModel.texteBasAfficheProperty().addListener((o, a, n) -> dessiner());
    dessiner();
  }

  private void dessiner() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    double largeur = canvas.getWidth();
    double hauteur = canvas.getHeight();

    // 1. Le template du mème en fond.
    gc.drawImage(fond, 0, 0, largeur, hauteur);

    // 2. Les légendes : MAJUSCULES, centrées, blanches cernées de noir.
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setFont(Font.font("Impact", FontWeight.BOLD, 34));
    gc.setLineWidth(3);
    gc.setStroke(Color.BLACK);
    gc.setFill(Color.WHITE);

    gc.setTextBaseline(VPos.TOP);
    ecrire(gc, viewModel.texteHautAfficheProperty().get(), largeur / 2, 12, largeur - 20);

    gc.setTextBaseline(VPos.BOTTOM);
    ecrire(gc, viewModel.texteBasAfficheProperty().get(), largeur / 2, hauteur - 12, largeur - 20);
  }

  /** Écrit un texte cerné de noir puis rempli de blanc : le contour caractéristique des mèmes. */
  private void ecrire(GraphicsContext gc, String texte, double x, double y, double maxLargeur) {
    gc.strokeText(texte, x, y, maxLargeur);
    gc.fillText(texte, x, y, maxLargeur);
  }
}
