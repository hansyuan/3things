package hansyuan.a3things;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

/**
 * Displays, in order of most recent, the list of posts.
 * Each link will be the date/time, and is a link to
 * open the file and display its contents.
 */

public class ReadPostsView extends AppCompatActivity {

    /**
     * The very nice default scrolling activity code that I need to learn.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_posts_view);

        TextView words = (TextView)findViewById(R.id.words);
        words.setText( readTableOfContents() );
    }

    public String readTableOfContents() {
        String tableContents = "00_TableOfContents.txt";
        FileInputStream inFile;
        String ret = "";

        try {

            // Open the table of contents.
            //inFile = openFileInput(tableContents);
            FileReader fr = new FileReader(new File( this.getFilesDir(), tableContents) );
            BufferedReader br = new BufferedReader(fr);

            // Read as many as possible (probably want to change to BR)
            while (true) {
                String s = br.readLine();
                if(s != null){
                    ret += s+"\n";
                }
                else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Context context = getApplicationContext();
        CharSequence text = ret;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return ret;

    }
}
