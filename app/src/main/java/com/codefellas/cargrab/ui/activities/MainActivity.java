package com.codefellas.cargrab.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codefellas.cargrab.R;
import com.codefellas.cargrab.databinding.ActivityMainBinding;
import com.codefellas.cargrab.ui.fragments.BookingFragment;
import com.codefellas.cargrab.ui.fragments.PassengerAccFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new BookingFragment())
                .commit();

        binding.bottomNavigation.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.menu_nav_book) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new BookingFragment())
                        .commit();
            } else if (menuItem.getItemId() == R.id.menu_nav_account) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new PassengerAccFragment())
                        .commit();
            }
            return true;
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}