import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        Connection connection = connectionDB.getConnection();
        Program program = new Program();
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        do {
            program.getMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("---------------------List of all users:------------");
                    System.out.println(program.getAllUsers(connection));
                    break;

                case 2:
                    program.createUser(connection);
                    System.out.println("---------------------List of all users:------------");
                    System.out.println(program.getAllUsers(connection));
                    break;

                case 3:
                    program.searchUser(connection);
                    break;

                case 4: program.updateUser(connection);
                    break;
                case 5: program.deleteUserOnId(connection);
                break;

                case 6:
                    System.out.println("Exit");
                    exit = true;
                    break;
            }


        } while (!exit);

        sc.close();
        connection.close();
    }
}