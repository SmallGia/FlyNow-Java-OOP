/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTBASE.flights;;

/**
 *
 * @author Tam
 */
public class Flights {
    private int id;
    private String from;
    private String to;
    private String airlines;
    private String datedepart;
    private String datearrive;
    private String timedepart;
    private String timearrive;
    private float price;
    private int leftticket;

    // Constructor
    public Flights(){

    }
    public Flights(int id, String from, String to, String airlines, String datedepart, String datearrive, String timedepart, String timearrive, float price, int leftticket) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.airlines = airlines;
        this.datedepart = datedepart;
        this.datearrive = datearrive;
        this.timedepart = timedepart;
        this.timearrive = timearrive;
        this.price = price;
        this.leftticket = leftticket;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAirlines() {
        return airlines;
    }

    public String getDatedepart() {
        return datedepart;
    }

    public String getDatearrive() {
        return datearrive;
    }

    public String getTimedepart() {
        return timedepart;
    }

    public String getTimearrive() {
        return timearrive;
    }

    public float getPrice() {
        return price;
    }

    public int getLeftticket() {
        return leftticket;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public void setDatedepart(String datedepart) {
        this.datedepart = datedepart;
    }

    public void setDatearrive(String datearrive) {
        this.datearrive = datearrive;
    }

    public void setTimedepart(String timedepart) {
        this.timedepart = timedepart;
    }

    public void setTimearrive(String timearrive) {
        this.timearrive = timearrive;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setLeftticket(int leftticket) {
        this.leftticket = leftticket;
    }
}

