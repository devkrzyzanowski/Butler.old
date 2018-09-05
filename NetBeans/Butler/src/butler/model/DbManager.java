/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MichalKrzyzanowski
 */
public class DbManager {
    private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private Connection connection;
    
    public void startDataBase(String dbName) {
        try {
            connection = DriverManager.getConnection("jdbc:derby:" + dbName +"; shutdown=true;");
        } catch (SQLException e){
            System.err.println(e);
        }
    }
    public void stopDataBase() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
