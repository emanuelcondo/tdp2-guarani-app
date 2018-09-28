package ar.edu.uba.fi.tdp2.guaraniapp.comunes.red;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class PingListener implements ResponseListener {

    private Context context;

    public PingListener(Context context) {
        this.context = context;
    }
    @Override
    public void onRequestCompleted(Object response) {

        Log.d("PingListener", response.toString());
        Toast.makeText(context, "Ping to server ok", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        String error = codError + ": " + errorMessage;
        Log.d("PingListener", error);
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }
}
