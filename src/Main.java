import database.DatabaseConnection;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getInstance().getConnection();


        if (connection != null) {
            System.out.println("Database connection is successful!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}