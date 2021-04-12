import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public void getMenu(){
        System.out.println("\t*****Program DB*****\n");
        System.out.println("\t1. Get all users");
        System.out.println("\t2. Add new user");
        System.out.println("\t3. Search user ");
        System.out.println("\t4. Update user ");
        System.out.println("\t5. Delete user");
        System.out.println("\t6. Exit\n");
        System.out.println(" \tEnter your choice: ");
    }

    public void getSearchMenu() {
        System.out.println("\t-----Search user-----\n");
        System.out.println("\t1. Search on person id");
        System.out.println("\t2. Search on username ");
        System.out.println("\t3. Search on last name");
        System.out.println("\t4. <- Back");
    }

    public void getUpdateMenu() {
        System.out.println("\t-----Update user-----\n");
        System.out.println("\t1. Update password");
        System.out.println("\t2. <- Back");
    }

    public List getAllUsers(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT u.id, u.username, u.password, u.id_person, p.first_name, p.last_name, p.address FROM user u JOIN person p on u.id_person = p.id;";
            ResultSet rs = statement.executeQuery(query);
            List <User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAddress(rs.getString("address"));
                users.add(user);
            }
            statement.close();
            rs.close();
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public void createUser(Connection connection) {
        System.out.println("Create new user");
        int personId = createPerson(connection);
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (username, password, id_person) VALUES (?, ?, ?)");
            System.out.println("Username...");
            Scanner sc = new Scanner(System.in);
            statement.setString(1, sc.next());
            System.out.println("Password...");
            statement.setString(2, sc.next());
            statement.setInt(3, personId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int createPerson(Connection connection) {
        try {
            int primaryKey = 0;
            Scanner sc = new Scanner(System.in);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO  person (first_name, last_name, address) VALUES (?, ?, ?)", 
									Statement.RETURN_GENERATED_KEYS);
            System.out.println("First Name...");
            statement.setString(1, sc.next());
            System.out.println("Last Name...");
            statement.setString(2, sc.next());
            System.out.println("Address...");
            statement.setString(3, sc.next());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                primaryKey = (int) resultSet.getLong(1);
            }
            else {
                throw new SQLException("Creating person failed, no ID obtained.");
            }

            statement.close();
            resultSet.close();

            return primaryKey;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void searchUser(Connection connection) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        do {
            getSearchMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Search on person id\n");
                    getOneUserOnPersonId(connection);
                    break;
                case 2:
                    System.out.println("Search on username\n");
                    getOneUserOnUsername(connection);
                    break;
                case 3:
                    System.out.println("Search on last name\n");
                    getOneUserOnLastname(connection);
                    break;
                case 4:
                    System.out.println("Back");
                    exit = true;
                    break;
            }

        } while (!exit);


    }

    public User getUserOnId(Connection connection, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person INNER JOIN user ON person.id = user.id_person WHERE user.id = ?" );
            statement.setInt(1, id);
            User user = setUserData(statement);
            System.out.println(user);

            statement.close();
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User getOneUserOnPersonId(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the person id...");
        int personId = Integer.parseInt(sc.next());
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person INNER JOIN user ON person.id = user.id_person WHERE person.id = ?" );
            statement.setInt(1, personId);
            User user = setUserData(statement);
            statement.close();
            System.out.println(user);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public User getOneUserOnUsername(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the username...");
        String username= sc.next();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person INNER JOIN user ON person.id = user.id_person WHERE username = ?" );
            statement.setString(1, username);
            User user = setUserData(statement);
            statement.close();
            System.out.println(user);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User getOneUserOnLastname(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the lastname...");
        String lastname= sc.next();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person INNER JOIN user ON person.id = user.id_person WHERE last_name = ?" );
            statement.setString(1, lastname);
            User user = setUserData(statement);
            statement.close();
            System.out.println(user);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User setUserData(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        User user = new User();
        Person person = new Person();
        while(rs.next()) {
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setAddress(rs.getString("address"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(person.getFirstName());
            user.setLastName(person.getLastName());
            user.setAddress(person.getAddress());
        }
        return user;
    }

    public void updateUser(Connection connection) throws SQLException {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        do {
            getUpdateMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Update password\n");
                    updatePassword(connection);
                    break;

                case 2:
                    System.out.println("Back\n");
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public User updatePassword(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the user id...");
        int userId = Integer.parseInt(sc.next());
        User user = getUserOnId(connection, userId);
        System.out.println("Input the new password...");
        String password= sc.next();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE id = ?");
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, userId);
        preparedStatement .executeUpdate();
        getUserOnId(connection, userId);

        preparedStatement.close();
	sc.close();
        return user;
    }

    public void deleteUserOnId(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Delete user on id---");
        System.out.println("Input the user id...");
        int userId = Integer.parseInt(sc.next());
        int personId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person INNER JOIN user ON person.id = user.id_person WHERE user.id = ?" );
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                System.out.println("You delete user - "+rs.getString(1)+" "+ rs.getString(2)+" "+rs.getString(3)+" "+ rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
                personId = Integer.parseInt(rs.getString("id_person"));
            }
            deletePersonOnId(connection, personId);
            deleteUserOnId(connection, userId);
            System.out.println("List of users");
            System.out.println(getAllUsers(connection));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePersonOnId(Connection connection, int personId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ? ");
            preparedStatement.setInt(1, personId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUserOnId(Connection connection, int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ? ");
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
