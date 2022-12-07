/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Service;

import com.bef.api.Entity.AboutMe;
import com.bef.api.Repository.RAboutMe;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SAboutMe {
     @Autowired
    RAboutMe rAboutMe;
    
     public List<AboutMe> list(){
    return rAboutMe.findAll();
    }
    
    public Optional<AboutMe> getOne(int id){
    return rAboutMe.findById(id);
    }
    
    public Optional<AboutMe> getByNombre(String nombre){
    return rAboutMe.findByNombre(nombre);
    }
    
    public void save(AboutMe about){
       rAboutMe.save(about);
    }
    
    public void delete(int id){
        rAboutMe.deleteById(id);
    }
    
    public boolean existsById(int id){
    return rAboutMe.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
    return rAboutMe.existsByNombre(nombre);
    }
}
