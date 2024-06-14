package domain.rooms.hotelRooms;

import domain.rooms.hotelRoom;

public class suiteRoom extends hotelRoom {
    Float price;
    Integer type;

    public suiteRoom(Integer roomNumber, Float price, Boolean available) {
        super(roomNumber, available);
        this.price = price;
        this.type = 3;
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
        return "suiteRoom{" +
                "price=" + price +
                ", type='" + type + '\'' +
                ", roomNumber=" + roomNumber +
                ", available=" + available +
                '}';
    }
}
