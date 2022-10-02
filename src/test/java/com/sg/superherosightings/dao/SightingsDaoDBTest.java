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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
public class SightingsDaoDBTest {
    
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
    
    
    public SightingsDaoDBTest() {
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
     * Test of getSightingById method, of class SightingsDaoDB.
     */
    @Test
    public void testAddAndGetSightingById() {
       
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
                
        List<Powers> powers = new ArrayList<>();
        superhero.setPowers(powers);
        
        List<Organizations> orgs = new ArrayList<>();
        superhero.setOrganizations(orgs);
        
        superhero = superheroDao.addSuperhero(superhero);
        
        Locations location = new Locations();
        location.setLocname("Test Locations Name");
        location.setLocdescription("Test Location Description");
        location.setLocstreet("Test Street");
        location.setLoccity("Test City");
        location.setLocstate("ON");
        location.setLoczip("L5M7K3");
        location.setLatitude("43.54272");
        location.setLongitude("-79.72037");
        location = locationDao.addLocation(location);
        
        Sightings sighting = new Sightings();
        sighting.setSuperheroId(superhero.getId());
        sighting.setLocationId(location.getLocid());
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String text = timestamp.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        sighting.setSightingTime(parsedDate);
        sighting = sightingDao.addSighting(sighting);
        
        Sightings fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    
    }

    /**
     * Test of getAllSightings method, of class SightingsDaoDB.
     */
    @Test
    public void testGetAllSightings() {
        
         Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
                
        List<Powers> powers = new ArrayList<>();
        superhero.setPowers(powers);
        
        List<Organizations> orgs = new ArrayList<>();
        superhero.setOrganizations(orgs);
        
        superhero = superheroDao.addSuperhero(superhero);
        
        Locations location = new Locations();
        location.setLocname("Test Locations Name");
        location.setLocdescription("Test Location Description");
        location.setLocstreet("Test Street");
        location.setLoccity("Test City");
        location.setLocstate("ON");
        location.setLoczip("L5M7K3");
        location.setLatitude("43.54272");
        location.setLongitude("-79.72037");
        location = locationDao.addLocation(location);
        
        Sightings sighting = new Sightings();
        sighting.setSuperheroId(superhero.getId());
        sighting.setLocationId(location.getLocid());
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String text = timestamp.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        sighting.setSightingTime(parsedDate);
        sighting = sightingDao.addSighting(sighting);
        
        
        Superheros superhero2 = new Superheros();
        superhero2.setName("Test Super Name");
        superhero2.setDescription("Test Super Description");
        superhero2.setIsHero(true);
        superhero2.setPowers(powers);
        superhero2.setOrganizations(orgs);
        superhero2 = superheroDao.addSuperhero(superhero2);
        
        Locations location2 = new Locations();
        location2.setLocname("Test Locations Name 2");
        location2.setLocdescription("Test Location Description 2");
        location2.setLocstreet("Test Street");
        location2.setLoccity("Test City");
        location2.setLocstate("ON");
        location2.setLoczip("L5M7K3");
        location2.setLatitude("43.54272");
        location2.setLongitude("-79.72037");
        location2 = locationDao.addLocation(location2);
        
        Sightings sighting2 = new Sightings();
        sighting2.setSuperheroId(superhero.getId());
        sighting2.setLocationId(location.getLocid());
        sighting2.setSuperhero(superhero2);
        sighting2.setLocation(location2);
         
        text = timestamp.format(formatter);
        parsedDate = LocalDate.parse(text, formatter);
        sighting2.setSightingTime(parsedDate);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Sightings> sightings = sightingDao.getAllSightings();
        
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertFalse(sightings.contains(sighting2));
    
             
    }

    

    /**
     * Test of updateSighting method, of class SightingsDaoDB.
     */
    @Test
    public void testUpdateSighting() {
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
                
        List<Powers> powers = new ArrayList<>();
        superhero.setPowers(powers);
        
        List<Organizations> orgs = new ArrayList<>();
        superhero.setOrganizations(orgs);
        
        superhero = superheroDao.addSuperhero(superhero);
        
        Locations location = new Locations();
        location.setLocname("Test Locations Name");
        location.setLocdescription("Test Location Description");
        location.setLocstreet("Test Street");
        location.setLoccity("Test City");
        location.setLocstate("ON");
        location.setLoczip("L5M7K3");
        location.setLatitude("43.54272");
        location.setLongitude("-79.72037");
        location = locationDao.addLocation(location);
        
        Sightings sighting = new Sightings();
        sighting.setSuperheroId(superhero.getId());
        sighting.setLocationId(location.getLocid());
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String text = timestamp.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        sighting.setSightingTime(parsedDate);
        sighting = sightingDao.addSighting(sighting);
        
        Sightings fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    
         Superheros superhero2 = new Superheros();
        superhero2.setName("Test Super Name ");
        superhero2.setDescription("Test Super Description ");
        superhero2.setIsHero(true);
        superhero2.setPowers(powers);
        superhero2.setOrganizations(orgs);
        superhero2 = superheroDao.addSuperhero(superhero2);
        
        Locations location2 = new Locations();
        location2.setLocname("Test Locations Name ");
        location2.setLocdescription("Test Location Description ");
        location2.setLocstreet("Test Street");
        location2.setLoccity("Test City");
        location2.setLocstate("ON");
        location2.setLoczip("L5M7K3");
        location2.setLatitude("43.54272");
        location2.setLongitude("-79.72037");
        location2 = locationDao.addLocation(location2);
        
        
        sighting.setSuperheroId(superhero.getId());
        sighting.setLocationId(location.getLocid());
        sighting.setSuperhero(superhero2);
        sighting.setLocation(location2);
        
        
        sightingDao.updateSighting(sighting);
         assertNotEquals(sighting, fromDao);
         
          fromDao = sightingDao.getSightingById(sighting.getSightingId());
           assertNotEquals(sighting, fromDao);
    }

    /**
     * Test of deleteSightingById method, of class SightingsDaoDB.
     */
    @Test
    public void testDeleteSightingById() {
        
        Superheros superhero = new Superheros();
        superhero.setName("Test Super Name");
        superhero.setDescription("Test Super Description");
        superhero.setIsHero(true);
                
        List<Powers> powers = new ArrayList<>();
        superhero.setPowers(powers);
        
        List<Organizations> orgs = new ArrayList<>();
        superhero.setOrganizations(orgs);
        
        superhero = superheroDao.addSuperhero(superhero);
        
        Locations location = new Locations();
        location.setLocname("Test Locations Name");
        location.setLocdescription("Test Location Description");
        location.setLocstreet("Test Street");
        location.setLoccity("Test City");
        location.setLocstate("ON");
        location.setLoczip("L5M7K3");
        location.setLatitude("43.54272");
        location.setLongitude("-79.72037");
        location = locationDao.addLocation(location);
        
        Sightings sighting = new Sightings();
        sighting.setSuperheroId(superhero.getId());
        sighting.setLocationId(location.getLocid());
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String text = timestamp.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        sighting.setSightingTime(parsedDate);
        sighting = sightingDao.addSighting(sighting);
        
        Sightings fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    
        sightingDao.deleteSightingById(sighting.getSightingId());
        
        fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);
    }
    
}
