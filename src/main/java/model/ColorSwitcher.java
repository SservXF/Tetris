package main.java.model;

import java.awt.Color;

public interface ColorSwitcher {

    public static Bloc toBloc(Color c){
        if(c == Color.RED){return Bloc.RED;}
        if(c == Color.BLUE){return Bloc.BLUE;}
        if(c == Color.ORANGE){return Bloc.ORANGE;}
        if(c == Color.GREEN){return Bloc.GREEN;}
        if(c == Color.CYAN){return Bloc.CYAN;}
        if(c == Color.YELLOW){return Bloc.YELLOW;}
        if(c == Color.PINK){return Bloc.PINK;}
        else{return null;}
    }

    public static Color toColor(Bloc c){
        if(c == Bloc.RED){return Color.RED;}
        if(c == Bloc.BLUE){return Color.BLUE;}
        if(c == Bloc.ORANGE){return Color.ORANGE;}
        if(c == Bloc.GREEN){return Color.GREEN;}
        if(c == Bloc.CYAN){return Color.CYAN;}
        if(c == Bloc.YELLOW){return Color.YELLOW;}
        if(c == Bloc.PINK){return Color.PINK;}
        else{return null;}
    }
}
