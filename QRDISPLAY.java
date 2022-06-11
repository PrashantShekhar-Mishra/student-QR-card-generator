package com.example.twophaseauthenticationsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRDISPLAY extends AppCompatActivity {
    ImageView ivOutput;
    TextView display;
    DBPARTICIPANT DB;
    Button send;
    DBQR Dp;
    public Uri getBitmapFromDrawable(Bitmap bmp){

        // Store image to default external storage directory
        Uri bmpUri = null;
        try {

            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();

            // wrap File object into a content provider. NOTE: authority here should match authority in manifest declaration
            bmpUri = FileProvider.getUriForFile(QRDISPLAY.this, "com.codepath.fileprovider", file);  // use this version for API >= 24

            // **Note:** For API < 24, you may use bmpUri = Uri.fromFile(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrprint);
        display = (TextView) findViewById(R.id.display);
        ivOutput = findViewById(R.id.iv_output);
        send = findViewById(R.id.send);
        Dp = new DBQR(this);
        DB = new DBPARTICIPANT(this);

        Cursor res = DB.getdata();
        if (res.getCount() == 0) {
            Toast.makeText(QRDISPLAY.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

                while (res.moveToNext()) {
                    display.setText(res.getString(0).toString());
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("Name :" + res.getString(0) + "\n");
                    String s1Text = res.getString(4).toString().trim();
                    ivOutput.setTooltipText(res.getString(0));
                    buffer.append("address :" + res.getString(1) + "\n");
                    buffer.append("Date of Birth :" + res.getString(2) + "\n");
                    buffer.append("college  :" + res.getString(3) + "\n");
                    buffer.append("Email :" + res.getString(4) + "\n\n");
                    String sText = buffer.toString().trim();
                    Dp.insertuserdata(sText,s1Text);
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try {
                        BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,
                                350, 350);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        ivOutput.setImageBitmap(bitmap);


                        InputMethodManager manager = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE
                        );


                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }


        send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor res = Dp.getdata();
                    if (res.getCount() == 0) {
                        Toast.makeText(QRDISPLAY.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    while (res.moveToNext()) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(res.getString(0) + "\n");
                        String sText = buffer.toString().trim();


                        Log.i("Send email", "");

                        String[] TO = {res.getString(1).toString()};
                        String[] CC = {"cajankar09@gmail.com"};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        MultiFormatWriter writer = new MultiFormatWriter();
                        try {
                            BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,
                                    350, 350);
                            BarcodeEncoder encoder = new BarcodeEncoder();
                            Bitmap bitmap = encoder.createBitmap(matrix);
                            InputMethodManager manager = (InputMethodManager) getSystemService(
                                    Context.INPUT_METHOD_SERVICE
                            );
                            Uri bmpUri = getBitmapFromDrawable(bitmap);
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                            emailIntent.putExtra(Intent.EXTRA_CC, CC);
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SCAN QR ");
                            emailIntent.putExtra(Intent.EXTRA_STREAM,bmpUri);
                            try {
                                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                                finish();
                                Log.i("Finished sending email...", "");
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(QRDISPLAY.this,
                                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }



                        } catch (WriterException e) {
                            e.printStackTrace();
                        }





                    }
                }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = Dp.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(QRDISPLAY.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                while (res.moveToNext()) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(res.getString(0) + "\n");
                    String sText = buffer.toString().trim();


                    Log.i("Send email", "");

                    String[] TO = {res.getString(1).toString()};
                    String[] CC = {"cajankar09@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try {
                        BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,
                                350, 350);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        InputMethodManager manager = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE
                        );
                        Uri bmpUri = getBitmapFromDrawable(bitmap);
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_CC, CC);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SCAN QR ");
                        emailIntent.putExtra(Intent.EXTRA_STREAM,bmpUri);
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(QRDISPLAY.this,
                                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }



                    } catch (WriterException e) {
                        e.printStackTrace();
                    }





                }
            }
        });
        }
}

