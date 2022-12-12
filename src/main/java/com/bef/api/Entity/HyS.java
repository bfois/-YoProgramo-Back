/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter @Setter
public class HyS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreHyS;
    private int porcentajeHyS;

    public HyS() {
    }

    public HyS(String nombreHyS, int porcentajeHyS) {
        this.nombreHyS = nombreHyS;
        this.porcentajeHyS = porcentajeHyS;
    }
    
    
}
