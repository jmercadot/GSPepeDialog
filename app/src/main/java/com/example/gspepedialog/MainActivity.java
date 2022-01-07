package com.example.gspepedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;

import com.bancoazteca.gspepedialog.GSPepeDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GSPepeDialog.Builder(this,this.getClass().getSimpleName())
                .setTitle(getString(R.string.title_alternativas_pago_aguantame))
                .setBodySpanned(Html.fromHtml( getString(R.string.descripcion_alternativa_pago_aguantame)), Gravity.LEFT)
                .setAhoraNo(true)
                .setPositiveButton("continuar")
                .setCancelable(false)
                .setStatusPepe(GSPepeDialog.PEPE_OK)
                .create()
                .show();
    }
}
