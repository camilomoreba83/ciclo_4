package co.edu.unab.mercapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ActivityResultLauncher<Intent> llamadoBoton = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==RESULT_OK){
                String msg="La edad del usuario es: ";
                Intent intent = result.getData();
                if (result.getData()!=null){
                    int edad=result.getData().getIntExtra("Edad",-1);
                    if (edad!=-1){
                        msg += edad;//para concatenar
                    }else{
                        msg += "N/D";//para concatenar 719am s3 s2
                    }
                }
                Toast.makeText(getApplicationContext(), "Todo Ok, "+ msg, Toast.LENGTH_SHORT).show();
            }else{
                Log.e("error","Algo paso");
            }
        }
    });

    View.OnClickListener eventoClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) { //735 s2 s2
            if (view.getId()==R.id.btnSalir){
                MainActivity.super.finish();
            }else if (view.getId()==R.id.btnInicio){
                EditText user=findViewById(R.id.txtUsername);//659am s3 s2
                EditText pass=findViewById(R.id.txtPassword);
                String username =user.getText().toString();
                String password =pass.getText().toString();

                Toast.makeText(getApplicationContext(), "Bienvenid@s todos", Toast.LENGTH_SHORT).show();
                //740am s2 s2
                Intent intent= new Intent(MainActivity.this, MenuActivity2.class);
                intent.putExtra("userName",username);//659am s3 s2
                llamadoBoton.launch(intent); //lanza el intent onActivityResult
                //startActivityForResult(intent,200 );//710am s3 s2
                //startActivity(intent);
                //753am s2 s2 finaliza
                //finish();
            }else{//738am s2 s2
                Toast.makeText(getApplicationContext(), "Click en la caja", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(getApplicationContext(), "Bienvenid@s", Toast.LENGTH_SHORT).show();
        }
    }; //boton salir s2 s2 726am

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("OnCreate","Ok MainActivity");//6.20am s4 s2
        setContentView(R.layout.activity_main);
        //738am s2 s2
        EditText pass= findViewById(R.id.txtPassword);
        pass.setOnClickListener(eventoClick);//va a eventoclick y ejecurta el else click en la caja
        //713am si no se hace la accion de onclick en la parte visual
        Button btnInicio=findViewById(R.id.btnInicio);
        Button btnSalir=findViewById(R.id.btnSalir);
        btnInicio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Click sostenido", Toast.LENGTH_SHORT).show();
                return false;
            }
        });//715 s2 s2
        btnSalir.setOnClickListener(eventoClick);
        btnInicio.setOnClickListener(eventoClick);//boton salir s3 s2 726am

    }


    public void iniciarSesion(View resultado){ //sesion 2 semana 2 625am
        EditText user=findViewById(R.id.txtUsername);//639am
        EditText pass=findViewById(R.id.txtPassword);
        String username=user.getText().toString();
        String password=pass.getText().toString();

        //707am para hacer seguimiento s2 s2
        //Log.i("Validar Username ",username);
        //Log.d("Validar Password ",password);
        //658am s2 s2
        if (username.equalsIgnoreCase("camilo")&&password.equalsIgnoreCase("123")){
            Toast.makeText(getApplicationContext(), "Bienvenid@ "+username, Toast.LENGTH_SHORT).show();
        }else{
            user.setText(null);
            pass.setText(null);
        }
    }
    //715am s3 s2
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==200){
            if (resultCode==RESULT_OK){
                String msg="La edad del usuario es:";
                if (data!=null){
                    int edad=data.getIntExtra("edad",-1);
                    if (edad!=-1){
                        msg += edad;//para concatenar
                    }else{
                        msg += "N/D";//para concatenar 719am s3 s2
                    }
                }
                Toast.makeText(getApplicationContext(), "Todo Ok, msg", Toast.LENGTH_SHORT).show();
            }else{
                Log.e("error:","algo paso");
            }
        }
    }*/
    //

    /*@Override
    public void finish() {
        super.finish();
    }*/
    //6.20 s3 s2
    /*@Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","Ok MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","Ok MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","Ok MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","Ok MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","Ok MainActivity");
    }*/


}