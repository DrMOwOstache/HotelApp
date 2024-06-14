package domain.rooms;

import java.io.Serializable;

public abstract class hotelRoom implements room<Integer>, Serializable {

    protected Integer roomNumber;
    protected Boolean available;

    public hotelRoom(Integer roomNumber, Boolean available) {
        this.roomNumber = roomNumber;
        this.available = available;
    }

    @Override
    public Integer getRoomNumber() {
        return roomNumber;
    }
    @Override
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    abstract public Float getPrice();

    @Override
    abstract public void setPrice(Float price);

    @Override
    abstract public Integer getType();

    @Override
    public Boolean getAvailable() {
        return available;
    }
    @Override
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "hotelRoom{" +
                "roomNumber=" + roomNumber +
                ", available=" + available +
                '}';
    }
}
