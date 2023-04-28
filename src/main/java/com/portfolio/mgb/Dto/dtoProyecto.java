package com.portfolio.mgb.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProyecto {

    @NotBlank
    private String nombreP;
    @NotBlank
    private String descriptionP;
    @NotBlank
    private String img;

    public dtoProyecto() {
    }

    public dtoProyecto(String nombreP, String descriptionP, String img) {
        this.nombreP = nombreP;
        this.descriptionP = descriptionP;
        this.img = img;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
