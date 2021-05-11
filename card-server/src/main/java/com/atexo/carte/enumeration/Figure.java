package com.atexo.carte.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Figure {
    AS(9),
    Dame(10),
    Roi(11),
    Valet(12);

    private final Integer code;

    Figure(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public static Map<Integer, String> getMap(){
        Map<Integer, String> figureMap = new HashMap<>();
        for (Figure figure : Figure.values()){
            figureMap.put(figure.getCode(), figure.name());
        }
        return figureMap;
    }
}
