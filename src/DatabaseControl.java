import java.sql.*;

public class DatabaseControl {
    private Connection connection;

    public void DatabaseControl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsRecords", "root", "");
        } catch (Exception e) {
            System.out.println("Could not connect to mySQL database");
            System.out.println(e.getMessage());
        }

    }

    public Connection getConnection() {
        return this.connection;
    }

    public void addField(int stuentId, String studentName, String studentAddress, String studentBirthday){
        getConnection();

        try {
            String addField = "INSERT INTO studentsRecords VALUES (?, ?, ?, ?)";
            PreparedStatement runQuery = connection.prepareStatement(addField);
            runQuery.setInt(1,stuentId);
            runQuery.setString(2, studentName);
            runQuery.setString(3, studentAddress);
            runQuery.setString(4, studentBirthday);;

            runQuery.execute();
        }catch (Exception e){
            System.out.println("Could not add the field");
            System.out.println(e.getMessage());
        }
    }

    public void deleteField(int idToDelete){
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM studentsRecords WHERE stuentId = " +idToDelete;
            statement.executeUpdate(deleteQuery);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int idValidation(int assignedId){
        DatabaseControl databaseControl = new DatabaseControl();
        Connection connection = databaseControl.getConnection();
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            String inputValidation = "SELECT * FROM studentsRecords WHERE itemID = " + assignedId;
            ResultSet resultSet = statement.executeQuery(inputValidation);
            if (resultSet.next()) {
                do {
                    id = resultSet.getInt(1);
                } while (resultSet.next());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }
}