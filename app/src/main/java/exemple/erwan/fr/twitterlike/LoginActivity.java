package exemple.erwan.fr.twitterlike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginActivity extends AppCompatActivity {

    TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig(Utilites.CONSUMER_KEY, Utilites.CONSUMER_SECRET))
                .build();
        Twitter.initialize(config);

        setContentView(R.layout.activity_login);

        final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

        //si la session existe déjà
        if(session != null)
            startActivity(intent);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(getApplicationContext(), "Connexion réussi !", Toast.LENGTH_SHORT).show();
                Log.i("twitter", "Connexion twitter réussi");
                startActivity(intent);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(), "Connexion echec !!", Toast.LENGTH_SHORT).show();
                Log.i("twitter", "Connexion twitter echec");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
