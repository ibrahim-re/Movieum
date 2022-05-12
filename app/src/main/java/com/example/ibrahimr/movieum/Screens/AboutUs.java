package com.example.ibrahimr.movieum.Screens;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibrahimr.movieum.R;

public class AboutUs extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        textView = findViewById(R.id.textView4);
        button = findViewById(R.id.closeAboutUs);

        String text = "All movie-related data used in Movieum including actors, directors, and posters is supplied by TMDb";
        SpannableString ss = new SpannableString(text);
        ClickableSpan c = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://www.themoviedb.org/?language=en-US";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        };
        ss.setSpan(c,95,99, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
