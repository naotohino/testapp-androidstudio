package com.example.naoto.testapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ItemDetailActivity extends Activity implements WebViewFragment.OnFragmentInteractionListener {

    private static String EXTRA_ITEM = "item";

    public static Intent getLaunchIntent(Context context, Item item){
        Intent intent = new Intent(context,ItemDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_ITEM,item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        Item item = intent.getParcelableExtra(EXTRA_ITEM);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction t = fm.beginTransaction();
        WebViewFragment fragment = WebViewFragment.newInstance(item);
        t.add(R.id.webview_fragment_container, fragment, "hoge_fragment");
        t.commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
