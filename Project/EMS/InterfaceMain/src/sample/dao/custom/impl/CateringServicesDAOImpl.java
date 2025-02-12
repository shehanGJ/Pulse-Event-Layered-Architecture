package sample.dao.custom.impl;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Database methods for Catering_Services class
// Manager/Event -> Catering_Service -> CateringServicesModel
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import sample.dao.SQLUtil;
import sample.dao.custom.CateringServiceDAO;
import sample.db.DBConnection;
import sample.dto.Catering_ServiceDTO;
import sample.entity.Catering_Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// displays one caterer
public class CateringServicesDAOImpl implements CateringServiceDAO {

    public void displayCatering(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("select * from CATERING where CATERING_ID = ? ", id);
        while (rs.next())
                {
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    String specialty = rs.getString("specialty");
                    int days = rs.getInt("days");
                    int charges = rs.getInt("charges");

                    Catering_Service caterer = new Catering_Service(name, contact, specialty, days, charges);
                    caterer.display();
                }

    }

    // displays all caterers
    public void displayAllCatering() {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all catering");

                String query = "select * from CATERING";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String id = rs.getString("catering_id");
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    String specialty = rs.getString("specialty");
                    int days = rs.getInt("days");
                    int charges = rs.getInt("charges");

                    System.out.print("Id = ");
                    System.out.println(id);

                    Catering_ServiceDTO caterer = new Catering_ServiceDTO(name, contact, specialty, days, charges);
                    caterer.display();

                    System.out.println("");
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCateringVendor()                           //Manager can add catering vendor
    {
        Scanner input = new Scanner(System.in);
        String company_name, contact_info, speciality;
        int days, charges;

        System.out.print("Enter name of the vendor: ");
        company_name = input.nextLine();

        System.out.print("Enter the contact info of the vendor : ");
        contact_info = input.nextLine();

        System.out.print("Enter their speciality (Continental / Desi): ");
        speciality = input.nextLine();

        System.out.print("Enter the days they need to informed prior to the event: ");
        days = input.nextInt();

        System.out.print("Enter their charges per event : ");
        charges = input.nextInt();

        Catering_ServiceDTO obj = new Catering_ServiceDTO(company_name, contact_info, speciality, days, charges);

        //////ADD TO DB//////

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - adding a catering service");

                String query = "select * from CATERING";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("catering_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into CATERING(catering_id, name, contact, specialty, days, charges)" +
                        "values('" + new_id + "','" + obj.getCompany_name() + "','" + obj.getContact_info() + "','"
                        + obj.getSpeciality()  + "'," + Integer.toString(obj.getDays()) + "," + Integer.toString(obj.getCharges()) + ")";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Overloaded insertion
    public void addCateringVendor(Catering_ServiceDTO obj, String id)
    {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - Adding catering service");

                Statement stmt = conn.createStatement();

                String query = "insert into CATERING(catering_id, name, contact, specialty, days, charges)" +
                        "values('" + id + "','" + obj.getCompany_name() + "','" + obj.getContact_info() + "','" + obj.getSpeciality()
                        + "'," + Integer.toString(obj.getDays()) + "," + Integer.toString(obj.getCharges()) + ")";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                return;
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete a caterer
    public void removeCatering(String id)
    {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - Removing catering");

                String query = "delete from CATERING where CATERING_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
            }

            else
            {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // edit a caterer
    public void editCatering(String id)
    {
        Scanner input = new Scanner(System.in);
        Catering_ServiceDTO obj = new Catering_ServiceDTO();

        System.out.print("Enter name of the vendor: ");
        obj.setCompany_name(input.nextLine());

        System.out.print("Enter the contact info of the vendor : ");
        obj.setContact_info(input.nextLine());

        System.out.print("Enter their speciality (Continental / Desi): ");
        obj.setSpeciality(input.nextLine());

        System.out.print("Enter the days they need to informed prior to the event: ");
        obj.setDays(input.nextInt());

        System.out.print("Enter their charges per event : ");
        obj.setCharges(input.nextInt());

        this.removeCatering(id);
        this.addCateringVendor(obj, id);
    }

    // return charges of the caterer
    public int getCateringCharges(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting catering charges");

                String query = "select * from CATERING where CATERING_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) // reads one row/record
                {
                    return rs.getInt("charges");
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public HashMap<ArrayList<String>, ArrayList<Catering_ServiceDTO>> getListOfCaterersAndIDs() {
        ArrayList<Catering_ServiceDTO> caterers = null;
        ArrayList<String> catererIDs = null;

        HashMap<ArrayList<String>, ArrayList<Catering_ServiceDTO>> caterersAndIDs = null;

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of all caterers and their IDs");

                String query = "SELECT * FROM catering";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                caterers = new ArrayList<Catering_ServiceDTO>();
                catererIDs = new ArrayList<String>();
                caterersAndIDs = new HashMap<ArrayList<String>, ArrayList<Catering_ServiceDTO>>();

                while (rs.next())
                {
                    String id = rs.getString("catering_id");
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    String specialty = rs.getString("specialty");
                    int days = rs.getInt("days");
                    int charges = rs.getInt("charges");

                    Catering_ServiceDTO caterer = new Catering_ServiceDTO(name, contact, specialty, days, charges);

                    caterers.add(caterer);
                    catererIDs.add(id);
                }

                caterersAndIDs.put(catererIDs, caterers);
                return caterersAndIDs;
            }

            else
            {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return caterersAndIDs;
    }

    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - updating a catering field");

                String query = new String();

                if (isNumeric)
                    query = "update catering set " + field + " = " + new_value + " where catering_id = " + id;
                else
                    query = "update catering set " + field + " = '" + new_value + "' where catering_id = " + id;

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Catering_Service> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Catering_Service dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(Catering_Service dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Catering_Service search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
    
