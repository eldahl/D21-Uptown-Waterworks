import java.util.Scanner;

/**
 * @author Esben, Marcus, Niels and Nikolai
 * @version 0.1
 */

public class Main {

    // main method below contains a menu, each calling a new method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // present menu and allow user to navigate
        boolean isFinishedWithMenu = false;
        do {
            System.out.println("\nUptown Waterworks internal system");
            System.out.println("1.\t Readings");
            System.out.println("2.\t Data management");
            System.out.println("3.\t Create bill");
            System.out.println("0.\t Exit system");

            boolean isValidInput = false;
            while (!isValidInput) {

                int choice = tryCatch();
                switch (choice) {
                    case 0:
                        System.exit(1);
                    case 1:
                        collection();
                        isValidInput = true;
                        break;
                    case 2:
                        dataManagement();
                        isValidInput = true;
                        break;
                    case 3:
                        billing();
                        isValidInput = true;
                        break;
                }
            }
        } while (!isFinishedWithMenu);
    }

    /**
     * method below allows the user to show readings and add new a reading
     */
    public static void collection() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nMENU 1: READINGS");
        System.out.println("-------------------");
        System.out.println("1.\t Add new reading");
        System.out.println("2.\t Show readings");
        System.out.println("0.\t Go back");

        boolean isValidInput = false;
        while (!isValidInput) {
            int choice = tryCatch();
            switch (choice) {
                case 0:
                    isValidInput = true;
                    break;
                case 1:
                    showCustomers();
                    System.out.println("Enter customer ID");
                    int fldCustomerID = tryCatchNoText();
                    showMeter();
                    System.out.println("Enter Meter ID");
                    int fldMeterID = tryCatchNoText();
                    System.out.println("Enter Water Consumption");
                    int fldWaterConsumption = tryCatchNoText();
                    System.out.println("Enter date (yyyy-mm-dd)");
                    String fldDate = tryCatchStringNoText();
                    System.out.println("Enter name of reader");
                    String fldReader = tryCatchStringNoText();

                    insertReading(fldCustomerID, fldMeterID, fldWaterConsumption, fldDate, fldReader);
                    isValidInput = true;
                    break;
                case 2:
                    showReadings();
                    isValidInput = true;
                    break;
            }
        }
    }

    /**
     * method below contains five submenus
     */
    public static void dataManagement() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nMENU 2: DATA MANAGEMENT");
        System.out.println("-------------------");
        System.out.println("1.\t Show customers");
        System.out.println("2.\t Add customer");
        System.out.println("3.\t Update customer");
        System.out.println("4.\t Delete customer\n");
        System.out.println("5.\t Show segments");
        System.out.println("8.\t Add segment");
        System.out.println("6.\t Update segments");
        System.out.println("7.\t Delete segments\n");
        System.out.println("9.\t Add new tax year");
        System.out.println("0.\t Go back");

        boolean isValidInput = false;
        while (!isValidInput) {
            int choice = tryCatch();
            switch (choice) {
                case 0:
                    isValidInput = true;
                    break;
                case 1:
                    showCustomers();
                    isValidInput = true;
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String fldName = tryCatchStringNoText();
                    System.out.print("Enter address: ");
                    String fldAddress = tryCatchStringNoText();
                    System.out.print("Enter phone number: ");
                    String fldPhoneNo = tryCatchStringNoText();
                    insertCustomer(fldName, fldAddress, fldPhoneNo);
                    isValidInput = true;
                    break;
                case 3:
                    showCustomers();
                    System.out.print("Enter customer ID: ");
                    int customerID = tryCatchNoText();
                    System.out.print("Enter name: ");
                    fldName = tryCatchStringNoText();
                    System.out.print("Enter address: ");
                    fldAddress = tryCatchStringNoText();
                    System.out.print("Enter phone number: ");
                    fldPhoneNo = tryCatchStringNoText();
                    updateCustomer(fldName, fldAddress, fldPhoneNo, customerID);
                    isValidInput = true;
                    break;
                case 4:
                    isValidInput = true;
                    break;
                case 5:
                    showCustomers();
                    isValidInput = true;
                    break;
                case 6:
                    isValidInput = true;
                    break;
                case 7:
                    showCustomers();
                    isValidInput = true;
                    break;
                case 8:
                    isValidInput = true;
                    break;
                case 9:
                    showCustomers();
                    isValidInput = true;
                    break;
            }
        }
    }

    public static void billing() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nMENU 3: CREATE BILL");
        System.out.println("-------------------");
        System.out.println("1.\t Calculate price");
        System.out.println("0.\t Go back");

        boolean isValidInput = false;
        while (!isValidInput) {
            int choice = tryCatch();
            switch (choice) {
                case 0:
                    isValidInput = true;
                    break;
                case 1:
                    System.out.println("CALCULATE PRICE OF WATER CONSUMPTION");
                    calculatePriceOfWaterConsumption();
                    isValidInput = true;
                    break;
            }
        }
    }

    public static void showMeter() {
        DB.selectSQL("SELECT * FROM tblWaterMeter");
        do {
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            }
            else {
                System.out.print(data);
            }
        } while (true);
    }

    public static void showCustomers() {
        DB.selectSQL("SELECT * FROM tblCustomers");
        do {
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            }
            else {
                System.out.print(data);
            }
        } while (true);
    }

    public static void insertCustomer(String fldName, String fldAddress, String fldPhoneNo) {

        DB.insertSQL("INSERT INTO tblCustomers VALUES (" + "'" + fldName + "','" + fldAddress + "','" + fldPhoneNo + "');");
    }

    public static void updateCustomer(String fldName, String fldAddress, String fldPhoneNo, int customerID) {
        DB.updateSQL("UPDATE tblCustomers SET fldName = '" + fldName + "', fldAddress = '" + fldAddress + "', fldPhoneNo = '" + fldPhoneNo +
                "' WHERE fldCustomerID = " + customerID +";");
    }



    public static void showReadings() {
        DB.selectSQL("SELECT * FROM tblReadings");
        do {
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            }
            else {
                System.out.print(data);
            }
        } while (true);
    }

    public static void insertReading(int fldCustomerID, int fldMeterID,
                                     int fldWaterConsumption, String fldDate, String fldReader) {

        DB.insertSQL("INSERT INTO tblReadings VALUES (" + fldCustomerID + "," + fldMeterID +
                "," + fldWaterConsumption + ",'" + fldDate + "','" + fldReader + "');");
    }

    public static void calculatePriceOfWaterConsumption() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter customer ID: ");
        int customerID = tryCatchNoText();
        System.out.println("Enter water consumption for previous period: ");
        int previousWaterConsumption = tryCatchNoText();
        System.out.println("Enter water consumption for last period: ");
        int lastWaterConsumption = tryCatchNoText();

        int waterConsumption = Math.abs(previousWaterConsumption - lastWaterConsumption);
        double priceOfWater = 7;
        double waterTaxHousehold = 10;
        double waterTaxIndustry = 9;
        double waterTaxAgriculture = 8;
        double drainageTaxHousehold = 10;
        double drainageTaxIndustry = 9;
        double drainageTaxAgriculture = 8;
        double priceOfWaterConsumption = 0;


        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Select customer type: ");
            System.out.println("1.\t Household");
            System.out.println("2.\t Industry");
            System.out.println("3.\t Agriculture");
            System.out.println("0.\t Go back");

            int customerType = tryCatchNoText();

            switch (customerType) {
                case 0:
                    break;
                case 1:
                    priceOfWaterConsumption = waterConsumption * priceOfWater +
                            (waterConsumption * waterTaxHousehold) +
                            (waterConsumption * drainageTaxHousehold);
                    isValidInput = true;
                    break;
                case 2:
                    priceOfWaterConsumption = waterConsumption * priceOfWater +
                            (waterConsumption * waterTaxIndustry) +
                            (waterConsumption * drainageTaxIndustry);
                    isValidInput = true;
                    break;
                case 3:
                    priceOfWaterConsumption = waterConsumption * priceOfWater +
                            (waterConsumption * waterTaxAgriculture) +
                            (waterConsumption * drainageTaxAgriculture);
                    isValidInput = true;
                    break;
            }
            System.out.println("Price of water = " + priceOfWaterConsumption);

            DB.insertSQL("INSERT INTO readings VALUES");
        }
    }

    public static void updateCustomer() {

        DB.updateSQL("UPDATE department SET XYZ =");
    }

    public static void updateSegment() {

        DB.updateSQL("UPDATE department SET XYZ =");
    }

    public static void regulateTaxes(int typeID, double freshWaterTax, double drainageTax) {

        DB.insertSQL("INSERT INTO taxes VALUES");
    }

    public static void addSettlement() {

        DB.insertSQL("INSERT INTO settlement VALUES");
    }

    // method below makes replaces manually implementing a try catch
    public static int tryCatch() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Navigate to: ");
                int choice = in.nextInt();
                return choice;
            }
            catch (Exception e) {
                in.next();
            }
        }
    }
    // method below makes replaces manually implementing a try catch NO TEXT
    public static int tryCatchNoText() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                int choice = in.nextInt();
                return choice;
            }
            catch (Exception e) {
                in.next();
            }
        }
    }
    // method below makes replaces manually implementing a try catch NO TEXT, STRING EDT
    public static String tryCatchStringNoText() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String choice = in.nextLine();
                return choice;
            }
            catch (Exception e) {
                in.nextInt();
            }
        }
    }

} // end of public class Main