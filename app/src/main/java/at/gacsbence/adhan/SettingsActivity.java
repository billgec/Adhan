package at.gacsbence.adhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Settings activity
 * Created by Bence on 21.10.2023
 * Project: adhan
 * Description: Allow users to configure their preferences, including selecting Adhan sounds, notification settings, and location settings.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}