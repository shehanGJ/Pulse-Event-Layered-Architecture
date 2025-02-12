package sample.dao.custom.impl;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Database to store each customer's media requirements
// MANAGER also has access to this class.
// Manager/Event -> Media_RequirementsModel
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import sample.dao.custom.Media_RequirementsDAO;
import sample.db.DBConnection;
import sample.dto.Media_RequirementsDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Media_RequirementsDAOImpl implements Media_RequirementsDAO
{
    public void displayAllMediaRequirements()                                                //Displays all requirement records
    {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all media requirements");

                String query = "select * from MEDIA_REQUIREMENTS";  // query to be sent                 ///TO BE EDITED

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String id = rs.getString("media_id");
                    int photography = rs.getInt("photography");
                    int videography = rs.getInt("videography");
                    int album = rs.getInt("album");
                    int drone = rs.getInt("drone");
                    int crane = rs.getInt("crane");

                    System.out.print("ID: ");
                    System.out.println(id);

                    Media_RequirementsDTO mr = new Media_RequirementsDTO(photography, videography,  album,  drone, crane);
                    mr.display();
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

    public String addMediaRequirement()
    {
        Scanner input = new Scanner(System.in);

        int photography, videography, album, drone, crane;

        System.out.print("Photography (0: No 1: Yes):");
        photography = input.nextInt();

        System.out.print("Videography (0: No 1: Yes):");
        videography = input.nextInt();

        System.out.print("Album printing (0: No 1: Yes): ");
        album = input.nextInt();

        System.out.print("Drone (0: No 1: Yes): ");
        drone = input.nextInt();

        System.out.print("Crane (0: No 1: Yes): ");
        crane = input.nextInt();

        Media_RequirementsDTO mr = new Media_RequirementsDTO(photography, videography,  album,  drone, crane);

        ////////// DB IMPLEMENTATION ///////////////

        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null) {
                System.out.println("Database - adding media requirement");

                String query = "select * from MEDIA_REQUIREMENTS";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("media_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into MEDIA_REQUIREMENTS(media_id, photography, videography, album, drone, crane)" +
                        "values('" + new_id + "'," + Integer.toString(mr.getPhotography()) + "," + Integer.toString(mr.getVideography()) + "," + Integer.toString(mr.getAlbum_printing()) +
                         "," + Integer.toString(mr.getDrone()) + "," + Integer.toString(mr.getCrane()) + ")";

                // System.out.println(query);

                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                return new_id;
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

        return "";
    }

    public void addMediaRequirement(Media_RequirementsDTO mr, String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        ) {
            if (conn != null) {
                System.out.println("Database - adding media requirement object + id");

                Statement stmt = conn.createStatement();

                String query = "insert into MEDIA_REQUIREMENTS(media_id, photography, videography, album, drone, crane)" +
                                "values('" + id + "'," + Integer.toString(mr.getPhotography()) + "," + Integer.toString(mr.getVideography()) + "," +
                                Integer.toString(mr.getAlbum_printing()) + "," + Integer.toString(mr.getDrone()) + "," + Integer.toString(mr.getCrane()) + ")";

                // System.out.println(query);

                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String addMediaRequirement(Media_RequirementsDTO mr) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        ) {
            if (conn != null) {
                System.out.println("Database - adding media requirement object");

                String query = "select * from MEDIA_REQUIREMENTS";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("media_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into MEDIA_REQUIREMENTS(media_id, photography, videography, album, drone, crane)" +
                        "values('" + new_id + "'," + Integer.toString(mr.getPhotography()) + "," + Integer.toString(mr.getVideography()) + "," +
                        Integer.toString(mr.getAlbum_printing()) + "," + Integer.toString(mr.getDrone()) + "," + Integer.toString(mr.getCrane()) + ")";

                // System.out.println(query);

                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                return new_id;

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public void removeMediaRequirement(String id)
    {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - removing media requirement");

                String query = "delete from MEDIA_REQUIREMENTS where MEDIA_ID = " + id;  // query to be sent

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

    public void displayMediaRequirement(String id)                    //Display a specific media requirement record
    {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying media requirement");

                String query = "select * from MEDIA_REQUIREMENTS where MEDIA_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    int photography = rs.getInt("photography");
                    int videography = rs.getInt("videography");
                    int album = rs.getInt("album");
                    int drone = rs.getInt("drone");
                    int crane = rs.getInt("crane");

                    System.out.print("ID: ");
                    System.out.println(id);

                    Media_RequirementsDTO mr = new Media_RequirementsDTO(photography, videography,  album,  drone, crane);
                    mr.display();
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

    public Media_RequirementsDTO getMediaRequirement(String id) {
        try (
                Connection conn = DBConnection.getInstance().getConnection();
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting media requirement");

                String query = "select * from MEDIA_REQUIREMENTS where MEDIA_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    int photography = rs.getInt("photography");
                    int videography = rs.getInt("videography");
                    int album = rs.getInt("album");
                    int drone = rs.getInt("drone");
                    int crane = rs.getInt("crane");

                    System.out.print("ID: ");
                    System.out.println(id);

                    Media_RequirementsDTO mr = new Media_RequirementsDTO(photography, videography,  album, drone, crane);
                    return mr;
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

        Media_RequirementsDTO emptyObj = new Media_RequirementsDTO();
        return emptyObj;
    }

    public void editMediaRequirement(String id) {
        Media_RequirementsDTO obj = this.getMediaRequirement(id);
        Scanner input = new Scanner(System.in);

        System.out.print("Photography (0: No 1: Yes) :");
        obj.setPhotography(input.nextInt());

        System.out.print("Videography (0: No 1: Yes):");
        obj.setVideography (input.nextInt());

        System.out.print("Album printing (0: No 1: Yes): ");
        obj.setAlbum_printing(input.nextInt());

        System.out.print("Drone (0: No 1: Yes): ");
        obj.setDrone(input.nextInt());

        System.out.print("Crane (0: No 1: Yes): ");
        obj.setCrane(input.nextInt());

        this.removeMediaRequirement(id);
        this.addMediaRequirement(obj, id);
    }
}
