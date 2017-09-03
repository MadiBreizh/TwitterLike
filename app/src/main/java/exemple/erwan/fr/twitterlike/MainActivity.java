package exemple.erwan.fr.twitterlike;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    String utilisateur = "twitter";
//    final long tweetId = 510908133917487104L;

    EditText rechercher;
    Button lancer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rechercher = (EditText)findViewById(R.id.et_recherche);
        lancer = (Button)findViewById(R.id.bt_chercher);


        //final ConstraintLayout lPrincipal = (ConstraintLayout) findViewById(R.id.lPrincipal);
        final ListView listView = (ListView) findViewById(R.id.list_timeline);

        lancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilites.afficherTimelineTwitter(getApplicationContext(), listView, rechercher.getText().toString());
            }
        });

        Utilites.afficherTimelineTwitter(getApplicationContext(), listView, utilisateur);


    }

}
