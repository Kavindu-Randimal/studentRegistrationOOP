import java.util.Scanner;

public class Main {
    public static void main (String [] args) throws Exception {
        int menuOption;
        DatabaseControl databaseController = new DatabaseControl();

        do {
            StudentStoreManager studentStoreManager = new StudentStoreManager();
            consoleMenu();
            menuOption = getIntegerInput("menuOption");

            switch (menuOption){
                case 1 :studentStoreManager.addStudent();
                break;
                case 2 :studentStoreManager.deleteStudent();
                break;
                case 3 :studentStoreManager.showStudent();
                break;
                case 4 : System.out.println("You chose to exit the program. Have a nice day!");
                break;
                default:
                    System.out.println("Menu option out of range");
            }
        }while (menuOption != 4);
    }

    private static void consoleMenu(){
        System.out.println("\nPlease select an option from the menu.");
        System.out.println("1.  Add a Student");
        System.out.println("2.  Delete a Student");
        System.out.println("3.  Show Student List");
        System.out.println("4.  Exit");
        System.out.println("<--------------------------------------->");
    }

    public static int getIntegerInput(String reenterValue){
        Scanner input = new Scanner(System.in);     //initializing scanner to input menu option
        while(!input.hasNextInt()){
            System.out.println("Invalid input. Please re-enter " + reenterValue);
            input.nextLine();
        }
        return input.nextInt();
    }

    public static String getStringInput(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static char getCharInput(){
        Scanner input = new Scanner(System.in);
        return input.next().toLowerCase().charAt(0);
    }

}
