package com.codefellas.cargrab.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Database;
import com.codefellas.cargrab.data.Driver;
import com.codefellas.cargrab.databinding.ActivityRegisterDriverBinding;

public class RegisterDriverActivity extends AppCompatActivity {

    private ActivityRegisterDriverBinding binding;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterDriverBinding.inflate(getLayoutInflater());
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

        binding.register.setOnClickListener(view -> registerDriver());
    }

    private void registerDriver() {
        String firstName = binding.firstNameInput.getText().toString().trim();
        String middlename = binding.middleNameInput.getText().toString().isEmpty()
                ? ""
                : binding.middleNameInput.getText().toString().trim();
        String lastName = binding.lastNameInput.getText().toString().trim();
        String email = binding.emailInput.getText().toString().trim();
        String password = binding.passwordInput.getText().toString().trim();
        String confirmPassword = binding.confirmPasswordInput.getText().toString().trim();
        String licenseID = binding.licenseIdInput.getText().toString().trim();
        String phone = binding.phoneInput.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || licenseID.isEmpty()) {
            Toast.makeText(this, "Please fill out all forms", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Both passwords must match", Toast.LENGTH_SHORT).show();
            return;
        }

        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setMiddleName(middlename);
        driver.setLastName(lastName);
        driver.setEmail(email);
        driver.setPassword(password);
        driver.setLicenseID(licenseID);
        driver.setPhone(phone);
        driver.setStatus("Pending");

        database.registerDriver(driver);
        Toast.makeText(this, "Driver registration successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}