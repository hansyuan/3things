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
    /*
    This method will initiate saving the post into the phone.
    To keep each file unique, it should be in the format:
    YEAR_MONTH_DAY_POSTNUM
     */

    public void toasting(String toToast){
        Context context = getApplicationContext();
        CharSequence text = toToast;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public String getFileName(){
        Calendar cal = Calendar.getInstance();
        String year, month, day, hour, minute, second;

        year = Integer.toString(cal.get(cal.YEAR));
        month = Integer.toString(cal.get(cal.MONTH)+1);
        day = Integer.toString(cal.get(cal.DAY_OF_MONTH));
        hour = Integer.toString(cal.get(cal.HOUR_OF_DAY));
        minute = Integer.toString(cal.get(cal.MINUTE));
        second = Integer.toString(cal.get(cal.SECOND));

        return new String(
                year+"_0"+month+"_"+day+"_"+hour+"_"+minute+"_"+second+".txt");

    }

    /**
     * Initiate saving the post into the phone.
     * Unique format: Year_Month_Day_Hour_Minute_Day
     * Should also update the overall table of contents
     */
    public void savePost(View view){
        toasting("This ran.");
        // Get unique filename.
        String fileName = getFileName();


        // Grab the text from the editview and then save into phone as string.
        EditText textRef = (EditText) findViewById(R.id.textPost);
        String getString = textRef.getText().toString();
        String tableContents = "00_TableOfContents.txt";

        File tableContentsFile = new File(this.getFilesDir(), tableContents);

        File fileNameFile = new File( this.getFilesDir(), fileName );
        FileOutputStream fos;

        // Write the post.try

        try {
            toasting ("Before FW");
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            toasting ("Before println");
            fos.write(getString.getBytes());
            toasting("after println");
            fos.close();
            toasting("Success");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        toasting(getString);

        // Record the time and date into the table of contents.
        try
        {
            FileWriter fw = new FileWriter(tableContentsFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(fileName);
            out.close();
            toasting ("Well, this worked.");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        toasting(fileName);


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


    }
}
