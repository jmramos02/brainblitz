package ph.edu.ue.rnd.brainblitz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ph.edu.ue.rnd.brainblitz.manager.UserManager;
import ph.edu.ue.rnd.brainblitz.manager.UserManager;
import ph.edu.ue.rnd.brainblitz.model.User;
import ph.edu.ue.rnd.brainblitz.utils.QuizConnection;

public class LoginController implements Initializable {

    @FXML
    Button loginButton;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Label outLabel;

    @FXML
    UserManager userManager = new UserManager();
    @FXML
    public void validateLogin(ActionEvent event) throws SQLException, IOException {
        outLabel.setText("");
        if (usernameField.getText().trim().length() == 0 || passwordField.getText().trim().length() == 0) {
            outLabel.setText("Please complete all fields");
        }else{
            User user = new User();
            user.setUsername(usernameField.getText());
            user.setPassword(user.getPassword());
            if(userManager.validateLogin(user)){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.hide();
                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setTitle("Admin Dashboard");
                stage.setScene(scene);
                stage.show();
            }else{
                outLabel.setText("Invalid Login Credentials");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}