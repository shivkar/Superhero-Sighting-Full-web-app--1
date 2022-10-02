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
public class PowersDaoDBTest {
    
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
    
    public PowersDaoDBTest() {
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
     * Test of getPowerById method, of class PowersDaoDB.
     */
    @Test
    public void testAddAndGetPowerById() {
        
        Powers power = new Powers();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);
        
        Powers fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);
    }
    

    /**
     * Test of getAllPowers method, of class PowersDaoDB.
     */
    @Test
    public void testGetAllPowers() {
        Powers power = new Powers();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);
        
        Powers power2 = new Powers();
        power2.setName("Test Power Name 2");
        power2.setDescription("Test Power Description 2");
        power2 = powerDao.addPower(power2);
        
        List<Powers> powers = powerDao.getAllPowers();
        
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
    
    }

    

    /**
     * Test of updatePower method, of class PowersDaoDB.
     */
    @Test
    public void testUpdatePower() {
        Powers power = new Powers();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);
        
        Powers fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);
        
        power.setName("New Test Power Name");
        powerDao.updatePower(power);
        
        assertNotEquals(power, fromDao);
        
        fromDao = powerDao.getPowerById(power.getId());
        
        assertEquals(power, fromDao);
    }

    /**
     * Test of deletePowerById method, of class PowersDaoDB.
     */
    @Test
    public void testDeletePowerById() {
        
        Powers power = new Powers();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);
        
        Powers fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);
        
        powerDao.deletePowerById(power.getId());
        
        fromDao = powerDao.getPowerById(power.getId());
        assertNull(fromDao);
    }
    
}
