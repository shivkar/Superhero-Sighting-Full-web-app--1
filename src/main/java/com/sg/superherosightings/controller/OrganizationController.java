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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
public class OrganizationController {

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
    
     Set<ConstraintViolation<Organizations>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganization(Model model) {
        List<Organizations> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
         model.addAttribute("errors", violations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Model model,HttpServletRequest request)
    {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String hotline = request.getParameter("hotline");
        Organizations organization = new Organizations();
          Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
           violations = validate.validate(organization);
           if(violations.isEmpty()) {
           organizationDao.addOrganization(organization);
        }
        
        model.addAttribute("errors", violations);

        
        organization.setName(name);
        organization.setDescription(description);
        organization.setHotline(hotline);
      
        return "redirect:/organizations";
    }
    
    @GetMapping("detailOrganization")
    public String detailOrganization(Integer id, Model model) {
        Organizations organizations = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organizations);
        return "detailOrganization";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organizations organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }
    
     @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request,RedirectAttributes redirectAttributes) {
         int id = Integer.parseInt(request.getParameter("id"));
          Organizations organization  = organizationDao.getOrganizationById(id);
         organization.setName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
         organization.setHotline(request.getParameter("hotline"));
        organizationDao.updateOrganization(organization);
        redirectAttributes.addAttribute("id", organization.id);
        return "redirect:detailOrganization";
    }
}
