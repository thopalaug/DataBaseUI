package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "testDataBase.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\ThomasPalme\\Desktop\\JavaDeveloperCourse\\DataBaseUI\\" + DB_NAME;

    public static final String TABLE_PERSONS = "persons";
    public static final String COLUMN_PERSON_ID = "_id";
    public static final String COLUMN_PERSON_NAME = "name";
    public static final String COLUMN_PERSON_PHONE = "phone";
    public static final String COLUMN_PERSON_EMAIL = "email";
    public static final int INDEX_PERSON_ID = 1;
    public static final int INDEX_PERSON_NAME = 2;
    public static final int INDEX_PERSON_PHONE = 3;
    public static final int INDEX_PERSON_EMAIL = 4;

    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_CAR_ID = "_id";
    public static final String COLUMN_CAR_MODEL = "model";
    public static final String COLUMN_CAR_REGISTRATION = "registration";
    public static final int INDEX_CAR_ID = 1;
    public static final int INDEX_CAR_MODEL = 2;
    public static final int INDEX_CAR_REGISTRATION = 3;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;


    private Connection conn;

    private static Datasource instance = new Datasource();

    private Datasource(){

    }

    public static Datasource getInstance(){
        return instance;
    }

    public boolean open() {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch(SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }

    }

    public void close() {
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }

    }


    public List<Person> queryPersons(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_PERSONS);
        if(sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_PERSON_NAME);
            sb.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                sb.append("DESC");
            }else{
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Person> persons = new ArrayList<>();
            while(results.next()) {
                Person person = new Person();
                person.setId(results.getInt(INDEX_PERSON_ID));
                person.setName(results.getString(INDEX_PERSON_NAME));
                person.setPhone(results.getInt(INDEX_PERSON_PHONE));
                person.setEmail(results.getString(INDEX_PERSON_EMAIL));
                persons.add(person);
            }
            return persons;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
