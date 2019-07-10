package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.data.CardViewAdapter;
import com.example.retrofit.data.DataModel;
import com.example.retrofit.data.PlayerData;
import com.example.retrofit.network.GetDataService;
import com.example.retrofit.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textStatus, textMsg, textData;
    private GetDataService service;
    DataModel dataModel;
    List<PlayerData> playerDataList;
    ProgressDialog progressDialog;
    private CardViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        networkRequest();

    }

    private void showRecyclerCardView(List<PlayerData> playerDataList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        ));
        adapter = new CardViewAdapter(this, playerDataList);
        recyclerView.setAdapter(adapter);
    }

    private void setupView() {
        recyclerView = (RecyclerView) findViewById(R.id.itemCategory);
    }

    private void networkRequest() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        service.getData().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                progressDialog.dismiss();
                if (response.body() != null){
                    dataModel = response.body();
                    playerDataList = dataModel.getData();
                    showRecyclerCardView(playerDataList);
                    Log.d("cekit",playerDataList.toString());
                }

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Connection problem", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
