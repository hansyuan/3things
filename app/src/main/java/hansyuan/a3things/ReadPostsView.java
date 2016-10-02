package hansyuan.a3things;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Displays, in order of most recent, the list of posts.
 * Each link will be the date/time, and is a link to
 * open the file and display its contents.
 */

public class ReadPostsView extends AppCompatActivity {
    private final String tableContents = "00_TableOfContents.txt";

    /**
     * The very nice default scrolling activity code that I need to learn.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Normal create
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_posts_view);

        /* TextView words = (TextView)findViewById(R.id.words);
        words.setText(   ); */
        readTableOfContents();
    }

    /**
     * Automatically initiate a toast based on the string parameter.
     * @param toToast
     */
    protected void toasting(String toToast){
        Context context = getApplicationContext();
        CharSequence text = toToast;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    /**
     *
     * @return
     */
    protected String readTableOfContents() {
        String ret = "";
        ArrayList<String> fileList = new ArrayList<String>();

        try {

            // Open the table of contents.
            //inFile = openFileInput(tableContents);
            FileReader fr = new FileReader(
                    new File( this.getFilesDir(), tableContents) );
            BufferedReader br = new BufferedReader(fr);

            // Read as many as possible (probably want to change to BR)
            while (true) {
                String currLine = br.readLine();

                if(currLine != null)
                {
                    ret += currLine + "\n";
                    fileList.add(currLine);
                }

                else
                {
                    break;
                }
            }


        } catch (Exception e) { e.printStackTrace(); }

        //toasting(ret);
        forEachFileCreateButton(fileList);
        return ret;

    }

    /**
     *
     */
    protected void forEachFileCreateButton(ArrayList<String> all)
    {
        /** Iterate through each file in the table of contents
         * and pass file name to next method. */


            /** Read as many as possible (probably want to change to BR) */
            for (String currFile: all)
            {
                toasting( currFile );
                createButton (currFile);
            }
    }

    /**
     * Creates a button for each file that exists.
     */
    protected void createButton(String filename)
    {
        /**
         * From the following website
         * http://stackoverflow.com/questions/14920535/how-to-add-more-than-one-button-to-scrollview-in-android
         */

        // Find the ScrollView
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);


        // Create a LinearLayout element
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Add Buttons
        toasting ("new button");
        Button button = new Button(this);
        button.setText(filename);
        //button.setId(5);                             //TODO
        linearLayout.addView(button);

        /** End copy pasted code */
        return;
    }


}
