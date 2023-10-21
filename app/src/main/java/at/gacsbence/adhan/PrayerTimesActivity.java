package at.gacsbence.adhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Prayer times activity
 * Created by Bence on 21.10.2023
 * Project: adhan
 * Description: Display the full prayer times schedule for the day, week, or month. Users can browse upcoming prayer times and view past prayer times.
 */
public class PrayerTimesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_times);
    }
}