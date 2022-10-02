/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Sightings;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHIVALI
 */
@Repository
public class SightingsDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    SuperheroDao superherosDao;

    @Autowired
    LocationDao locationsDao;

    List<String> superherosByLocation = new ArrayList<>();

    @Override
    public Sightings getSightingById(int id) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sighting_id = ?";
            Sightings sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);

            sighting.setSuperhero(superherosDao.getSuperheroById(sighting.getSuperheroId()));
            sighting.setLocation(locationsDao.getLocationById(sighting.getLocationId()));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sightings> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sightings ORDER BY sighting_time DESC";
        List<Sightings> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateSupersAndLocations(sightings);
        return sightings;
    }

    @Override
    public Sightings addSighting(Sightings sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sightings(superhero_id, loc_id, sighting_time) VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getSuperheroId(),
                sighting.getLocationId(),
                sighting.getSightingTime());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);

        return sighting;
    }

    @Override
    public void updateSighting(Sightings sighting) {
        final String UPDATE_SIGHTING = "UPDATE sightings SET superhero_id = ?, loc_id = ?, "
                + "sighting_time = ? WHERE sighting_id = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSuperheroId(),
                sighting.getLocationId(),
                sighting.getSightingTime(),
                sighting.getSightingId());
    }

    @Override
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE sighting_id = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }

    private void associateSupersAndLocations(List<Sightings> sightings) {
        for (Sightings sighting : sightings) {
            sighting.setSuperhero(superherosDao.getSuperheroById(sighting.getSuperheroId()));
            sighting.setLocation(locationsDao.getLocationById(sighting.getLocationId()));
        }
    }

    @Override
    public List<Sightings> getLastTenSightings() {
        final String GET_LAST_TEN_SIGHTINGS = "SELECT * FROM Sightings ORDER BY sighting_time DESC LIMIT 0, 10";
        List<Sightings> sightings = jdbc.query(GET_LAST_TEN_SIGHTINGS, new SightingMapper());
        for (Sightings sighting : sightings) {
            sighting.setSuperhero(superherosDao.getSuperheroById(sighting.getSuperheroId()));
            sighting.setLocation(locationsDao.getLocationById(sighting.getLocationId()));
        }
        return sightings;
    }
    

    public static final class SightingMapper implements RowMapper<Sightings> {

        @Override
        public Sightings mapRow(ResultSet rs, int index) throws SQLException {
            Sightings sighting = new Sightings();
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setSuperheroId(rs.getInt("superhero_id"));
            sighting.setLocationId(rs.getInt("loc_id"));
            Date date = rs.getDate("sighting_time");
            sighting.setSightingTime(date.toLocalDate());

            return sighting;
        }
    }
}
