package com.atexo.carte.enumeration;

public enum Color {
    Coeur,
    Pique,
    Carreaux,
    Trefle;

    public String toString()
    {
        if(name().startsWith("T"))
        {
            return "Tr"+'\u00e8'+"fle";
        }
        return name();
    }
}
