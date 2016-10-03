package hansyuan.a3things;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;


public class WriteThing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_thing);
    }

    /**
     * Automatically initiate a toast based on the string parameter
     * @param toToast the string you display in a toast
     */
    public void toasting(String toToast){
        Context context = getApplicationContext();
        CharSequence text = toToast;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    /**
     * Initiate saving the post to the phone.
     * Keeping each file unique with the format:
     * YEAR_MONTH_DAY_POSTNUM
     * @return String name to be the file name, always unique
     */
    public String getFileName(){
        Calendar cal = Calendar.getInstance();
        String year, month, day, hour, minute, second;
        int tempMonth, tempDay, tempHour, tempMinute, tempSecond;

        year = Integer.toString(cal.get(cal.YEAR));

        tempMonth = cal.get(cal.MONTH) + 1;
        month = Integer.toString(tempMonth);
        if (tempMonth < 10) month = "0" + month;

        tempDay = cal.get(cal.DAY_OF_MONTH);
        day = Integer.toString(tempDay);
        if (tempDay < 10) day = "0" + day;

        tempHour = cal.get(cal.HOUR_OF_DAY);
        hour = Integer.toString(tempHour);
        if (tempHour < 10) hour = "0" + hour;

        tempMinute = cal.get(cal.MINUTE);
        minute = Integer.toString( tempMinute );
        if (tempMinute < 10) minute = "0"+ minute;

        tempSecond = cal.get(cal.SECOND);
        second = Integer.toString(tempSecond);
        if (tempSecond < 10) second = "0"+ second;




        return (year + "_" + month + "_" + day + "_" + hour
                + "_" + minute + "_" + second + ".txt");

    }

    /**
     * Initiate saving the post into the phone.
     * Unique format: Year_Month_Day_Hour_Minute_Day
     * Should also update the overall table of contents
     */
    public void savePost(View view){
        /** Set to true for toast statements */
        Boolean debug = false;

        if (debug) toasting("This ran.");

        /** Get unique filename. */
        String fileName = getFileName();


        /** Grab the text from the editview and then save into phone as string. */
        EditText textRef = (EditText) findViewById(R.id.textPost);
        String getString = textRef.getText().toString();

        String tableContents = "00_TableOfContents.txt";
        File tableContentsFile = new File(this.getFilesDir(), tableContents);

        File fileNameFile = new File( this.getFilesDir(), fileName );
        FileOutputStream fos;

        /** Write the post.try */

        try {
            if (debug) toasting ("Before FW");
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            if (debug) toasting ("Before println");
            fos.write(getString.getBytes());
            if (debug) toasting("after println");
            fos.close();
            if (debug) toasting("Success");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (debug) toasting(getString);

        // Record the time and date into the table of contents.
        try
        {
            FileWriter fw = new FileWriter(tableContentsFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(fileName);
            out.close();

            if(debug) toasting ("Well, this worked.");

        }
        catch (Exception e) {
            e.printStackTrace();
        }



        /** Reads all available files. */
        if (debug)  toasting(fileName);

        /*
        try {
            toasting("Initate reading");

            // Open the table of contents.
            //inFile = openFileInput(tableContents);
            FileReader fr = new FileReader(new File(this.getFilesDir(), tableContents));
            BufferedReader br = new BufferedReader(fr);

            // Read as many as possible (probably want to change to BR)
            while (true) {
                String s = br.readLine();
                toasting(s);
                if( s == null){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
