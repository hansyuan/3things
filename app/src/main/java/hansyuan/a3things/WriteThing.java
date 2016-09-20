package hansyuan.a3things;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.FileOutputStream;
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
    public void savePost(){
        // Grab the text from the editview and then save into phone as string.
        EditText textRef = (EditText) findViewById(R.id.textPost);
        String getString = textRef.getText().toString();

        FileOutputStream out;

        // Get unique filename.
        Calendar cal = Calendar.getInstance();
        String year, month, day, hour, minute, second;

        year = Integer.toString(cal.get(cal.YEAR));
        month = Integer.toString(cal.get(cal.MONTH)+1);
        day = Integer.toString(cal.get(cal.DAY_OF_MONTH));
        hour = Integer.toString(cal.get(cal.HOUR_OF_DAY));
        minute = Integer.toString(cal.get(cal.MINUTE));
        second = Integer.toString(cal.get(cal.SECOND));

        String fileName = new String(year+"_0"+month+"_"+day+"_"+hour+"_"+minute+"_"+second+".txt");

        try {
            out = openFileOutput(fileName, Context.MODE_PRIVATE);
            out.write(getString.getBytes());
            out.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
