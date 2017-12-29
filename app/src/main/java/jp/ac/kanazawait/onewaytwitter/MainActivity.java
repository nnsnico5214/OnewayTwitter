package jp.ac.kanazawait.onewaytwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.view.View.*;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Twitterアカウントの認証
        if(!TwitterUtils.hasAccessToken(this)){
            Intent intent = new Intent(getApplicationContext(), OAuthActivity.class);
            startActivity(intent);
            finish();
        }

        findViewById(R.id.tweetButton).setOnClickListener(this);
        findViewById(R.id.authorization).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v != null){
            switch (v.getId()){
                // Click Tweet Button
                case R.id.tweetButton:

                    break;
                // Click Authorize Button
                case R.id.authorization:
                    Intent intent = new Intent(getApplication(), OAuthActivity.class);
                    startActivity(intent);
                    finish();
                default:
                    break;
            }
        }
    }

}
