package com.example.ipsenspiegel.mynetworkmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_MAIN_ACTIVITY = "TAG_MAIN_ACTIVITY";
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mImageView = (ImageView) findViewById(R.id.imageViewMain);

        final Button Feed_Btn = (Button) this.findViewById(R.id.btnReadFeed);
        Feed_Btn.setOnClickListener(this);
        final Button Dwnld_Im_Btn = (Button) this.findViewById(R.id.imgBtnDownload);
        Dwnld_Im_Btn.setOnClickListener(this);


        ConnectivityManager mConnectionManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    @Override
    public void onClick(View whichview) {
        if (whichview.getId() == R.id.imageViewMain) {
            
            Picasso.with(this).load(urlString).into(this.mImageView);

        } else if (whichview.getId() == R.id.btnReadFeed) {

            final String urlFeed = "https://www.theguardian.com/internatiional/rss";
        } else {

        }
    }
}
