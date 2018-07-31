package me.monster.hencoderplusview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ImageActivity.class);
        context.startActivity(starter);
    }
}
