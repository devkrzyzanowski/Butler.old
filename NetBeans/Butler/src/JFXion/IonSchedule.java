/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFXion;

import butler.model.Model;
import butler.utils.Booking;
import butler.utils.Room;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

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
             
//        for (NewCell nc : gridPaneList) {
//            gridPane.add(nc, nc.getColumnIndex(), nc.getRowIndex(), nc.getColumnSpan(), nc.getRowSpan());
//        }
        
        setUpData();
        draw();
        this.setContent(gridPane);
    }
    
    private void setUpData(){
        addRoomsRow(model.getRoomList());
        addTimeColumn();
        addSchedules(model.getBookingList(), timeCells, roomCells);        
    }
    
    private void draw() {
        for (int i = 0; i <= roomCells.size(); i++) {
            for (int j = 0; j <= timeCells.size(); j++) {
                gridPane.add(new NewCell(i, j), i, j);
            }
        }
        for (RoomCell rc : roomCells) {
            gridPane.add(rc, rc.getIdColumn(), 0);
        } 
        for (TimeCell tc : timeCells) {
            gridPane.add(tc, 0, tc.getIdRow());
        }
        for (ScheduleCell sc : scheduleCells) {
            gridPane.add(sc, sc.getIdColumn(), sc.getIdRow(), 1, sc.getBookingDays());
        }
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
        draw();
    }
}
