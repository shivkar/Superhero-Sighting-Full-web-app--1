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
import com.sg.superherosightings.entity.Powers;
import com.sg.superherosightings.entity.Sightings;
import com.sg.superherosightings.entity.Superheros;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SHIVALI
 */
@Controller
public class PowerController {

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

    Set<ConstraintViolation<Powers>> violations = new HashSet<>();

    @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Powers> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("errors", violations);
        return "powers";
    }

    @PostMapping("addPower")
    public String addSuperhero(HttpServletRequest request, Model model) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Powers power = new Powers();
        power.setName(name);
        power.setDescription(description);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(power);
        if (violations.isEmpty()) {
            powerDao.addPower(power);
        }

        // model.addAttribute("errors", violations);
        return "redirect:/powers";
    }

    @GetMapping("detailPower")
    public String detailPower(Integer id, Model model) {
        Powers powers = powerDao.getPowerById(id);
        model.addAttribute("power", powers);
        return "detailPower";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        powerDao.deletePowerById(id);
        return "redirect:/powers";
    }

    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Powers power = powerDao.getPowerById(id);
        //String name = request.getParameter("name");
        //String description = request.getParameter("description");

        model.addAttribute("power", power);
        return "editPower";
    }

    @PostMapping("editPower")
    public String performEditPower(
        HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) 
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Powers power = powerDao.getPowerById(id);
        power.setName(request.getParameter("name"));
        power.setDescription(request.getParameter("description"));

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(power);
        if (violations.isEmpty()) {
            powerDao.updatePower(power);
         
         return "redirect:/detailPower";
    }
        
       
       // redirectAttributes.addAttribute("errors", violations.toString());
        redirectAttributes.addAttribute("id", power.id);
        return "redirect:editPower";
    }
}
