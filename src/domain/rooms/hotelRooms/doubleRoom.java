package domain.rooms.hotelRooms;

import domain.rooms.hotelRoom;

import java.io.Serializable;

public class doubleRoom extends hotelRoom {
    Float price;
    Integer type;

    public doubleRoom(Integer roomNumber, Float price, Boolean available) {
        super(roomNumber, available);
        this.price = price;
        this.type = 2;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    @Override
    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "doubleRoom{" +
                "price=" + price +
                ", type='" + type + '\'' +
                ", roomNumber=" + roomNumber +
                ", available=" + available +
                '}';
    }
}
