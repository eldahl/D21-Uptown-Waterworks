import javax.swing.text.DateFormatter;
import java.awt.font.LineBreakMeasurer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Esben, Marcus, Niels and Nikolai
 * @version 0.1
 */

public class Main {

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
                    case 0 -> {
                        isValidInput = true;
                        isFinishedWithMenu = true;
                        System.out.println("Closing system ... ");
                    }
                    case 1 -> {
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
                    }
                    case 2 -> {
                        showReadings();
                        isValidInput = true;
                    }
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
        System.out.println("1.\t Add readings to bill");
        System.out.println("2.\t Calculate total bill price");
        System.out.println("0.\t Go back");

        boolean isValidInput = false;
        firstLoop:
        while (!isValidInput) {
            int choice = tryCatch();
            switch (choice) {
                case 0 -> isValidInput = true;
                case 1 -> {
                    showCustomers();
                    System.out.print("Enter customer ID to select customer: ");
                    int customerID = tryCatchNoText();
                    int billID = 0;

                    // Choose whether to make a new bill or add to an existing one
                    System.out.println("1.\t Create new bill");
                    System.out.println("2.\t Add to existing bill");

                    System.out.println("\n0.\t Go back");
                    int secondChoice = tryCatchNoText();

                    boolean isValidInput2 = false;
                    while (!isValidInput2) {
                        switch (secondChoice) {
                            case 0:
                                isValidInput2 = true;
                                break firstLoop;
                            case 1: {
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                String date = df.format(LocalDateTime.now());
                                insertBill(customerID, date);
                                selectBillID(customerID, date);
                                String data = DB.getDisplayData();
                                data = data.replace("\n", "");
                                billID = Integer.parseInt(data);
                                isValidInput2 = true;
                            }
                            break;
                            case 2: {
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                String date = df.format(LocalDateTime.now());

                                if (!ShowBillsByCustomerID(customerID)) {
                                    System.out.println("Enter bill id of bill you want to update with a reading: ");
                                    billID = tryCatchNoText();
                                } else {
                                    insertBill(customerID, date);
                                    selectBillID(customerID, date);
                                    String data = DB.getDisplayData();
                                    data = data.replace("\n", "");
                                    billID = Integer.parseInt(data);
                                }
                                isValidInput2 = true;
                            }
                            break;
                        }
                    }
                    ShowReadingsByCustomerID(customerID);
                    System.out.print("Choose meter ID: ");
                    int meterID = tryCatchNoText();
                    showReadingsByMeterID(meterID);
                    System.out.print("Choose first reading: ");
                    int firstReading = tryCatchNoText();
                    System.out.print("Choose second reading: ");
                    int secondReading = tryCatchNoText();

                    DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String date = df1.format(LocalDateTime.now());

                    insertMeterUsage(meterID, billID, firstReading, secondReading, date);
                    System.out.println("Water usage added to database!");


                    isValidInput = true;
                }
                case 2 -> {
                    // Select which customer we are working with
                    showCustomers();
                    System.out.print("Enter customer ID to select customer: ");
                    int customerID = tryCatchNoText();

                    // Select which bill to calculate price for
                    ShowBillsByCustomerID(customerID);
                    System.out.print("Choose bill ID: ");
                    int billID = tryCatchNoText();

                    // Get price of each meter usage
                    ArrayList<Integer> readingIDs = GetMeterUsagesFromBillID(billID);
                    if (readingIDs == null) {
                        break;
                    }
                    for (int i = 0; i < readingIDs.size(); i += 2) {
                        MeterUsage usage = new MeterUsage();

                        int id1 = readingIDs.get(i);
                        int id2 = readingIDs.get(i + 1);

                        // Water consumption
                        usage.WaterConsumption = GetWaterConsumptionByReadingIDs(id1, id2);

                        // Tax
                        GetMeterTaxByReadingID(id1, usage);

                        // Total price for meter usage
                        CalculateTotalPriceForMeterUsage(usage);

                    }


                    // Calculate total price for all meters of the bill

                    // add tax

                    // add MOMS

                    // Send data to bank and generate giro card
                    System.out.println("Sending giro data to bank...!");

                    isValidInput = true;
                }
            }
        }
    }

    public static float GetWaterConsumptionByReadingIDs(int readingID1, int readingID2) {

        DB.selectSQL("SELECT fldWaterConsumption FROM tblReadings WHERE fldReadingID = " + readingID1 + ";");
        float consumption1 = Float.parseFloat(DB.getData());

        DB.selectSQL("SELECT fldWaterConsumption FROM tblReadings WHERE fldReadingID = " + readingID2 + ";");
        float consumption2 = Float.parseFloat(DB.getData());

        // DEBUG
        //System.out.println("1: " + consumption1 + " | 2: " + consumption2 + " | diff: " + (consumption2 - consumption1));

        // Get the difference between the two
        return consumption2 - consumption1;
    }

    public static void CalculateTotalPriceForMeterUsage(MeterUsage usage) {

    }

    public static void GetMeterTaxByReadingID(int readingID, MeterUsage usage) {

    }

    /**
     * Displays meter readings of a given customer ID.
     * @param customerID The ID of the customer whos meter readings to display.
     */
    public static void ShowReadingsByCustomerID(int customerID) {
        System.out.print("Showing readings table...\n");
        System.out.println("ID | Customer ID | Meter ID | Consumption | Date | Reader");
        DB.selectSQL("SELECT * FROM tblReadings WHERE fldCustomerID = " + customerID + ";");
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
     * Displays all bills for the given customer ID.
     * @param customerID The customer whos bills to display.
     */
    public static boolean ShowBillsByCustomerID(int customerID){
        boolean doFirstIteration = true;
        DB.selectSQL("SELECT * FROM tblBill WHERE fldCustomerID = " + customerID + ";");
        do {
            String data = DB.getDisplayData();

            if(data.equals("|ND|") || data.equals("")  || data.equals("\n")  || data.equals(" ")) {
                return false;
            }

            if(doFirstIteration) {
                System.out.print("Showing bill table...\n");
                System.out.println("Bill ID | Customer ID | Date");
                doFirstIteration = false;
            }

            if (data.equals(DB.NOMOREDATA)) {
                break;
            }
            else {
                System.out.print(data);
            }


        } while (true);

        return true;
    }

    /**
     * Gets the water meter usage measurements from the tblMeterUsage table.
     * @param billID The billID to get usage measurements from.
     */
    public static ArrayList<Integer> GetMeterUsagesFromBillID(int billID) {
        //System.out.print("Showing meter usage table...\n");
        DB.selectSQL("SELECT fldFirstReading, fldSecondReading FROM tblMeterUsage WHERE fldBillID = " + billID + ";");
        ArrayList<Integer> readingIDs = new ArrayList<Integer>();
        do {
            String data = DB.getDisplayData();

            if (data.equals(DB.NOMOREDATA)) {
                break;
            }

            data = data.replace("\n", "");
            data = data.replace(" ", "");
            if(data.equals("|ND|")) {
                System.out.println("No data found, returning");
                return null;
            }
            int i = Integer.parseInt(data);
            readingIDs.add(i);
        } while (true);
        return readingIDs;
    }

    public static void showWaterMeters(int customerID) {
        System.out.print("Showing water meter table...\n");
        DB.selectSQL("SELECT * FROM tblWaterMeter WHERE fldCustomerID = " + customerID + ";");
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

    public static void showMeterUsage() {
        System.out.print("Showing meter usage table...\n");
        System.out.println("ID | Meter ID | Bill ID | First reading | Second reading | Date");
        DB.selectSQL("SELECT * FROM tblMeterUsage ;");
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

    public static void calculation() {
        int totalPrice = 0;
        double moms = 1.25;
        int counter = 0;
        boolean finishedWithCustomer = false;
        while (!finishedWithCustomer) {

            showMeterUsage();

            System.out.print("Enter meter usage: ");
            int waterConsumption = tryCatchNoText();

            showSegments();

            System.out.print("Enter price of freshwater: ");
            int freshWaterPrice = tryCatchNoText();
            System.out.print("Enter tax for freshwater");
            int freshWaterTax = tryCatchNoText();
            System.out.print("");
            int drainageTax = tryCatchNoText();

            double priceOfWater = waterConsumption * freshWaterPrice + (waterConsumption * freshWaterTax) + (waterConsumption * drainageTax) * moms;

            totalPrice += priceOfWater;

            counter++;

            System.out.println("Price of meter " + counter + "= " + priceOfWater);
        }
        System.out.print("Price to be added to girocard = " + totalPrice);
    }

    /**
     * Method selects a bill ID from a customer ID in the bill table
     * @param customerID
     * @param date
     */
    public static void selectBillID(int customerID, String date) {
        DB.selectSQL("SELECT fldBillID FROM tblBill WHERE fldCustomerID =" + customerID + "AND fldDate = '" + date + "';");
    }

    /**
     * Displays all bills under a given customer ID.
     * @param customerID The ID of the customer.
     */
    public static void showBillsByCustomerID(int customerID) {
        DB.selectSQL("SELECT * FROM tblBill WHERE fldCustomerID = " + customerID + ";");
    }

    /**
     *  method below inserts a bill to database and is used in billing()
     * @param CustomerID
     * @param date
     */
    public static void insertBill (int CustomerID, String date) {
        DB.insertSQL("INSERT INTO tblBill VALUES (" + CustomerID + ",'" + date + "');");

    }

    /**
     * Inserts a row of data into the tblMeterUsage table with the given data.
     * @param meterID Water Meter ID
     * @param billID Bill ID
     * @param firstReading First reading
     * @param secondReading Second reading
     * @param date the date of the consumption measurement
     */
    public static void insertMeterUsage(int meterID, int billID, int firstReading, int secondReading, String date) {
        DB.insertSQL("INSERT INTO tblMeterUsage VALUES (" + meterID + ", " + billID + ", " + firstReading + ", " + secondReading + ", '" + date + "');");
    }

    /**
     * Fetches a list of readings for the given customer ID
     * @param customerID The customer whos readings we will get
     */
    public static void showReadings(int customerID) {
        System.out.print("Showing readings table...\n");
        DB.selectSQL("SELECT * FROM tblReadings WHERE fldCustomerID = " + customerID + ";");
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

    public static void showReadingsByMeterID(int meterID) {
        System.out.print("Showing readings table...\n");
        DB.selectSQL("SELECT * FROM tblReadings WHERE fldMeterID = " + meterID + ";");
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
     * method below shows everything in table Customers from the database
     */
    public static void showCustomers() {
        System.out.print("Showing customer table...\n");
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
        System.out.print("Showing readings table...\n");
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
        System.out.print("Showing Water meter table...\n");
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
        System.out.print("Showing tax table...\n");
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
    public static void updateTaxForSegment(int fldTypeID, double fldFreshWaterPrice, double fldFreshWaterTax,
                                           double fldDrainageTax, int fldYear) {
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