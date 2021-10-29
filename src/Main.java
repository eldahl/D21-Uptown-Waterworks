import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Esben, Marcus, Niels and Nikolai
 * @version 0.9
 */

public class Main {

    /** main method below contains a menu, each calling a new method
     *
     * @param args main menu
     */
    public static void main(String[] args) {

        // present menu and allow user to navigate
        while (true) { // main menu must loop at all times
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
                    case 0 -> { // exit application
                        System.out.println("Closing application ...");
                        System.exit(1);
                    }
                    case 1 -> {
                        collection(); // opens new menu called "Readings"
                        isValidInput = true;
                    }
                    case 2 -> {
                        dataManagement(); // opens new menu called "Data management"
                        isValidInput = true;
                    }
                    case 3 -> {
                        billing(); // opens new menu called "Create bill"
                        isValidInput = true;
                    }
                }
            }
        }
    }

    /**
     * method allows the user to show readings and add new a reading
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
                    case 0 -> { // go back to main menu
                        isValidInput = true;
                        isFinishedWithMenu = true;
                    }
                    case 1 -> { // add a new reading
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
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 2 -> { // show readings
                        showReadings();
                        pressEnterKeyToContinue();
                        isValidInput = true;
                        }
                    }
                }
            }
        }

    /**
     * method manages data in the database
     */
    public static void dataManagement() {
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
                    case 0 -> { // go back to main menu
                        isValidInput = true;
                        isFinishedWithMenu = true;
                    }
                    case 1 -> { // show customers
                        System.out.println("\nSHOW CUSTOMERS");
                        System.out.println("-------------------");
                        showCustomers();
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 2 -> { // add a new customer
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
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 3 -> { // update an existing customer
                        System.out.println("\nUPDATE CUSTOMER");
                        System.out.println("-------------------");
                        showCustomers();
                        System.out.print("Enter customer ID: ");
                        int customerID = tryCatchNoText();
                        System.out.print("Enter name: ");
                        String fldName = tryCatchStringNoText();
                        System.out.print("Enter address: ");
                        String fldAddress = tryCatchStringNoText();
                        System.out.print("Enter phone number: ");
                        String fldPhoneNo = tryCatchStringNoText();
                        updateCustomer(fldName, fldAddress, fldPhoneNo, customerID);
                        System.out.println("Customer updated!");
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 4 -> { // delete an existing customer
                        System.out.println("\nDELETE CUSTOMER");
                        System.out.println("-------------------");
                        showCustomers();
                        System.out.print("Enter customer ID to delete customer: ");
                        int fldCustomerID = tryCatchNoText();
                        deleteCustomer(fldCustomerID);
                        System.out.println("Customer deleted!");
                        isValidInput = true;
                    }
                    case 5 -> { // show customer types and corresponding tax rates
                        System.out.println("\nSHOW SEGMENTS");
                        System.out.println("-------------------");
                        showSegments();
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 6 -> { // add customer type and corresponding tax rates
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
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 7 -> { // update freshwater price and tax rates in tblTax
                        System.out.println("\nUPDATE TAX FOR SEGMENT");
                        System.out.println("-------------------");
                        showSegments();
                        System.out.print("Enter ID number of the type you want to change: ");
                        int fldTypeID = tryCatchNoText();
                        System.out.print("Enter new price for freshwater: ");
                        double fldFreshWaterPrice = tryCatchDoubleNoText();
                        System.out.print("Enter new tax for freshwater: ");
                        double fldFreshWaterTax = tryCatchDoubleNoText();
                        System.out.print("Enter new tax for drainage: ");
                        double fldDrainageTax = tryCatchDoubleNoText();
                        System.out.print("Enter year: ");
                        int fldYear = tryCatchNoText();
                        updateTaxForSegment(fldTypeID, fldFreshWaterPrice, fldFreshWaterTax, fldDrainageTax, fldYear);
                        System.out.println("Segment updated!");
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                    case 8 -> { // delete an existing customer type
                        System.out.println("\nDELETE SEGMENT");
                        System.out.println("-------------------");
                        showSegments();
                        System.out.print("Enter type ID to delete segment: ");
                        int fldTypeID = tryCatchNoText();
                        deleteSegment(fldTypeID);
                        System.out.println("Segment deleted!");
                        pressEnterKeyToContinue();
                        isValidInput = true;
                    }
                }
            }
        }
    }

    /**
     *  method takes value from database to calculate a consumption and price of water
     */
    public static void billing() {
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

                    boolean isValidInputInnerLoop = false;
                    while (!isValidInputInnerLoop) {
                        switch (secondChoice) {
                            case 0 -> { break firstLoop; }
                            case 1 -> {
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                String date = df.format(LocalDateTime.now());
                                insertBill(customerID, date);
                                selectBillID(customerID, date);
                                String data = DB.getDisplayData();
                                data = data.replace("\n", "");
                                billID = Integer.parseInt(data);
                                isValidInputInnerLoop = true;
                            }
                            case 2 -> {
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
                                isValidInputInnerLoop = true;
                            }
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
                case 2 -> { // Select which customer we are working with
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
                    ArrayList<MeterUsage> meterUsages = new ArrayList<MeterUsage>();
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
                        // Add to list for calculating total of all meters
                        meterUsages.add(usage);
                    }

                    // Calculate total price for all meters of the bill
                    double totalPrice = 0;
                    for(MeterUsage mu : meterUsages) {
                        totalPrice += mu.TotalPrice;
                    }

                    // add MOMS
                    totalPrice = totalPrice * 1.25;

                    System.out.println("\nTotal price for bill: " + totalPrice);

                    // Send data to bank and generate giro card
                    System.out.println("Press ENTER key to send data to bank...");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    System.out.println("Sending giro data to bank...!");
                    pressEnterKeyToContinue();
                    isValidInput = true;
                }
            }
        }
    }

    /**
     * method pulls two readings from tblReadings and calculates the absolute of these values
     * @param readingID1 first reading
     * @param readingID2 second reading
     * @return value for water consumption
     */
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

    /**
     * method calculates a total price based on single values
     * @param usage freshwater consumption
     */
    public static void CalculateTotalPriceForMeterUsage(MeterUsage usage) {
        double price = (usage.WaterConsumption * usage.FreshWaterPrice);
        price += (usage.FreshWaterTax * usage.WaterConsumption);
        price += (usage.DrainageTax * usage.WaterConsumption);

        usage.TotalPrice = price;
    }

    /**
     * get tax for meters by a given reading ID
     * @param readingID needed to differentiate
     * @param usage water consumption
     */
    public static void GetMeterTaxByReadingID(int readingID, MeterUsage usage) {
        DB.selectSQL("SELECT fldMeterID FROM tblReadings WHERE fldReadingID = " + readingID + ";");
        int meterID = Integer.parseInt(DB.getData());
        DB.selectSQL("SELECT fldTaxTypeID FROM tblWaterMeter WHERE fldWaterMeterID = " + meterID + ";");
        int taxTypeID = Integer.parseInt(DB.getData());
        DB.selectSQL("SELECT * FROM tblTax WHERE fldTypeID = " + taxTypeID + ";");

        int id = Integer.parseInt(DB.getData());
        String taxName = DB.getData().replace(" ", "");

        usage.FreshWaterPrice = Float.parseFloat(DB.getData());
        usage.FreshWaterTax = Float.parseFloat(DB.getData());
        usage.DrainageTax = Float.parseFloat(DB.getData());
    }

    /**
     * Displays meter readings of a given customer ID.
     * @param customerID The ID of the customer whose meter readings to display.
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
        System.out.println("ID | Customer ID | Meter ID | Consumption | Date | Reader");
    }

    /**
     * Displays all bills for the given customer ID.
     * @param customerID The customer whose bills to display.
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

    /**
     * method isn't used in current version but might be relevant later. Show meter belonging to customerID
     * @param customerID necessary in order to isolate water meters
     */
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

    /**
     * method isn't used in current version but might be relevant later. Shows meter usage
     */
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

    /**
     * Method selects a bill ID from a customer ID in the bill table
     * @param customerID select every bill belonging to this customerID
     * @param date which day is the bill from
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
     *  method inserts a bill to database and is used in billing()
     * @param CustomerID choose which customer to add bill to
     * @param date automatically uses the current day
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
     * @param customerID The customer whose readings we will get
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

    /**
     * method shows every reading done in meterID chosen by user
     * @param meterID chosen by user
     */
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
     * method shows everything in table Customers from the database
     */
    public static void showCustomers() {
        System.out.print("Showing customer table...\n");
        System.out.println("Customer ID | Name | Address | Phone number");
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
        System.out.println("Customer ID | Name | Address | Phone number");
    }

    /**
     * method below shows everything in table Readings from the database
     */
    public static void showReadings() {
        System.out.print("Showing readings table...\n");
        System.out.println("Reading ID | Customer ID | Meter ID | Water consumption | Date | Read by");
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
        System.out.println("Reading ID | Customer ID | Meter ID | Water consumption | Date | Read by");
    }

    /**
     * method below shows everything in table Water Meters from the database
     */
    public static void showMeter() {
        System.out.print("Showing Water meter table...\n");
        System.out.println("Water meter ID | Brand | Customer ID | Tax type ID");
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
        System.out.println("Water meter ID | Brand | Customer ID | Tax type ID");
    }

    /**
     * method below inserts a reading to database and is used in collection()
     * @param fldCustomerID  needed data in order to add a new reading
     * @param fldMeterID  needed data in order to add a new reading
     * @param fldWaterConsumption  needed data in order to add a new reading
     * @param fldDate  needed data in order to add a new reading
     * @param fldReader  needed data in order to add a new reading
     */
    public static void insertReading(int fldCustomerID, int fldMeterID,
                                     int fldWaterConsumption, String fldDate, String fldReader) {

        DB.insertSQL("INSERT INTO tblReadings VALUES (" + fldCustomerID + "," + fldMeterID +
                "," + fldWaterConsumption + ",'" + fldDate + "','" + fldReader + "');");
    }

    /**
     * method below inserts a customer to database and is used in dataManagement()
     * @param fldName needed data in order to add a customer row to tblCustomers
     * @param fldAddress needed data in order to add a customer row to tblCustomers
     * @param fldPhoneNo needed data in order to add a customer row to tblCustomers
     */
    public static void insertCustomer(String fldName, String fldAddress, String fldPhoneNo) {

        DB.insertSQL("INSERT INTO tblCustomers VALUES (" + "'" + fldName + "','" + fldAddress + "','" + fldPhoneNo + "');");
    }

    /**
     * method updates a customer in database and is used in dataManagement()
     * @param fldName from tblCustomer
     * @param fldAddress from tblCustomer
     * @param fldPhoneNo from tblCustomer
     * @param customerID from tblCustomer
     */
    public static void updateCustomer(String fldName, String fldAddress, String fldPhoneNo, int customerID) {
        DB.updateSQL("UPDATE tblCustomers SET fldName = '" + fldName + "', fldAddress = '" + fldAddress + "', fldPhoneNo = '" + fldPhoneNo +
                "' WHERE fldCustomerID = " + customerID +";");
    }

    /**
     * method deletes a customer from database and is used in dataManagement()
     * @param fldCustomerID selected from tblCustomer in order to delete corresponding row
     */
    public static void deleteCustomer(int fldCustomerID) {
        DB.deleteSQL("DELETE FROM tblCustomers WHERE fldCustomerID = ('" + fldCustomerID + "')");
    }

    /**
     * method shows everything in table Tax from the database
     */
    public static void showSegments() {
        System.out.print("Showing tax table...\n");
        System.out.println("Type ID | Type name | Freshwater price | Freshwater tax | Drainage tax | Year");
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
        System.out.println("Type ID | Type name | Freshwater price | Freshwater tax | Drainage tax | Year");
    }

    /**
     * method adds a segment to database and is used in dataManagement()
     * @param fldTypeName from tblTax
     * @param fldFreshWaterPrice from tblTax
     * @param fldFreshWaterTax from tblTax
     * @param fldDrainageTax from tblTax
     * @param fldYear from tblTax
     */
    public static void addSegment(String fldTypeName, double fldFreshWaterPrice, double fldFreshWaterTax,
                              double fldDrainageTax, int fldYear) {
        DB.insertSQL("INSERT INTO tblTax VALUES (" + "'" + fldTypeName + "'," + fldFreshWaterPrice + "," +
                fldFreshWaterTax + "," + fldDrainageTax + "," + fldYear + ");");
    }

    /**
     * method updates taxes for segments in database and is used in dataManagement()
     * @param fldTypeID from tblTax
     * @param fldFreshWaterTax from tblTax
     * @param fldDrainageTax from tblTax
     * @param fldYear from tblTax
     */
    public static void updateTaxForSegment(int fldTypeID, double fldFreshWaterPrice, double fldFreshWaterTax,
                                           double fldDrainageTax, int fldYear) {
        DB.updateSQL("UPDATE tblTax SET fldFreshWaterPrice = " + fldFreshWaterPrice + ", fldFreshWaterTax = " + fldFreshWaterTax +
                ", fldDrainageTax = " + fldDrainageTax + ", fldYear = " + fldYear + " WHERE fldTypeID = " + fldTypeID +";");

    }

    /**
     * method deletes a segment from database and is used in dataManagement()
     * @param fldTypeID from tblTax is selected in order to delete corresponding row
     */
    public static void deleteSegment (int fldTypeID) {
        DB.deleteSQL("DELETE FROM tblTax WHERE fldTypeID = ('" + fldTypeID + "')");
    }

    /** method replaces manually implementing a try catch INT
     * @return an int input from user
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
    /** method replaces manually implementing a try catch INT with NO TEXT
     * @return an int input from user
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
    /** method replaces manually implementing a try catch DOUBLE with NO TEXT
     * @return a double input from user
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
    /** method replaces manually implementing a try catch STRING with NO TEXT
     * @return a string input from user
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

    /**
     * method makes it possible to continue with the ENTER key
     */
    public static void pressEnterKeyToContinue() {
        System.out.println("Press any key to continue");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
} // end of public class Main