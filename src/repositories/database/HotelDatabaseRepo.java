package repositories.database;

import domain.hotel;
import domain.review;
import domain.rooms.hotelRooms.doubleRoom;
import domain.rooms.hotelRooms.matrimonialRoom;
import domain.rooms.hotelRooms.singleRoom;
import domain.rooms.hotelRooms.suiteRoom;
import domain.rooms.room;
import org.sqlite.SQLiteDataSource;
import repositories.DatabaseRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class HotelDatabaseRepo extends DatabaseRepo<hotel,Integer> {


    public HotelDatabaseRepo(String url) {
        super(url);
    }

    @Override
    public void addToDatabase(hotel item, Integer id) {
        dataList.putIfAbsent(id, item);
        writeInDatabase(item);
    }

    @Override
    public void removeFromDatabase(Integer id) {

    }

    private static void writeInDatabase(hotel dictHotel){
        try{
            openConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into hotel values " +
                            "("+ dictHotel.getId() + ",'" +
                            dictHotel.getName() + "'," +
                            dictHotel.getLatitude() + "," +
                            dictHotel.getLongitude() + ")");
            for(room<Integer> dictRoom : dictHotel.getListRooms()) {
                stmt.executeUpdate("insert into room values " +
                                "(" + dictRoom.getRoomNumber() + "," +
                                dictHotel.getId() + "," +
                                dictRoom.getAvailable() + "," +
                                dictRoom.getPrice() + "," +
                                dictRoom.getType() + ")");
            }
            for(review dictReview : dictHotel.getListReviews()) {
                stmt.executeUpdate("insert into review values " +
                        "(" + dictHotel.getId() + "," +
                        dictReview.getUserId() + ",'" +
                        dictReview.getDescription() + "',)");
            }


            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void readFromDataBase() {
        try {
            openConnection();
            ResultSet resultSet = conn.prepareStatement("select * from hotel").executeQuery();


            while (resultSet.next()) {
                ResultSet resultRooms = conn.prepareStatement("select * from room where id=" + resultSet.getInt("id")).executeQuery();
                ArrayList<room<Integer>> rooms = new ArrayList<room<Integer>>();
                while (resultRooms.next()) {
                    if (resultRooms.getInt("type") == 1)
                        rooms.add(new singleRoom(resultRooms.getInt("roomnumber"),
                                resultRooms.getFloat("price"),
                                resultRooms.getBoolean("isAvailable")));
                    if (resultRooms.getInt("type") == 2)
                        rooms.add(new doubleRoom(resultRooms.getInt("roomnumber"),
                                resultRooms.getFloat("price"),
                                resultRooms.getBoolean("isAvailable")));
                    if (resultRooms.getInt("type") == 4)
                        rooms.add(new matrimonialRoom(resultRooms.getInt("roomnumber"),
                                resultRooms.getFloat("price"),
                                resultRooms.getBoolean("isAvailable")));
                    if (resultRooms.getInt("type") == 3)
                        rooms.add(new suiteRoom(resultRooms.getInt("roomnumber"),
                                resultRooms.getFloat("price"),
                                resultRooms.getBoolean("isAvailable")));
                }

                ResultSet resultReview = conn.prepareStatement("select * from review where id=" + resultSet.getInt("id")).executeQuery();
                ArrayList<review> reviews = new ArrayList<review>();
                while (resultRooms.next()) {
                    reviews.add(new review(resultReview.getInt("userid"),resultReview.getString("description")));
                }
                System.out.println(rooms);
                dataList.putIfAbsent(resultSet.getInt("id"), new hotel(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude"), rooms, reviews));

                //repoItem.put(resultSet.getInt("ID"), new Recipe(resultSet.getInt("ID"),
                //resultSet.getString("recipeName"), resultSet.getInt("totalCook"),ingred));
            }
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
