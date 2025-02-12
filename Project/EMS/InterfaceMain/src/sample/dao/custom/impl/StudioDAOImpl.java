package sample.dao.custom.impl;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Database to store each studio's information
// MANAGER has access to this class.
// Manager/Customer -> StudioModel
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import sample.dao.custom.StudioDAO;
import sample.db.DBConnection;
import sample.dto.StudioDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudioDAOImpl implements StudioDAO{

    public void displayAllStudios() {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all studios");

                String query = "select * from STUDIO";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String id = rs.getString("studio_id");
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    StudioDTO std = new StudioDTO(name, info, cost);
                    std.display();
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

    public void displayStudio(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying a studio");

                String query = "select * from STUDIO where STUDIO_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    StudioDTO std = new StudioDTO(name, info, cost);
                    std.display();
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

    public void addStudio() {        //Adds a new studio -> Manager
        Scanner input = new Scanner(System.in);

        String name, contact_info, reviews, props;
        int cap, cost, photo, video, album, drone, crane;
        char c;

        System.out.print("Enter name: ");
        name = input.nextLine();

        System.out.print("Enter contact info: ");
        contact_info = input.nextLine();

        System.out.print("Enter cost: ");
        cost = input.nextInt();

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - adding a studio");

                String query = "select * from STUDIO";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("studio_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into STUDIO(studio_id, name, contact_info, cost) " +
                        "values('" + new_id + "','" + name + "','" + contact_info + "'," + cost + ")";

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

    public void addStudio(StudioDTO std, String id) {        //Adds a new studio -> Manager

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - adding studio object");

                Statement stmt = conn.createStatement();

                String query = "insert into STUDIO(studio_id, name, contact_info, cost) " +
                        "values('" + id + "','" + std.getCompany_name() + "','" + std.getContact_info() + "'," + std.getCost() + ")";

                // System.out.println(query);
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

    public void removeStudio(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - removing a studio");

                String query = "delete from STUDIO where STUDIO_ID = " + id;  // query to be sent

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

    public StudioDTO getStudio(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting a studio");

                String query = "select * from STUDIO where STUDIO_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    StudioDTO std = new StudioDTO(name, info, cost);
                    return std;
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

        StudioDTO emptyObj = new StudioDTO();
        return emptyObj;
    }

    public void editStudio(String id) {
        StudioDTO std = this.getStudio(id);

        Scanner input = new Scanner(System.in);

        String name, contact_info, reviews, props;
        int cap, cost;
        boolean photo, video, album, drone, crane;
        char c;

        System.out.print("Enter name: ");
        name = input.nextLine();
        std.setCompany_name(name);

        System.out.print("Enter contact info: ");
        contact_info = input.nextLine();
        std.setContact_info(contact_info);

        System.out.print("Enter cost: ");
        cost = input.nextInt();
        std.setCost(cost);

        this.removeStudio(id);
        this.addStudio(std, id);
    }

    public int getStudioCost(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting studio cost");

                String query = "select * from STUDIO where STUDIO_ID = " + id;  // query to be sent

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

    public HashMap<ArrayList<String>, ArrayList<StudioDTO>> getListOfStudiosAndIDs() {
        ArrayList<StudioDTO> studioDTOS = null;
        ArrayList<String> studioIDs = null;

        HashMap<ArrayList<String>, ArrayList<StudioDTO>> studiosAndIDs = null;

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of studios/ids");

                String query = "SELECT * FROM studio";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                studioDTOS = new ArrayList<StudioDTO>();
                studioIDs = new ArrayList<String>();
                studiosAndIDs = new HashMap<ArrayList<String>, ArrayList<StudioDTO>>();

                while (rs.next())
                {
                    String id = rs.getString("studio_id");
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");


                    StudioDTO studioDTO = new StudioDTO(name, info, cost);
                    studioDTOS.add(studioDTO);
                    studioIDs.add(id);
                }

                studiosAndIDs.put(studioIDs, studioDTOS);
                return studiosAndIDs;
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

        return studiosAndIDs;
    }

    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - updating studio field");

                String query = new String();

                if (isNumeric)
                    query = "update studio set " + field + " = " + new_value + " where studio_id = " + id;
                else
                    query = "update studio set " + field + " = '" + new_value + "' where studio_id = " + id;

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
}
