/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Locations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SHIVALI
 */

@Repository
public class LocationsDaoDB implements LocationDao {
  
    @Autowired
    JdbcTemplate jdbc; 

    @Override
    public Locations getLocationById(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM locations WHERE loc_id = ?";
            Locations location = jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
            return location;  
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Locations> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM locations";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Locations addLocation(Locations location) {
        {
    final String INSERT_LOCATION = "INSERT INTO locations(loc_name, loc_description, " + 
                "loc_street, loc_city, loc_state, loc_zip, loc_latitude, loc_longitude) " +
                 "VALUES(?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION, 
                location.getLocname(), 
                location.getLocdescription(),
                location.getLocstreet(),
                location.getLoccity(),
                location.getLocstate(),
                location.getLoczip(),
                location.getLatitude(),
                location.getLongitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocid(newId);
        return location;   
        
        }

    }

    @Override
    public void updateLocation(Locations location) {
        
        final String UPDATE_LOCATION = "UPDATE locations SET loc_name = ?, "
                + "loc_description = ?, loc_street = ?, loc_city = ?, loc_state = ?, "
                + "loc_zip = ?, loc_latitude = ?, loc_longitude = ? WHERE loc_id = ?";
       
        jdbc.update(UPDATE_LOCATION,
                location.getLocname(), 
                location.getLocdescription(),
                location.getLocstreet(),
                location.getLoccity(),
                location.getLocstate(),
                location.getLoczip(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocid());
    }

    @Override
    @Transactional
    
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE loc_id = ?";
        jdbc.update(DELETE_SIGHTING, id);

        final String DELETE_LOCATION = "DELETE FROM locations WHERE loc_id = ?";
        jdbc.update(DELETE_LOCATION, id);
    }
    
     public static final class LocationMapper implements RowMapper<Locations> {

        @Override
        public Locations mapRow(ResultSet rs, int index) throws SQLException {
            Locations location = new Locations();
            location.setLocid(rs.getInt("loc_id"));
            location.setLocname(rs.getString("loc_name"));
            location.setLocdescription(rs.getString("loc_description"));
            location.setLocstreet(rs.getString("loc_street"));
            location.setLoccity(rs.getString("loc_city"));
            location.setLocstate(rs.getString("loc_state"));
            location.setLoczip(rs.getString("loc_zip"));
            location.setLatitude(rs.getString("loc_latitude"));
            location.setLongitude(rs.getString("loc_longitude"));
            
            return location;
        }
    }
}
