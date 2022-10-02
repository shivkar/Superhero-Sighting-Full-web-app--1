/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Locations;
import com.sg.superherosightings.entity.Organizations;
import com.sg.superherosightings.entity.Powers;
import com.sg.superherosightings.entity.Sightings;
import com.sg.superherosightings.entity.Superheros;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author SHIVALI
 */

@SpringBootTest
public class OrganizationsDaoDBTest {
    
     @Autowired
    SuperheroDao superheroDao;
     
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    PowerDao powerDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    JdbcTemplate jdbc;
    
    public OrganizationsDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Superheros> superhero = superheroDao.getAllSuperheros();
        for(Superheros superheros : superhero) {
            superheroDao.deleteSuperheroById(superheros.getId());
        }
        
         List<Powers> powers = powerDao.getAllPowers();
         for(Powers power : powers) {
           powerDao.deletePowerById(power.getId());
        }
         
         List<Sightings> sightings = sightingDao.getAllSightings();
         for(Sightings sighting : sightings) {
           sightingDao.deleteSightingById(sighting.getSightingId());
        }
         
          List<Locations> locations = locationDao.getAllLocations();
         for(Locations location : locations) {
           locationDao.deleteLocationById(location.getLocid());
        }
         
       List<Organizations> organizations = organizationDao.getAllOrganizations();
         for(Organizations organization : organizations) {
           organizationDao.deleteOrganizationById(organization.getId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrganizationById method, of class OrganizationsDaoDB.
     */
    @Test
    public void testAddandGetOrganizationById() {
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Superheros> members = new ArrayList();
        org.setMembers(members);
        org = organizationDao.addOrganization(org);

        Organizations fromDao = organizationDao.getOrganizationById(org.getId());
        assertEquals(org, fromDao);
        
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationsDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
        
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Superheros> members = new ArrayList();
        org.setMembers(members);
        org = organizationDao.addOrganization(org);

        
        Organizations org2 = new Organizations();
        org2.setName("Test Organization Name 2");
        org2.setDescription("Test Organization Description 2");
        org2.setHotline("(777) 777-7777");
        org2 = organizationDao.addOrganization(org);
        
        List<Superheros> members2 = new ArrayList();
        org2.setMembers(members2);
        org = organizationDao.addOrganization(org);
        
         List<Organizations> orgs = organizationDao.getAllOrganizations();
        
        assertEquals(4, orgs.size());
        assertTrue(orgs.contains(org));
        assertTrue(orgs.contains(org2));
    }

    

    /**
     * Test of updateOrganization method, of class OrganizationsDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Superheros> members = new ArrayList();
        org.setMembers(members);
        org = organizationDao.addOrganization(org);
        
        Organizations fromDao = organizationDao.getOrganizationById(org.getId());
        assertEquals(org, fromDao);
        
        org.setName("New Test Org Name");
        organizationDao.updateOrganization(org);
        
        assertNotEquals(org, fromDao);
        
        fromDao = organizationDao.getOrganizationById(org.getId());
        
        assertEquals(org, fromDao);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationsDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Superheros> members = new ArrayList();
        org.setMembers(members);
        org = organizationDao.addOrganization(org);
        
        Organizations fromDao = organizationDao.getOrganizationById(org.getId());
        assertEquals(org, fromDao);
        
        organizationDao.deleteOrganizationById(org.getId());
        
        fromDao = organizationDao.getOrganizationById(org.getId());
        assertNull(fromDao);
        
        
    }
    
}
