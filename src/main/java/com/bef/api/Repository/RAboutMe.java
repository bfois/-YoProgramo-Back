/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bef.api.Repository;

import com.bef.api.Entity.AboutMe;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAboutMe extends JpaRepository<AboutMe, Integer> {
     public Optional<AboutMe> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
