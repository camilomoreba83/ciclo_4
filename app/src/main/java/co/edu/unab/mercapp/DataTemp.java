package co.edu.unab.mercapp;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Categoria;

public class DataTemp {
    public static final ArrayList<Categoria> CATEGORIAS = new ArrayList<>();

    //s4 sem3  646am para cuando haga click en la activity item lista recicler en alguno de los elementos
    //se asocie como carateristica de interes y se presente en el listview del menu activity
    public static final ArrayList<Categoria> CATEGORIAS_INTERES = new ArrayList<>();
    //___
    static void cargarCategorias(){
        CATEGORIAS.add(new Categoria("Aseo personal","Elementos de aseo para el cuidado personal"));
        CATEGORIAS.add(new Categoria("Alimentos","Productos de gran calidad"));
        CATEGORIAS.add(new Categoria("Bebidas","Productos refrescantes"));
        CATEGORIAS.add(new Categoria("Electrodomesticos","Dispositivos electronicos para entretenimiento"));
        CATEGORIAS.add(new Categoria("Lacteos","Productos derivados de la lecha"));
        CATEGORIAS.add(new Categoria("Cereales","Productos cereales"));
    }
}
