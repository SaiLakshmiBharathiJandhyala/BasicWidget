package com.example.widgets;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        editor = preferences.edit();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                /*you can also get the same function with this code
                 * String s=parent.getItemAtPosition(position).toString()*/
                editor.putString(getPackageName(), s);
                /* you can use apply() instead of commit on shared preferences.
                 *as commit blocks and writes its data to persistent storage immediately,
                 *apply will handle it in the background. */
                editor.apply();


                Intent intent = new Intent(MainActivity.this, SampleWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids = AppWidgetManager.getInstance(MainActivity.this).getAppWidgetIds(new ComponentName(getApplicationContext(), SampleWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                sendBroadcast(intent);


            }
        });

    }
}