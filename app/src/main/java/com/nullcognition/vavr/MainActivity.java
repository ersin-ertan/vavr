package com.nullcognition.vavr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/*
* Why - side-effect that affects our programs state
 * break normal control flow
 *
 * and created new collections class
 *
 * always favor the encapsulation of sideeffects with deterministic outputs on error
 * Try.of(()-> 0/0); which returns a safe object to operate on
* */

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView textView = (TextView) findViewById(R.id.text);

    //textView.setText(List.of("This", "is", "the", "end.").mkString("-*-"));

  }
}