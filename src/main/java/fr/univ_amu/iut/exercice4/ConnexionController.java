package fr.univ_amu.iut.exercice4;

import com.google.inject.Inject;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Contrôleur de vue de l'exercice 4.
 *
 * <p>Différence clef avec l'exercice 3 : le ViewModel n'est plus passé par le constructeur à la
 * main, il est <b>injecté par Guice</b> dans le champ annoté {@code @Inject}.
 *
 * <p>Deux mécanismes d'injection cohabitent ici, et c'est normal :
 *
 * <ul>
 *   <li>{@code @Inject} (Guice) remplit {@code viewModel} quand le {@code controllerFactory}
 *       construit le contrôleur ;
 *   <li>{@code @FXML} (FXMLLoader) remplit les champs d'interface au chargement du FXML, puis
 *       appelle {@code initialize()}.
 * </ul>
 */
public class ConnexionController {

  @Inject private ConnexionViewModel viewModel;

  @FXML private TextField champIdentifiant;
  @FXML private PasswordField champMotDePasse;
  @FXML private Button boutonValider;
  @FXML private Label labelStatut;

  @FXML
  private void initialize() {
    champIdentifiant.textProperty().bindBidirectional(viewModel.identifiantProperty());
    champMotDePasse.textProperty().bindBidirectional(viewModel.motDePasseProperty());
    labelStatut.textProperty().bind(viewModel.statutProperty());
    boutonValider.disableProperty().bind(viewModel.validableProperty().not());

    // Affordance (Nielsen #5) : bouton vert et actionnable quand le formulaire
    // est validable, gris sinon (même property que disableProperty).
    String styleActif =
        "-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;";
    String styleInactif = "-fx-background-color: #cccccc; -fx-text-fill: #777777; -fx-opacity: 1;";
    boutonValider
        .styleProperty()
        .bind(
            Bindings.when(viewModel.validableProperty()).then(styleActif).otherwise(styleInactif));
  }

  @FXML
  private void surValider() {
    viewModel.connecterCommand();
  }
}
