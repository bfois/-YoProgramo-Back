/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Controller;

import com.bef.api.Entity.HyS;
import com.bef.api.Service.SHyS;
import com.bef.api.dto.DtoHyS;

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
@RequestMapping("/skills")
@CrossOrigin(origins = {"https://portfoliobf-d3405.web.app/","http://localhost:4200"})
public class CHyS {
     @Autowired
    SHyS sHyS;
    
   
    @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list(){
        List<HyS> list = sHyS.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHyS dtoHyS){
        if(StringUtils.isBlank(dtoHyS.getNombreHyS())){
         return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }   
        if(sHyS.existsByNombreHyS(dtoHyS.getNombreHyS())){
        return new ResponseEntity(new Mensaje("Esa skill existe"),HttpStatus.BAD_REQUEST);
        }
        HyS hyS = new HyS(dtoHyS.getNombreHyS(),dtoHyS.getPorcentajeHyS());
        sHyS.save(hyS);
        return new ResponseEntity(new Mensaje("skill agregada"),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHyS dtoHyS){
        if(!sHyS.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        if(sHyS.existsByNombreHyS(dtoHyS.getNombreHyS()) && sHyS.getByNombreHyS(dtoHyS.getNombreHyS()).get().getId() != id){
            return new ResponseEntity(new Mensaje("esa skill ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoHyS.getNombreHyS())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        HyS hyS = sHyS.getOne(id).get();
        hyS.setNombreHyS(dtoHyS.getNombreHyS());
        hyS.setPorcentajeHyS(dtoHyS.getPorcentajeHyS());
               sHyS.save(hyS);
               return new ResponseEntity(new Mensaje("skill actualizada"),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    if(!sHyS.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        sHyS.delete(id);
        return new ResponseEntity(new Mensaje("skill eliminada"),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id){
        if(!sHyS.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        HyS hyS = sHyS.getOne(id).get();
        return new ResponseEntity(hyS, HttpStatus.OK);
    }
}
