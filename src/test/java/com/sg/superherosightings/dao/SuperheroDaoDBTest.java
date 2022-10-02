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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author SHIVALI
 */

@SpringBootTest
public class SuperheroDaoDBTest {
    
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
    
    
    
    
    public SuperheroDaoDBTest() {
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
     * Test of getSuperheroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testAddAndGetSuperheroById() {
            
            Powers power = new Powers();
        power.setName("Test Powers Name");
        power.setDescription("Test Powers Description");
        power = powerDao.addPower(power);
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Powers> powersList = new ArrayList<>();
        powersList.add(power);
        
        List<Organizations> orgList = new ArrayList<>();
        orgList.add(org);
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
        superhero.setPowers(powersList);
        superhero.setOrganizations(orgList);
        superhero = superheroDao.addSuperhero(superhero);
        
        Superheros fromDao = superheroDao.getSuperheroById(superhero.getId());
        assertEquals(superhero, fromDao);
    }
    

    

    /**
     * Test of getAllSuperheros method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetAllSuperheros() {
        
         Powers power = new Powers();
        power.setName("Test Powers Name");
        power.setDescription("Test Powers Description");
        power = powerDao.addPower(power);
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Powers> powersList = new ArrayList<>();
        powersList.add(power);
        
        List<Organizations> orgList = new ArrayList<>();
        orgList.add(org);
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Superhero Name");
        superhero.setDescription("Test Superhero Description");
        superhero.setIsHero(true);
        superhero.setPowers(powersList);
        superhero.setOrganizations(orgList);
        superhero = superheroDao.addSuperhero(superhero);
        
         Superheros superhero2 = new Superheros();
        superhero2.setName("Test Superhero Name 2");
        superhero2.setDescription("Test Superhero Description 2");
        superhero2.setIsHero(false);
        superhero2.setPowers(powersList);
        superhero2.setOrganizations(orgList);
        superhero2 = superheroDao.addSuperhero(superhero2);
        
        List<Superheros> superheroList = superheroDao.getAllSuperheros();
        assertEquals(2, superheroList.size());
        assertTrue(superheroList.contains(superhero));
        assertTrue(superheroList.contains(superhero2));
    
    }

    
    

    /**
     * Test of updateSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testUpdateSuperhero() {
            
             Powers power = new Powers();
        power.setName("Test Powers Name");
        power.setDescription("Test Powers Description");
        power = powerDao.addPower(power);
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Powers> powersList = new ArrayList<>();
        powersList.add(power);
        
        List<Organizations> orgList = new ArrayList<>();
        orgList.add(org);
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
        superhero.setPowers(powersList);
        superhero.setOrganizations(orgList);
        superhero = superheroDao.addSuperhero(superhero);
        
        Superheros fromDao = superheroDao.getSuperheroById(superhero.getId());
        assertEquals(superhero, fromDao);
       
        superhero.setName("New Test Super Name 2");
        superhero.setDescription("New Test Super Description 2");
        superhero.setIsHero(false);
        Powers power2 = new Powers();
        power2.setName("New Power Name");
        power2.setDescription("New Description Power");
        power2 = powerDao.addPower(power2);
        
        Organizations org2 = new Organizations();
        org2.setName("New Test Organization Name");
        org2.setDescription("New Test Organization Description");
        org2.setHotline("(551) 777-5555");
        org2 = organizationDao.addOrganization(org2);
        powersList.add(power2);
        orgList.add(org2);
        superhero.setPowers(powersList);
        superhero.setOrganizations(orgList);
        
        superheroDao.updateSuperhero(superhero);
        
        assertNotEquals(superhero, fromDao);
        
        fromDao = superheroDao.getSuperheroById(superhero.getId());
        assertEquals(superhero, fromDao);
    }
    

    /**
     * Test of deleteSuperheroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testDeleteSuperheroById() {
        
        Powers power = new Powers();
        power.setName("Test Powers Name");
        power.setDescription("Test Powers Description");
        power = powerDao.addPower(power);
        
        Organizations org = new Organizations();
        org.setName("Test Organization Name");
        org.setDescription("Test Organization Description");
        org.setHotline("(777) 555-7777");
        org = organizationDao.addOrganization(org);
        
        List<Powers> powersList = new ArrayList<>();
        powersList.add(power);
        
        List<Organizations> orgList = new ArrayList<>();
        orgList.add(org);
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
        superhero.setPowers(powersList);
        superhero.setOrganizations(orgList);
        superhero = superheroDao.addSuperhero(superhero);
        
        Superheros fromDao = superheroDao.getSuperheroById(superhero.getId());
        assertEquals(superhero, fromDao);
        
        superheroDao.deleteSuperheroById(superhero.getId());
        
        fromDao = superheroDao.getSuperheroById(superhero.getId());
        assertNull(fromDao);
    }
    
}
