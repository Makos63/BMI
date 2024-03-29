package com.example.praktikum1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    final String CHANNEL_ID = "My first Activity";
    int notificationId = 123;
    double weight, height, bmi;

    String notification_message;

    EditText HeightField;
    EditText WeightField;

    Button OkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HeightField = (EditText) findViewById(R.id.height);
        WeightField = (EditText) findViewById(R.id.weight);
        OkButton = (Button) findViewById(R.id.myButton);

        OkButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                height = Double.valueOf(HeightField.getText().toString());
                                                weight = Double.valueOf(WeightField.getText().toString());
                                                bmi = weight / (height * height);
                                                showToast(Double.toString(bmi));

                                                if (bmi > 25) {
                                                    notification_message = "Übergewichtet";
                                                } else {
                                                    notification_message = "Untergewichtet";
                                                }


                                                // NotifcationChannel erzeugen
                                                createNotificationChannel();

                                                // ... und dann auch Notificationobjekte erzeugen
                                                NotificationCompat.Builder mBuilder =
                                                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                                                .setSmallIcon(R.mipmap.ic_launcher)
                                                                .setContentTitle("BMI Rechner Praktikum 1 Aufgabe 2")
                                                                .setContentText(notification_message)
                                                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                                NotificationManagerCompat notificationManager =
                                                        NotificationManagerCompat.from(MainActivity.this);
                                                // notificationId is a unique int for each notification
                                                // that you must define
                                                notificationManager.notify(notificationId, mBuilder.build());


                                            } catch (Exception e) {
                                                showToast("sth went wrong");
                                            }


                                        }


                                    }
        );
    }//String muss groß geschrieben werden lol

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "BMI";
            String description = "BMI index";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system;
            // you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /*Aufgabe 2*/
/*    final String CHANNEL_ID = "My first Activity";
    int notificationId = 123;

    private void createNotificationChannel() {
// Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system;
            // you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // NotifcationChannel erzeugen
    createNotificationChannel();

    // ... und dann auch Notificationobjekte erzeugen
    NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Nachricht sichtbar machen")
                    .setContentText("Nachricht soll vom Nutzer wahrgenommen werden")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    NotificationManagerCompat notificationManager =
            NotificationManagerCompat.from(this);
// notificationId is a unique int for each notification
// that you must define
notificationManager.notify(notificationId,mBuilder.build());*/
}

//alt+enter import lib
//