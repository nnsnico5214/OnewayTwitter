package jp.ac.kanazawait.onewaytwitter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.view.View.*;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Twitterアカウントの認証
        if(!TwitterUtils.hasAccessToken(this)){
            Intent intent = new Intent(this, OAuthActivity.class);
            startActivity(intent);
            finish();
        }

        Twitter twitter = TwitterUtils.getTwitterInstance(this);
        try {
            List<Status> statuses = twitter.getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        findViewById(R.id.transition_tweet_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v != null){
            switch (v.getId()){
                // Click Tweet
                case R.id.transition_tweet_activity:
                    Intent tweetIntent = new Intent(getApplication(), TweetActivity.class);
                    startActivity(tweetIntent);
                    break;
                default:
                    break;
            }
        }
    }

}
