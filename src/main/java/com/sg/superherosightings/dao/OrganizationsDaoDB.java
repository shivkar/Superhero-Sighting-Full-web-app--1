/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Organizations;
import com.sg.superherosightings.entity.Superheros;
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
public class OrganizationsDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organizations getOrganizationById(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM organizations WHERE org_id = ?";
            Organizations org = jdbc.queryForObject(SELECT_ORG_BY_ID, new OrgMapper(), id);
            org.setMembers(getMembersForOrg(id));
            return org;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organizations> getAllOrganizations() {
        final String SELECT_ALL_ORGS = "SELECT * FROM organizations";
        List<Organizations> orgs = jdbc.query(SELECT_ALL_ORGS, new OrgMapper());
        associateSuperheros(orgs);
        return orgs;
    }

    @Override
    public Organizations addOrganization(Organizations org) {
        final String INSERT_ORG = "INSERT INTO organizations(org_name, org_description, "
                + "org_hotline) VALUES(?,?,?)";
        jdbc.update(INSERT_ORG,
                org.getName(),
                org.getDescription(),
                org.getHotline());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setId(newId);
        insertSuperheroOrganization(org);
        return org;
    }

    @Override
    @Transactional
    public void updateOrganization(Organizations org) {
        final String UPDATE_ORG = "UPDATE organizations SET org_name = ?, org_description = ?, "
                + "org_hotline = ? WHERE org_id = ?";
        jdbc.update(UPDATE_ORG,
                org.getName(),
                org.getDescription(),
                org.getHotline(),
                org.getId());

        final String DELETE_SUPER_ORG = "DELETE FROM super_organization WHERE org_id = ?";
        jdbc.update(DELETE_SUPER_ORG, org.getId());
        insertSuperheroOrganization(org);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_SUPER_ORG = "DELETE FROM super_organization WHERE org_id = ?";
        jdbc.update(DELETE_SUPER_ORG, id);

        final String DELETE_ORG = "DELETE FROM organizations WHERE org_id = ?";
        jdbc.update(DELETE_ORG, id);
    }

    private List<Superheros> getMembersForOrg(int id) {
       final String SELECT_MEMBERS_FOR_ORG = "SELECT s.* FROM superheros s "
                + "JOIN super_organization so ON so.superhero_id = s.superhero_id WHERE so.org_id = ?";
        return jdbc.query(SELECT_MEMBERS_FOR_ORG, new SuperherosMapper(), id);
    }

   
    private void associateSuperheros(List<Organizations> orgs) {
        for (Organizations org : orgs) {
            org.setMembers(getMembersForOrg(org.getId()));
        }
    }

   

    private void insertSuperheroOrganization(Organizations org) {
        final String INSERT_SUPER_ORG = "INSERT INTO "
                + "super_organization(superhero_id, org_id) VALUES(?,?)";
        if (org.getMembers() != null) {
            for (Superheros superhero : org.getMembers()) {
                jdbc.update(INSERT_SUPER_ORG,
                        superhero.getId(),
                        org.getId());
            }
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
}
