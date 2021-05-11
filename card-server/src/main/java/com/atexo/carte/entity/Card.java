package com.atexo.carte.entity;


import com.atexo.carte.enumeration.Color;
import com.atexo.carte.enumeration.Figure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
public class Card implements Serializable {

    private static final long serialVersionUID = 123456789123456789L;


    public final static byte[] valueCard = new byte[15];
    public final static byte[] colorCard = new byte[8];


    static {
        colorCard[0]=0;
        colorCard[1]=1;
        colorCard[2]=2;
        colorCard[3]=3;

        valueCard[0]=0;
        valueCard[10]=1;
        valueCard[11]=2;
        valueCard[12]=3;

        valueCard[0]=2;
        valueCard[1]=3;
        valueCard[2]=4;
        valueCard[3]=5;
        valueCard[4]=6;
        valueCard[5]=7;
        valueCard[6]=8;
        valueCard[7]=9;
        valueCard[8]=10;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private byte value;

    @Getter @Setter
    private byte color;

    @Setter
    private String libelle;

    public Card(byte value, byte color){
        this.value = value;
        this.color = colorCard[color];
    }


    @Override
    public String toString() {
        return valueString(value) + " " +
                colorString (color);
    }

    private String colorString(byte color) {
        return Color.values()[color].toString();
    }

    private String valueString(byte value) {
        if(value <= 8){
            return String.valueOf(valueCard[value]);
        }
        return Figure.getMap().get((int) value);
    }

    public String getLibelle() {
        return this.toString();
    }
}
