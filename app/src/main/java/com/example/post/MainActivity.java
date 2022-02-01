package com.example.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private CFragment cFragment;
    private GFragment gFragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.car_btn).setOnClickListener(this);
        findViewById(R.id.gal_btn).setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        cFragment = new CFragment();
        gFragment = new GFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, cFragment);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.car_btn:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame, cFragment);
                transaction.commit();
                break;
            case R.id.gal_btn:

                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);
                        Toast.makeText(this, "권한 허용", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.frame, gFragment);
                    transaction.commit();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.frame, gFragment);
                    transaction.commit();
                } else {
                    Toast.makeText(this, "권한 허용", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}