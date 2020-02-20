package com.devnovikov.clickcounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceiveActivity extends AppCompatActivity {

    public  final static String RECEIVER_COUNT = "com.devnovikov.clickcounter.receiver.count";
    private final static String savedConstant = "com.devnovikov.clickcounter.receiver";
    @BindView(R.id.receiverTv)
    TextView receiverTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        ButterKnife.bind(this);
        receiverTv.setText((String) Objects.requireNonNull(getIntent().getExtras()).get(RECEIVER_COUNT));
        if (savedInstanceState != null) {
            receiverTv.setText(savedInstanceState.getString(savedConstant));
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(savedConstant, receiverTv.getText().toString());
    }

}
