package at.gacsbence.adhan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Qibla;

/**
 * Compass activity
 * Created by Bence on 21.10.2023
 * Project: adhan
 * Description: Display a compass that points to the Qibla. Users can also view the Qibla on a map.
 */
public class CompassActivity extends AppCompatActivity {

    private double latitude = MainActivity.getLatitude();
    private double longitude = MainActivity.getLongitude();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        Coordinates coordinates = new Coordinates(latitude, longitude);
        Qibla qibla = new Qibla(coordinates);
        // qibla.direction is the qibla direction
    }



}