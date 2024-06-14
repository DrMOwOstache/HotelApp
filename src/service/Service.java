package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.booking;
import domain.hotel;
import domain.review;
import domain.rooms.hotelRooms.doubleRoom;
import domain.rooms.hotelRooms.matrimonialRoom;
import domain.rooms.hotelRooms.singleRoom;
import domain.rooms.hotelRooms.suiteRoom;
import domain.rooms.room;
import domain.user;
import repositories.IRepo;
import repositories.database.UserDatabaseRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Service {

    IRepo<hotel,Integer> hotelRepository;

    IRepo<user,Integer> userRepository;
    String jsonFilePath = "src/data/hoteldata.json";

    Integer requestId;
    ArrayList<user> localUserSave = new ArrayList<>();
    ArrayList<hotel> localHotelSave = new ArrayList<>();
    ArrayList<room<Integer>> localRoomsSave = new ArrayList<>();

    public Service(IRepo<hotel,Integer> hotelRepository, IRepo<user,Integer> userRepository, String jsonFilePath) {
        this.hotelRepository = hotelRepository;
        if(hotelRepository.getAllItems().isEmpty())
            putJsonHotelDataIntoDatabase();
        requestId =0;
        this.userRepository = userRepository;
        userRepository.addToRepo(new user(0,"Ioan Florian",new ArrayList<>()),0);
    }

    private void putJsonHotelDataIntoDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(jsonFilePath));

            Iterator<JsonNode> itr1 = jsonNode.elements();
            while(itr1.hasNext()) {
                Iterator<JsonNode> itrHotels = itr1.next().elements();
                while (itrHotels.hasNext()) {
                    JsonNode id = itrHotels.next();
                    JsonNode name = itrHotels.next();
                    JsonNode latitude = itrHotels.next();
                    JsonNode longitude = itrHotels.next();


                    ArrayList<room<Integer>> auxRooms = new ArrayList<room<Integer>>();
                    Iterator<JsonNode> itr2 = itrHotels.next().elements();
                    while(itr2.hasNext()) {
                        Iterator<JsonNode> itrRooms = itr2.next().elements();
                        while (itrRooms.hasNext()) {
                            JsonNode roomNumber = itrRooms.next();
                            JsonNode type = itrRooms.next();
                            JsonNode price = itrRooms.next();
                            JsonNode isAvailable = itrRooms.next();
                            if (type.asInt() == 1)
                                auxRooms.add(new singleRoom(roomNumber.asInt(), price.floatValue(), isAvailable.asBoolean()));
                            if (type.asInt() == 2)
                                auxRooms.add(new doubleRoom(roomNumber.asInt(), price.floatValue(), isAvailable.asBoolean()));
                            if (type.asInt() == 3)
                                auxRooms.add(new matrimonialRoom(roomNumber.asInt(), price.floatValue(),
                                        isAvailable.asBoolean()));
                            if (type.asInt() == 4)
                                auxRooms.add(new suiteRoom(roomNumber.asInt(), price.floatValue(), isAvailable.asBoolean()));
                        }
                    }
                    hotelRepository.addToRepo(new hotel(id.asInt(), name.asText(), latitude.asDouble(),
                            longitude.asDouble(), auxRooms, new ArrayList<>()), id.asInt());

                }
            }
            ArrayList<hotel> auxListHotel = new ArrayList<>(hotelRepository.getAllItems());
            localHotelSave = auxListHotel;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List listOfHotelsInArea() {
        ArrayList<hotel> auxListHotel = new ArrayList<>(hotelRepository.getAllItems());
        localHotelSave = auxListHotel;
        return auxListHotel.stream().map(hotel::toString).toList();
    }

    public List<String> getListOfRooms(Integer index){
        //System.out.println(localHotelSave.get(index));
        localRoomsSave.addAll(localHotelSave.get(index).getListRooms());
        return localHotelSave.get(index).getListRooms().stream().map(room::toString).toList();
    }

    public List<String> getListOfReviews(Integer index){
        //System.out.println(localHotelSave.get(index));
        return localHotelSave.get(index).getListReviews().stream().map(review::toString).toList();
    }

    public ArrayList<Integer> getRoomAdv(Integer indexRoom, Integer hotelId)
    {
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(localRoomsSave.get(indexRoom).getRoomNumber());
        aux.add(localHotelSave.get(hotelId).getId());
        return aux;
    }

    public List listOfBookings(Integer index) {
        ArrayList<user> auxListBookings = new ArrayList<>(userRepository.getAllItems());
        localUserSave = auxListBookings;
        return localUserSave.get(index).getListBookings().stream().map(booking::toString).toList();
    }

    public void makeBooking(Integer userId,Integer roomnumber, Integer id)
    {
        int i=0;
        while(requestId.equals(localUserSave.get(userId).getListBookings().get(i).getRequestBooking()))
            i++;
        localUserSave.get(userId).addBooking(new booking(requestId,id,roomnumber));
        userRepository.addToRepo(new user(localUserSave.get(userId).getUserid(),localUserSave.get(userId).getName(),
                localUserSave.get(userId).getListBookings()),userId);
    }

    /*public List listOfBookings() {

    }*/
}
