package domain;

import domain.rooms.room;

import java.util.ArrayList;

public class hotel {
    Integer id;
    String name;
    Double latitude;
    Double longitude;

    ArrayList<room<Integer>> listRooms = new ArrayList<room<Integer>>();
    ArrayList<review> listReviews = new ArrayList<review>();

    public hotel(Integer id, String name, Double latitude, Double longitude, ArrayList<room<Integer>> listRooms, ArrayList<review> listReviews) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.listRooms = listRooms;
        this.listReviews = listReviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<room<Integer>> getListRooms() {
        return listRooms;
    }

    public void setListRooms(ArrayList<room<Integer>> listRooms) {
        this.listRooms = listRooms;
    }

    public ArrayList<review> getListReviews() {
        return listReviews;
    }

    public void setListReviews(ArrayList<review> listReviews) {
        this.listReviews = listReviews;
    }

    @Override
    public String toString() {
        return "hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
