package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneActivity extends AppCompatActivity {
    public static final String MANUFACTURER_KEY = "MANUFACTURER_KEY";
    public static final String MODEL_KEY = "MODEL_KEY";
    public static final String ANDROID_VERSION_KEY = "ANDROID_VERSION_KEY";
    public static final String WEB_SITE_KEY = "WEB_SITE_KEY";

    public static final String PHONE_ID_KEY = "PHONE_ID_KEY";

    private EditText manufacturerEt;
    private EditText modelEt;
    private EditText androidVersionEt;
    private EditText webSiteEt;
    private Button saveBt;
    private Button cancelBt;
    private Button webSiteBt;
    private Long phoneId = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        this.manufacturerEt = findViewById(R.id.manufacturer_et);
        this.modelEt = findViewById(R.id.model_et);
        this.androidVersionEt = findViewById(R.id.android_version_et);
        this.webSiteEt = findViewById(R.id.web_site_et);
        this.saveBt = findViewById(R.id.save_bt);
        this.webSiteBt = findViewById(R.id.web_site_bt);
        this.saveBt.setOnClickListener(view -> savePhone());
        this.webSiteBt.setOnClickListener(view -> strona());
        this.cancelBt = findViewById(R.id.cancel_bt);
        this.cancelBt.setOnClickListener(view -> cancelSave());

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            this.phoneId = bundle.getLong(PHONE_ID_KEY);
            this.manufacturerEt.setText(bundle.getString(MANUFACTURER_KEY));
            this.modelEt.setText(bundle.getString(MODEL_KEY));
            this.androidVersionEt.setText(bundle.getString(ANDROID_VERSION_KEY));
            this.webSiteEt.setText(bundle.getString(WEB_SITE_KEY));
        }
        daneCheck();
        przyciskSave();
    }

    private void strona() {
        String adres = webSiteEt.getText().toString();
        if (!adres.startsWith("http://") && !adres.startsWith("https://"))
            adres = "http://" + adres;
        Intent zamiarPrzegladarki = new Intent(Intent.ACTION_VIEW, Uri.parse(adres));
        startActivity(zamiarPrzegladarki);
    }

    private void cancelSave() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }


    private void savePhone() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putLong(PHONE_ID_KEY, this.phoneId);
        bundle.putString(this.MANUFACTURER_KEY,this.manufacturerEt.getText().toString());
        bundle.putString(this.MODEL_KEY,this.modelEt.getText().toString());
        bundle.putString(this.ANDROID_VERSION_KEY,this.androidVersionEt.getText().toString());
        bundle.putString(this.WEB_SITE_KEY,this.webSiteEt.getText().toString());
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void daneCheck(){
        EditText manufacturerEt = findViewById(R.id.manufacturer_et);
        EditText modelEt = findViewById(R.id.model_et);
        EditText androidEt = findViewById(R.id.android_version_et);
        EditText websiteEt = findViewById(R.id.web_site_et);
        manufacturerEt.setOnFocusChangeListener((view, b) -> {
            if(b){
                return;
            }
            else{
                if(!manufacturerCheck(manufacturerEt.getText().toString())){
                    manufacturerEt.setError(getString(R.string.blad_manufacturer));
                    Toast toast = Toast.makeText(this,R.string.blad_manufacturer,Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        modelEt.setOnFocusChangeListener((view, b) -> {
            if(b){
                return;
            }
            else{
                if(!modelCheck(modelEt.getText().toString())){
                    modelEt.setError(getString(R.string.blad_model));
                    Toast toast = Toast.makeText(this,R.string.blad_model,Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        androidEt.setOnFocusChangeListener((view, b) -> {
            if(b){
                return;
            }
            else{
                if(!androidCheck(androidEt.getText().toString())){
                    androidEt.setError(getString(R.string.blad_android));
                    Toast toast = Toast.makeText(this,R.string.blad_android,Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        websiteEt.setOnFocusChangeListener((view, b) -> {
            if(b){
                return;
            }
            else{
                if(!websiteCheck(websiteEt.getText().toString())){
                    websiteEt.setError(getString(R.string.blad_website));
                    Toast toast = Toast.makeText(this,R.string.blad_website,Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public void przyciskSave(){
        EditText manufacturerEt = findViewById(R.id.manufacturer_et);
        EditText modelEt = findViewById(R.id.model_et);
        EditText androidEt = findViewById(R.id.android_version_et);
        EditText websiteEt = findViewById(R.id.web_site_et);
        Button saveBt = findViewById(R.id.save_bt);

        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isValid = manufacturerCheck(manufacturerEt.getText().toString())
                        && modelCheck(modelEt.getText().toString())
                        && androidCheck(androidEt.getText().toString())
                        && websiteCheck(websiteEt.getText().toString());
                if (isValid) {
                    saveBt.setVisibility(View.VISIBLE);
                } else {
                    saveBt.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        manufacturerEt.addTextChangedListener(inputWatcher);
        modelEt.addTextChangedListener(inputWatcher);
        androidEt.addTextChangedListener(inputWatcher);
        websiteEt.addTextChangedListener(inputWatcher);
    }

    public boolean manufacturerCheck(String text){
        return text.matches("^[A-Z]{1}[a-z]{2,12}$");
    }

    public boolean modelCheck(String text){
        return text.matches("^[A-Za-z0-9 ]{1,20}$");
    }
    public boolean androidCheck(String text){
        return text.matches("^[A-Za-z0-9 .-]{1,12}$");
    }
    public boolean websiteCheck(String text){
        return text.matches("^(https?://)?([a-zA-Z0-9-]+\\.)*[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(/[a-zA-Z0-9-]+)*$");
    }
}