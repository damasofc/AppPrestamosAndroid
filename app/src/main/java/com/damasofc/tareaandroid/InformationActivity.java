package com.damasofc.tareaandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class InformationActivity extends AppCompatActivity {
    TextView txt_Anos;
    TextView txt_Monto;
    TextView txt_FechaFin;
    TextView txt_TipoPrestamo;
    TextView txt_MontoAjustado;
    TextView txt_MontoIntereses;
    TextView txt_MontoTotal;
    TextView txt_CuotaMensual;
    Button btn_Cotizar;
    double monto;
    int anos;
    int fuente;
    Calendar hoy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initComponents();
    }

    private void initComponents(){
        hoy = Calendar.getInstance();
        monto = getIntent().getExtras().getDouble("monto");
        anos = getIntent().getExtras().getInt("años");
        fuente = getIntent().getExtras().getInt("fuente");

        txt_Anos = (TextView)findViewById(R.id.txt_Años);
        txt_FechaFin = (TextView)findViewById(R.id.txt_FechaFin);
        txt_TipoPrestamo = (TextView)findViewById(R.id.txt_TipoPrestamo);
        txt_Monto = (TextView)findViewById(R.id.txt_Monto);
        txt_MontoAjustado = (TextView)findViewById(R.id.txt_MontoAjustado);
        txt_MontoIntereses = (TextView)findViewById(R.id.txt_MontoIntereses);
        txt_MontoTotal = (TextView)findViewById(R.id.txt_MontoTotal);
        txt_CuotaMensual = (TextView)findViewById(R.id.txt_CuotaMensual);
        btn_Cotizar = (Button)findViewById(R.id.btn_Cotizar);
        setDatos();
        btn_Cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.ficohsa.com/hn");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
    private void setDatos(){
        txt_Monto.setText("Lps. "+String.valueOf(monto));
        txt_Anos.setText(String.valueOf(anos));
        int anoFin = hoy.get(Calendar.YEAR) + anos;
        hoy.set(anoFin,hoy.get(Calendar.MONTH),hoy.get(Calendar.DATE));
        txt_FechaFin.setText(calendarToString(hoy));
        double tasa;
        double montoAjustado = 0;
        double montoIntereses = 0;
        switch(fuente){
            case 1:
                txt_TipoPrestamo.setText("Casa");
                txt_MontoAjustado.setText("Lps.30,000");
                montoAjustado = 30000;
                tasa = 0.1;
                montoIntereses = tasa*monto*anos;
                txt_MontoIntereses.setText("Lps."+String.valueOf(montoIntereses));
                break;
            case 2:
                txt_TipoPrestamo.setText("Personal");
                txt_MontoAjustado.setText("Lps.0");
                montoAjustado = 0;
                tasa = 0.35;
                montoIntereses = tasa*monto*anos;
                txt_MontoIntereses.setText("Lps."+String.valueOf(montoIntereses));
                break;
            case 3:
                txt_TipoPrestamo.setText("Auto");
                montoAjustado = monto*0.3;
                txt_MontoAjustado.setText("Lps." +String.valueOf(montoAjustado));
                tasa = 0.25;
                montoIntereses = tasa*monto*anos;
                txt_MontoIntereses.setText("Lps."+String.valueOf(montoIntereses));
                break;

        }
        double montoTotal = monto + montoAjustado + montoIntereses;
        txt_MontoTotal.setText("Lps." + String.valueOf(montoTotal));
        txt_CuotaMensual.setText("Lps."+String.valueOf(montoTotal/(anos*12)));
    }

    private String calendarToString(Calendar c){
        Locale locale = Locale.getDefault();
        String dia = c.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT,locale);
        String mes = c.getDisplayName(Calendar.MONTH,Calendar.SHORT,locale);
        return  mes + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InformationActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
