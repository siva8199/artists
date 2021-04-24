package com.example.artist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class client extends AppCompatActivity {

    CardView photos;
    CardView videos;
    CardView sket;
    CardView paint;
    CardView illus;
    CardView grad;
    CardView anima;
    CardView inter;
    CardView eventp;
    Button btsa;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        photos = findViewById(R.id.photo);
        videos = findViewById(R.id.video);
        btsa = findViewById(R.id.btn_sa);
        logout = findViewById(R.id.btnlo);
        sket = findViewById(R.id.sketch);
        paint = findViewById(R.id.paint);
        illus = findViewById(R.id.illustrator);
        grad = findViewById(R.id.graphic);
        anima = findViewById(R.id.animator);
        inter = findViewById(R.id.interior);
        eventp = findViewById(R.id.event);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,photocontent.class));
            }
        });

        btsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,photography.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(client.this,videography.class));

            }
        });

        sket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,sketcher.class));
            }
        });

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(client.this,paintcon.class));
            }
        });

        illus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(client.this,illustrator.class));

            }
        });

        grad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,graphicsdesign.class));
            }
        });

        anima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,ani.class));
            }
        });

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,interior.class));
            }
        });

        eventp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(client.this,eventplanner.class));
            }
        });

    }

}
