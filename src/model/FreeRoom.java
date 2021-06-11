package model;

public class FreeRoom extends Room {
    public Double price;

    public FreeRoom(String number, RoomType type) {
        super(number, 0, type);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "price=" + price +
                ", roomNumber='" + roomNumber + '\'' +
                ", type=" + type +
                '}';
    }
}
