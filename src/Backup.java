import java.util.Scanner;

/**
 * @author Esben, Marcus, Niels and Nikolai
 * @version 0.1
 */

public class Backup {

    /** main method below contains a menu, each calling a new method
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // present menu and allow user to navigate
        boolean isFinishedWithMenu = false;
        do {
            System.out.println("\nUPTOWN WATERWORKS INTERNAL SYSTEM");
            System.out.println("------------------------------------");
            System.out.println("1.\t Readings");
            System.out.println("2.\t Data management");
            System.out.println("3.\t Create bill");
            System.out.println("\n0.\t Exit system");

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
        boolean isFinishedWithMenu = false;
        while (!isFinishedWithMenu) {
            System.out.println("\nMENU 1: READINGS");
            System.out.println("-------------------");
            System.out.println("1.\t Add new reading");
            System.out.println("2.\t Show readings");
            System.out.println("\n0.\t Go back");
            boolean isValidInput = false;
            while (!isValidInput) {
                int choice = tryCatch();
                switch (choice) {
                    case 0:
                        isValidInput = true;
                        isFinishedWithMenu = true;
                        System.out.println("Closing system ... ");
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
                        System.out.println("Reading added!");
                        isValidInput = true;
                        break;
                    case 2:
                        showReadings();
                        isValidInput = true;
                        break;
                }
            }
        }
    }

    /**
     * method below manages data in the database
     */
    public static void dataManagement() {
        Scanner in = new Scanner(System.in);
        boolean isFinishedWithMenu = false;
        while (!isFinishedWithMenu) {
            System.out.println("\nMENU 2: DATA MANAGEMENT");
            System.out.println("-------------------");
            System.out.println("1.\t Show customers");
            System.out.println("2.\t Add customer");
            System.out.println("3.\t Update customer");
            System.out.println("4.\t Delete customer\n");
            System.out.println("5.\t Show segments");
            System.out.println("6.\t Add segment");
            System.out.println("7.\t Update prices and taxes for segment");
            System.out.println("8.\t Delete segment");
            System.out.println("\n0.\t Go back");

            boolean isValidInput = false;
            while (!isValidInput) {
                int choice = tryCatch();
                switch (choice) {
                    case 0:
                        isValidInput = true;
                        isFinishedWithMenu = true;
                        break;
                    case 1:
                        System.out.println("\nSHOW CUSTOMERS");
                        System.out.println("-------------------");
                        showCustomers();
                        isValidInput = true;
                        break;
                    case 2:
                        System.out.println("\nADD CUSTOMER");
                        System.out.println("-------------------");
                        System.out.print("Enter name: ");
                        String fldName = tryCatchStringNoText();
                        System.out.print("Enter address: ");
                        String fldAddress = tryCatchStringNoText();
                        System.out.print("Enter phone number: ");
                        String fldPhoneNo = tryCatchStringNoText();
                        insertCustomer(fldName, fldAddress, fldPhoneNo);
                        System.out.println("Customer added!");
                        isValidInput = true;
                        break;
                    case 3:
                        System.out.println("\nUPDATE CUSTOMER");
                        System.out.println("-------------------");
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
                        System.out.println("Customer updated!");
                        isValidInput = true;
                        break;
                    case 4:
                        System.out.println("\nDELETE CUSTOMER");
                        System.out.println("-------------------");
                        showCustomers();
                        System.out.print("Enter customer ID to delete customer: ");
                        int fldCustomerID = tryCatchNoText();
                        deleteCustomer(fldCustomerID);
                        System.out.println("Customer deleted!");
                        isValidInput = true;
                        break;
                    case 5:
                        System.out.println("\nSHOW SEGMENTS");
                        System.out.println("-------------------");
                        showSegments();
                        isValidInput = true;
                        break;
                    case 6:
                        System.out.println("\nADD SEGMENT");
                        System.out.println("-------------------");
                        System.out.print("Enter type name of customer: ");
                        String fldTypeName = tryCatchStringNoText();
                        System.out.print("Enter price of freshwater: ");
                        double fldFreshWaterPrice = tryCatchDoubleNoText();
                        System.out.print("Enter tax of freshwater: ");
                        double fldFreshWaterTax = tryCatchDoubleNoText();
                        System.out.print("Enter tax of drainage: ");
                        double fldDrainageTax = tryCatchDoubleNoText();
                        System.out.print("Enter year: ");
                        int fldYear = tryCatchNoText();
                        addSegment(fldTypeName, fldFreshWaterPrice, fldFreshWaterTax,
                                fldDrainageTax, fldYear);
                        System.out.println("Segment added!");
                        isValidInput = true;
                        break;
                    case 7:
                        System.out.println("\nUPDATE TAX FOR SEGMENT");
                        System.out.println("-------------------");
                        showSegments();
                        System.out.print("Enter ID number of the type you want to change: ");
                        int fldTypeID = tryCatchNoText();
                        System.out.print("Enter new price for freshwater: ");
                        fldFreshWaterPrice = tryCatchDoubleNoText();
                        System.out.print("Enter new tax for freshwater: ");
                        fldFreshWaterTax = tryCatchDoubleNoText();
                        System.out.print("Enter new tax for drainage: ");
                        fldDrainageTax = tryCatchDoubleNoText();
                        System.out.print("Enter year: ");
                        fldYear = tryCatchNoText();
                        updateTaxForSegment(fldTypeID, fldFreshWaterPrice, fldFreshWaterTax, fldDrainageTax, fldYear);
                        System.out.println("Segment updated!");
                        isValidInput = true;
                        break;
                    case 8:
                        System.out.println("\nDELETE SEGMENT");
                        System.out.println("-------------------");
                        showSegments();
                        System.out.print("Enter type ID to delete segment: ");
                        fldTypeID = tryCatchNoText();
                        deleteSegment(fldTypeID);
                        System.out.println("Segment deleted!");
                        isValidInput = true;
                        break;
                }
            }
        }
    }

