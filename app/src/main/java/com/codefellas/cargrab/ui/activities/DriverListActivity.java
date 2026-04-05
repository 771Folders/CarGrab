package com.codefellas.cargrab.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.data.Database;
import com.codefellas.cargrab.data.Driver;
import com.codefellas.cargrab.databinding.ActivityDriverListBinding;
import com.codefellas.cargrab.databinding.ActivityRegisterDriverBinding;
import com.codefellas.cargrab.ui.adapters.DriverAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class DriverListActivity extends AppCompatActivity implements DriverAdapter.Listener {

    private ActivityDriverListBinding binding;

    Database database;
    ArrayList<Driver> drivers;
    DriverAdapter driverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDriverListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = new Database(this);
        refreshList();

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void OnAcceptClick(int positon) {
        Driver selectedDriver = drivers.get(positon);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Approve driver?")
                .setMessage("This is approve the driver. Please select 'confirm' to proceed.")
                .setNegativeButton("Close", null)
                .setPositiveButton("Confirm", (dialogInterface, i) -> {
                    database.approveDriver(selectedDriver.getDriverID());
                    Toast.makeText(DriverListActivity.this, "Driver has been approved", Toast.LENGTH_SHORT).show();
                    refreshList();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void refreshList() {
        drivers = new ArrayList<>(database.getAllDrivers());
        driverAdapter = new DriverAdapter(this, this, drivers);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(driverAdapter);
    }

    @Override
    public void OnRejectClick(int position) {
        Driver selectedDriver = drivers.get(position);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Reject driver?")
                .setMessage("This is reject the application for the driver. Please select 'confirm' to proceed.")
                .setNegativeButton("Close", null)
                .setPositiveButton("Confirm", (dialogInterface, i) -> {
                    database.rejectDriver(selectedDriver.getDriverID());
                    Toast.makeText(DriverListActivity.this, "Driver has been rejected", Toast.LENGTH_SHORT).show();
                    refreshList();
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