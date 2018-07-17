package me.monster.hencoderplusview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import me.monster.hencoderplusview.view.DashView;
import me.monster.hencoderplusview.view.PieView;
import me.monster.hencoderplusview.view.SportsView;

/**
 * @author PVer
 */
public class MainActivity extends AppCompatActivity {

    private DashView mDashView;
    private EditText etValue;
    private SportsView spView;
    private PieView mPieView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mDashView = findViewById(R.id.dash_board);
        etValue = findViewById(R.id.et_value);
        spView = findViewById(R.id.dash_board);

        etValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String strEtValue = etValue.getText().toString().trim();
//                    mDashView.setValue(Integer.parseInt(strEtValue));
                    spView.setValue(Integer.parseInt(strEtValue));
                }
                return false;
            }
        });
    }
}
