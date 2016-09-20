package hansyuan.a3things;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//TODO figure out how to keep track of how many posts i did today


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // New method that calls new activity for the WriteThing
    public void startWrite(View view){
        Intent i = new Intent(this,WriteThing.class);
        startActivity(i);
    }
}
