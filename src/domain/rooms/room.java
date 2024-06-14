package domain.rooms;

public interface room<T> {
    public T getRoomNumber();
    public void setRoomNumber(Integer roomNumber);
    public Boolean getAvailable();
    public void setAvailable(Boolean available);

    public Float getPrice();

    public Integer getType();

    public void setPrice(Float price);
    public String toString();
}
