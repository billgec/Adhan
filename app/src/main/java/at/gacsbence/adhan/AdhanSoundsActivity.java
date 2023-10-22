package at.gacsbence.adhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/**
 * Adhan sounds activity
 * Created by Bence on 21.10.2023
 * Project: adhan
 * Description: Provide a dedicated area for users to browse and select Adhan sounds.
 */
public class AdhanSoundsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhan_sounds);
    }
}