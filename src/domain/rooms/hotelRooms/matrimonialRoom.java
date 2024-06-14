package domain.rooms.hotelRooms;

import domain.rooms.hotelRoom;

public class matrimonialRoom extends hotelRoom {
    Float price;
    Integer type;

    public matrimonialRoom(Integer roomNumber, Float price, Boolean available) {
        super(roomNumber, available);
        this.price = price;
        this.type = 4;
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
        return "matrimonialRoom{" +
                "price=" + price +
                ", type='" + type + '\'' +
                ", roomNumber=" + roomNumber +
                ", available=" + available +
                '}';
    }
}
