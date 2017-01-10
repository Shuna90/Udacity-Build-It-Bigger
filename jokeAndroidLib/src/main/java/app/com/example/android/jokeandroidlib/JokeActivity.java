package app.com.example.android.jokeandroidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "EXTRA_JOKE";
    public static final String JOKE_KEY = "JOKE_KEY";
    public static final String KEY_FREE = "KEY_FREE";
    public static final String KEY_PAID = "KEY_PAID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView text_joke = (TextView)findViewById(R.id.text_joke);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().hasExtra(EXTRA_JOKE)){
            if (getIntent().getStringExtra(JOKE_KEY).equals(KEY_PAID)){
                text_joke.setText(getIntent().getStringExtra(EXTRA_JOKE));
            }else {
                text_joke.setText(R.string.no_joke);
            }
        }
    }
}
