package com.example.shiptodoor.ShipToDoorAuth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shiptodoor.MainActivity;
import com.example.shiptodoor.Models.LoginResponce;
import com.example.shiptodoor.Models.User;
import com.example.shiptodoor.Network.RetrofitClient;
import com.example.shiptodoor.R;
import com.example.shiptodoor.Utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_ShipToDoor extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvRegister;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ship_to_door);
        session = new SessionManager(this);
        if (session.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> login());
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, Register_ShipToDoor.class)));
    }
    private void login() {


        User user = new User(
                etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim()
        );

        RetrofitClient.getApiService()
                .login(user)
                .enqueue(new Callback<LoginResponce>() {

                    @Override
                    public void onResponse(Call<LoginResponce> call,
                                           Response<LoginResponce> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            if (response.body().isStatus()) {
                                Toast.makeText(Login_ShipToDoor.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                                session.saveLogin();
                                startActivity(new Intent(Login_ShipToDoor.this,
                                        MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(Login_ShipToDoor.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Login_ShipToDoor.this,
                                    "Invalid server response",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponce> call, Throwable t) {
                        Toast.makeText(Login_ShipToDoor.this,
                                "Error: " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("LOGIN_ERROR", t.toString());
                    }
                });
    }
}

