package sample;

import Model.Datasource;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {

    @FXML
    private TableView<Person> tableView;

    public void listPersons(){
        Task<ObservableList<Person>> task = new GetAllPersonsTask();
        tableView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

}

class GetAllPersonsTask extends Task{

    @Override
    public ObservableList<Person> call(){
        return FXCollections.observableArrayList(
                Datasource.getInstance().queryPersons(Datasource.ORDER_BY_ASC)
        );
    }
}

