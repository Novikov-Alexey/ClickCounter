package com.devnovikov.clickcounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReceiveActivity extends AppCompatActivity {

    private static final String RECEIVER_COUNT = "com.devnovikov.clickcounter.receiver.count";
    private static final String SAVED_CONSTANT = "com.devnovikov.clickcounter.receiver";
    private Unbinder unbinder;

    @BindView(R.id.receiver_textview)
    TextView receiverTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        unbinder = ButterKnife.bind(this);
        receiverTextView.setText((String) Objects.requireNonNull(getIntent().getExtras()).get(RECEIVER_COUNT));
        if (savedInstanceState != null) {
            receiverTextView.setText(savedInstanceState.getString(SAVED_CONSTANT));
        }
    }

    public static void start(Activity activity, String count) {
        Intent intent = new Intent(activity, ReceiveActivity.class);
        intent.putExtra(RECEIVER_COUNT, count);
        activity.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_CONSTANT, receiverTextView.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
