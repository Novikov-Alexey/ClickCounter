package com.devnovikov.clickcounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countTv)
    TextView countTv;
    private final static String savedConstant = "package com.devnovikov.clickcounter.count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            countTv.setText(savedInstanceState.getString(savedConstant));
        }
    }

    @OnClick(R.id.clickBtn)
    public void onCounterClicked() {
        countTv.setText(getString(R.string.counter, Integer.parseInt(countTv.getText().toString()) + 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Заполнение меню; элементы действий добавляются на панель приложения
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_cook) {
            Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
            intent.putExtra(ReceiveActivity.RECEIVER_COUNT, countTv.getText().toString());
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }




    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(savedConstant, countTv.getText().toString());
    }

}
