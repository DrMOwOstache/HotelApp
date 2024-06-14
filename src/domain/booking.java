package domain;

import java.time.LocalDateTime;

public class booking {

    Integer requestBooking;
    Integer idHotel;
    Integer roomNumber;

    public booking(Integer requestBooking, Integer idHotel, Integer roomNumber) {
        this.requestBooking = requestBooking;
        this.idHotel = idHotel;
        this.roomNumber = roomNumber;
    }

    public Integer getRequestBooking() {
        return requestBooking;
    }

    public void setRequestBooking(Integer requestBooking) {
        this.requestBooking = requestBooking;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "booking{" +
                "requestBooking=" + requestBooking +
                ", idHotel=" + idHotel +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
