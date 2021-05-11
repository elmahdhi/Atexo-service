package com.atexo.carte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class CardDto implements Serializable {

    private static final long serialVersionUID = 12345678912236789L;


    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private byte value;

    @Getter @Setter
    private byte color;

    @Getter @Setter
    private String libelle;


    @Override
    public String toString() {
        return libelle;
    }
}
