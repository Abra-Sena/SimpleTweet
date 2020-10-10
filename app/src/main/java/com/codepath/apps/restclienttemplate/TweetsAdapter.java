package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    //constructor: pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get tweet at the position
        Tweet tweet = tweets.get(position);
        //bind the tweet with view holder
        holder.bindTweet(tweet);
        //add time stamp(when tweet was posted)
        tweet.getFormattedTimestamp();
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    //define a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvUserName;
        TextView tvHandler;
        TextView tvBody;
        TextView tvTimestamp;

        //itemView is the representation of one row in the RecyclerView (a tweet in this case)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvHandler = itemView.findViewById(R.id.tvHandler);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bindTweet(final Tweet tweet) {
            int radius = 60;
            int margin = 10;

            tvBody.setText(tweet.body);
            tvUserName.setText(tweet.user.name);
            tvHandler.setText("@" + tweet.user.screenName);
            tvTimestamp.setText(tweet.getFormattedTimestamp()); // set time when tweet was posted
            Glide.with(context)
                    .load(tweet.user.profileImageURl)
                    .transform(new RoundedCornersTransformation(radius,margin))
                    .into(ivProfileImage);

            tvBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("on click", "Access Tweet details");
                    //navigate to a new activity on tap
                    Intent i = new Intent(context, TweetDetailsActivity.class);
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    context.startActivity(i);
                }
            });

        }
    }
}
