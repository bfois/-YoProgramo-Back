/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Service;

import com.bef.api.Entity.Proyectos;
import com.bef.api.Repository.RProyectos;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyectos {
    @Autowired
    RProyectos rProyectos;
    
    public List<Proyectos> list(){
    return rProyectos.findAll();
    }
    
    public Optional<Proyectos> getOne(int id){
    return rProyectos.findById(id);
    }
    
    public Optional<Proyectos> getByNombre(String nombre){
    return rProyectos.findByNombre(nombre);
    }
    
    public void save(Proyectos proyec){
        rProyectos.save(proyec);
    }
    
    public void delete(int id){
        rProyectos.deleteById(id);
    }
    
    public boolean existsById(int id){
    return rProyectos.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
    return rProyectos.existsByNombre(nombre);
    }
    
}
