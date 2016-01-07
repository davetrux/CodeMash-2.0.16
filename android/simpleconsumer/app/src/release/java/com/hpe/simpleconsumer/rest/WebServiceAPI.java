package com.hpe.simpleconsumer.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.hpe.simpleconsumer.Person;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * Created by andrjone on 2/6/2015.
 */
public abstract class WebServiceAPI {
    private static final String SERVICE_ENDPOINT = "http://1.1.1.1/dev/api/names";
    private static PersonApi mInstance = null;

    public static PersonApi getInstance() {
        if (mInstance == null) {
            Gson gson = new Gson();

            Log.d("Service Endpoint", SERVICE_ENDPOINT);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(SERVICE_ENDPOINT)
                    .setConverter(new GsonConverter(gson))
                    .build();

            mInstance = restAdapter.create(PersonApi.class);
        }
        return mInstance;
    }

    public interface PersonApi {
        @GET("/10")
        List<Person> getPersons();
    }
}
