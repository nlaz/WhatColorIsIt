package com.colortime.nlaz.whatcolorisit;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private TextView timeView;
    private TextView hexView;
    private View background;
    private Handler handler;
    int hours, minutes, seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.background);
        timeView = (TextView) findViewById(R.id.time);
        hexView  = (TextView) findViewById(R.id.hex);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SanFranciscoText-Light.otf");

        timeView.setTypeface(face);
        hexView.setTypeface(face);

        handler = new Handler();
        handler.post(runnable);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minutes = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);
            timeView.setText(formatTime(hours, minutes, seconds));
            String hex = formatHex(hours,minutes,seconds);
            hexView.setText(hex);
            background.setBackgroundColor(Color.parseColor(hex));
            handler.postDelayed(this,1000);
        }
    };

    private String formatTime(int hours, int minutes, int seconds){
        return formatDigits(hours) + " : " + formatDigits(minutes) + " : " + formatDigits(seconds);
    }

    private String formatHex (int hours, int minutes, int seconds){
        return "#" + formatDigits(hours) + formatDigits(minutes) + formatDigits(seconds);
    }


    private String formatDigits(int val){
        String digits = Integer.toString(val);

        if (digits.length() < 2)
            return  "0" + digits;

        return digits;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
