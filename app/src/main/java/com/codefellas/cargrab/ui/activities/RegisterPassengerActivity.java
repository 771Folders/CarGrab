package com.codefellas.cargrab.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Database;
import com.codefellas.cargrab.data.Passenger;
import com.codefellas.cargrab.databinding.ActivityRegisterPassengerBinding;

public class RegisterPassengerActivity extends AppCompatActivity {

    private ActivityRegisterPassengerBinding binding;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterPassengerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = new Database(this);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.register.setOnClickListener(view -> registerPassenger());
    }

    private void registerPassenger() {
        String firstName = binding.firstNameInput.getText().toString().trim();
        String middleName = binding.middleNameInput.getText().toString().isEmpty()
                ? ""
                : binding.middleNameInput.getText().toString().trim();

        String lastName = binding.lastNameInput.getText().toString().trim();
        String phoneNumber = binding.phoneInput.getText().toString().trim();
        String email = binding.emailInput.getText().toString().trim();
        String password = binding.passwordInput.getText().toString().trim();
        String confirmPassword = binding.confirmPasswordInput.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Both passwords must match!", Toast.LENGTH_SHORT).show();
            return;
        }

        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setMiddleName(middleName);
        passenger.setLastName(lastName);
        passenger.setEmail(email);
        passenger.setPhone(phoneNumber);
        passenger.setPassword(password);

        database.registerPassenger(passenger);
        Toast.makeText(this, "Passenger registration successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}