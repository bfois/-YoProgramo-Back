/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bef.api.Repository;

import com.bef.api.Entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    
}
