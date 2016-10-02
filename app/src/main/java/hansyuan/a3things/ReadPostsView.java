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
    private ArrayList<Button> listButtons = new ArrayList<>();

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
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

            /** Read as many as possible (probably want to change to BR) */
            for ( String curr: all)
            {
                final String currFile = curr;
                Button button = new Button(this);
                button.setText(currFile);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayFile(currFile);
                    }
                });
                linearLayout.addView(button);
                listButtons.add(button);
            }
    }


    /**
     *
     * @param view
     */
    protected void displayFile(String filename)
    {
        String content = "";
        try {

            // Open the table of contents.
            //inFile = openFileInput(tableContents);
            FileReader fr = new FileReader(
                    new File( this.getFilesDir(), filename) );
            BufferedReader br = new BufferedReader(fr);

            // Read as many as possible (probably want to change to BR)
            while (true) {
                String currLine = br.readLine();

                if(currLine != null)
                {
                    content += currLine + "\n";
                }

                else
                {
                    break;
                }
            }


        } catch (Exception e) { e.printStackTrace(); }

        toasting(content);

    }


}
