package com.upt.touchupt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionClientActivity extends AppCompatActivity {

     private Button btOpcionHel;
     Button btOptionE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_client);

        btOpcionHel = (Button)findViewById(R.id.btOptionHelp);
        btOptionE = (Button)findViewById(R.id.btOptionEx);

        btOpcionHel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionClientActivity.this,MapsClientActivity.class);
                startActivity(intent);
            }
        });

        btOptionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionClientActivity.this,MapsClientActivity.class);
                startActivity(intent);
            }
        });
    }
}
