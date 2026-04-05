package com.codefellas.cargrab.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Admin;
import com.codefellas.cargrab.data.Database;
import com.codefellas.cargrab.data.Passenger;
import com.codefellas.cargrab.databinding.ActivityAdminBinding;
import com.codefellas.cargrab.ui.adapters.PassengerAdapter;
import com.codefellas.cargrab.util.NotificationUtil;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private Database database;
    private ArrayList<Passenger> passengers;
    private PassengerAdapter passengerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.toolbar);

        database = new Database(this);
        passengers = new ArrayList<>(database.getAllPassengers());

        passengerAdapter = new PassengerAdapter(this, passengers);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(passengerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_driver_list) {
            startActivity(new Intent(AdminActivity.this, DriverListActivity.class));
        } else if (item.getItemId() == R.id.menu_logout) {
            showLogoutDialog();
        } else if (item.getItemId() == R.id.menu_test_notification) {
            NotificationUtil.createNotification(this, "NOTIFICATION TITLE", "NOTIFICATION DESCRIPTION");
        } else if (item.getItemId() == R.id.menu_admin_account) {
            startActivity(new Intent(AdminActivity.this, AdminAccActivity.class));
        }
        return true;
    }

    private void showLogoutDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Logout?")
                .setMessage("This is will log out the account. Please select 'confirm' to proceed.")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", (dialogInterface, i) -> {
                    Toast.makeText(AdminActivity.this, "Logging out acccount", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminActivity.this, LoginActivity.class));
                    finish();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}