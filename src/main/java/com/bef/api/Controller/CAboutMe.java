/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Controller;

import com.bef.api.Entity.AboutMe;
import com.bef.api.Service.SAboutMe;
import com.bef.api.dto.DtoAboutMe;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aboutMe")
@CrossOrigin(origins = {"https://portfoliobf-d3405.web.app/","http://localhost:4200"})
public class CAboutMe {
    @Autowired
   SAboutMe sAboutMe;
    
    @GetMapping("/lista")
    public ResponseEntity<List<AboutMe>> list(){
        List<AboutMe> list =  sAboutMe.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoAboutMe dtoAbout){
        if(!sAboutMe.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        if(sAboutMe.existsByNombre(dtoAbout.getNombre()) && sAboutMe.getByNombre(dtoAbout.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("esa persona ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoAbout.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        AboutMe about = sAboutMe.getOne(id).get();
        about.setNombre(dtoAbout.getNombre());
        about.setDescripcion(dtoAbout.getDescripcion());
      
        
               sAboutMe.save(about);
               return new ResponseEntity(new Mensaje("persona actualizada"),HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<AboutMe> getById(@PathVariable("id") int id){
        if(!sAboutMe.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        AboutMe about = sAboutMe.getOne(id).get();
        return new ResponseEntity(about, HttpStatus.OK);
    }
}
