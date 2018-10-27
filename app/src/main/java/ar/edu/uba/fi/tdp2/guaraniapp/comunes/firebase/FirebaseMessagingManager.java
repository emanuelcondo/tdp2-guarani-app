package ar.edu.uba.fi.tdp2.guaraniapp.comunes.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.login.Token;

public class FirebaseMessagingManager implements ResponseListener {

    private MainActivity activity;
    private String registrationToken;

    public FirebaseMessagingManager(final MainActivity activity) {
        this.activity = activity;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = activity.getString(R.string.default_notification_channel_id);
            String channelName = activity.getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    activity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (activity.getIntent().getExtras() != null) {
            for (String key : activity.getIntent().getExtras().keySet()) {
                Object value = activity.getIntent().getExtras().get(key);
                Log.d("FirebaseMessagingManager", "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]


        // Get token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FirebaseMessagingManager", "getInstanceId failed", task.getException());
                            return;
                        }

                        registrationToken = task.getResult().getToken();

                        Log.d("FirebaseMessagingManager", "Firebase token: " + registrationToken);

                    }
                });

        FirebaseMessaging.getInstance().subscribeToTopic("topicAll");
    }

    public void sendFirebaseToken() {
        Map<String,String> parametros;
        parametros = new HashMap<>();
        parametros.put("registrationToken", registrationToken);

        RequestSender requestSender = new RequestSender(activity);


        JSONObject jsonObject = new JSONObject(parametros);


        String url = activity.getString(R.string.urlAppServer) + "notificaciones/token/";

        requestSender.doPut_expectJSONObject(this, url, jsonObject);
    }

    @Override
    public void onRequestCompleted(Object response) {

    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(activity, errorMessage);
    }
}
