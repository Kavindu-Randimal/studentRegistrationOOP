import java.util.ArrayList;
import java.util.List;

public class StudentStoreManager implements StudentManager {

    private DatabaseControl databaseControl = new DatabaseControl();
    public static List<Students> StudentList = new ArrayList<>();


    @Override
    public void addStudent() {
        System.out.println("Enter Student Id - ");
        int studentId = getId();
        while (studentId <= 0){
            System.out.print("ID cannot be less than 1. Please re-enter : ");
            studentId = getId();
        }
        String studentName = getName();
        String studentAddress = getAddress();
        String studentBirthday = getBirthday();

        StudentList.add(new Students(studentId,studentName,studentAddress,studentBirthday));

        //inserting values to the MySQL database
        databaseControl.addField(studentId,studentName,studentAddress,studentBirthday);
    }

    private String getBirthday() {
        System.out.println("Enter the birthday - ");
        return Main.getStringInput();
    }

    private String getAddress() {
        System.out.println("Enter the Address - ");
        return Main.getStringInput();
    }

    private String getName() {
        System.out.println("Enter the Name - ");
        return Main.getStringInput();
    }

    private int getId() {
        DatabaseControl databaseControl = new DatabaseControl();
        int idValue = Main.getIntegerInput("the ID");
        int idValidation = databaseControl.idValidation(idValue);
        while (idValidation != 0) {
            System.out.print("ID " + idValue + "\' is already taken. re-enter the ID : ");
            idValue = Main.getIntegerInput("the ID");
            idValidation = databaseControl.idValidation(idValue);
        }
        return idValue;
    }

    @Override
    public void deleteStudent() {

        DatabaseControl databaseControl = new DatabaseControl();
        if (StudentList.size() > 0) {
            System.out.println("Enter the ID of the item you wish to delete : ");
            int itemIDToDelete = Main.getIntegerInput("the ID of the item to delete");
            for (int x = 0; x < StudentList.size(); x++) {
                if (StudentList.get(x).getId() == itemIDToDelete) {
                    databaseControl.deleteField(itemIDToDelete);
                    String studentName = StudentList.get(x).getName();
//                    String itemType = StudentList.get(x).getType();

                    System.out.print("Are you sure you want to delete " + studentName + " (y/n) : ");
                    char deleteConfirmation = Main.getCharInput();
                    if (deleteConfirmation == 'y') {
                        StudentList.remove(x);
                        System.out.println(studentName + " has been deleted successfully");
                    } else if (deleteConfirmation == 'n') {
                        System.out.println("You chose not to delete the item " + studentName);
                    } else {
                        System.out.println("Invalid input. Item was not deleted");
                    }
                }
            }
        } else {
            System.out.println("No items in the store to delete.");
        }

    }

    @Override
    public void showStudent() {
        if (StudentList.size() == 0) {
            System.out.println("Add atleast 1 entry to print the list of items.");
        } else {
            System.out.println("+--------------+----------------------------------+----------------------------------+------------------------+");
            System.out.printf("|  %10s  |  %30s  |  %30s  |  %20s  |\n", "Student ID", "Student Name", "Student Address", "Student BDay");
            System.out.println("+--------------+----------------------------------+----------------------------------+------------------------+");
            for (Students x : StudentList) {
                int currentID = x.getId();
                String currentName = x.getName();
                String currentAddress = x.getAddress();
                String currentBirthday= x.getBirthDay();
                System.out.printf("|  %10s  |  %30s  |  %30s  |  %20s  |\n", currentID, currentName, currentAddress,currentBirthday);
            }
            System.out.println("+--------------+----------------------------------+----------------------------------+------------------------+");
        }

    }
}
