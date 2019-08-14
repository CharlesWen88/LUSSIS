package sg.edu.nus.lussis.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.service.TrackerService;

public class TrackerActivity extends AppCompatActivity {

    private static final int PERMISSIONS_TRACKING_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tracker);
        initChannels(this);

        // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Check location permission is granted - if it is, start
        // the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_TRACKING_REQUEST);
        }

    }

    private void initChannels(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("default",
                "Default Notifications Channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Default Notifications");
        notificationManager.createNotificationChannel(channel);
    }

    private void startTrackerService() {
        Intent i = new Intent(this, TrackerService.class);
        String login = getIntent().getStringExtra("loginDto");
        i.putExtra("loginDto", login);
        startService(i);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        if (requestCode == PERMISSIONS_TRACKING_REQUEST && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Start the service when the permission is granted
            Intent i = new Intent(this, TrackerService.class);
            String login = getIntent().getStringExtra("loginDto");
            i.putExtra("loginDto", login);
            startTrackerService();
        } else {
            finish();
        }
    }
}
