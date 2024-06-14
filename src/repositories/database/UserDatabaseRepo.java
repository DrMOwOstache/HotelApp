package repositories.database;

import domain.booking;
import domain.hotel;
import domain.rooms.hotelRooms.doubleRoom;
import domain.rooms.hotelRooms.matrimonialRoom;
import domain.rooms.hotelRooms.singleRoom;
import domain.rooms.hotelRooms.suiteRoom;
import domain.rooms.room;
import domain.user;
import repositories.DatabaseRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDatabaseRepo extends DatabaseRepo<user,Integer> {

    public UserDatabaseRepo(String url) {
        super(url);
    }

    @Override
    public void addToDatabase(user item, Integer id) {
        dataList.putIfAbsent(id, item);
        writeInDatabase(item);
    }

    @Override
    public void removeFromDatabase(Integer id) {

    }

    private static void writeInDatabase(user dictUser){
        try{
            openConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from user ");
            stmt.executeUpdate("delete from booking ");
            stmt.executeUpdate("insert into user values " +
                    "("+ dictUser.getUserid() + ",'" +
                    dictUser.getName() + "')");
            for(booking dictBook : dictUser.getListBookings()) {
                stmt.executeUpdate("insert into booking values " +
                        "(" + dictBook.getRequestBooking() + "," +
                        dictUser.getUserid() + "," +
                        dictBook.getRoomNumber() + "," +
                        dictBook.getIdHotel() + ",)");
            }

            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void readFromDataBase() {
        try{
            openConnection();
            ResultSet resultSet = conn.prepareStatement("select * from user").executeQuery();


            while(resultSet.next())
            {
                ResultSet resultBooking = conn.prepareStatement("select * from booking where userId=" + resultSet.getInt("userId") ).executeQuery();
                ArrayList<booking> bookings = new ArrayList<booking>();
                while(resultBooking.next())
                {
                        bookings.add(new booking(resultBooking.getInt("requestBooking"),
                                resultBooking.getInt("id"),
                                resultBooking.getInt("roomnumber")));
                }
                //System.out.println(bookings);
                dataList.putIfAbsent(resultSet.getInt("id"), new user(resultSet.getInt("userId"),
                        resultSet.getString("name"),bookings));

                //repoItem.put(resultSet.getInt("ID"), new Recipe(resultSet.getInt("ID"),
                //resultSet.getString("recipeName"), resultSet.getInt("totalCook"),ingred));
            }
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
