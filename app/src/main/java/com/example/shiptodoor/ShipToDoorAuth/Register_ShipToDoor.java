package com.example.shiptodoor.ShipToDoorAuth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shiptodoor.Models.RegisterResponse;
import com.example.shiptodoor.Models.User;
import com.example.shiptodoor.Network.RetrofitClient;
import com.example.shiptodoor.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_ShipToDoor extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ship_to_door);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> register());
    }

    private void register() {

        User user = new User(
                etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString()
        );

        RetrofitClient.getApiService()
                .register(user)
                .enqueue(new Callback<RegisterResponse>() {

                    @Override
                    public void onResponse(Call<RegisterResponse> call,
                                           Response<RegisterResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            Toast.makeText(Register_ShipToDoor.this,
                                    response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            if (response.body().isStatus()) {
                                finish(); // back to login
                            }

                        } else {
                            Toast.makeText(Register_ShipToDoor.this,
                                    "Invalid server response",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(Register_ShipToDoor.this,
                                "Error: " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}