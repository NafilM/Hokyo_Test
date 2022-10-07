package dxg.word.hokyotest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button hokyo,tvfdm,callus,contactus,download_hokyo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hokyo = findViewById(R.id.hokyo_btn);
        tvfdm = findViewById(R.id.tvfdm_btn);
        callus = findViewById(R.id.callus_btn);
        contactus = findViewById(R.id.contactus_btn);
        download_hokyo = findViewById(R.id.download_btn);

        hokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hokyo_intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.hokyo.in/");
                hokyo_intent.setData(uri);
                startActivity(hokyo_intent);
            }
        });


        tvfdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent hokyo_intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.tvfdm.in/");
                hokyo_intent.setData(uri);
                startActivity(hokyo_intent);
            }
        });

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callus_intent = new Intent(Intent.ACTION_DIAL);
                callus_intent.setData(Uri.parse("tel:8100929050"));
                startActivity(callus_intent);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Contact Us");
                alertDialog.setMessage("Enter Details");

                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String input_detail = input.getText().toString();
                                if (!input_detail.equals("")){
                                    Intent email = new Intent(Intent.ACTION_SEND);
                                    email.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@tvfdm.in" });
                                    email.putExtra(Intent.EXTRA_TEXT, input_detail);
                                    email.putExtra(Intent.EXTRA_SUBJECT, "Hokyo Test Mail");
                                    email.setType("message/rfc822");
                                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                                }else {
                                    Toast.makeText(MainActivity.this, "Please add some details", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }

        });

        download_hokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.vootflix.app");

                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }else {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.vootflix.app"));
                    startActivity(i);
                }
            }
        });

    }
}