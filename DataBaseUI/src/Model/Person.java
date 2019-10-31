package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty phone;
    private SimpleStringProperty email;

    public Person() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.phone = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public int getPhone() {
        return phone.get();
    }
    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
}
