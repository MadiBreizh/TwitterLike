package exemple.erwan.fr.twitterlike;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by Erwan on 15/08/2017.
 */

public class Utilites {

    public static final String CONSUMER_KEY = "TNAogk1vJrIQNw15jgcp1xMbM";
    public static final String CONSUMER_SECRET = "Gw3IZE6u0mBF93GPMIfxAU2MfB7IN72v2hdvKaFvG2XF6veaJp";


    static public void afficherTweet(final Context context, final ConstraintLayout lPrincipal, long tweetId) {
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                lPrincipal.addView(new TweetView(context, result.data));
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(context, "Erreur de chargement du tweet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static public void afficherTimelineTwitter(final Context context, ListView listView, String utilisateur) {

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(utilisateur)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(context)
                .setTimeline(userTimeline)
                .build();


        listView.setAdapter(adapter);
    }

}
