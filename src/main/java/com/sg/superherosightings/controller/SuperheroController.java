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
import com.sg.superherosightings.entity.Organizations;
import com.sg.superherosightings.entity.Powers;
import com.sg.superherosightings.entity.Superheros;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SHIVALI
 */
@Controller

public class SuperheroController {

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

    Set<ConstraintViolation<Superheros>> violations = new HashSet<>();
    
    @GetMapping("superheros")
    public String displaySuperheros(Model model) {
        List<Superheros> superheros = superheroDao.getAllSuperheros();
        List<Powers> powers = powerDao.getAllPowers();
        List<Organizations> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("errors", violations);
        model.addAttribute("superheros", superheros);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);

        return "superheros";
    }

    @PostMapping("addSuperhero")
    public String addSuperhero(Superheros superhero,Model model, 
            HttpServletRequest request) 
    {
    
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] powerIds = request.getParameterValues("powerId");
        String[] organizationIds = request.getParameterValues("organizationId");
        boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
       
            
        List<Powers> powers = new ArrayList<>();
       
        if (powerIds != null) {
            for (String powerId : powerIds) {
                powers.add(powerDao.getPowerById(Integer.parseInt(powerId)));
            }
        }

        List<Organizations> organizations = new ArrayList<>();
        if (organizationIds != null) {
            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
        }
                     
        superhero.setName(name);
        superhero.setDescription(description);
        superhero.setPowers(powers);
        superhero.setOrganizations(organizations);
        superhero.setIsHero(isHero);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
           violations = validate.validate(superhero);
           if(violations.isEmpty()) {
                   superheroDao.addSuperhero(superhero);
        }
           
            model.addAttribute("errors", violations);
       // superheroDao.addSuperhero(superhero);
        return "redirect:/superheros";
    }
    
   @GetMapping("detailSuperhero")
    public String detailSuperhero(Integer id, Model model) {
        Superheros superheros = superheroDao.getSuperheroById(id);
        model.addAttribute("superhero", superheros);
        
        return "detailSuperhero";
    }

     
    @GetMapping("deleteSuperhero")
    public String deleteSuperhero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superheroDao.deleteSuperheroById(id);

        return "redirect:/superheros";
    }
    
    @GetMapping("editSuperhero")
        public String editSuperhero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superheros superhero = superheroDao.getSuperheroById(id);
        List<Powers> powers = powerDao.getAllPowers();
        List<Organizations> organizations = organizationDao.getAllOrganizations();
        
        model.addAttribute("superhero", superhero);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);
           
        return "editSuperhero";
    }

    @PostMapping("editSuperhero")
    
    public String performEditSuperhero(@Valid Superheros superhero,BindingResult result,
            HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) 
    {
         
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] powerIds = request.getParameterValues("powerId");
        String[] organizationIds = request.getParameterValues("organizationId");
        
        List<Powers> powers = new ArrayList<>();
        if (powerIds != null) {
            for (String powerId : powerIds) {
                powers.add(powerDao.getPowerById(Integer.parseInt(powerId)));
            }
        }
        else {
            FieldError error = new FieldError("superheros", "powers", "Must include one power");
            result.addError(error);
        }
        superhero.setPowers(powers);
        
        List<Organizations> organizations = new ArrayList<>();
        if (organizationIds != null) {
            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
        }
        
        else {
            FieldError error = new FieldError("superheros", "organizations", "Must include one organization");
            result.addError(error);
        }
        
         superhero.setOrganizations(organizations);
          
        //Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
       //   violations = validate.validate(superhero);
         //  if(violations.isEmpty()) {superheroDao.updateSuperhero(superhero);
           
        
       //  return "redirect:/superheros";
   // }
        
         //else {
          //  superhero = superheroDao.getSuperheroById(superhero.getId());
          //  model.addAttribute("superhero", superhero);
        
        superhero.setName(name);
        superhero.setDescription(description);
        
         if(result.hasErrors()) {
            model.addAttribute("powers", powerDao.getAllPowers());
            model.addAttribute("organizations", organizationDao.getAllOrganizations());
            model.addAttribute("superhero", superhero);
            redirectAttributes.addAttribute("id", superhero.id);
            return "redirect:detailSuperhero";
        }
       
       //  superheroDao.updateSuperhero(superhero);
       
        model.addAttribute("errors", violations);
        redirectAttributes.addAttribute("id", superhero.id);
       // return  "redirect:/superheros";
         
       return  "redirect: detailSuperhero";
    }

}