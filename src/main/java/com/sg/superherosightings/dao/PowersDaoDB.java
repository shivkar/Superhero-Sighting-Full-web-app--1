/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.SuperheroDaoDB.PowerMapper;
import com.sg.superherosightings.entity.Powers;
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
public class PowersDaoDB  implements PowerDao {
    
     @Autowired
    JdbcTemplate jdbc;

    @Override
    public Powers getPowerById(int id) {
        try {
            final String SELECT_POWER_BY_ID = "SELECT * FROM powers WHERE power_id = ?";
            Powers power = jdbc.queryForObject(SELECT_POWER_BY_ID, new PowerMapper(), id);
            return power;  
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Powers> getAllPowers() {
        final String SELECT_ALL_POWERS = "SELECT * FROM powers";
        return jdbc.query(SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    @Transactional
    
    public Powers addPower(Powers power) {
       final String INSERT_POWER = "INSERT INTO powers(power_name, power_description) " + 
                "VALUES(?,?)";
        jdbc.update(INSERT_POWER, 
                power.getName(), 
                power.getDescription());
                
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setId(newId);
        return power;
    }

    @Override
    public void updatePower(Powers power) {
       final String UPDATE_POWER = "UPDATE powers SET power_name = ?, power_description = ? " +
                "WHERE power_id = ?";
        jdbc.update(UPDATE_POWER,
                power.getName(),
                power.getDescription(),
                power.getId());
    }

    @Override
    @Transactional
    
    public void deletePowerById(int id) {
        final String DELETE_SUPER_POWER = "DELETE FROM superhero_power WHERE power_id = ?";
        jdbc.update(DELETE_SUPER_POWER, id);
        
        final String DELETE_POWER = "DELETE FROM powers WHERE power_id = ?";
        jdbc.update(DELETE_POWER, id);
    }
    public static final class PowerMapper implements RowMapper<Powers> {

        @Override
        public Powers mapRow(ResultSet rs, int index) throws SQLException {
            Powers power = new Powers();
            power.setId(rs.getInt("power_id"));
            power.setName(rs.getString("power_name"));
            power.setDescription(rs.getString("power_description"));
            
            return power;
        }
    }
}