    /**
     *  method below takes value from database to calculates a consumption and price of water
     */
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

    /**
     * method below shows everything in table Customers from the database
     */
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

    /**
     * method below shows everything in table Readings from the database
     */
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

    /**
     * method below shows everything in table Water Meters from the database
     */
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

    /**
     * method below inserts a reading to database and is used in collection()
     * @param fldCustomerID
     * @param fldMeterID
     * @param fldWaterConsumption
     * @param fldDate
     * @param fldReader
     */
    public static void insertReading(int fldCustomerID, int fldMeterID,
                                     int fldWaterConsumption, String fldDate, String fldReader) {

        DB.insertSQL("INSERT INTO tblReadings VALUES (" + fldCustomerID + "," + fldMeterID +
                "," + fldWaterConsumption + ",'" + fldDate + "','" + fldReader + "');");
    }

    /**
     * method below inserts a customer to database and is used in dataManagement()
     * @param fldName
     * @param fldAddress
     * @param fldPhoneNo
     */
    public static void insertCustomer(String fldName, String fldAddress, String fldPhoneNo) {

        DB.insertSQL("INSERT INTO tblCustomers VALUES (" + "'" + fldName + "','" + fldAddress + "','" + fldPhoneNo + "');");
    }

    /**
     * methow below updates a customer in database and is used in dataManagement()
     * @param fldName
     * @param fldAddress
     * @param fldPhoneNo
     * @param customerID
     */
    public static void updateCustomer(String fldName, String fldAddress, String fldPhoneNo, int customerID) {
        DB.updateSQL("UPDATE tblCustomers SET fldName = '" + fldName + "', fldAddress = '" + fldAddress + "', fldPhoneNo = '" + fldPhoneNo +
                "' WHERE fldCustomerID = " + customerID +";");
    }

    /**
     * method below deletes a customer from database and is used in dataManagement()
     * @param fldCustomerID
     */
    public static void deleteCustomer(int fldCustomerID) {
        DB.deleteSQL("DELETE FROM tblCustomers WHERE fldCustomerID = ('" + fldCustomerID + "')");
    }

    /**
     * method below shows everything in table Tax from the database
     */
    public static void showSegments() {
        DB.selectSQL("SELECT * FROM tblTax");
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

    /**
     * method below adds a segment to database and is used in dataManagement()
     * @param fldTypeName
     * @param fldFreshWaterPrice
     * @param fldFreshWaterTax
     * @param fldDrainageTax
     * @param fldYear
     */
    public static void addSegment(String fldTypeName, double fldFreshWaterPrice, double fldFreshWaterTax,
                                  double fldDrainageTax, int fldYear) {
        DB.insertSQL("INSERT INTO tblTax VALUES (" + "'" + fldTypeName + "'," + fldFreshWaterPrice + "," +
                fldFreshWaterTax + "," + fldDrainageTax + "," + fldYear + ");");
    }

    /**
     * method below updates taxes for segments in database and is used in dataManagement()
     * @param fldTypeID
     * @param fldFreshWaterTax
     * @param fldDrainageTax
     * @param fldYear
     */
    public static void updateTaxForSegment(int fldTypeID, double fldFreshWaterPrice, double fldFreshWaterTax, double fldDrainageTax, int fldYear) {
        DB.updateSQL("UPDATE tblTax SET fldFreshWaterPrice = " + fldFreshWaterPrice + ", fldFreshWaterTax = " + fldFreshWaterTax +
                ", fldDrainageTax = " + fldDrainageTax + ", fldYear = " + fldYear + " WHERE fldTypeID = " + fldTypeID +";");

    }

    /**
     * method below deletes a segment from database and is used in dataManagement()
     * @param fldTypeID
     */
    public static void deleteSegment (int fldTypeID) {
        DB.deleteSQL("DELETE FROM tblTax WHERE fldTypeID = ('" + fldTypeID + "')");
    }

    /**
     * NOT YET COMPLETED
     */
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


    /** method below replaces manually implementing a try catch INT
     * @return
     */
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
    /** method below replaces manually implementing a try catch INT with NO TEXT
     * @return
     */
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
    /** method below replaces manually implementing a try catch DOUBLE with NO TEXT
     * @return
     */
    public static double tryCatchDoubleNoText() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                double choice = in.nextDouble();
                return choice;
            }
            catch (Exception e) {
                in.next();
            }
        }
    }
    /** method below replaces manually implementing a try catch STRING with NO TEXT
     * @return
     */
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