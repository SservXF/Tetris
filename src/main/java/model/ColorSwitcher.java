package main.java.model;

import java.awt.Color;

import main.java.model.Bloc.Couleur;

public interface ColorSwitcher {

    public static Couleur toBloc(Color c){
        if(c == Color.RED){return Couleur.RED;}
        if(c == Color.BLUE){return Couleur.BLUE;}
        if(c == Color.ORANGE){return Couleur.ORANGE;}
        if(c == Color.GREEN){return Couleur.GREEN;}
        if(c == Color.CYAN){return Couleur.CYAN;}
        if(c == Color.YELLOW){return Couleur.YELLOW;}
        if(c == Color.PINK){return Couleur.PINK;}
        else{return null;}
    }

    public static Color toColor(Couleur c){
        if(c == Couleur.RED){return Color.RED;}
        if(c == Couleur.BLUE){return Color.BLUE;}
        if(c == Couleur.ORANGE){return Color.ORANGE;}
        if(c == Couleur.GREEN){return Color.GREEN;}
        if(c == Couleur.CYAN){return Color.CYAN;}
        if(c == Couleur.YELLOW){return Color.YELLOW;}
        if(c == Couleur.PINK){return Color.PINK;}
        else{return null;}
    }
}
