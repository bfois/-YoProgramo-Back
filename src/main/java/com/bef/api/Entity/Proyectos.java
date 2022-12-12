/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
     @NotNull
    private String descripcion;
      @NotNull
    private String img;
      private String url;

    public Proyectos() {
    }

    public Proyectos(String nombre, String descripcion, String img,String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img = img;
        this.url=url;
    }
}
