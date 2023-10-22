package at.gacsbence.adhan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;

    public static double latitude;
    public static double longitude;

    public static double getLatitude() {
        return latitude;
    }
    public static double getLongitude() {
        return longitude;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton compassButton = findViewById(R.id.compassButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        ImageButton calendarButton = findViewById(R.id.CalendarButton);

        compassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(intent);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

    }


    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Location permission is required to determine your location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    //TODO: Handle this case
                    return;
                }
                latitude = locationResult.getLastLocation().getLatitude();
                longitude = locationResult.getLastLocation().getLongitude();
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            } else {
                getLocation();
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    public void getPrayerTimes() {
        Coordinates coordinates = new Coordinates(latitude, longitude);

        // get current date
        LocalDate today = LocalDate.now();

        DateComponents date = new DateComponents(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        DateComponents date = DateComponents.from(new Date());

        CalculationParameters params = CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();
        params.madhab = Madhab.HANAFI;
        params.adjustments.fajr = 2;

        // Calculate prayer times
        PrayerTimes prayerTimes = new PrayerTimes(coordinates, date, params);

        // Access prayer times
        String fajrTime = prayerTimes.fajr.toString();
        String dhuhrTime = prayerTimes.dhuhr.toString();
        String asrTime = prayerTimes.asr.toString();
        String maghribTime = prayerTimes.maghrib.toString();
        String ishaTime = prayerTimes.isha.toString();

        // set the prayer times in the UI
        TextView fajrTextView = findViewById(R.id.time_fajr);
        TextView dhuhrTextView = findViewById(R.id.time_dhuhr);
        TextView asrTextView = findViewById(R.id.time_asr);
        TextView maghribTextView = findViewById(R.id.time_maghrib);
        TextView ishaTextView = findViewById(R.id.time_isha);
        fajrTextView.setText(fajrTime);
        dhuhrTextView.setText(dhuhrTime);
        asrTextView.setText(asrTime);
        maghribTextView.setText(maghribTime);
        ishaTextView.setText(ishaTime);

        //TODO How to get jumua/shuruq time?
    }

    // TODO: Implement methods to get city name and country name based on coordinates
    public String getCityName(double latitude, double longitude) {
        // Implement the logic to get the city name using the coordinates.
        // You may use reverse geocoding services or databases for this.
        // Return the city name as a string.
        return "CityName"; // Example: New York
    }

    public String getCountryName(double latitude, double longitude) {
        // Implement the logic to get the country name using the coordinates.
        // You may use reverse geocoding services or databases for this.
        // Return the country name as a string.
        return "CountryName"; // Example: United States
    }

}