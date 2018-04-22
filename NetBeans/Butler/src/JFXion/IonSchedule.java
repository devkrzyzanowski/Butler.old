/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFXion;

import static butler.Butler.bundle;
import butler.controller.dialogs.ModifyBookingController;
import butler.model.Model;
import butler.utils.Booking;
import butler.utils.Room;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author MichalKrzyzanowski
 */
public class IonSchedule extends ScrollPane {
    private GridPane gridPane;
    private List<NewCell> gridPaneList;
    private List<TimeCell> timeCells;
    private List<RoomCell> roomCells;
    private List<ScheduleCell> scheduleCells;
    private ObservableList<Room> roomList;
    private ObservableList<Booking> bookingList;
    private Model model;
    
    public IonSchedule(ObservableList<Room> roomList, ObservableList<Booking> bookingList){
        this.model = butler.Butler.model;
        this.roomList = roomList;
        this.bookingList = bookingList;
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPaneList = new ArrayList<>();
        timeCells = new ArrayList<>();
        roomCells = new ArrayList<>();
        scheduleCells = new ArrayList<>();
        setUpData();
        draw();
        this.setContent(gridPane);
        addListeners();
    }
    
    private void setUpData(){
        addRoomsRow(model.getRoomList());
        addTimeColumn();
        addSchedules(model.getBookingList(), timeCells, roomCells);        
    }
    
    private void addListeners() {
        scheduleCells.forEach((sc) -> {
            sc.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bundle = ResourceBundle.getBundle("resources.bundles.messages", new Locale("pl"));
                    FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/butler/view/dialogs/modifyBooking.fxml"), bundle);
                    Parent parent;
                    try {
                        parent = fXMLLoader.load();
                        ModifyBookingController mdf = fXMLLoader.getController();
                        mdf.init(sc.getBooking(), IonSchedule.this);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(IonSchedule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
        });
    }
    
    private void draw() {
        for (int i = 0; i <= roomCells.size(); i++) {
            for (int j = 0; j <= timeCells.size(); j++) {
                gridPane.add(new NewCell(i, j), i, j);
            }
        }
        roomCells.forEach((rc) -> {
            gridPane.add(rc, rc.getIdColumn(), 0);
        }); 
        timeCells.forEach((tc) -> {
            gridPane.add(tc, 0, tc.getIdRow());
        });
        scheduleCells.forEach((sc) -> {
            gridPane.add(sc, sc.getIdColumn(), sc.getIdRow(), 1, sc.getBookingDays());
        });
    }

    
    private void addRoomsRow(ObservableList<Room> roomList) {
        roomCells.clear();
        int i = 1;
        for (Room r : roomList) {
            RoomCell rc = new RoomCell(i, r);
            roomCells.add(rc);
            i++;
        }
    }
    private void addTimeColumn() {
        timeCells.clear();
        for (int i = 1; i < 120; i++) {
            TimeCell tc = new TimeCell(i, new Timestamp(System.currentTimeMillis()- (86400000l * 15) + 86400000l*i));
            timeCells.add(tc);
        }

    }
    
    public void addSchedules(ObservableList<Booking> bookingList, List<TimeCell> tcl, List<RoomCell> rcl) {
        scheduleCells.clear();
        Integer x = 0, y = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             
        for (Booking b : bookingList) {
            for (TimeCell tc : tcl) {
                Timestamp ts = Timestamp.valueOf(b.getBeginOfBooking());
                if (tc.getFormattedTs("yyyy-MM-dd").equals(sdf.format(ts))){
                    y = tc.getIdRow();
                }
            }           
        for (RoomCell rc : rcl) {
            if (rc.getIdRoom() == b.getIdRoom()) {
                x = rc.getIdColumn();
            }
        }
            ScheduleCell sc = new ScheduleCell(x, y, b);
            scheduleCells.add(sc);
        }
    }

    public void update() {
        gridPane.getChildren().clear();
        setUpData();
        addListeners();
        draw();
    }
}
