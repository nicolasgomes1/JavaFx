
package com.qaflow.desktopdemo;

import com.qaflow.desktopdemo.config.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToDoApplication extends Application {

    public static void show(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                ToDoApplication.class.getResource("todo-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("ToDo CRUD App");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        show(stage);
    }

    @Override
    public void stop() {
        HibernateUtil.shutdown();
    }

}