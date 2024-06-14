package main;

import domain.hotel;
import domain.user;
import gui.GUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repositories.IRepo;
import repositories.database.HotelDatabaseRepo;
import repositories.database.UserDatabaseRepo;
import service.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main extends Application {
    private static Service service;
    private static GUIController controller;

    public static void main(String[] args) {

        IRepo<hotel,Integer> hotelRepo = null;
        IRepo<user,Integer> userRepository = null;
        Properties saveFolderProps = new Properties();

        String locjson = "";
        try(BufferedReader reader = new BufferedReader(new FileReader("src/properties/settings.properties"))) {
            saveFolderProps.load(reader);
            reader.close();
            String loc = saveFolderProps.getProperty("LocationDatabase");
            locjson = saveFolderProps.getProperty("JsonLocation");


            hotelRepo = new HotelDatabaseRepo(loc);
            userRepository = new UserDatabaseRepo(loc);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        service = new Service(hotelRepo,userRepository, locjson);
        //UI = new ui(service);
        //UI.startUp();
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        controller = new GUIController(service);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HotelBookingGUI.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}