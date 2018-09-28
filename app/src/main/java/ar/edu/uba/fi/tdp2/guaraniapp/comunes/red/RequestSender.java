package ar.edu.uba.fi.tdp2.guaraniapp.comunes.red;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class RequestSender {

    private RequestQueue queue;

    public RequestSender(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void doRequest(JsonRequest request){

        queue.add(request);
    }

    public void doPost(final ResponseListener listener, String url, final JSONObject jsonObject){
        Log.d("RequestSender", "Sending post to " + url + " params " + jsonObject.toString());

        doRequest(new CustomJsonObjetRequest(url, jsonObject, listener));

    }

    public void doGet_expectJSONArray(final ResponseListener listener, String url){
        Log.d("RequestSender", "Sending get to " + url );

        doRequest(new CustomJsonArrayRequest(url, listener));

    }

    public void doGet_expectJSONObject(final ResponseListener listener, String url){
        Log.d("RequestSender", "Sending get to " + url );

        doRequest(new CustomJsonObjetRequest(Request.Method.GET, url, null, listener));

    }
    public void doDelete(final ResponseListener listener, String url){
        Log.d("RequestSender", "Deleting from " + url);

        doRequest(new CustomJsonObjetRequest(Request.Method.DELETE, url, null, listener));

    }

    public void doPut_expectJSONObject(final ResponseListener listener, String url, JSONObject jsonObject) {
        Log.d("RequestSender", "Put to " + url);
        doRequest(new CustomJsonObjetRequest(Request.Method.PUT, url, jsonObject, listener));
    }


    public void doPost_expectJSONArray(final ResponseListener listener, String url, final JSONObject jsonObject){
        Log.d("RequestSender", "Sending postGetArray to " + url + " params " + jsonObject.toString());

        JsonRequest<JSONArray> request;

        request = new JsonRequest<JSONArray>(
                Request.Method.POST,
                url,
                jsonObject.toString(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        listener.onRequestCompleted(jsonArray);
                        Log.d("RequestSender", "onResponse");

                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("RequestSender", "onErrorResponse");
                Pair<Integer, String> errorDetail;

                errorDetail = RequestHelper.getError(volleyError);

                listener.onRequestError(errorDetail.t, errorDetail.u);
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return RequestHelper.getHeaders();
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse networkResponse) {


                try {
                    Log.d("RequestSender", "intenta parsear");
                    String jsonString = new String(networkResponse.data,
                            HttpHeaderParser
                                    .parseCharset(networkResponse.headers));
                    return Response.success(new JSONArray(jsonString),
                            HttpHeaderParser
                                    .parseCacheHeaders(networkResponse));
                } catch (UnsupportedEncodingException e) {
                    Log.d("RequestSender", "intenta parsear UnsupportedEncodingException");
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    Log.d("RequestSender", "intenta parsear");
                    return Response.error(new ParseError(je));
                }

            }
        };



        doRequest(request);

    }



}
