package edu.ptu.instancemsg;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import edu.ptu.instancemsg.smack.SmackUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Object,Object,Object>(){

            @Override
            protected Object doInBackground(Object... params) {
                new SmackUtils().connect();
                return null;
            }
        }.execute();
    }


}
