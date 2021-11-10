package co.edu.unab.mercapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.mercapp.entity.Categoria;

public class MenuActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        String user=getIntent().getStringExtra("userName");

        if (DataTemp.CATEGORIAS.size()==0){
            DataTemp.cargarCategorias();
        }

        //s1 sem3 617am se crean los datos de tipo String en el arreglo
        //-------------------OPCION CON CLASE CATEGORIAS--------------------
        ArrayList<Categoria> categorias = new ArrayList<>();
        ///////SESION 3 SEM3 653

        /**/categorias.add(new Categoria("Aseo personal","Elementos de aseo para el cuidado personal"));
        categorias.add(new Categoria("Alimentos","Productos de gran calidad"));
        categorias.add(new Categoria("Bebidas","Productos refrescantes"));
        categorias.add(new Categoria("Electrodomesticos","Dispositivos electronicos para entretenimiento"));
        categorias.add(new Categoria("Lacteos","Productos derivados de la lecha"));
        categorias.add(new Categoria("Cereales","Productos cereales"));
        //categorias generlamente no se quema de codigo, vienen de codigo o web
        //-------------------FIN QUE SE DEBE AÑADIR CON CLASE CATEGORIAS--------------------

        //-------------------OPCION SIN CLASE CATEGORIAS--------------------
        String [] datos= {"Aseo Personal", "Alimentos", "Bebidas","Electrodomesticos", "Lacteos","Cereales","Aseo Hogar", "Frios"};//s1 s3 617am
        //array adapater se trabaja cadenas o elementos String (contexto=, layout=los tiene android, datos)

        /*Sin categoria
        Spinner comboBox = findViewById(R.id.spinner);//s1 sem3 748am
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, datos);
        comboBox.setAdapter(adapterSpinner);*/

        /*Con categoria
        Spinner comboBox = findViewById(R.id.spinner);//s1 sem3 748am
        ArrayAdapter<Categoria> adapterSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, categorias);
        comboBox.setAdapter(adapterSpinner);*/
        /*Con DataTemp*/
        Spinner comboBox = findViewById(R.id.spinner);//s1 sem3 748am
        ArrayAdapter<Categoria> adapterSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, DataTemp.CATEGORIAS);
        comboBox.setAdapter(adapterSpinner);


        /*opcion 1 despliegue sencillo
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,datos);
        final ListView lista= findViewById(R.id.lista);//650am s1 sem3
        lista.setAdapter(adapter);//final opcion 1*/
        /*opcion 1 despliegue sencillo con categorias se debe cambiar el setOnItemClickListener tambien
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,categorias);
        final ListView lista= findViewById(R.id.lista);//650am s1 sem3
        lista.setAdapter(adapter);//final opcion 1*/

        /*opcion 2 despliegue con chuleado
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_checked,datos);
        ListView lista= findViewById(R.id.lista);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//para seleccionar y chulear un ellemnto (single) o varios  (multiple)
        lista.setAdapter(adapter);//final opcion 2*/

        /*opcion 2 despliegue con chuleado con clase categoria aqui cambia 729am
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_checked,categorias);
        ListView lista= findViewById(R.id.lista);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//para seleccionar y chulear un ellemnto (single) o varios  (multiple)
        lista.setAdapter(adapter);//final opcion 2*/


        /*opcion 3 despliegue con check box
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice,datos);
        final ListView lista= findViewById(R.id.lista);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//para seleccionar y chulear un ellemnto (single) o varios  (multiple)
        lista.setAdapter(adapter);//final opcion 3*/

        /*opcion 3 despliegue con check box con clase categoria
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice,categorias);
        final ListView lista= findViewById(R.id.lista);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//para seleccionar y chulear un ellemnto (single) o varios  (multiple)
        lista.setAdapter(adapter);//final opcion 3*/

        /*opcion con CategoriaAdapter
        CategoriaAdapter adapter = new CategoriaAdapter(getApplicationContext(),categorias);//s2 sem3 712am
        final ListView lista= findViewById(R.id.lista);
        lista.setAdapter(adapter);//final opcion 3*/

        /*opcion con DataTemp   */
        CategoriaAdapter adapter = new CategoriaAdapter(getApplicationContext(),
                //DataTemp.CATEGORIAS)
                DataTemp.CATEGORIAS_INTERES);//s4 sem3 648am
        final ListView lista= findViewById(R.id.lista);
        lista.setAdapter(adapter);

        /*   opcion sin clase categoria
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() { //sirve para devolver la escucha cuando se haga click haga algo
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                //adapterView=sobre el cual se hace click, view=sel item espécifico q se selecciona, pos o i=la pos donde se hace clik, long l o id=elemento asociado a esa vista
                String valor = (String) lista.getItemAtPosition(pos);//cuando se hace esto se define el listView de debe dfinir arriba como tipo final
                Toast.makeText(getApplicationContext(), valor, Toast.LENGTH_SHORT).show();
            }
        });*/
        /*    opcion con clase categoria s1 sem 3 716am
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() { //sirve para devolver la escucha cuando se haga click haga algo
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Categoria valor = (Categoria) lista.getItemAtPosition(pos);
                Toast.makeText(getApplicationContext(), valor.getNombre(), Toast.LENGTH_SHORT).show();//toString() o getNombre()
            }
        });*/
        //final s1 sem3
        Button volver=findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent= new Intent(MenuActivity2.this, MainActivity.class);
                //startActivity(intent);
                //753am s2 s2 finaliza

                setResult(RESULT_CANCELED);//
                finish();
            }
        });
        TextView lbMsg=findViewById(R.id.lbSaludo);
        lbMsg.setText("Hola "+user+" Bienvenido");
        TextView celular = findViewById(R.id.textView5);
        celular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText cel = (EditText) view;// se crea un casting que se va a convertir al tipo edittext del elemento view q genero el proceso
                //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+cel.getText().toString()));
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3001234567"));
                //s4 sem3 650am
                //startActivity(intent);
                //startActivityForResult(intent,300);//para la guia de los llamados se uso
                startActivity(intent);
                //----
            }
        });
    }
    //s4 sem3 700am
    ActivityResultLauncher<Intent> llamada_recicler = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        ListView lista = findViewById(R.id.lista);
                        ((CategoriaAdapter) lista.getAdapter()).notifyDataSetChanged();
                    }
                }
            }
    );
    //------
    /*s3 sem3 734
    public void callRecycler(View view){
        Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
        startActivity(intent);
    }*/

    //s4 sem3 656
    public void callRecycler(View view){
        Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
        llamada_recicler.launch(intent);
    }


    //725am s3 s2
    public void enviar(View view){
        /*EditText txtEdad = findViewById(R.id.txtEdad);
        int edad = -1;
        if (txtEdad.getText().toString().length()>0){
            edad = Integer.parseInt(txtEdad.getText().toString());
        }

        Intent intent = new Intent();//es para almacenamiento no llamado sesion 4 semana 2 729am
        intent.putExtra("Edad",edad);//guardo la edad y envio como argumento la variable edad
        setResult(RESULT_OK,intent);
        //setResult(RESULT_OK);
        finish();*/
        //s1 sem 3 para usar el boton y recuperar info
        /*
        ListView lista = findViewById(R.id.lista);
        String valor = (String) lista.getItemAtPosition(lista.getCheckedItemPosition());//para recuoerar elemento seleccionado 659
        Toast.makeText(getApplicationContext(), valor, Toast.LENGTH_SHORT).show();
        */
        /* Con clase categoria s1 sem 3 para solo elegir una opcion 726
        ListView lista = findViewById(R.id.lista);
        //Log.i("Pos= ",""+lista.getCheckedItemPosition());para ver error
        Categoria valor = (Categoria) lista.getItemAtPosition(lista.getCheckedItemPosition());//para recuoerar elemento seleccionado 659
        Toast.makeText(getApplicationContext(), valor.getNombre(), Toast.LENGTH_SHORT).show();*/

        /* Con clase categoria s1 sem 3 para elegir multiples opciones 734*/
        ListView lista = findViewById(R.id.lista);
        String cadena="";
        SparseBooleanArray valores = lista.getCheckedItemPositions();//plural positions
        for (int i=0;i<valores.size();i++){
            if (valores.valueAt(i)){
                Categoria valor = (Categoria) lista.getItemAtPosition(valores.keyAt(i));
                cadena += valor.getNombre()+"\n";
            }
        }
        Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
        //s1 sem 3 final

        /*spinner sin categorias
        Spinner comboBox = findViewById(R.id.spinner);
        String elemento = (String) comboBox.getSelectedItem();*/

        /*spinner sin categorias*/
        Spinner comboBox = findViewById(R.id.spinner);
        Categoria elemento = (Categoria) comboBox.getSelectedItem();
        Toast.makeText(getApplicationContext(), elemento.getNombre(), Toast.LENGTH_SHORT).show();
    }

    public void callAgregar(View view){
        Intent intent = new Intent(getApplicationContext(),AgregarCategoriaActivity.class);
        startActivity(intent);
    }

}