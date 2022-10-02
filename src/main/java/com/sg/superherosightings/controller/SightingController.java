/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.PowerDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.entity.Locations;
import com.sg.superherosightings.entity.Sightings;
import com.sg.superherosightings.entity.Superheros;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SHIVALI
 */
@Controller
public class SightingController {

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;
    
     @GetMapping("home.html")
        public String displayLastTenSightings(Model model) {
        List<Sightings> sightings = sightingDao.getLastTenSightings();
        model.addAttribute("sightings", sightings);
        return "home.html";
    }

    @GetMapping("sightings")
    public String displayOrganization(Model model) {

        List<Sightings> sightings = sightingDao.getAllSightings();
        List<Superheros> superheros = superheroDao.getAllSuperheros();
        List<Locations> locations = locationDao.getAllLocations();
        
        model.addAttribute("sightings", sightings);
        model.addAttribute("superheros", superheros);
        model.addAttribute("locations", locations);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(Sightings sighting, HttpServletRequest request) {
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");
         LocalDate sightingTime = LocalDate.parse(request.getParameter("sightingtime"));
         sighting.setSuperhero(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));
         sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));  
       /* List<Superheros> superheros = new ArrayList<>();
        if (superheroIds != null) {
            for (String superheroId : superheroIds) {
            superheros.add(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));   }
        }
        List<Locations> locations = new ArrayList<>();
        if (locationIds != null) {
            for (String locationId : locationIds) {
                locations.add(locationDao.getLocationById(Integer.parseInt(locationId)));
            }
        }*/
      //  sighting.setLocation(locations);
      //  sighting.setSuperhero((Superheros) superheros);
        sighting.setSightingTime(sightingTime);
        sightingDao.addSighting(sighting);
        return "redirect:/sightings";
    }
    
     @GetMapping("detailSighting")
    public String detailSighting(Integer id, Model model) {
        Sightings sightings = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sightings);
        return "detailSighting";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        Sightings sighting = sightingDao.getSightingById(id);
        List<Superheros> superheros = superheroDao.getAllSuperheros();
        List<Locations> locations = locationDao.getAllLocations();
        model.addAttribute("sighting", sighting);
        model.addAttribute("superheros", superheros);
        model.addAttribute("locations", locations);
        return "editSighting";
    }
    
@PostMapping("editSighting")
    public String performEditSighting(Sightings sighting, HttpServletRequest request,
            RedirectAttributes redirectAttributes) 
    {
        
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");
        LocalDate sightingTime = LocalDate.parse(request.getParameter("sightingtime"));
        
         sighting.setSuperhero(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));
         sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));

      
        sighting.setSightingTime(sightingTime);
        sightingDao.updateSighting(sighting);
       redirectAttributes.addAttribute("id", sighting.sightingId);
      return  "redirect:detailSighting";
    }
}
