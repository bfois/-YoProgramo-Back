/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Controller;

import com.bef.api.Entity.Redes;
import com.bef.api.Service.SRedes;
import com.bef.api.dto.DtoRedes;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redes")
@CrossOrigin(origins = {"https://portfoliobf-d3405.web.app/","http://localhost:4200"})
public class CRedes {
    @Autowired
    SRedes sRedes;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Redes>> list(){
        List<Redes> list = sRedes.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public String createPersona(@RequestBody Redes red){
    sRedes.save(red);
    return "Agregar red social";
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoRedes dtoRedes){
        if(!sRedes.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        if(sRedes.existsByNombre(dtoRedes.getNombre()) && sRedes.getByNombre(dtoRedes.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("esa red social ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoRedes.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        Redes redes = sRedes.getOne(id).get();
        redes.setNombre(dtoRedes.getNombre());
        redes.setIcono(dtoRedes.getIcono());
        redes.setUrl(dtoRedes.getUrl());
               sRedes.save(redes);
               return new ResponseEntity(new Mensaje("red social actualizada"),HttpStatus.OK);
    }
    
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    if(!sRedes.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        sRedes.delete(id);
        return new ResponseEntity(new Mensaje("red social eliminada"),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Redes> getById(@PathVariable("id") int id){
        if(!sRedes.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Redes redes = sRedes.getOne(id).get();
        return new ResponseEntity(redes, HttpStatus.OK);
    }
    
}
