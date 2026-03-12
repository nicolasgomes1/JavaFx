module com.qaflow.desktopdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.qaflow.desktopdemo to javafx.fxml;
    opens com.qaflow.desktopdemo.controller to javafx.fxml;
    opens com.qaflow.desktopdemo.model to javafx.base, org.hibernate.orm.core;

    exports com.qaflow.desktopdemo;
    exports com.qaflow.desktopdemo.controller;
    exports com.qaflow.desktopdemo.model;
}
