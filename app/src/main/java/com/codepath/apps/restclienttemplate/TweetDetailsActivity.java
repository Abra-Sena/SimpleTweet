package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {

    Context context;
    ImageView ivProfileImage;
    TextView tvHandler;
    TextView tvUserName;
    TextView tvBody;
    TextView tvTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvHandler = findViewById(R.id.tvHandler);
        tvUserName = findViewById(R.id.tvUserName);
        tvBody = findViewById(R.id.tvBody);
        tvTimestamp = findViewById(R.id.tvTimestamp);

        //retrieve data
        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
       // ivProfileImage.setImageDrawable(Drawable.createFromPath(tweet.user.profileImageURl));
        tvUserName.setText(tweet.user.name);
        tvHandler.setText("@" + tweet.user.screenName);
        tvBody.setText(tweet.body);
        tvTimestamp.setText(tweet.getFullTimestamp());
    }
}