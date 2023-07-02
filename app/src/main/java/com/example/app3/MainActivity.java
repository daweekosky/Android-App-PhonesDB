package com.example.app3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView phonesListRv;
    private PhonesAdapter adapter;
    private PhonesViewModel viewModel;
    private ActivityResultLauncher<Intent> launcher;

    private FloatingActionButton addPhoneFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.phonesListRv = findViewById(R.id.phone_list_tv);
        this.addPhoneFab = findViewById(R.id.add_phone_fab);
        this.addPhoneFab.setOnClickListener(view -> newPhone());
        this.adapter = new PhonesAdapter(this.getLayoutInflater());
        this.adapter.setOnPhoneClickListener(phone -> editSelectedPhone(phone));
        this.phonesListRv.setAdapter(adapter);
        this.phonesListRv.setLayoutManager(new LinearLayoutManager(this));
        this.viewModel = new ViewModelProvider(this).get(PhonesViewModel.class);
        this.viewModel.getPhoneList().observe(this, phones -> {
            this.adapter.setPhoneList(phones);
        });
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result ->  savePhone(result));
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Phone phone = MainActivity.this.adapter.getPhoneList().get(viewHolder.getAdapterPosition());
                MainActivity.this.viewModel.delete(phone);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(this.phonesListRv);
    }

    private void editSelectedPhone(Phone phone) {
        Bundle bundle = new Bundle();
        bundle.putLong(PhoneActivity.PHONE_ID_KEY, phone.getId());
        bundle.putString(PhoneActivity.MANUFACTURER_KEY, phone.getManufacturer());
        bundle.putString(PhoneActivity.MODEL_KEY, phone.getModel());
        bundle.putString(PhoneActivity.ANDROID_VERSION_KEY, phone.getAndroidVersion());
        bundle.putString(PhoneActivity.WEB_SITE_KEY, phone.getWebSite());
        Intent intent = new Intent(this, PhoneActivity.class);
        intent.putExtras(bundle);
        this.launcher.launch(intent);
    }

    private void newPhone() {
        Intent intent = new Intent(this, PhoneActivity.class);
        this.launcher.launch(intent);
    }

    private void savePhone(ActivityResult result) {
        if(result.getResultCode()==RESULT_OK){
            Bundle bundle = result.getData().getExtras();
            Long phoneId = bundle.getLong(PhoneActivity.PHONE_ID_KEY);
            String manufacturer = bundle.getString(PhoneActivity.MANUFACTURER_KEY);
            String model = bundle.getString(PhoneActivity.MODEL_KEY);
            String androidVersion = bundle.getString(PhoneActivity.ANDROID_VERSION_KEY);
            String webSite = bundle.getString(PhoneActivity.WEB_SITE_KEY);
            if(phoneId == 0){
                this.viewModel.insert(new Phone(manufacturer,model,androidVersion,webSite));
            }
            if(phoneId > 0){
                this.viewModel.update(new Phone(phoneId, manufacturer, model, androidVersion, webSite));
            }
        }
    }
}