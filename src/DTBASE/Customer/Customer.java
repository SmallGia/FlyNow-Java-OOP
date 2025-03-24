package DTBASE.Customer;

public class Customer {
    private String ticketCode,name,birthdate,gender,phone,email,address,group,seat;
    private int id;
    public Customer(String ticketCode, String name, String birthdate, String gender, String phone, String email, String address, String group, int id,String seat) {
        this.ticketCode = ticketCode;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.group = group;
        this.id = id;
        this.seat=seat;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
