package com.example.jisuanqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.action_add:
			Toast.makeText(this, "÷Ì÷Æ∏Ë", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_history:
			startActivity(new Intent(this,History.class));
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
}
