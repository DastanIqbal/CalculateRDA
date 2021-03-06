package com.dastanapps.dastanLib.networks;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.dastanapps.calculaterda.App;
import com.dastanapps.dastanLib.log.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class VolleyRequest {

    private static final String TAG = "VolleyRequest";
    private static RequestQueue mRequestQueue;

    public static void getJsonObj(String url, final int reqId, final IRestRequest restReq) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.d(TAG, response.toString());
                        restReq.onResponse(reqId, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.d(TAG, error.toString());
                        restReq.onError(error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, //timeout second
                2,          //Retry
                1));        //Backoff multiplier
        //time=(time+(timeout*multi)
        App.getInstance().addToRequestQueue(jsonObjectRequest, url);
    }

    public static void getJsonArray(String url, final int reqId, final IRestRequest restReq) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, "",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Logger.d(TAG, response.toString());
                        restReq.onResponse(reqId, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.d(TAG, error.toString());
                        restReq.onError(error.toString());
                    }
                });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, //timeout second
                2,          //Retry
                1));        //Backoff multiplier
        //time=(time+(timeout*multi)
        App.getInstance().addToRequestQueue(jsonArrayRequest, url);
    }

    public static void delete(String url, final int reqId, final IRestRequest restReq) {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.DELETE, url, "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.d(TAG, response.toString());
                        restReq.onResponse(reqId, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.d(TAG, error.toString());
                        restReq.onError(error.toString());
                    }
                });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, //timeout second
                2,          //Retry
                1));        //Backoff multiplier
        //time=(time+(timeout*multi)
        App.getInstance().addToRequestQueue(jsonArrayRequest, url);
    }

    public static void getString(String url, final int reqId, final IRestRequest restReq) {
        Logger.i(TAG, url);
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                        Log.i("address priyam response",response);
//                        RestResponse.parseDisplayAdd(response);
                Logger.d(TAG, response.toString());
                restReq.onResponse(reqId, response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.i("address priyam response",error.toString());
                        Logger.d(TAG, error.toString());
                        restReq.onError(error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(90 * 1000, //timeout second
                0,          //Retry don't change this value it will affect on cart page
                0));        //Backoff multiplier
        //time=(time+(timeout*multi)
        App.getInstance().addToRequestQueue(jsonObjectRequest, url);
    }

    public static void getPostJsonObject(String url, final HashMap<String, String> postParams, final int reqId, final IRestRequest restReq) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                restReq.onResponse(reqId, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 500)
                    restReq.onError("500");
                else
                    restReq.onError(error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return postParams;
            }

        };


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, //timeout second
                2,          //Retry
                1));        //Backoff multiplier
        //time=(time+(timeout*multi)
        App.getInstance().addToRequestQueue(jsonObjectRequest, url);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


//    public static void getAppConfigJson(String url, final int reqId, final IRestRequest iRestRequest) {
//        StringRequest objectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                iRestRequest.onResponse(reqId, response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                iRestRequest.onError(error.getMessage());
//            }
//        });
//
//        MkartApp.getInstance().addToRequestQueue(objectRequest, url);
//    }


    /*public static void getPostJsonObject(String url, final JSONObject paramObject, final int reqId, final IRestRequest restReq) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                restReq.onResponse(reqId, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restReq.onError(error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return postParams;
            }

        };


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, //timeout second
                2,          //Retry
                1));        //Backoff multiplier
        //time=(time+(timeout*multi)
        MkartApp.getInstance().addToRequestQueue(jsonObjectRequest, url);
    }*/
}



