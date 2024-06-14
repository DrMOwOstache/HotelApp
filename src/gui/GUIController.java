package gui;

import domain.hotel;
import domain.rooms.room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class GUIController {

    Service service;
    public GUIController(Service service) {
        this.service = service;
    }

    private ObservableList<String> bookings = FXCollections.observableArrayList();

    private ObservableList<String> hotels = FXCollections.observableArrayList();

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField roomNumberField;

    @FXML
    private TextField jsonField;

    @FXML
    private ListView<String> bookedRooms;

    @FXML
    private ListView<String> hotelList;

    @FXML
    private ListView<String> roomList;

    @FXML
    private ListView<String> reviewList;

    @FXML
    private Button updateButton;

    @FXML
    void cancelClicked(MouseEvent event) {

    }

    @FXML
    void changeClicked(MouseEvent event) {

    }

    private ObservableList<String> selectedBookings = FXCollections.observableArrayList();

    @FXML
    void makeClicked(MouseEvent event) {

        service.makeBooking(0,service.getRoomAdv(roomList.getSelectionModel().getSelectedIndex(),hotelList.getSelectionModel().getSelectedIndex()).get(0),
                service.getRoomAdv(roomList.getSelectionModel().getSelectedIndex(),hotelList.getSelectionModel().getSelectedIndex()).get(1));
        bookedRooms.getItems().clear();
        //System.out.println(service.getListOfRooms(hotelList.getSelectionModel().getSelectedIndex()));
        selectedBookings.clear();
        selectedBookings.addAll(service.listOfBookings(0));
        bookedRooms.getItems().addAll(selectedBookings);
    }

    @FXML
    void pressedUpdate(MouseEvent event) {

    }

    private ObservableList<String> selectedRooms = FXCollections.observableArrayList();
    private ObservableList<String> selectedReviews = FXCollections.observableArrayList();

    @FXML
    void selectHotel(MouseEvent event) {
        roomList.getItems().clear();
        //System.out.println(service.getListOfRooms(hotelList.getSelectionModel().getSelectedIndex()));
        selectedRooms.clear();
        selectedRooms.addAll(service.getListOfRooms(hotelList.getSelectionModel().getSelectedIndex()));
        roomList.getItems().addAll(selectedRooms);

        reviewList.getItems().clear();
        //System.out.println(service.getListOfRooms(hotelList.getSelectionModel().getSelectedIndex()));
        selectedReviews.clear();
        selectedReviews.addAll(service.getListOfReviews(hotelList.getSelectionModel().getSelectedIndex()));
        reviewList.getItems().addAll(selectedRooms);
    }

    @FXML
    void selectRoom(MouseEvent event) {

    }

    @FXML
    void selectBooking(MouseEvent event) {

    }

    @FXML
    private void initialize()
    {

        hotels.clear();
        hotels.addAll(service.listOfHotelsInArea());
        hotelList.getItems().addAll(hotels);
        hotelList.onEditCommitProperty();
        hotelList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        bookings.clear();
        bookings.addAll(service.listOfBookings(0));
        bookedRooms.getItems().addAll(bookings);
        bookedRooms.onEditCommitProperty();
        bookedRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


//        comboTypes.addAll("Verb","Noun","Adjective","Adverb","Connector");
//        ComboBoxID.setItems(comboTypes);
//        ComboBoxID.getSelectionModel().select(0);

    }

}
