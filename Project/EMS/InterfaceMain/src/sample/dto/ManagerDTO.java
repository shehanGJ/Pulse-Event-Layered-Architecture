package sample.dto;

import sample.dao.custom.impl.*;

import java.util.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// This class will store/edit/remove the manager's information The information will be stored in ManagerModel
// It is inherited from Employee
// Manager -> ManagerModel
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ManagerDTO extends EmployeeDTO
{

    public ManagerDTO()
    {
        super();
    }

    /////////////// CALLING EMPLOYEE DATABASE METHODS ///////////////

    public void viewAllEmployees()
    {
        EmployeeDAOImpl obj = new EmployeeDAOImpl();
        obj.displayAllEmployees();
    }

    public void viewEmployee()
    {
        EmployeeDAOImpl obj = new EmployeeDAOImpl();
        String id = new String();
        System.out.print("Viewing employee details. Enter employee ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.displayEmployee(id);
    }

    public void addEmployee()                               //Add a new employee
    {
        EmployeeDAOImpl obj = new EmployeeDAOImpl();
        employee_id = obj.insertEmployee(this);
    }

    public void deleteEmployee()                            //Delete an existing Employee
    {
        EmployeeDAOImpl obj = new EmployeeDAOImpl();
        String id = new String();
        System.out.print("Removing an employee. Enter employee ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.removeEmployee(id);
    }

    @Override
    public void editEmployee() {
        EmployeeDAOImpl obj = new EmployeeDAOImpl();
        String id = new String();
        System.out.print("Editing an employee. Enter employee ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.editEmployee(id);
    }

    void changeEmployeeManager() {
        EmployeeDAOImpl obj = new EmployeeDAOImpl();
        String mid, eid;

        System.out.print("Enter employee ID: ");
        Scanner input = new Scanner(System.in);
        eid = input.nextLine();

        System.out.print("Enter manager ID: ");
        mid = input.nextLine();

        obj.changeManager(eid, mid);
    }

    /////////////// CALLING CUSTOMER DATABASE METHODS ///////////////

    public void viewAllCustomers() {
        CustomerDAOImpl obj = new CustomerDAOImpl();
        obj.displayAllCustomers();
    }

    public void viewCustomer() {
        CustomerDAOImpl obj = new CustomerDAOImpl();
        String id = "";
        System.out.print("Viewing a customer. Enter customer ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.displayCustomer(id);
    }

    public void createCustomerAccount()                     //Manager can create customer's account
    {
        CustomerDAOImpl obj = new CustomerDAOImpl();
        obj.insertCustomer();
    }

    public void editCustomerAccount()                     //Manager can edit customer's account details
    {
        CustomerDAOImpl obj = new CustomerDAOImpl();
        String id = new String();
        System.out.print("Editing a customer. Enter ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.editCustomer(id);
    }

    public void deleteCustomerAccount()                     //Manager can delete customer's account
    {
        CustomerDAOImpl obj = new CustomerDAOImpl();
        String id = "";
        System.out.print("Deleting a customer. Enter ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.removeCustomer(id);
    }

    /////////////////// CALLING VENUE DATABASE METHODS ////////////////////

    public void addVenue() {
        VenueDAOImpl obj = new VenueDAOImpl();
        obj.addVenue();
    }

    public void deleteVenue() {
        String id = "";
        System.out.print("Removing a venue record. Enter venue ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        VenueDAOImpl obj = new VenueDAOImpl();
        obj.deleteVenue(id);
    }

    public void viewVenue() {
        String id = "";
        System.out.print("Viewing venue details. Enter venue ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        VenueDAOImpl obj = new VenueDAOImpl();
        obj.displayVenue(id);
    }

    public void viewAllVenues() {
        VenueDAOImpl obj = new VenueDAOImpl();
        obj.displayAllVenues();
    }

    public void editVenue() {
        String id = "";
        System.out.print("Editing a venue record. Enter venue ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        VenueDAOImpl obj = new VenueDAOImpl();
        obj.editVenue(id);
    }

    ////////////////// CALLING STUDIO DATABASE METHODS ///////////////////

    public void addStudio() {
        StudioDAOImpl obj = new StudioDAOImpl();
        obj.addStudio();
    }

    public void deleteStudio() {
        String id = "";
        System.out.print("Deleting a studio. Enter it's studio ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        StudioDAOImpl obj = new StudioDAOImpl();
        obj.removeStudio(id);
    }

    public void viewAllStudios() {
        StudioDAOImpl obj = new StudioDAOImpl();
        obj.displayAllStudios();
    }

    public void viewStudio() {
        String id = "";
        System.out.print("Viewing a studio. Enter it's studio ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        StudioDAOImpl obj = new StudioDAOImpl();
        obj.displayStudio(id);
    }

    public void editStudio() {
        String id = "";
        System.out.print("Editing a studio. Enter it's studio ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        StudioDAOImpl obj = new StudioDAOImpl();
        obj.editStudio(id);
    }

    /////////////////////// CALLING MENU DATABASE METHODS ////////////////

    public void viewMenu() {
        System.out.print("Viewing a menu. Enter it's ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        MenuDAOImpl obj = new MenuDAOImpl();
        obj.displayMenu(id);
    }

    public void viewAllMenus() {
        MenuDAOImpl obj = new MenuDAOImpl();
        obj.displayAllMenus();
    }

    public void deleteMenu() {
        System.out.print("Deleting a menu. Enter it's ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        MenuDAOImpl obj = new MenuDAOImpl();
        obj.removeMenu(id);
    }

    /////////////////////// CALLING CATERING SERVICE DATABASE METHODS ///////////////

    public void viewAllCateringServices()
    {
        CateringServicesDAOImpl obj = new CateringServicesDAOImpl();
        obj.displayAllCatering();
    }

    public void viewCateringService()
    {
        CateringServicesDAOImpl obj = new CateringServicesDAOImpl();
        String id = new String();
        System.out.print("Viewing a catering service. Enter it's ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.displayCatering(id);
    }

    public void addCateringService()
    {
        CateringServicesDAOImpl obj = new CateringServicesDAOImpl();
        obj.addCateringVendor();
    }

    public void deleteCateringService()
    {
        CateringServicesDAOImpl obj = new CateringServicesDAOImpl();
        String id = new String();
        System.out.print("Deleting a catering service. Enter it's ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.removeCatering(id);
    }

    public void editCateringService() {
        CateringServicesDAOImpl obj = new CateringServicesDAOImpl();
        String id = new String();
        System.out.print("Editing a catering service. Enter it's ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.editCatering(id);
    }

    /////////////////// CALLING MEDIA REQUIREMENTS DATABASE METHODS ///////////////////

    public void viewAllMediaRequirements() {
        Media_RequirementsDAOImpl obj = new Media_RequirementsDAOImpl();
        obj.displayAllMediaRequirements();
    }

    public void viewMediaRequirement() {
        Media_RequirementsDAOImpl obj = new Media_RequirementsDAOImpl();
        String id = "";
        System.out.print("Viewing a media requirement record. Enter ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.displayMediaRequirement(id);
    }

    public void createMediaRequirement()
    {
        Media_RequirementsDAOImpl obj = new Media_RequirementsDAOImpl();
        obj.addMediaRequirement();
    }

    public void editMediaRequirement()
    {
        Media_RequirementsDAOImpl obj = new Media_RequirementsDAOImpl();
        String id = new String();
        System.out.print("Editing a media requirement record. Enter it's ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.editMediaRequirement(id);
    }

    public void deleteMediaRequirement()
    {
        Media_RequirementsDAOImpl obj = new Media_RequirementsDAOImpl();
        String id = "";
        System.out.print("Deleting a media requirement record. Enter it's ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.removeMediaRequirement(id);
    }

    //////////////////////

    public void approveEvent(String eventid) {
        ManagerDAOImpl obj = new ManagerDAOImpl();
        obj.approveEvent(eventid);
    }
}
