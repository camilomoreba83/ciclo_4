package co.edu.unab.mercapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import co.edu.unab.mercapp.entity.Categoria;

public class AgregarCategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_categoria);
    }
//s4 sem3 720am
    public void volver(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    public void agregar(View view){
        EditText nombre = findViewById(R.id.txtNombre);
        EditText descripcion = findViewById(R.id.txtDescripcionImage);
        EditText url = findViewById(R.id.txtUrlImage);

        Categoria categoria = new Categoria(nombre.getText().toString(),descripcion.getText().toString());
        categoria.setUrlImagen(url.getText().toString());
        DataTemp.CATEGORIAS.add(categoria);
        finish();
    }
}