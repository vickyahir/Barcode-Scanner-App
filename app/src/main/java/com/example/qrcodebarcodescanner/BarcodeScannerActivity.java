package com.example.qrcodebarcodescanner;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.BarcodeModel;
import com.google.zxing.Result;
import com.gpfreetech.awesomescanner.ui.GpCodeScanner;
import com.gpfreetech.awesomescanner.ui.ScannerView;
import com.gpfreetech.awesomescanner.util.DecodeCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarcodeScannerActivity extends AppCompatActivity {

    private GpCodeScanner mCodeScanner;
    private MediaPlayer mp;
    private List<BarcodeModel> barcodeModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        ScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new GpCodeScanner(this, scannerView);
        barcodeModelList = new ArrayList<>();
        mp = MediaPlayer.create(BarcodeScannerActivity.this, R.raw.beep_two);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCodeScanner.startPreview();
//                        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
                        mp.start();
                        mCodeScanner.releaseResources();
                        mCodeScanner.stopPreview();
                        mCodeScanner.setAutoFocusEnabled(true);
                        DataAlertDialog(result.getText());
                    }
                });
            }
        });


    }

    private void DataAlertDialog(String result) {
        AlertDialog alertDialog = new AlertDialog.Builder(BarcodeScannerActivity.this).create();
        alertDialog.setTitle("Barcode Scanner App");
        alertDialog.setMessage(result);


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SCAN AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                mCodeScanner.startPreview();

                List<String> elephantList = Arrays.asList(result.split("/"));

                BarcodeModel barcodeModel = new BarcodeModel();
                barcodeModel.setMrp(elephantList.get(5));
                barcodeModel.setIndexNumber(elephantList.get(4));
                barcodeModel.setProdNumber(elephantList.get(3));
                barcodeModel.setUnicNumber(elephantList.get(2));
                barcodeModel.setQty(elephantList.get(7));

                barcodeModelList.add(barcodeModel);

                MainActivity.notificationDataItems.add(barcodeModel);
            }
        });

        alertDialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCodeScanner != null) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
        if (mCodeScanner != null) {
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }
}