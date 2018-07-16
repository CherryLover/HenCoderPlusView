package me.monster.hencoderplusview;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.monster.hencoderplusview.view.DashView;
import me.monster.hencoderplusview.view.SportsView;

/**
 * @author PVer
 */
public class MainActivity extends AppCompatActivity {

    private DashView mDashView;
    private EditText etValue;
    private SportsView spView;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDashView = findViewById(R.id.dash_board);
        etValue = findViewById(R.id.et_value);
//        spView = findViewById(R.id.dash_board);
        btnClick = findViewById(R.id.btn_set);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(spView, "value", 0, Float.parseFloat(etValue.getText().toString().trim()));
//                objectAnimator.start();
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mDashView, "value", 0, Float.parseFloat(etValue.getText().toString().trim()));
                objectAnimator.start();
            }
        });

    }

    public void setValue(View view) {
        String strEtValue = etValue.getText().toString().trim();
        mDashView.setValue(Integer.parseInt(strEtValue));

//        spView.setValue(Integer.parseInt(etValue.getText().toString().trim()));
    }
}
