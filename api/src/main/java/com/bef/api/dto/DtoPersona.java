/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoPersona {
    @NotBlank
    private int id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
     @NotBlank
    private String descripcion;
     @NotBlank
    private String img;

    public DtoPersona() {
    }

    public DtoPersona(String nombre, String apellido, String descripcion, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.img = img;
}
}
