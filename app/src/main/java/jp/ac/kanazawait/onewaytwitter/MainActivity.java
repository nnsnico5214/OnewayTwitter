package jp.ac.kanazawait.onewaytwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.os.Bundle;

import twitter4j.Twitter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!TwitterUtils.hasAccessToken(this)){
            Intent intent = new Intent(getApplicationContext(), OAuthActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
