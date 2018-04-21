/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package butler.model;

import butler.utils.Client;
import butler.utils.AdditionalRoomItems;
import butler.utils.Booking;
import butler.utils.OperationHistory;
import butler.utils.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Michał
 */
public class Model {
    private final String DBAddress = "jdbc:derby://localhost:1527/ButlerDB;";
    private final String DBUser = "User=root;";
    private final String DBPassword = "Pass=;";
    private final String DBDriver = "org.apache.derby.jdbc.ClientDriver";
    
    private String connectedUserNick;
    
    Connection con;
    
    /**
     * init Model
     */
    public Model(){
        try {
            Class.forName(DBDriver);
        } catch (ClassNotFoundException ex) {
            System.err.println("Derby driver not found.");
        }
        try {
            con = DriverManager.getConnection(DBAddress + DBUser + DBPassword);
        } catch ( SQLException ex){
            System.err.println("NIE POLACZONO");
        }
    }
    
    /**
     * 
     * @param login 
     * @param password
     * @return true on successfully connect to db or false on fail
     * @throws java.sql.SQLException
     */
    public boolean connectToDataBase(String login, String password) throws SQLException{
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DBUser");
            while (rs.next()){
                if (rs.getString("nick").equals(login)){
                    if (rs.getString("password").equals(password)){
                        connectedUserNick = rs.getString("nick");
                        return true;
                    }
                }
            }
        } catch (NullPointerException e){
            return false;
        }
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
     * @return true on successfuly close DB connection of false on fail
     */
    public boolean closeConnection(){       
        try {
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    /**
     * 
     * @param address
     * @param port
     * @param userName
     * @param password
     * @return true on successfully connect to DB or false on fail
     */
    public boolean tryConnectionToDataBase(String address, String port, String dbName, String userName, String password){
        //TODO change String password to char[] password
        try {
            con = DriverManager.getConnection("jdbc:derby://" + address + ":" + port + "/" + dbName + "; User=" + userName + "; Pass=" +  password);
            con.close();
            return true;
        } catch ( SQLException e){
            return false;
        }
    }
    
    /**
     * 
     * @param message
     * @param user
     * @return true on successfully add or false on fail
     */
    public boolean addToOperationHistory(String message, String user) throws SQLException {
        try {
            con.createStatement().execute("INSERT INTO Operation(operation, date, dbUser_idDbUser) VALUES ('"+message+"', CURRENT_TIMESTAMP, 0)");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
        public boolean addRoomToDataBase(Room room) {
        try {
            con.createStatement().execute("INSERT INTO Room(room_name, number_of_single_beds,"
                    + " number_of_double_beds, number_of_extra_beds, floor_number,"
                    + " price_of_room, price_of_adult, price_of_minor, small_description,"
                    + " big_description, extra_description, building, balcon, "
                    + " beach_screen, blanket, sunbed, tv, wi_fi, individual_entrance,"
                    + " friendly_animal, kettle, tableware, table_lamp ) "
                    + "VALUES ('"+room.getRoomName()+"', "+room.getNumberOfSingleBeds()
                    +","+room.getNumberOfDoubleBeds()+","+room.getNumberOfExtraBeds()
                    +","+room.getFloorNumber()+","+room.getPriceOfRoom()
                    +","+room.getPriceOfAdult()+","+room.getPriceOfMinor()
                    +",'"+room.getSmallDescription()+"','"+room.getBigDescription()
                    +"','"+room.getExtraDescription()+"','"+room.getBuilding()
                    +"',"+room.getBalcon()+","+room.getBeachScreen()+","+room.getBlanket()
                    +","+room.getSunbed()+","+room.getTv()+","+room.getWiFi()
                    +","+room.getIndividualEntrance()+","+room.getFriendlyAnimal()+","+room.getKettle()
                    +","+room.getTableware()+","+room.getTableLamp()+")");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
        
        public boolean addClientToDataBase(Client client) {
            try {
                con.createStatement().execute("INSERT INTO Client(first_name,"
                        + " last_name, city, street, home_number, flat_number,"
                        + " zip_code, contact_phone_number, email) VALUES "
                        + "('"+client.getFirstName()+"','"+client.getLastName()
                        +"','"+client.getCity()+"','"+client.getStreet()
                        +"',"+client.getHomeNumber()+","+client.getFlatNumber()
                        +","+client.getZipCode()+","+client.getContactPhoneNumber()
                        +",'"+client.getEmail()+"')");
                return true;
            } catch (SQLException e) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        }
        
        public boolean addBookingToDataBase(Timestamp value, Timestamp value0, Integer selectedClientId, Integer selectedRoomId) {
            try {
                System.out.println(value);
                con.createStatement().execute("INSERT INTO Booking(begin_of_booking, end_of_booking, Client_idClient, Room_idRoom) VALUES ('"+value+"', '"+value0+"', "+selectedClientId+", "+selectedRoomId+")");
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        
    public ObservableList<Booking> getBookingList() {
        ObservableList<Booking> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.BOOKING");
            while (rs.next()){
            Integer id = rs.getInt("idBooking");
            Timestamp beginOfBooking = rs.getTimestamp("begin_of_booking");
            Timestamp endOfBooking = rs.getTimestamp("end_of_booking");
            Integer clientId = rs.getInt("Client_idClient");
            Integer roomId = rs.getInt("Room_idRoom");
            Integer bookingStatus = rs.getInt("booking_status");
            list.add(new Booking(id, String.valueOf(beginOfBooking), String.valueOf(endOfBooking), clientId, roomId, bookingStatus));
            }
        } catch (SQLException e) {System.out.println(e);}
        return list;
    }
   
        public Client getClientById(Integer idClient) {
            Client returnedClient = null;
       try (Statement stmt = con.createStatement()) {
           ResultSet rs = stmt.executeQuery("SELECT * FROM APP.CLIENT WHERE idClient = "+idClient+" ");
            while (rs.next()) {                
                Integer id = rs.getInt("idClient");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String city = rs.getString("city");
                String street = rs.getString("street");
                Integer homeNumber = rs.getInt("home_number");
                Integer flatNumber = rs.getInt("flat_number");
                Integer zipCode = rs.getInt("zip_code");
                Integer contactPhoneNumber = rs.getInt("contact_phone_number");
                String email = rs.getString("email");
                returnedClient = new Client(id, firstName, lastName, city, street,
                        homeNumber, flatNumber, zipCode, contactPhoneNumber,
                        email);
            }
       } catch (SQLException e){
           System.out.println(e);
       }
       return returnedClient;
    }
    
    public ObservableList<Client> getClientList() throws SQLException {
        ObservableList<Client> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.CLIENT");
            while (rs.next()) {                
                Integer id = rs.getInt("idClient");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String city = rs.getString("city");
                String street = rs.getString("street");
                Integer homeNumber = rs.getInt("home_number");
                Integer flatNumber = rs.getInt("flat_number");
                Integer zipCode = rs.getInt("zip_code");
                Integer contactPhoneNumber = rs.getInt("contact_phone_number");
                String email = rs.getString("email");
                list.add(new Client(id, firstName, lastName, city, street,
                        homeNumber, flatNumber, zipCode, contactPhoneNumber,
                        email));
            }
        }
        return list;        
    }
    
    public void removeBookingById(Integer id) {
        try {
            con.createStatement().execute("DELETE FROM Booking WHERE idBooking = "+id+"");
        } catch (Exception e) {
        }
    }
    
        public Room getRoomById(Integer roomId) throws SQLException {
            Room returnedRoom = null;
       try (Statement stmt = con.createStatement()) {
           ResultSet rs = stmt.executeQuery("SELECT * FROM APP.ROOM WHERE idRoom = "+roomId+" ");
            while (rs.next()) {               
                Integer id = rs.getInt("idRoom");
                System.out.println("DD" + id);
                String roomName = rs.getString("room_name");
                Integer numberOfSingleBeds = rs.getInt("number_of_single_beds");
                Integer numberOfDoubleBeds = rs.getInt("number_of_double_beds");
                Integer numberOfExtraBeds = rs.getInt("number_of_extra_beds");
                Integer floorNumber = rs.getInt("floor_number");
                Double priceOfRoom = rs.getDouble("price_of_room");
                Double priceOfAdult = rs.getDouble("price_of_adult");
                Double priceOfMinor = rs.getDouble("price_of_minor");
                String smallDescription = rs.getString("small_description");
                String bigDescription = rs.getString("big_description");
                String extraDescription = rs.getString("extra_description");
                String building = rs.getString("building");
                Boolean balcon = rs.getBoolean("balcon");
                Boolean beachScreen = rs.getBoolean("beach_screen");
                Boolean blanket = rs.getBoolean("blanket");
                Boolean sunbed = rs.getBoolean("sunbed");
                Boolean tv = rs.getBoolean("tv");
                Boolean wiFi = rs.getBoolean("wi_fi");
                Boolean individualEntrance = rs.getBoolean("individual_entrance");
                Boolean friendlyAnimal = rs.getBoolean("friendly_animal");
                Boolean kettle = rs.getBoolean("kettle");
                Boolean tableware = rs.getBoolean("tableware");
                Boolean tableLamp = rs.getBoolean("table_lamp");
                AdditionalRoomItems e = new AdditionalRoomItems();
                returnedRoom = new Room(id, roomName, numberOfSingleBeds, numberOfDoubleBeds,
                        numberOfExtraBeds, floorNumber, priceOfRoom, priceOfAdult,
                        priceOfMinor, smallDescription, bigDescription, extraDescription,
                        building, balcon, beachScreen, blanket, sunbed, tv, wiFi,
                        individualEntrance, friendlyAnimal, kettle, tableware, tableLamp);
            }
       } catch (SQLException e){
           System.out.println(e);
       }
       return returnedRoom;
    }
        
    public ObservableList<Room> getRoomList() {
        ObservableList<Room> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.ROOM");
            while (rs.next()) {    
                Integer id = rs.getInt(("idRoom"));
                String roomName = rs.getString("room_name");
                Integer numberOfSingleBeds = rs.getInt("number_of_single_beds");
                Integer numberOfDoubleBeds = rs.getInt("number_of_double_beds");
                Integer numberOfExtraBeds = rs.getInt("number_of_extra_beds");
                Integer floorNumber = rs.getInt("floor_number");
                Double priceOfRoom = rs.getDouble("price_of_room");
                Double priceOfAdult = rs.getDouble("price_of_adult");
                Double priceOfMinor = rs.getDouble("price_of_minor");
                String smallDescription = rs.getString("small_description");
                String bigDescription = rs.getString("big_description");
                String extraDescription = rs.getString("extra_description");
                String building = rs.getString("building");
                Boolean balcon = rs.getBoolean("balcon");
                Boolean beachScreen = rs.getBoolean("beach_screen");
                Boolean blanket = rs.getBoolean("blanket");
                Boolean sunbed = rs.getBoolean("sunbed");
                Boolean tv = rs.getBoolean("tv");
                Boolean wiFi = rs.getBoolean("wi_fi");
                Boolean individualEntrance = rs.getBoolean("individual_entrance");
                Boolean friendlyAnimal = rs.getBoolean("friendly_animal");
                Boolean kettle = rs.getBoolean("kettle");
                Boolean tableware = rs.getBoolean("tableware");
                Boolean tableLamp = rs.getBoolean("table_lamp");
                AdditionalRoomItems e = new AdditionalRoomItems();
                list.add(new Room(id, roomName, numberOfSingleBeds, numberOfDoubleBeds,
                        numberOfExtraBeds, floorNumber, priceOfRoom, priceOfAdult,
                        priceOfMinor, smallDescription, bigDescription, extraDescription,
                        building, balcon, beachScreen, blanket, sunbed, tv, wiFi,
                        individualEntrance, friendlyAnimal, kettle, tableware, tableLamp));
            }
        } catch (SQLException e){ System.out.println(e); }
        return list;
    }
    
    private boolean timeBeetwen(Timestamp time, Timestamp after, Timestamp before) {
        return (time.after(after) && time.before(before));
    }
    
    public String roomOwnedBy(Integer idRoom, Timestamp ts) {
        String s = "";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT begin_of_booking, end_of_booking, Client_idClient FROM APP.BOOKING WHERE Room_idRoom = " + idRoom + "");
        while (rs.next()) {
            Timestamp beginOfBooking = rs.getTimestamp("begin_of_booking");
            Timestamp endOfBooking = rs.getTimestamp("end_of_booking");
            Integer idClient = rs.getInt("Client_idClient");
            System.out.println(idClient);
            if (timeBeetwen(ts, beginOfBooking, endOfBooking)) {
                try (Statement stmt2 = con.createStatement()) {
                    ResultSet rs2 = stmt2.executeQuery("SELECT first_name, last_name FROM APP.CLIENT WHERE idClient = " + idClient + "");
                    while (rs2.next()) {
                        System.out.println("w");
                        s = rs2.getString("first_name") + " " + rs2.getString("last_name");
                    }
                } catch (SQLException e) {System.out.println(e);}
            }
        }
        } catch (SQLException e){System.out.println(e);}
        return s;
    }
    
    public ObservableList<OperationHistory> getOperationHistoryList() throws SQLException {
        ObservableList<OperationHistory> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT operation, date, nick FROM APP.OPERATION o INNER JOIN APP.DBUSER u ON u.idDBUser = o.DBUser_idDBUser");
            while (rs.next()){
                String operation = rs.getString("operation");
                String date = rs.getString("date");
                String user = rs.getString("nick");
                
                list.add(new OperationHistory(operation, date, user));
            }
        }
        return list;
    }
    
    /**
     * 
     * @param dateFrom
     * @param dateTo
     * @return 
     * @throws java.sql.SQLException 
     */
     public ObservableList<OperationHistory> getOperationHistoryListFromTo(LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ObservableList<OperationHistory> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT operation, date, nick FROM APP.OPERATION o INNER JOIN APP.DBUSER u ON u.idDBUser = o.DBUser_idDBUser WHERE date BETWEEN TIMESTAMP('"+dateFrom+" 00:00:00') AND TIMESTAMP('"+dateTo+" 23:59:59')");
            while (rs.next()){
                String nick = rs.getString("nick");
                String operation = rs.getString("operation");
                String date = rs.getString("date");
                list.add(new OperationHistory(operation, date, nick));
            }
        }
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
    
    public String getDataBaseName(){
        return DBAddress;
    }
    public String getUserName(){
        return DBUser;
    }


    public String getConnectedUserNick() {
        if (!connectedUserNick.isEmpty()){
        return connectedUserNick;
        } else {
            return null;
        }
    } 
}
