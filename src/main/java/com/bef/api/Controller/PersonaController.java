/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Controller;
import com.bef.api.Entity.Persona;
import com.bef.api.Service.ImpPersonaService;
import com.bef.api.dto.DtoPersona;
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
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://portfoliobf-d3405.web.app/","http://localhost:4200"})
public class PersonaController {
    @Autowired
    ImpPersonaService impPersonaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list =  impPersonaService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoPer){
        if(!impPersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        if(impPersonaService.existsByNombre(dtoPer.getNombre()) && impPersonaService.getByNombre(dtoPer.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("esa persona ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPer.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        Persona persona = impPersonaService.getOne(id).get();
        persona.setNombre(dtoPer.getNombre());
        persona.setApellido(dtoPer.getApellido());
        persona.setDescripcion(dtoPer.getDescripcion());
        persona.setImg(dtoPer.getImg());
        
               impPersonaService.save(persona);
               return new ResponseEntity(new Mensaje("persona actualizada"),HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    @PostMapping("/crear")
    public String createPersona(@RequestBody Persona persona){
    impPersonaService.save(persona);
    return "la persona fue creada con exito";
    }
}
