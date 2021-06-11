package model;

public interface IRoom {
    public String getRoomId();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}
