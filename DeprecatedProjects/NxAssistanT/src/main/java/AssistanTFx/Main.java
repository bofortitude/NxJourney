package AssistanTFx;/**
 * Created by bofei on 1/18/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setUserAgentStylesheet(STYLESHEET_MODENA);
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("AppView.fxml"));
        primaryStage.setTitle("NxAssistanT  --  by Bo Fei");
//        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
