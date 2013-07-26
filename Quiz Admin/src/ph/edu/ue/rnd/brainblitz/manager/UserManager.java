
package ph.edu.ue.rnd.brainblitz.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import ph.edu.ue.rnd.brainblitz.model.User;
import ph.edu.ue.rnd.brainblitz.utils.QuizConnection;
import ph.edu.ue.rnd.brainblitz.utils.QuizUpdateConnection;

public class UserManager {
    QuizConnection connection = new QuizConnection();
    QuizUpdateConnection conn = new QuizUpdateConnection();
    public void add(User user){
        conn.execute("INSERT INTO User(username,password,role,login_status,score) VALUES('" + user.getUsername() + "','" + user.getPassword() + "', " + user.getRole() + ",0,0)");
    }
    
    public void edit(User user){
        conn.execute("UPDATE User SET username = '" + user.getId() + "', password = '" + user.getPassword() + "', role = " + user.getRole() +  ", login_status = " + user.getLoginStatus() + ", score = " + user.getScore() + " WHERE id = " + user.getId());
    }
    
    public void delete(User user){
        conn.execute("DELETE FROM User WHERE id = " + user.getId());
    }
    
    public ResultSet search(User user){
        return connection.execute("SELECT * FROM User WHERE id = " + user.getId() + " OR username = '" + user.getUsername() +  "'");
    }
    public ResultSet getAll(){
        return connection.execute("SELECT * FROM User");
    }
    
    public boolean validateLogin(User user) throws SQLException{
        ResultSet rs = search(user);
        if(rs.next()){
            if(rs.getString("username").equals(user.getUsername()) || rs.getString("password").equals(user.getPassword())){
                if(rs.getInt("login_status" )== 0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}