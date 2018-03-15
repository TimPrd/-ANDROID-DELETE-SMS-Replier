package com.example.licence.helloworld;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private EditText eT, eN;
    private TextView tV;
    private NumberPicker nP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ACTIVITY_STATE", "onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ACTIVITY_STATE", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ACTIVITY_STATE", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ACTIVITY_STATE", "onDestroy");
    }

    public void showGreeting(View view) {
        sendSMS(String.valueOf(eN.getText()), String.valueOf(eT.getText()));
        tV.setText(eT.getText());

    }


    private void sendSMS(String phoneNumber, String message) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED)
                {
                    Log.d("PERMISSION", "permission denied to SEND_SMS - requesting it");
                    String[] permissions = {Manifest.permission.SEND_SMS};
                    requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                }
            }
            SmsManager sms = SmsManager.getDefault();
            for (int i = 0 ; i < this.nP.getValue() ; i++)
                sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), this.nP.getValue() + "SMS Sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS not Sent!", Toast.LENGTH_LONG).show();

        }
    }
}
