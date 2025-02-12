package sample.dao.custom;

import sample.dao.CrudDAO;
import sample.dto.Catering_ServiceDTO;
import sample.entity.Catering_Service;

import java.sql.SQLException;

public interface CateringServiceDAO extends CrudDAO <Catering_Service> {
    void displayCatering(String id) throws SQLException, ClassNotFoundException;
}
