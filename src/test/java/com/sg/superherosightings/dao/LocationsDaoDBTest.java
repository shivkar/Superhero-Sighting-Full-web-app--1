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
public class LocationsDaoDBTest {

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

    public LocationsDaoDBTest() {
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
        for (Superheros superheros : superhero) {
            superheroDao.deleteSuperheroById(superheros.getId());
        }

        List<Powers> powers = powerDao.getAllPowers();
        for (Powers power : powers) {
            powerDao.deletePowerById(power.getId());
        }

        List<Sightings> sightings = sightingDao.getAllSightings();
        for (Sightings sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingId());
        }

        List<Locations> locations = locationDao.getAllLocations();
        for (Locations location : locations) {
            locationDao.deleteLocationById(location.getLocid());
        }

        List<Organizations> organizations = organizationDao.getAllOrganizations();
        for (Organizations organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getLocationById method, of class LocationsDaoDB.
     */
    @Test
    public void testAddandGetLocationById() {

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

        Locations fromDao = locationDao.getLocationById(location.getLocid());
        assertEquals(location, fromDao);
    }

    /**
     * Test of getAllLocations method, of class LocationsDaoDB.
     */
    @Test
    public void testGetAllLocations() {

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

        Locations location2 = new Locations();
        location2.setLocname("Test Locations Name 2");
        location2.setLocdescription("Test Location Description 2");
        location2.setLocstreet("Test Street 2");
        location2.setLoccity("Test City 2");
        location2.setLocstate("TX");
        location2.setLoczip("41502");
        location2.setLatitude("81.99998");
        location2.setLongitude("-72.99901");
        location = locationDao.addLocation(location2);
        
        List<Locations> locations = locationDao.getAllLocations();
        
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));

    }

    /**
     * Test of updateLocation method, of class LocationsDaoDB.
     */
    @Test
    public void testUpdateLocation() {
        
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
        
         Locations fromDao = locationDao.getLocationById(location.getLocid());
        assertEquals(location, fromDao);
        
        location.setLocname("New Test Location");
        locationDao.updateLocation(location);
        
        assertNotEquals(location, fromDao);
        
        fromDao = locationDao.getLocationById(location.getLocid());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteLocationById method, of class LocationsDaoDB.
     */
    @Test
    public void testDeleteLocationById() {
        
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
        
        Locations fromDao = locationDao.getLocationById(location.getLocid());
        assertEquals(location, fromDao);
        
        locationDao.deleteLocationById(location.getLocid());
        
        fromDao = locationDao.getLocationById(location.getLocid());
        assertNull(fromDao);
    }

}
