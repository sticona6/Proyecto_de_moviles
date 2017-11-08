package com.upt.touchupt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ComentClientActivity extends AppCompatActivity {

    RatingBar vRatingBar;

    TextView tRatingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coment_client);

        vRatingBar = (RatingBar)findViewById(R.id.vRatingBar);
        tRatingText = (TextView) findViewById(R.id.tRatingText);

        vRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if(v==0.0){
                    tRatingText.setText("");
                }
                if(v==1.0){
                    tRatingText.setText("Lo odio");
                }
                if(v==2.0){
                    tRatingText.setText("No me ha gustado");
                }
                if(v==3.0){
                    tRatingText.setText("No está mal");
                }
                if(v==4.0){
                    tRatingText.setText("Me ha gustado");
                }
                if(v==5.0){
                    tRatingText.setText("Me ha encantado");
                }
                Toast.makeText(getApplicationContext(),"Mensaje: " + v,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
