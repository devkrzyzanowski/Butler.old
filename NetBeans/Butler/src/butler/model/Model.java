/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package butler.model;

import butler.sql.SQLcommands;
import butler.utils.Client;
import butler.utils.AdditionalRoomItems;
import butler.utils.Booking;
import butler.utils.Legend;
import butler.utils.OperationHistory;
import butler.utils.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    //private final String dbName = "testDB";
    private final String connectionURL = "jdbc:derby:";
    
    Connection con = null;
    
    /**
     * init Model
     */
    public Model(){

    }
    
    public boolean loadDriver() {
        boolean success = false;
        try {
            Class.forName(driver);
            System.out.println(driver + " loaded.");
            success = true;
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("\n Make sure your CLASSPATH variable " +
                    "contains %DERBY_HOME%\\lib\\derby.jar (&{DERBY_HOME}/lib/derby.jar). \n");
        } 
        return success;
    }
    
    public boolean startDataBase(String dbName) {
        boolean success = false;
        try {        
            System.out.println("Trying to connect to " + connectionURL + dbName + ";");        
            con = DriverManager.getConnection(connectionURL + dbName + ";");
            System.out.println("Connected to database " + connectionURL + dbName + ";");
            success = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }
    
    public void stopDataBase(Connection con) {
        try {
        con.close();
        System.out.println("Closed connection");
        } catch (SQLException e) {
            System.out.println("stopDataBase : " + e);
        }
    }
    private void shutdownDataBaseConfirm(String dbName) {
        boolean SQLExc = false;
        try {
            DriverManager.getConnection("jdbc:derby: "+dbName+";shutdown=true");
        } catch (SQLException e) {
            SQLExc = (e.getSQLState().equals("XJ015")) ? true : false;
        }
        if (!SQLExc) {
                 System.out.println("Database did not shut down normally");
        } else {
                 System.out.println("Database shut down normally");
        }
        System.gc();
    }
    
    public boolean logInToDataBase(String nick, String password, String dbName) {
        boolean success = false;
        try {
            String newURL = connectionURL +dbName+";user="+nick+";password="+password;
            System.out.println("Trying to connect to : " + newURL + " | user = " +nick);
            con = DriverManager.getConnection(newURL);
            System.out.println("Connected to database " + dbName + " with access");
            success = true;
        } catch (SQLException e) {
            System.out.println("TEST"+e);
        }
        return success;
    }
    
    public boolean createDataBase(String dbName, String nick, String password) {
        boolean success = false;
        try {        
            System.out.println("Trying to create " + connectionURL + dbName + ";create=true;");        
            con = DriverManager.getConnection(connectionURL + dbName + ";create=true;");
            System.out.println("Connected to database " + connectionURL + dbName + ";");
            addReadWriteUser(con, nick, password);
            turnOnBuiltInUsers(con);
            System.out.println("0");            
            Statement stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_CLIENT);
            stmt.close();
            System.out.println("1");            
            stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_ROOM);
            stmt.close();
            System.out.println("2");
            stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_LEGEND);
            stmt.close();
            System.out.println("3");            
            stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_BOOKING);
            stmt.close();
            System.out.println("4");            
            stmt = con.createStatement();
            stmt.executeUpdate(SQLcommands.INSERT_LEGEND);
            stmt.close();
            System.out.println("5");            
            //stmt.execute(SQLcommands.CREATE_OPERATION);
            //stmt.execute(SQLcommands.CREATE_DBUSER);            

            con.close();
            success = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }
    
    public void addReadOnlyUser(Connection con, String nick, String password){
        System.out.println("Adding read-only user.");
        try {
            Statement s = con.createStatement();
            s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user."+nick+"', '"+password+"')");
            s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.readOnlyAccessUsers', '"+nick+"')");
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void addReadWriteUser(Connection con, String nick, String password){
        System.out.println("Adding read-write user.");
        try {
            Statement s = con.createStatement();
            s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user."+nick+"', '"+password+"')");
            s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.fullAccessUsers', '"+nick+"')");
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Turn on built-in user authentication and user authorization.
     *
     * @param conn a connection to the database.
     * @throws java.sql.SQLException
     */
    public static void turnOnBuiltInUsers(Connection conn) throws SQLException {
        System.out.println("Turning on authentication.");
        Statement s = conn.createStatement();

        // Setting and Confirming requireAuthentication
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication', 'true')");
        ResultSet rs = s.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication')");
        rs.next();
        System.out.println("Value of requireAuthentication is " +
            rs.getString(1));
        // Setting authentication scheme to Derby
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.authentication.provider', 'BUILTIN')");

        // Setting default connection mode to no access
        // (user authorization)
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.defaultConnectionMode', 'noAccess')");
        // Confirming default connection mode
        rs = s.executeQuery (
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.database.defaultConnectionMode')");
        rs.next();
        System.out.println("Value of defaultConnectionMode is " +
            rs.getString(1));

        // Confirming full-access users
        rs = s.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.database.fullAccessUsers')");
        rs.next();
        System.out.println("Value of fullAccessUsers is " + rs.getString(1));

        // Confirming read-only users
        rs = s.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.database.readOnlyAccessUsers')");
        rs.next();
        System.out.println("Value of readOnlyAccessUsers is " +
            rs.getString(1));

        // We would set the following property to TRUE only
        // when we were ready to deploy.
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.propertiesOnly', 'false')");
        //WATCH ON DEPLOY
        s.close();
    }

    /**
     * Turn off built-in user authentication and user authorization.
     *
     * @param conn a connection to the database.
     */
    public static void turnOffBuiltInUsers (Connection conn) throws SQLException {
        Statement s = conn.createStatement();
        System.out.println("Turning off authentication.");

        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication', 'false')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.authentication.provider', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.user.sa', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.user.guest', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.user.mary', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.defaultConnectionMode', 'fullAccess')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.fullAccessUsers', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.readOnlyAccessUsers', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.propertiesOnly', 'false')");

        // Confirming requireAuthentication
        ResultSet rs = s.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication')");
        rs.next();
        System.out.println("Value of requireAuthentication is " +
            rs.getString(1));

        // Confirming default connection mode
        rs = s.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.database.defaultConnectionMode')");
        rs.next();
        System.out.println("Value of defaultConnectionMode is " +
            rs.getString(1));
        System.out.println("Turned off all the user-related properties");
        s.close();
    }

    /** Exception reporting methods
     *   with special handling of SQLExceptions
     */
    static void errorPrint(Throwable e) {
        if (e instanceof SQLException)
            SQLExceptionPrint((SQLException)e);
        else {
            System.out.println("A non-SQL error occurred.");
            e.printStackTrace();
        }
    }  // END errorPrint

    //  Iterates through a stack of SQLExceptions
    static void SQLExceptionPrint(SQLException sqle) {
        while (sqle != null) {
            System.out.println("\n---SQLException Caught---\n");
            System.out.println("SQLState:   " + (sqle).getSQLState());
            System.out.println("Severity: " + (sqle).getErrorCode());
            System.out.println("Message:  " + (sqle).getMessage());
            sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }  //  END SQLExceptionPrint
    
    
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
            con = DriverManager.getConnection("jdbc:debry://" + address + ":" + port + "/" + dbName + "; User=" + userName + "; Pass=" +  password);
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
            con.createStatement().execute("INSERT INTO APP.OPERATION (operation, date, dbUser_idDbUser) VALUES ('"+message+"', CURRENT_TIMESTAMP, 0)");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
        public boolean addRoomToDataBase(Room room) {
        try {
            con.createStatement().execute("INSERT INTO APP.ROOM(room_name, number_of_single_beds,"
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
        
        public boolean addClientToDataBase(Client client){
            
            try {
                String sql = "INSERT INTO APP.CLIENT (first_name,"
                        + " last_name, city, street, home_number, flat_number,"
                        + " zip_code, contact_phone_number, email) VALUES "
                        + "('"+client.getFirstName()+"','"+client.getLastName()
                        +"','"+client.getCity()+"','"+client.getStreet()
                        +"',"+client.getHomeNumber()+","+client.getFlatNumber()
                        +","+client.getZipCode()+","+client.getContactPhoneNumber()
                        +",'"+client.getEmail()+"')";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating 'Client' failed");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println(rs.getInt("idClient"));
                  //  client.setId(rs.getInt("idClient"));
                } else {
                    throw new SQLException("Creating 'Client' failed, no ID obtained");
                }
            }
                return true;
            } catch (SQLException e) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        }
        
        public boolean addBookingToDataBase(Timestamp value, Timestamp value0, Integer selectedClientId, Integer selectedRoomId, Integer selectedLegendId) {
            startDataBase("debug");
            try {
                System.out.println(value);
                con.createStatement().execute("INSERT INTO APP.BOOKING(begin_of_booking, end_of_booking, Client_idClient, Room_idRoom, Legend_idLegend) VALUES ('"+value+"', '"+value0+"', "+selectedClientId+", "+selectedRoomId+", "+selectedLegendId+")");
                return true;
            } catch (SQLException e) {
                System.out.println(e);
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
            Integer legendId = rs.getInt("Legend_idLegend");
                System.out.println(id + " id||" + beginOfBooking.toString() + " begin||" + endOfBooking.toString() + " end||" + clientId + " client||" + roomId + " room||" + legendId + " legend||" );
            list.add(new Booking(id, String.valueOf(beginOfBooking), String.valueOf(endOfBooking), clientId, roomId, legendId));
            }
        } catch (SQLException e) {System.out.println(e);}
        return list;
    }
    
    public Legend getLegendById(Integer idLegend) {
        Legend legend = null;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.LEGEND WHERE idLegend = "+idLegend+" ");
            while (rs.next()) {                
                Integer id = rs.getInt("idLegend");
                String legendName = rs.getString("legend_name");
                String color = rs.getString("color");
                legend = new Legend(id, legendName, color);
            }
        } catch (SQLException e) {System.out.println(e);}
        return legend;       
    }
    
    public void setBookingStatus(Integer bookingId, Integer statusId) {
        try {
            con.createStatement().execute("UPDATE APP.BOOKING SET Legend_idLegend = "+statusId+" WHERE idBooking = "+bookingId+"");

        } catch (SQLException e) {System.out.println(e);}        
    }
    
    public ObservableList<Legend> getLegendList() {
        ObservableList<Legend> legends = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.LEGEND");
            while (rs.next()) {                
                Integer id = rs.getInt("idLegend");
                String legendName = rs.getString("legend_name");
                String color = rs.getString("color");
                legends.add(new Legend(id, legendName, color));
            }
        } catch (SQLException e) {System.out.println(e);}
        return legends;
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
            con.createStatement().execute("DELETE FROM APP.BOOKING WHERE idBooking = "+id+"");
        } catch (SQLException e) {
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
        return "test";
    }
    public String getUserName(){
        return "no implement";
    }

    
}

