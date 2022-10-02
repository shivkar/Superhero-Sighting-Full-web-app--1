/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Organizations;
import com.sg.superherosightings.entity.Powers;
import com.sg.superherosightings.entity.Superheros;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author SHIVALI
 */

@Repository
public class SuperheroDaoDB implements SuperheroDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superheros getSuperheroById(int id) {
       try {
            final String SELECT_SUPERHERO_BY_ID = "SELECT * FROM superheros WHERE superhero_id = ?";
            Superheros superhero = jdbc.queryForObject(SELECT_SUPERHERO_BY_ID, new SuperherosMapper(), id);
            superhero.setPowers(getPowersForSuperhero(id));
            superhero.setOrganizations(getOrganizationsForSuperhero(id));
            return superhero;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Superheros> getAllSuperheros() {
        final String SELECT_ALL_SUPERHEROS = "SELECT * FROM superheros";
        List<Superheros> superheros = jdbc.query(SELECT_ALL_SUPERHEROS, new SuperherosMapper());
        associatePowersAndOrgs(superheros);
        return superheros;
    }

    @Override
    @Transactional
    public Superheros addSuperhero(Superheros superhero) {
       
        final String INSERT_SUPERHERO = "INSERT INTO superheros(super_name, super_description, "
                + "is_superhero) VALUES(?,?,?)";
        jdbc.update(INSERT_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
               superhero.isIsHero());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superhero.setId(newId);
        insertSuperPower(superhero);
        insertSuperOrganization(superhero);
        return superhero;

    }

    @Override
    @Transactional
    
    public void updateSuperhero(Superheros superhero) {
        final String UPDATE_SUPERHERO = "UPDATE superheros SET super_name = ?, super_description = ?, "
                + "is_superhero = ? WHERE superhero_id = ?";
        jdbc.update(UPDATE_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                superhero.isIsHero(),
                superhero.getId());

        final String DELETE_SUPER_POWER = "DELETE FROM superhero_power WHERE superhero_id = ?";
        jdbc.update(DELETE_SUPER_POWER, superhero.getId());
        insertSuperPower(superhero);
        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM super_organization WHERE superhero_id = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, superhero.getId());
        insertSuperOrganization(superhero);
    }

    @Override
    @Transactional
    public void deleteSuperheroById(int id) {
        final String DELETE_SUPER_POWER = "DELETE FROM superhero_power WHERE superhero_id = ?";
        jdbc.update(DELETE_SUPER_POWER, id);

        final String DELETE_SUPER_ORG = "DELETE FROM super_organization WHERE superhero_id = ?";
        jdbc.update(DELETE_SUPER_ORG, id);

        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE superhero_id = ?";
        jdbc.update(DELETE_SIGHTING, id);

        final String DELETE_SUPERHERO = "DELETE FROM superheros WHERE superhero_id = ?";
        jdbc.update(DELETE_SUPERHERO, id);
    }

    private List<Powers> getPowersForSuperhero(int id) {
        final String SELECT_POWERS_FOR_SUPERHERO = "SELECT p.* FROM powers p "
                + "JOIN superhero_power sp ON sp.power_id = p.power_id WHERE sp.superhero_id = ?";
        return jdbc.query(SELECT_POWERS_FOR_SUPERHERO, new PowerMapper(), id);
    }

    private List<Organizations> getOrganizationsForSuperhero(int id) {
        final String SELECT_ORGS_FOR_SUPERHERO = "SELECT o.* FROM organizations o "
                + "JOIN super_organization so ON so.org_id = o.org_id WHERE so.superhero_id = ?";
        return jdbc.query(SELECT_ORGS_FOR_SUPERHERO, new OrgMapper(), id);
    }

    private void associatePowersAndOrgs(List<Superheros> superheros) {
        
        for (Superheros superhero : superheros) {
            superhero.setPowers(getPowersForSuperhero(superhero.getId()));
            superhero.setOrganizations(getOrganizationsForSuperhero(superhero.getId()));
        }
    }

    private void insertSuperPower(Superheros superhero) {
       final String INSERT_SUPER_POWER = "INSERT INTO "
                + "superhero_power(superhero_id, power_id) VALUES(?,?)";
        if (superhero.getPowers() != null) {
            for (Powers power : superhero.getPowers()) {
                jdbc.update(INSERT_SUPER_POWER,
                        superhero.getId(),
                        power.getId());
            }
        }
    }

    private void insertSuperOrganization(Superheros superhero) {
       final String INSERT_SUPER_ORGANIZATION = "INSERT INTO "
                + "super_organization(superhero_id, org_id) VALUES(?,?)";
        if (superhero.getOrganizations() != null) {
            for (Organizations org : superhero.getOrganizations()) {
                jdbc.update(INSERT_SUPER_ORGANIZATION,
                        superhero.getId(),
                        org.getId());
            }
        }
    }

    
       
        
    

    

 
    public static final class SuperherosMapper implements RowMapper<Superheros> {

        @Override
        public Superheros mapRow(ResultSet rs, int index) throws SQLException {
            Superheros superhero = new Superheros();
            superhero.setId(rs.getInt("superhero_id"));
            superhero.setName(rs.getString("super_name"));
            superhero.setDescription(rs.getString("super_description"));
            superhero.setIsHero(rs.getBoolean("is_superhero"));
            return superhero;
        }
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
  
  public static final class OrgMapper implements RowMapper<Organizations> {

        @Override
        public Organizations mapRow(ResultSet rs, int index) throws SQLException {
            Organizations org = new Organizations();
            org.setId(rs.getInt("org_id"));
            org.setName(rs.getString("org_name"));
            org.setDescription(rs.getString("org_description"));
            org.setHotline(rs.getString("org_hotline"));

            return org;
        }
    }

}
