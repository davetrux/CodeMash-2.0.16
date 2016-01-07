package com.hpe.simpleconsumer;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.hpe.simpleconsumer.rest.WebServiceAPI;

import java.util.List;

/**
 * @author trux on 11/10/15.
 */
public class DataService extends IntentService {

    public static final String DATA_RESULT = "DATA-RESULT";

    public DataService() {
        super("DataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        getData();
    }

    private void getData() {
        Log.d(getClass().getName(), "getData() called");

        int result = 2;

        WebServiceAPI.PersonApi personApi = WebServiceAPI.getInstance();
        List<Person> personList = personApi.getPersons();

        Gson parser = new Gson();

        String personJson = null;

        if (personList != null) {
            personJson = parser.toJson(personList);
            result = Activity.RESULT_OK;
        }

        sendResult(personJson, DATA_RESULT, "basic-data", result);
    }

    /*
     * Place the results into an intent and return it to the caller
     */
    private void sendResult(String data, String name, String action, int result) {

        Intent sendBack = new Intent(name);

        sendBack.putExtra("call", action);
        sendBack.putExtra("result", result);
        sendBack.putExtra("data", data);

        //Keep the intent local to the application
        LocalBroadcastManager.getInstance(this).sendBroadcast(sendBack);
    }
}
