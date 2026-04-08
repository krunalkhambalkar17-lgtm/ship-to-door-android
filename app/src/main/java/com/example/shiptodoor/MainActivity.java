package com.example.shiptodoor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shiptodoor.ShipToDoorAuth.Login_ShipToDoor;
import com.example.shiptodoor.Utils.SessionManager;

public class MainActivity extends AppCompatActivity {
    Button btnLogout;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            session.logout();
            startActivity(new Intent(this, Login_ShipToDoor.class));
            finish();
        });
    }
}