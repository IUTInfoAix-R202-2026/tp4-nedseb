package fr.univ_amu.iut.bonus9;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Contrôleur de vue du bonus 9.
 *
 * <p>Trois widgets, un seul ViewModel : la zone de saisie est bidirectionnelle, l'aperçu et le
 * compteur sont en lecture seule. Une frappe dans la zone de saisie se propage instantanément aux
 * deux autres, sans aucune ligne de code de synchronisation : tout passe par les bindings sur le
 * même ViewModel.
 */
public class NoteTerrainController {

  @Inject private NoteTerrainViewModel viewModel;

  @FXML private TextArea zoneSaisie;
  @FXML private Label apercu;
  @FXML private Label compteur;

  @FXML
  private void initialize() {
    zoneSaisie.textProperty().bindBidirectional(viewModel.noteProperty());
    apercu.textProperty().bind(viewModel.noteProperty());
    compteur.textProperty().bind(viewModel.nombreCaracteresProperty());
  }
}
