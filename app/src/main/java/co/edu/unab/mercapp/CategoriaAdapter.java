package co.edu.unab.mercapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Categoria;

public class CategoriaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Categoria> datos;

    public CategoriaAdapter(Context context, ArrayList<Categoria> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() { //dice cuantos items se van a representar o mostrar en datos
        if (this.datos==null){
            return 0;
        }else{
            return this.datos.size();
        }
    }

    @Override
    public Object getItem(int pos) {//devuelve el item de una posicion especifica
        return this.datos.get(pos);
    }

    @Override
    public long getItemId(int pos) {//se puede definir que cada item op lelemento tiene un id unico se devuelve elk item
        return this.datos.get(pos).getId();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {//permite crear la vista, ver 658 s2 sem 4
        if (view==null){//si viene nula se necesita instanicar o crear el view
            view = LayoutInflater.from(context).inflate(R.layout.item_lista, viewGroup, false);//inflate es que se toma lo que ya esta y se coloca en el contexto igual paraq representar la info
        }
        //Categoria categoria = (Categoria) this.getItem(pos);//primer forma de traer un objeto de categoria
        Categoria categoria = this.datos.get(pos);//segunda forma

        TextView nombre = view.findViewById(R.id.lbNombre);//como item_lista no es un actividad se escribe este codigo para recuperar el objeto
        TextView descripcion = view.findViewById(R.id.lbDescripcion);

        ImageView imagen = view.findViewById(R.id.imagen);//s2 sem3 743am

        //nombre.setText(""+categoria.getId());//ver esto es importante 708am s2 sem3, las comillas mas + es para convertir en una cadena
        nombre.setText(categoria.getNombre());
        descripcion.setText(categoria.getDescripcion());

        //Picasso.get().load(categoria.getUrlImagen()).into(imagen);
        Picasso.get().load(categoria.getUrlImagen()).placeholder(R.drawable.error_load).error(R.drawable.twitter).resize(200,200).into(imagen);

        return view;
    }
}
