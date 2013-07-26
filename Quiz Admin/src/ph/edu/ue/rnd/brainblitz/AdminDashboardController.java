/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.ue.rnd.brainblitz;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ph.edu.ue.rnd.brainblitz.manager.UserManager;
import ph.edu.ue.rnd.brainblitz.model.User;

/**
 * FXML Controller class
 *
 * @author Default
 */
public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    static TableView userTable;
    @FXML
    static TableColumn<User, String> id;
    @FXML
    static TableColumn<User, String> username;
    @FXML
    static TableColumn<User, String> loginStatus;
    @FXML
    static TableColumn<User, String> role;
    @FXML
    static TableColumn<User, String> score;
    static UserManager um = new UserManager();

    @FXML
    public void handleAddButton(ActionEvent evt) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void deleteUser(){
        refreshTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        loginStatus.setCellValueFactory(new PropertyValueFactory<User, String>("loginStatus"));
        score.setCellValueFactory(new PropertyValueFactory<User, String>("score"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        try {
            userTable.getItems().setAll(initializeData());
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<User> initializeData() throws SQLException {
        ResultSet rs = um.getAll();
        List<User> users = new ArrayList();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setScore(rs.getInt("score"));
            user.setRole(rs.getInt("role"));
            users.add((user));
        }
        return users;
    }

    public static void refreshTable() {
        id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        loginStatus.setCellValueFactory(new PropertyValueFactory<User, String>("loginStatus"));
        score.setCellValueFactory(new PropertyValueFactory<User, String>("score"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        try {
            userTable.getItems().setAll(initializeData());
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
