package sjsu.aparajitamitra.th_sensor_driver_using_asynctask;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
*/


    @Override
    public void onCreate(Bundle savedInstanceState) {

        final int[] counter = new int[1];


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGenerate = (Button) findViewById(R.id.btnGenerate);

        btnGenerate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                TextView textViewOutput = (TextView) findViewById(R.id.tvOutput);
                textViewOutput.setText(" ");
                counter[0] = 1;
                EditText etSensorReading = (EditText) findViewById(R.id.etCount) ;
                int iCount = Integer.valueOf(etSensorReading.getText().toString());

                if(iCount<=0) {

                        textViewOutput.setText(" Number of Sensor Reading should be greater than 0");
                        textViewOutput.setTextColor(Color.BLACK);

                }
                while(iCount>0) {

                    MyAsyncTask asyncTask = new MyAsyncTask(new MyAsyncTask.AsyncResponse() {

                        @Override
                        public void processFinish(Object output) {
                            //Log.d("Response:", (String) output);



                            TextView textViewOutput = (TextView) findViewById(R.id.tvOutput);
                            textViewOutput.setTextColor(Color.WHITE);
                            EditText editText;

                            List<Integer> listOutput = (List) output;

                            int iTemp = listOutput.get(0);
                            editText = (EditText) findViewById(R.id.etTemp);
                            editText.setText("\t "+new String(String.valueOf(iTemp) + " F"));
                            int iHumidity = listOutput.get(1);
                            editText = (EditText) findViewById(R.id.etHumidity);
                            editText.setText("\t "+new String(String.valueOf(iHumidity + "%")));
                            int iActivity = listOutput.get(2);
                            editText = (EditText) findViewById(R.id.etActivity);
                            editText.setText("\t "+new String(String.valueOf(iActivity)));

                            String strValues = "\n" + "Output "+String.valueOf(counter[0])+" : \n";
                            strValues = strValues + "Temperature : " + String.valueOf(iTemp) + " F\n";
                            strValues = strValues + "Humidity : " + String.valueOf(iHumidity) + "%\n";
                            strValues = strValues + "Activity : " + String.valueOf(iActivity);

                            if(counter[0]!=1) strValues = textViewOutput.getText() + strValues;

                            textViewOutput.setText(strValues);
                            counter[0]++;
                        }
                    });
                    asyncTask.execute(new Object[]{"Async task"});
                    iCount--;
                }
            }
        });

        // Exit Application
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText ;

                editText = (EditText) findViewById(R.id.etTemp);
                editText.setText("0");
                editText = (EditText) findViewById(R.id.etActivity);
                editText.setText("0");
                editText = (EditText) findViewById(R.id.etHumidity);
                editText.setText("0");
                editText = (EditText) findViewById(R.id.etCount);
                editText.setText("0");
                TextView tvOutput = (TextView) findViewById(R.id.tvOutput);
                tvOutput.setText("0");

            }
        });

    }
}
