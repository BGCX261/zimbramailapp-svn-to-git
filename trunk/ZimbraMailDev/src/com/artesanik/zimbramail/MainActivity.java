package com.artesanik.zimbramail;

import java.util.List;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String EXTRA_MESSAGE = "com.artesanik.zimbramail.MESSAGE";
    TextView textView; // Member variable for text view in the layout
    
    @TargetApi(14)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DatabaseHandler db = new DatabaseHandler(this);
        
     // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addAccount(new MailAccount("ISM DO", "joenilson@kolareal.com.do", "algo1", "null", "mail.kolareal.com.do", "si", 1, "enabled", "null"));
        db.addAccount(new MailAccount("ISM PE", "joenilson@kolareal.com.pe", "algo2", null, "mail.kolareal.com.do", "no", 2, "enabled", null));
        db.addAccount(new MailAccount("ISM BR", "joenilson@kolareal.com.br", "algo3", null, "mail.kolareal.com.do", "no", 3, "enabled", null));
        db.addAccount(new MailAccount("ISM CO", "joenilson@kolareal.com.co", "algo4", null, "mail.kolareal.com.do", "no", 4, "enabled", null));
        
        
     // Reading all contacts
        Log.d("Reading: ", "Reading all accounts..");
        List<MailAccount> accounts = db.getAllAccounts();       
 
        for (MailAccount cn : accounts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getDescription() + " ,Email: " + cn.getAccount(); 
                // Writing Contacts to log
            Log.d("Name: ", log);
        }
        
        /*
        Button btnPrefs = (Button) findViewById(R.id.button_editprefs);
        Button btnShowPrefs = (Button) findViewById(R.id.button_showprefs);
        Button btnShowPanel = (Button) findViewById(R.id.button_showpanel);
        */
        SharedPreferences prefs = PreferenceManager
          	    .getDefaultSharedPreferences(MainActivity.this);
        
        String user = prefs.getString("login_un", "DEFAULT");
        String pass = prefs.getString("login_pw", "DEFAULT");
        
        if(user.toString().trim().length()!=0){
        	if(pass.toString().trim().length()!=0){
                textView = (TextView) findViewById(R.id.txtPrefs);
                displaySharedPreferences();
        	}else{
        		Intent intent = new Intent(MainActivity.this,
				SettingsActivity.class);
				startActivity(intent);
        	}
        }else{
        	Intent intent = new Intent(MainActivity.this,
			SettingsActivity.class);
			startActivity(intent);
        }
       
        /*
        View.OnClickListener listener = new View.OnClickListener() {
        
    	   public void onClick(View v) {
        	   switch (v.getId()) {
	        	   case R.id.button_editprefs:
	        	      Intent intent = new Intent(MainActivity.this,
	        	      SettingsActivity.class);
	        	      startActivity(intent);
	        	      break;
	
	        	   case R.id.button_showprefs:
	        	      displaySharedPreferences();
	        	      break;

	        	   case R.id.button_showpanel:
		        	      displayPanel();
		        	      break;
		
	        	   default:
	        	     break;
        	   }
    	   }
    	   
	   };
	   */
	   
       /* 	   
	   btnPrefs.setOnClickListener(listener);
	   btnShowPrefs.setOnClickListener(listener);
	   btnShowPanel.setOnClickListener(listener);
       */ 
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // For the main activity, make sure the app icon in the action bar
            // does not behave as a button
            ActionBar actionBar = getActionBar();
            actionBar.setHomeButtonEnabled(false);
        }
        
    }

    private void displaySharedPreferences() {
  	   SharedPreferences preferences = PreferenceManager
  	    .getDefaultSharedPreferences(MainActivity.this);

  	 String account = preferences.getString("login_id", "Default Account ID");
  	   String username = preferences.getString("login_un", "Default NickName");
  	   String passw = preferences.getString("login_pw", "Default Password");
  	   String servername = preferences.getString("login_sn", "Server Name");

  	   StringBuilder builder = new StringBuilder();
 	   builder.append(this.getString(R.string.login_account_text) + ": " + account + "\n");
  	   builder.append(this.getString(R.string.login_user_text) + ": " + username + "\n");
  	   builder.append(this.getString(R.string.login_pass_text) + ": " + passw + "\n");
  	   builder.append(this.getString(R.string.login_server_text) + ": " + servername + "\n");

  	   textView.setText(builder.toString());
  	}
    /*
    private void displayPanel() {
    	Intent intent = new Intent(MainActivity.this, 
				panelListActivity.class);
				startActivity(intent);
    }
    */ 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
            	Intent intent = new Intent(MainActivity.this,
				SettingsActivity.class);
				startActivity(intent);
                return true;
            case R.id.menu_exit:
            	System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    /** Called when the user clicks the Login button */
    public void showAlert(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	//EditText editText = (EditText) findViewById(R.id.txtPrefs);
    	//String message = editText.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void showPreferences(){
    	
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass
        
        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }
    /*
    public static class ArrayListFragment extends ListFragment  {

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, Shakespeare.TITLES));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }
    */
}
