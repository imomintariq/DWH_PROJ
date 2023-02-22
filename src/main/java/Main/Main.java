package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Main extends Application {
    private static Stage stg;

    private static ObjectPool<HashMap> pool;

    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        String fxmlPath = "MainScreen.fxml";
        //String fxmlPath = "ScreenFXMLs/Patient/ViewProfile.fxml";
        URL url = getClass().getClassLoader().getResource(fxmlPath);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 630, 450);
        stage.setTitle("HMS");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException{
        URL url = getClass().getClassLoader().getResource(fxml);
        Parent pane = FXMLLoader.load(url);
        stg.getScene().setRoot(pane);

    }
}