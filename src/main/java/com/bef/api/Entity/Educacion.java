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
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreEdu;
    private String descripcionEdu;
    private int initDate;
    private int finishDate;
    
    public Educacion() {
    }

    public Educacion(String nombreEdu, String descripcionEdu, int initDate, int finishDate) {
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
        this.initDate = initDate;
        this.finishDate = finishDate;
    }
    
}
