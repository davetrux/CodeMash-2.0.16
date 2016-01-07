package com.hpe.simpleconsumer.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.hpe.simpleconsumer.Person;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * @author andrjone
 * 2/6/2015.
 */
public abstract class WebServiceAPI {

    private static PersonApi mInstance = null;

    public static PersonApi getInstance() {
        if (mInstance == null) {
            Gson gson = new Gson();

            Log.d("Service Endpoint", Constants.SERVICE_URL);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.SERVICE_URL)
                    .setConverter(new GsonConverter(gson))
                    .build();

            mInstance = restAdapter.create(PersonApi.class);
        }
        return mInstance;
    }

    public interface PersonApi {
        @GET(Constants.SERVICE_RECORDS)
        List<Person> getPersons();
    }
}
