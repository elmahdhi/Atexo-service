package com.atexo.carte.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class Erreur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private Integer code;

    public Erreur message(String message) {
        this.message = message;
        return this;
    }

    public Erreur code(Integer code){
        this.code = code;
        return this;
    }
}
