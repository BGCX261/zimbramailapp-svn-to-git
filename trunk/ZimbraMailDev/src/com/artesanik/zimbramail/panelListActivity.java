package com.artesanik.zimbramail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class panelListActivity extends FragmentActivity
        implements panelListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.panel_detail_container) != null) {
            mTwoPane = true;
            ((panelListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.panel_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	startActivityAfterCleanup(MainActivity.class);
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
    private void startActivityAfterCleanup(Class<?> cls) {
    //if (projectsDao != null) projectsDao.close();
      Intent intent = new Intent(getApplicationContext(), cls);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
    }
    
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(panelDetailFragment.ARG_ITEM_ID, id);
            panelDetailFragment fragment = new panelDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.panel_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, panelDetailActivity.class);
            detailIntent.putExtra(panelDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
