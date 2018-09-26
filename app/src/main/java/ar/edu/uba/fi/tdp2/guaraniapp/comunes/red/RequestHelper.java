package ar.edu.uba.fi.tdp2.guaraniapp.comunes.red;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.login.Token;


public class RequestHelper {

    public static Pair<Integer, String> getError(VolleyError error) {
        String errorDesc;
        Integer codError = 0;



        if (error instanceof TimeoutError || error instanceof NoConnectionError) {

            errorDesc = "No fue posible conectarse al servidor, por favor intente mas tarde. ";

        } else if (error instanceof AuthFailureError) {

            codError = 401;
            errorDesc = "Error de autenticacion";

            NetworkResponse networkResponse = error.networkResponse;
            if (networkResponse != null && networkResponse.data != null) {
                String jsonError = new String(networkResponse.data);
                try {
                    JSONObject result = new JSONObject(jsonError);
                    errorDesc = result.getJSONObject("error").getString("message");

                } catch (Exception e) {

                }

            }


        } else if (error instanceof ServerError) {

            codError = error.networkResponse.statusCode;

            switch (codError) {
                case 409:
                    errorDesc = "Nombre de usuario ya existe";
                    break;
                case 400:
                    errorDesc = "No se recibieron todos los parametros";
                    break;
                case 404:
                    errorDesc = "La url invocada no corresponde a un servicio valido";
                    break;
                case 405:
                    errorDesc = "La url invocada no acepta el tipo de operacion dado";
                    break;
                default:
                    errorDesc = new String(error.networkResponse.data);

            }
        } else if (error instanceof NetworkError) {

            errorDesc = "Error de red";

        } else if (error instanceof ParseError) {

            errorDesc = "Error de parseo";
            error.printStackTrace();

        } else {

            errorDesc = "Error desconocido. ";

        }

        return new Pair<>(codError, errorDesc);
    }

    public static Map getHeaders() {

        Map headers = new HashMap<>();
        String auth = Token.id;
        headers.put("Content-Type", "application/json");
        if (auth != null) {
            headers.put("token", auth);
        }

        return headers;
    }

    public static void showError(Context context, String error) {
        Log.d("LoginActivity", error);
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }
}
