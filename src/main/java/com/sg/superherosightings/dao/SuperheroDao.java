/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entity.Superheros;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author SHIVALI
 */
public interface SuperheroDao {
    
    
    Superheros getSuperheroById(int id);
    List<Superheros> getAllSuperheros();
    Superheros addSuperhero(Superheros superhero);
    void updateSuperhero(Superheros superhero);
    void deleteSuperheroById(int id);
   
    
    //List<Powers> getPowersForSuper(Super super);
    //List<Course> getCoursesForStudent(Student student);
    
}
