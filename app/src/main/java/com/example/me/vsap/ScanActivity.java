package com.example.me.vsap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.app.Activity;
import android.app.AlertDialog;    
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;

public class ScanActivity extends Activity implements OnQRCodeReadListener {

    private QRCodeReaderView mDecoderView;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        //linking decoder view and start to listen to any code
        mDecoderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mDecoderView.setOnQRCodeReadListener(this);
    }

    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        //show Dialog to user contain decoded message
        //check if there's no another dialog is show
        if( mAlertDialog != null && mAlertDialog.isShowing() ){
            //TODO action if already Dialog is showing
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Code")
                    .setMessage(text)
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.dismiss();
                        }
                    });
            mAlertDialog = builder.create();
            mAlertDialog.show();

        }

    }

    // Called when your device have no camera
    @Override
    public void cameraNotFound() {
    }

    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDecoderView.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDecoderView.getCameraManager().stopPreview();
    }
}
