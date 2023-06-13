/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Controller;

import com.bef.api.Entity.Proyectos;
import com.bef.api.Service.SProyectos;
import com.bef.api.dto.DtoProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://foisbrian.online/","http://localhost:4200"})
public class CProyectos {
    
    @Autowired
    SProyectos sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoProyectos){
        if(StringUtils.isBlank(dtoProyectos.getNombre())){
         return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }   
        if(sProyectos.existsByNombre(dtoProyectos.getNombre())){
        return new ResponseEntity(new Mensaje("Ese proyecto existe"),HttpStatus.BAD_REQUEST);
        }
        Proyectos proyec = new Proyectos(dtoProyectos.getNombre(),dtoProyectos.getDescripcion(),dtoProyectos.getImg(),dtoProyectos.getUrl());
        sProyectos.save(proyec);
        return new ResponseEntity(new Mensaje("proyecto agregado"),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoProyectos){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        if(sProyectos.existsByNombre(dtoProyectos.getNombre()) && sProyectos.getByNombre(dtoProyectos.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("ese proyecto ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProyectos.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        Proyectos proyec = sProyectos.getOne(id).get();
        proyec.setNombre(dtoProyectos.getNombre());
        proyec.setDescripcion(dtoProyectos.getDescripcion());
        proyec.setImg(dtoProyectos.getImg());
        proyec.setUrl(dtoProyectos.getUrl());
               sProyectos.save(proyec);
               return new ResponseEntity(new Mensaje("proyecto actualizado"),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("proyecto eliminado"),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyectos proyec = sProyectos.getOne(id).get();
        return new ResponseEntity(proyec, HttpStatus.OK);
    }
    
}
