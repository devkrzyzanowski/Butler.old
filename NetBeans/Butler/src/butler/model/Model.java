/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package butler.model;

import butler.utils.OperationHistory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Michał
 */
public class Model {
    private final String DBAddress = "jdbc:derby:EmbeddedDB; create=true;";
    private final String DBUser = "root";
    private final String DBPassword = "";
    private final String DBDriver = "org.apache.derby.jdbc.EmbeddedDriver";
    Connection con;
    
    /**
     * init Model
     */
    public Model(){
        try{
            Class.forName(DBDriver);
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try {
            con = DriverManager.getConnection(DBAddress, DBUser, DBPassword);
            //con.createStatement().execute("DROP TABLE Operations");
            //con.createStatement().execute("CREATE TABLE Operations(operation varchar(45), time TIME, date DATE, users varchar(25))");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Operations");
            stmt.close();
        } catch ( SQLException e){
            System.err.println(e);
        }
    }
    
    /**
     * 
     * @param login 
     * @param password
     * @return true on successfully connect to db or false on fail
     */
    public boolean connectToDataBase(String login, String password){
        return false;
    }
    
    /**
     * 
     * @return currently logged user
     */
    public String getNickById(){
        return null; // Tu ma zwracać Nick po ID
    }
    /**
     * encryptPassword
     * @param password 
     * private function encrypting password
     * @return encrypted password
     */
    private String encryptPassword(String password){
        //TODO create
        return null;
    }
    
    /**
     * 
     * @param userName
     * @param message
     * @return true on successfully add or false on fail
     */
    public boolean addToOperationHistory(String message, String user){
        try {
            con.createStatement().execute("INSERT INTO Operations(operation, time, date, users) VALUES ('"+message+"', CURRENT_TIME, CURRENT_DATE, '"+user+"')");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ObservableList<OperationHistory> getOperationHistoryList() throws SQLException {
        ObservableList<OperationHistory> list = FXCollections.observableArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Operations");
        while (rs.next()){
            String operation = rs.getString("operation");
            String time = rs.getString("time");
            String date = rs.getString("date");
            String user = rs.getString("users");
            
            list.add(new OperationHistory(operation, time, date, user));
        }
        stmt.close();
        return list;
    }
    
    /**
     * registerUser
     * @param login login for new user (must be unique)
     * @param password password for new user (must have more than 8 letters or
     * numbers, and the first character must be a letter)
     * @param password2 must be this same to parameter password
     * @param email email for new user (must be unique and have email rules)
     * @param regulationsAccepted regulations must be accepted
     * @return true if successfully create account or false if fail
     */
    public boolean registerUser(String login, String password, String password2, String email, boolean regulationsAccepted){
        //TODO create
        return false;
    }
    
    /**
     * 
     * @return db name or null if not connected
     */
    public String getDataBaseName(){
        //TODO create
        return null;
    }
    
    /**
     * addClient
     * @param firstName
     * @param lastName
     * @param voivodeship
     * @param Town
     * @param Street
     * @param houseNumber (this number can contain letters)
     * @param flatNumber (this number can contain letters)
     * @param phoneNumber
     * @param homeNumber
     * @param email
     * @return true if successfully add client to DB or false if fail
     */
    public boolean addClient(
            String firstName, String lastName, 
            String voivodeship, String Town, 
            String Street, String houseNumber, 
            String flatNumber, Double phoneNumber,
            Double homeNumber, String email){
        //TODO create
        return false;
    }
    
}
