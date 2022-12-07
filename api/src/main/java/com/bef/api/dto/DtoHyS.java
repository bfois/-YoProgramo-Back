/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoHyS {
    @NotBlank
    private String nombreHyS;
    @NotBlank
    private int porcentajeHyS;

    public DtoHyS() {
    }

    public DtoHyS(String nombreHyS, int porcentajeHyS) {
        this.nombreHyS = nombreHyS;
        this.porcentajeHyS = porcentajeHyS;
    }
    
}
