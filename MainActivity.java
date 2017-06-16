package com.example.android.mediaplayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    TextView number1;
    TextView number2;
    TextView number3;
    TextView number4;
    TextView mantra1;
    TextView mantra2;
    TextView mantra3;
    TextView mantra4;
    TextView text;
    ImageButton play;
    ImageButton stop;
    ImageView image;
    TextView countTextView;
    Button share;
    Button rate;
    Button show;
    Button hide;
    MediaPlayer mp;
    int count = 0;
    Intent serviceIntent;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number1 = (TextView) findViewById(R.id.number1);
        text=(TextView) findViewById(R.id.text);
        number2 = (TextView) findViewById(R.id.number2);
        number3 = (TextView) findViewById(R.id.number3);
        number4 = (TextView) findViewById(R.id.number4);
        mantra1=(TextView)findViewById(R.id.mantra1);
        mantra2=(TextView)findViewById(R.id.mantra2);
        mantra3=(TextView)findViewById(R.id.mantra3);
        mantra4=(TextView)findViewById(R.id.mantra4);
        play = (ImageButton) findViewById(R.id.play);
        share = (Button) findViewById(R.id.share);
        rate = (Button) findViewById(R.id.rate);
        show = (Button) findViewById(R.id.show);
        hide=(Button)findViewById(R.id.hide);
        stop = (ImageButton) findViewById(R.id.stop);
        countTextView = (TextView) findViewById(R.id.count);
        image  = (ImageView)findViewById(R.id.image);
         final int[] imageArray = {R.drawable.krishna, R.drawable.krishna1, R.drawable.krishna2};

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            @Override
            public void run() {
                image.setImageResource(imageArray[i]);
                i++;
                if (i>=imageArray.length){
                    i=0;
                }
                handler.postDelayed(this,2000);
            }
        };
        handler.postDelayed(runnable,2000);

        serviceIntent = new Intent(this, BackgroundService.class);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService(serviceIntent);
                play.setImageResource(R.drawable.ic_pause_black_24dp);
                countTextView.setVisibility(View.VISIBLE);

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
                play.setImageResource(R.drawable.play);
                countTextView.setVisibility(View.INVISIBLE);

            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 11;
                serviceIntent.putExtra("count", "1");
                startService(serviceIntent);

            }
        });
        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 21;
                serviceIntent.putExtra("count", "2");
                startService(serviceIntent);
            }
        });
        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 51;
                serviceIntent.putExtra("count", "3");
                startService(serviceIntent);

            }
        });
        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 108;
                serviceIntent.putExtra("count", "4");
                startService(serviceIntent);

            }
        });



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mantra1.setVisibility(View.VISIBLE);
                text.setVisibility(View.INVISIBLE);
                mantra2.setVisibility(View.VISIBLE);
                mantra3.setVisibility(View.VISIBLE);
                mantra4.setVisibility(View.VISIBLE);
                number1.setVisibility(View.INVISIBLE);
                number2.setVisibility(View.INVISIBLE);
                number3.setVisibility(View.INVISIBLE);
                number4.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.INVISIBLE);
                countTextView.setVisibility(View.INVISIBLE);

            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mantra1.setVisibility(View.INVISIBLE);
                text.setVisibility(View.VISIBLE);
                mantra2.setVisibility(View.INVISIBLE);
                mantra3.setVisibility(View.INVISIBLE);
                mantra4.setVisibility(View.INVISIBLE);
                number1.setVisibility(View.VISIBLE);
                number2.setVisibility(View.VISIBLE);
                number3.setVisibility(View.VISIBLE);
                number4.setVisibility(View.VISIBLE);
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                countTextView.setVisibility(View.VISIBLE);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.share:
                return true;
            case R.id.rate:
                return true;
            case R.id.audio_options:
                return true;
            case R.id.audio1:
                return true;
            case R.id.audio2:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Application");
        builder.setMessage("Do you want to exit the application");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                stopService(serviceIntent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
