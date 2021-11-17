package co.edu.unab.mercapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.mercapp.R;
import co.edu.unab.mercapp.entity.Categoria;
//s3 sem3 722
public class CategoriaRecyclerAdapter
        extends RecyclerView.Adapter<CategoriaRecyclerAdapter.CategoriaViewHolder> {

    ArrayList<Categoria> categorias;
    //s4 sem3 627am
    OnItemClickListener listener;
    static class CategoriaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView descripcion;
        ImageView imagen;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.txt_nom_card);
            this.descripcion = itemView.findViewById(R.id.txt_desc_card);
            this.imagen = itemView.findViewById(R.id.img_card);
            //s4 sem3
        }
        //s4 sem3 633am
        public void bind(final Categoria categoria, final OnItemClickListener listener) {
            this.nombre.setText(categoria.getNombre());
            this.descripcion.setText(categoria.getDescripcion());
            Picasso.get().load(categoria.getUrlImagen()).into(this.imagen);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(categoria);
                }
            });
        }
        //----
    }
    public CategoriaRecyclerAdapter(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    //consrtuctor sobre cargado s4 sem3 628am categorias es el data y listener el metodo asociado
    public CategoriaRecyclerAdapter(ArrayList<Categoria> categorias, OnItemClickListener listener) {
        this.categorias = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_recycler,parent, false);
        CategoriaViewHolder categoriaViewHolder = new CategoriaViewHolder(vista);
        return categoriaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = this.categorias.get(position);
        //s4 sem3 629am
        /*holder.nombre.setText(categoria.getNombre());
        holder.descripcion.setText(categoria.getDescripcion());
        Picasso.get().load(categoria.getUrlImagen()).into(holder.imagen);*/
        holder.bind(categoria, listener);
    }

    @Override
    public int getItemCount() {
        return this.categorias.size();
    }

    //s4 sem3 627am
    public interface OnItemClickListener{//interfaz para definir la comunicacion
        void onItemClick(Categoria categoria);
    }

}
