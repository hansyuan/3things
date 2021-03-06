package hansyuan.a3things;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // TODO figure out how to keep track of how many posts i did today
    // TODO How do I use the notification system to display the graphs?


    /**
     * Loads the main screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // New method that calls new activity for the WriteThing

    /**
     * Calls writeThing
     * @param view
     */
    public void startWrite(View view) {
        Intent i = new Intent(this, WriteThing.class);
        startActivity(i);
    }

    /**
     * Opens the activity that displays the prev posts.
     * @param view
     */
    public void readPreviousPosts(View view) {
        Intent i = new Intent(this, ReadPostsView.class);
        startActivity(i);
    }

    /**
     * Opens activity to display stats and data.
     * @param view
     */
    public void dataStats(View view) {
        Intent i = new Intent(this, DataAndStates.class);
        startActivity(i);
    }
}
