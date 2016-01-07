package com.hpe.simpleconsumer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    protected ListView mPersonList;
    protected ArrayList<Person> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(loginListener);

        mPersonList = (ListView) findViewById(R.id.results);
    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(DataService.DATA_RESULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(onAuthenticate, filter);
    }

    /*
     * The listener that responds to intents sent back from the service
     */
    protected BroadcastReceiver onAuthenticate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            int serviceResult = intent.getIntExtra("result", -1);

            if (serviceResult == RESULT_OK) {
                String json = intent.getStringExtra("data");

                Gson parser = new Gson();
                mData = parser.fromJson(json, new TypeToken<ArrayList<Person>>(){}.getType());

                BindPersonList(context);
            } else {
                Toast.makeText(context, "Rest call failed.", Toast.LENGTH_SHORT).show();
            }

            Log.d("BroadcastReceiver", "onReceive called");
        }
    };

    /*
     * Helper method to put the list of persons into the ListView
     */
    protected void BindPersonList(Context context) {
        PersonAdapter adapter = new PersonAdapter(context, mData);
        mPersonList.setAdapter(adapter);

        TextView resultsLabel = (TextView) findViewById(R.id.resultsLabel);
        resultsLabel.setText(mData.size() + " results.");
    }

    private View.OnClickListener loginListener = new View.OnClickListener(){
        public void onClick(View v){

            //Send credentials via intent
            Intent intent = new Intent(MainActivity.this, DataService.class);
            startService(intent);
        }
    };
}
