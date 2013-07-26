/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.ue.rnd.brainblitz.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizUpdateConnection {

    public void execute(String query) {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/brainblitz?user=root");
            Statement st = conn.createStatement(); 
            st.executeUpdate(query); 
        } catch (SQLException ex) {
            Logger.getLogger(QuizConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}