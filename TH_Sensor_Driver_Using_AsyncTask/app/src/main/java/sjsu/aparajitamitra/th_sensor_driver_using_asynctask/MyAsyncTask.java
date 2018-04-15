package sjsu.aparajitamitra.th_sensor_driver_using_asynctask;

import android.os.AsyncTask;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by pbiswas on 3/18/17.
 */


public class MyAsyncTask extends AsyncTask<Object, Object, Object> {

    public interface AsyncResponse {
        void processFinish(Object output);
    }

    public AsyncResponse delegate = null;//Call back interface

    public MyAsyncTask(AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        //Assigning call back interface with constructor
    }



    @Override
    protected Object doInBackground(Object... objects) {


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> list = new ArrayList<>();

        Random rand = new Random();

        int  iTemp = rand.nextInt(100) + 1;
        int  iHumidity = rand.nextInt(100) + 1;
        int iActivity = rand.nextInt(1000)+1;



        list.add(iTemp);
        list.add(iHumidity);
        list.add(iActivity);

        return list;
    }

    @Override
    protected void onPostExecute(Object result) {
        delegate.processFinish(result);
    }


}

