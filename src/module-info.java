module Siemens.Hotels {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires javafx.controls;
    requires org.xerial.sqlitejdbc;

    opens gui to javafx.fxml;
    opens main to javafx.fxml;
    exports gui;
    exports main;
}