package co.edu.unab.mercapp.view.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import co.edu.unab.mercapp.R;
import co.edu.unab.mercapp.entity.Producto;

public class FireBaseActivity extends AppCompatActivity {

    private static final String TAG = "FireBaseActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();//s2 sem4
    CollectionReference productos = db.collection("productos");//sem 4 s4 701

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);
        /*FirebaseFirestore db = FirebaseFirestore.getInstance();//s2 sem4
        db.collection("productos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                Producto producto = document.toObject(Producto.class);
                                Log.d(TAG, document.getId()+ " => " +producto);//sem4 s3 635am
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });*/
    }
    public void listar(View view){
        cargarLista();
    }

    private void cargarLista(){
        EditText txtResultado = findViewById(R.id.txtResultado);
        txtResultado.setText("");
        db.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Producto producto = document.toObject(Producto.class);
                        //txtResultado.append(document.getId()+ "=>"+document.getData()+"\n"); //trae todos los atributos
                        txtResultado.append(document.getId()+ "=>"+producto+"\n");
                    }
                } else {
                    //Log.w(TAG, "Error getting documents.", task.getException());
                    txtResultado.append("Error getting documents."+ task.getException());
                }
            }
        });
    }

    public void agregar(View view){
        Producto producto = null;
        EditText txtData = findViewById(R.id.txtData);
        String [] valores =  txtData.getText().toString().split(";");// delimitador para ingresar el archivo json, osea separa la info cada vez q encuentre ;
        if (valores.length==4){
             producto = new Producto(valores[0],valores[1],Integer.parseInt(valores[2]),Boolean.parseBoolean(valores[3]));
        }else{
             producto = new Producto(valores[0],valores[1],Integer.parseInt(valores[2]));
        }
/////////Otra forma
        Map<String, Object> infoProducto = new HashMap<>();
        infoProducto.put("codigo",valores[0]);
        infoProducto.put("nombre",valores[1]);
        infoProducto.put("precio",Integer.parseInt(valores[2]));

        productos.add(infoProducto).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                cargarLista();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                EditText txtResultado = findViewById(R.id.txtResultado);
                txtResultado.append("Error agregando un documento."+ e.getMessage());
            }
        });
        //////////fin otra forma
        /*productos.add(producto).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                cargarLista();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                EditText txtResultado = findViewById(R.id.txtResultado);
                txtResultado.append("Error agregando un documento."+ e.getMessage());
            }
        });*/
    }

    public void eliminar(View view){
        EditText txtIdDoc = findViewById(R.id.txtIdDoc);
        DocumentReference producto = productos.document(txtIdDoc.getText().toString());

        producto.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        producto.delete();
                    }else{
                        Toast.makeText(FireBaseActivity.this, "Error al eliminar producto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*producto.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FireBaseActivity.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
                    cargarLista();
                }else{
                    Toast.makeText(FireBaseActivity.this, "Error al eliminar producto", Toast.LENGTH_SHORT).show();

                }
            }
        });*/
    }

    public void update(View view){
        EditText txtIdDoc = findViewById(R.id.txtIdDoc);
        DocumentReference producto = productos.document(txtIdDoc.getText().toString());
        EditText txtNewData = findViewById(R.id.txtNewData);
        String [] valores = txtNewData.getText().toString().split(";");
        Map<String, Object> values = new HashMap<>();
        for (String item: valores){
            String [] atributos = item.split(":");
            if (atributos[0].equals("precio")){
                values.put(atributos[0],Integer.parseInt(atributos[1]));
            }else if (atributos[0].equals("disponible")){
                values.put(atributos[0],Boolean.parseBoolean(atributos[1]));
            }else{
                values.put(atributos[0],atributos[1]);
            }
        }
        producto.update(values).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                cargarLista();
            }
        });
    }

    public void setData(View view){
        EditText txtIdDoc = findViewById(R.id.txtIdDoc);
        DocumentReference producto = productos.document(txtIdDoc.getText().toString());
        EditText txtNewData = findViewById(R.id.txtNewData);
        String [] valores = txtNewData.getText().toString().split(";");
        Map<String, Object> values = new HashMap<>();
        for (String item: valores){
            String [] atributos = item.split(":");
            if (atributos[0].equals("precio")){
                values.put(atributos[0],Integer.parseInt(atributos[1]));
            }else if (atributos[0].equals("disponible")){
                values.put(atributos[0],Boolean.parseBoolean(atributos[1]));
            }else{
                values.put(atributos[0],atributos[1]);
            }
        }
        producto.set(values).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                cargarLista();
            }
        });
    }
}