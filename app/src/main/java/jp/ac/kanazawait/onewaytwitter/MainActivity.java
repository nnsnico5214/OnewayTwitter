package jp.ac.kanazawait.onewaytwitter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.view.View.*;

import java.util.Collections;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    List<Status> statuses = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Twitterアカウントの認証
        if (!TwitterUtils.hasAccessToken(this)) {
            Intent intent = new Intent(this, OAuthActivity.class);
            startActivity(intent);
            finish();
        } else {
            Twitter twitter = TwitterUtils.getTwitterInstance(this);
            getTimeline(twitter);
            findViewById(R.id.transition_tweet_activity).setOnClickListener(this);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void getTimeline(final Twitter twitter) {
        // FIX: メモリリーク起きるかもっておこられるので無視するアノテーションつけた
        new AsyncTask<Void, Void, List<Status>>() {
            @Override
            protected List<twitter4j.Status> doInBackground(Void... voids) {
                try {
                    statuses = twitter.getHomeTimeline();
                    return statuses;
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<twitter4j.Status> statuses) {
                // TODO: タイムラインをなんかごにょごにょする
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
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
