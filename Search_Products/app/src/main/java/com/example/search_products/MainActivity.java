package com.example.search_products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        Button brouse = (Button)findViewById(R.id.brouser);
        Button barcode = (Button)findViewById(R.id.barcode);
        EditText editText = (EditText)findViewById(R.id.url);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        barcode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    new IntentIntegrator(MainActivity.this)
                            .setOrientationLocked(false)
                            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                            .initiateScan();
            }

        });


        brouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedId = radioGroup.getCheckedRadioButtonId();


                String code = editText.getText().toString();
                if(code.length() > 0) {

                    if(checkedId != -1) {

                        RadioButton radioButton = (RadioButton) findViewById(checkedId);
                        String text = radioButton.getText().toString();
                        Uri uri;

                        if (text.equals("cravelweb")) {
                            uri = Uri.parse("https://jansearch.cravelweb.com/search/?q=" + code);
                        }
                        else if (text.equals("amazon")){
                            uri = Uri.parse("https://www.amazon.co.jp/s?k=" + code + "&__mk_ja_JP=カタカナ&ref=nb_sb_noss");
                        }
                        else{
                            uri = Uri.parse("https://search.rakuten.co.jp/search/mall/" + code + "/");
                        }


                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                    else {
                        String popup = "Choose one  !!!!";
                        Toast myToast = Toast.makeText(
                                getApplicationContext(),
                                popup,
                                Toast.LENGTH_SHORT
                        );
                        myToast.show();
                    }
                }
                else {
                    String popup = getString(R.string.introduce) + "  !!!!";
                    Toast myToast = Toast.makeText(
                            getApplicationContext(),
                            popup,
                            Toast.LENGTH_SHORT
                    );
                    myToast.show();

                }
            }
        });


    }
}

class SubActivity extends AppCompatActivity {

}