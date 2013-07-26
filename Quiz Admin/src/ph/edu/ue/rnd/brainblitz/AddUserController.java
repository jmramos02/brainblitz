package ph.edu.ue.rnd.brainblitz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ph.edu.ue.rnd.brainblitz.manager.UserManager;
import ph.edu.ue.rnd.brainblitz.model.User;

public class AddUserController implements Initializable {

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    ComboBox roleComboBox;
    @FXML
    Button saveButton;
    @FXML
    Button cancelButton;
    @FXML
    Label out;

    @FXML
    private void closeButtonHandler(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        if (usernameTextField.getText().trim().length() == 0 || passwordField.getText().trim().length() == 0) {
            out.setText("Please complete all fields");
        } else {
            User user = new User();
            user.setUsername(usernameTextField.getText());
            user.setPassword(passwordField.getText());
            if (roleComboBox.getValue().toString().equals("Admin")) {
                user.setRole(0);
            } else if (roleComboBox.getValue().toString().equals("Quiz Master")) {
                user.setRole(1);
            } else {
                user.setRole(2);
            }
            UserManager manager = new UserManager();
            manager.add(user);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            AdminDashboardController adbc = (AdminDashboardController) loader.getController();
            adbc.refreshTable();
            stage.close();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
