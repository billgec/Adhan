package at.gacsbence.adhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/**
 * Profile activity
 * Created by Bence on 21.10.2023
 * Project: adhan
 * Description: Allow users to view their profile information, including their name, email address, and location.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}