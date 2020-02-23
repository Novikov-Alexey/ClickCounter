package com.devnovikov.clickcounter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String SAVED_CONSTANT = "com.devnovikov.clickcounter.count";
    private Unbinder unbinder;

    @BindView(R.id.count_textview)
    TextView countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        if (savedInstanceState != null) {
            countTextView.setText(savedInstanceState.getString(SAVED_CONSTANT));
        }
    }

    @OnClick(R.id.click_button)
    public void onCounterClicked() {
        countTextView.setText(getString(R.string.counter, Integer.parseInt(countTextView.getText().toString()) + 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.action_cook) {
            ReceiveActivity.start(MainActivity.this, countTextView.getText().toString());
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_CONSTANT, countTextView.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
