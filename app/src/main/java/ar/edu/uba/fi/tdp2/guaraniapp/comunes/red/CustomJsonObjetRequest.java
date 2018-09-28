package ar.edu.uba.fi.tdp2.guaraniapp.comunes.red;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

public class CustomJsonObjetRequest extends JsonObjectRequest {


    public CustomJsonObjetRequest(String url, JSONObject jsonRequest, final ResponseListener listener) {
        super
            (
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Pair<Integer, String> errorDetail;

                        errorDetail = RequestHelper.getError(error);

                        listener.onRequestError(errorDetail.t, errorDetail.u);

                    }
                }
            );
        setShouldCache(false);

    }

    public CustomJsonObjetRequest(int method, String url, JSONObject jsonRequest, final ResponseListener listener) {
        super
                (
                        method,
                        url,
                        jsonRequest,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                listener.onRequestCompleted(response);
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Pair<Integer, String> errorDetail;

                                errorDetail = RequestHelper.getError(error);

                                listener.onRequestError(errorDetail.t, errorDetail.u);

                            }
                        }
                );
        setShouldCache(false);

    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        if (response.data == null || response.data.length == 0) {
            Log.d("CustomJsonObjetRequest", "response is null");
            return Response.success(new JSONObject(), HttpHeaderParser.parseCacheHeaders(response));
        } else {
            return super.parseNetworkResponse(response);
        }
    }

    @Override
    public Map getHeaders() {

        return RequestHelper.getHeaders();
    }

}
