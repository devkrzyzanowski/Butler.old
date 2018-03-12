/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

/**
 *
 * @author Michał
 */
public class OperationHistory {
    private String operation;
    private String time;
    private String date;
    private String user;
    
    //TODO change date format from String to DATE
    public OperationHistory(String operation,String time, String date, String user){
        this.operation = operation;
        this.time = time;
        this.date = date;
        this.user = user;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setUser(String user){
        this.user = user;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getOperation(){
        return operation;
    }
    public String getDate(){
        return date;
    }
    public String getUser(){
        return user;
    }
    public String getTime(){
        return time;
    }
    
}
