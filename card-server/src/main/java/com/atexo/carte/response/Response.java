package com.atexo.carte.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private Erreur error;
}
