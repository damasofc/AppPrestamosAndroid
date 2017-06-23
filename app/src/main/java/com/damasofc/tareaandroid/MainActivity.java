package com.damasofc.tareaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {
    ImageButton btn_Auto;
    ImageButton btn_Personal;
    ImageButton btn_Casa;
    EditText edt_Monto;
    EditText edt_Años;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        btn_Auto = (ImageButton)findViewById(R.id.btn_Auto);
        btn_Personal = (ImageButton) findViewById(R.id.btn_Personal);
        btn_Casa = (ImageButton) findViewById(R.id.btn_Casa);
        edt_Monto = (EditText)findViewById(R.id.edt_Monto);
        edt_Años = (EditText)findViewById(R.id.edt_Años);
        btn_Casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSecond(1);
            }
        });
        btn_Personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSecond(2);
            }
        });
        btn_Auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSecond(3);
            }
        });
    }

    private void onSecond(int fuente){
        if (comprobarDatosLlenos()){
            double monto = Double.valueOf(edt_Monto.getText().toString());
            int años =  Integer.valueOf(edt_Años.getText().toString());
            if(monto > 5000 && años > 0){
                Intent intent = new Intent(MainActivity.this,InformationActivity.class);
                intent.putExtra("monto",monto);
                intent.putExtra("años",años);
                intent.putExtra("fuente",fuente);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(MainActivity.this,"Porfavor revise que el monto del prestamo sea mayor a 5,000\n" +
                        "y que haya colocado los años correctamente",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(MainActivity.this,"Porfavor Llene todos los datos",Toast.LENGTH_LONG).show();
        }

    }

    private boolean comprobarDatosLlenos(){
        try {
            String monto = edt_Monto.getText().toString();
            String años = edt_Años.getText().toString();
            if(monto.length() > 0 && años.length() > 0){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }
}
