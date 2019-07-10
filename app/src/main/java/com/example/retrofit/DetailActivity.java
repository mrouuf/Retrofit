package com.example.retrofit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.retrofit.data.PlayerData;

public class DetailActivity extends AppCompatActivity {
    private TextView detailName, detailCountry, detailCity;
    private ImageView detailPhoto;
    private PlayerData playerData;

    public static final String EXTRA_DETAIL = "extra_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        playerData = (PlayerData) getIntent().getSerializableExtra(EXTRA_DETAIL);
        setupView();
        populateView(playerData);
    }

    private void setupView() {
        detailPhoto = findViewById(R.id.imgPhotoDetail);
        detailName = findViewById(R.id.fieldName);
        detailCountry = findViewById(R.id.fieldNationality);
        detailCity = findViewById(R.id.fieldCity);
    }

    private void populateView(PlayerData data) {
        Glide.with(this)
                .load(data.getImgURL())
                .into(detailPhoto);
        detailName.setText(data.getName());
        detailCountry.setText(data.getCountry());
        detailCity.setText(data.getCity());
    }
}
