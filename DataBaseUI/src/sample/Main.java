package sample;

import Model.Datasource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.listPersons();

        primaryStage.setTitle("DatabaseUI");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception{
        if(!Datasource.getInstance().open()){
            System.out.println("Error: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception{
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
