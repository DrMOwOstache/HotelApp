package domain;

import java.util.ArrayList;

public class user {
    Integer userid;
    String name;
    ArrayList<booking> listBookings = new ArrayList<booking>();

    public user(Integer userid, String name, ArrayList<booking> listBookings) {
        this.userid = userid;
        this.name = name;
        this.listBookings = listBookings;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<booking> getListBookings() {
        return listBookings;
    }

    public void setListBookings(ArrayList<booking> listBookings) {
        this.listBookings = listBookings;
    }

    public void addBooking(booking newBook)
    {
        listBookings.add(newBook);
    }

    @Override
    public String toString() {
        return "user{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", listBookings=" + listBookings +
                '}';
    }
}
