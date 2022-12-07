/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoRedes {
    @NotBlank
    private String nombre;
    @NotBlank
    private String icono;
    @NotBlank
    private String url;

    public DtoRedes() {
    }

    public DtoRedes(String nombre, String icono, String url) {
        this.nombre = nombre;
        this.icono = icono;
        this.url = url;
    }
    
    
}
