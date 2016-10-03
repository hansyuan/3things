package hansyuan.a3things;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The very ambitious activity that will read the available
 * posts from the phone to gather data such as:
 *
 * - Avg number of posts per day.
 * - Graph of posts by week or month (maybe use some kind of regression?)
 * - Graph of words per day per week
 */
public class DataAndStates extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_and_states);
    }
}
