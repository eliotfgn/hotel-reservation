package model;

public class Room implements IRoom{
    public String roomNumber;
    public Double price;
    public RoomType type;

    public Room(String number, double price, RoomType type){
        this.roomNumber = number;
        this.price = price;
        this.type = type;
    }

    @Override
    public String getRoomId() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public RoomType getRoomType() {
        return type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
