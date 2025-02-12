package sample.dao.custom.impl;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Database to store each venue's information
// MANAGER also has access to this class.
// Manager/Event -> VenueDB
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import sample.dao.custom.VenueDAO;
import sample.db.DBConnection;
import sample.dto.VenueDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VenueDAOImpl implements VenueDAO {

    public void displayAllVenues() {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all venues");

                String query = "select * from venue";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String id = rs.getString("venue_id");
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    String cap = rs.getString("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    System.out.print("Name: ");
                    System.out.println(name);

                    System.out.print("Location: ");
                    System.out.println(loc);

                    System.out.print("Address: ");
                    System.out.println(addr);

                    System.out.print("Max capacity: ");
                    System.out.println(cap);

                    System.out.print("Description: ");
                    System.out.println(desc);

                    System.out.print("Category: ");
                    System.out.println(cat);

                    System.out.print("Contact Info: ");
                    System.out.println(info);

                    System.out.print("Cost: ");
                    System.out.println(cost);

                    System.out.println("");
                }
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

    public void displayVenue(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying a venue");

                String query = "select * from venue where VENUE_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    String cap = rs.getString("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    System.out.print("Name: ");
                    System.out.println(name);

                    System.out.print("Location: ");
                    System.out.println(loc);

                    System.out.print("Address: ");
                    System.out.println(addr);

                    System.out.print("Max capacity: ");
                    System.out.println(cap);

                    System.out.print("Description: ");
                    System.out.println(desc);

                    System.out.print("Category: ");
                    System.out.println(cat);

                    System.out.print("Contact Info: ");
                    System.out.println(info);

                    System.out.print("Cost: ");
                    System.out.println(cost);

                    System.out.println("");
                }
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

    public VenueDTO getVenue(String id)  {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting a venue");

                String query = "select * from venue where VENUE_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    int cap = rs.getInt("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    VenueDTO venueDTO = new VenueDTO(name, loc, addr, cap, desc, cat, info, cost);
                    return venueDTO;
                }
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

        VenueDTO emptyObj = new VenueDTO();
        return emptyObj;
    }

    public void addVenue() {
        Scanner input = new Scanner(System.in);

        String location, address, desc, category, name, contact_info;
        int max_capacity;

        System.out.print("Enter name: ");
        name = input.nextLine();

        System.out.print("Enter location: ");
        location = input.nextLine();

        System.out.print("Enter address: ");
        address = input.nextLine();

        System.out.print("Enter description: ");
        desc = input.nextLine();

        System.out.print("Enter category: ");
        category = input.nextLine();

        System.out.print("Enter contact info: ");
        contact_info = input.nextLine();

        System.out.print("Enter max capacity: ");
        max_capacity = input.nextInt();

        System.out.print("Enter cost: ");
        int cost = input.nextInt();

        VenueDTO obj = new VenueDTO(name, location, address, max_capacity, desc, category, contact_info, cost);

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - adding a venue");

                String query = "select * from VENUE";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("venue_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into VENUE(VENUE_ID, NAME, LOCATION, ADDRESS, MAX_CAPACITY, DESCRIPTION, CATEGORY, CONTACT_INFO, COST)" +
                        "values('" + new_id + "','" + obj.getName() + "','" + obj.getLocation() + "','" + obj.getVenue_address() + "'," + Integer.toString(obj.getMax_capacity()) +
                        ",'" + obj.getDescription() + "','" + obj.getCategory() + "','" + obj.getContact_info() + "'," + Integer.toString(obj.getCost()) + ")";

                // System.out.println(query);

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

    public void addVenue(VenueDTO obj, String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {

                System.out.println("Database - adding venue object");

                Statement stmt = conn.createStatement();

                String query = "insert into VENUE(VENUE_ID, NAME, LOCATION, ADDRESS, MAX_CAPACITY, DESCRIPTION, CATEGORY, CONTACT_INFO, COST)" +
                        "values('" + id + "','" + obj.getName() + "','" + obj.getLocation() + "','" + obj.getVenue_address() + "'," + Integer.toString(obj.getMax_capacity()) +
                        ",'" + obj.getDescription() + "','" + obj.getCategory() + "','" + obj.getContact_info() + "'," + Integer.toString(obj.getCost()) + ")";

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

    public void deleteVenue(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - deleting venue");

                String query = "delete from venue where VENUE_ID = " + id;  // query to be sent

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

    public void editVenue(String id) {
        Scanner input = new Scanner(System.in);
        VenueDTO venueDTO = this.getVenue(id);

        System.out.print("Enter name: ");
        venueDTO.setName(input.nextLine());

        System.out.print("Enter location: ");
        venueDTO.setLocation(input.nextLine());

        System.out.print("Enter address: ");
        venueDTO.setVenue_address(input.nextLine());

        System.out.print("Enter description: ");
        venueDTO.setDescription(input.nextLine());

        System.out.print("Enter category: ");
        venueDTO.setCategory(input.nextLine());

        System.out.print("Enter contact info: ");
        venueDTO.setContact_info(input.nextLine());

        System.out.print("Enter max capacity: ");
        venueDTO.setMax_capacity(input.nextInt());

        System.out.print("Enter cost: ");
        venueDTO.setCost(input.nextInt());

        this.deleteVenue(id);
        this.addVenue(venueDTO, id);
    }

    public int getVenueCost(String id)  {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting venue cost");

                String query = "select * from venue where VENUE_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    int cost = rs.getInt("cost");
                    return cost;
                }
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

        return 0;
    }

    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - updating venue field");

                String query = new String();

                if (isNumeric)
                    query = "update venue set " + field + " = " + new_value + " where venue_id = " + id;
                else
                    query = "update venue set " + field + " = '" + new_value + "' where venue_id = " + id;

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

    public HashMap<ArrayList<String>, ArrayList<VenueDTO>> getListOfVenuesAndIDs() {
        ArrayList<VenueDTO> venueDTOS = null;
        ArrayList<String> venueIDs = null;

        HashMap<ArrayList<String>, ArrayList<VenueDTO>> venuesAndIDs = null;

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of venues/ids");

                String query = "SELECT * FROM venue";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                venueDTOS = new ArrayList<VenueDTO>();
                venueIDs = new ArrayList<String>();
                venuesAndIDs = new HashMap<ArrayList<String>, ArrayList<VenueDTO>>();

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    int cap = rs.getInt("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");
                    String id = rs.getString("venue_id");

                    VenueDTO venueDTO = new VenueDTO(name, loc, addr, cap, desc, cat, info, cost);
                    venueDTOS.add(venueDTO);
                    venueIDs.add(id);
                }

                venuesAndIDs.put(venueIDs, venueDTOS);
                return venuesAndIDs;
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

        return venuesAndIDs;
    }
}
