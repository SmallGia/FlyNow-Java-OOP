package DTBASE.Information;

public class Information {
    private String username;
    private int ID,ticket;
    private float cost;
    private String ticketClass,ticketCode;
    private String seats;
    public Information(String username, int ID, float cost,int ticket,String ticketClass,String ticketCode) {
        this.username = username;
        this.ID = ID;
        this.cost = cost;
        this.ticket= ticket;
        this.ticketClass = ticketClass;
        this.ticketCode = ticketCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
