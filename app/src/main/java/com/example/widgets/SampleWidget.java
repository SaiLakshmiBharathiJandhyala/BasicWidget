package com.example.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import static android.content.Context.MODE_PRIVATE;

public class SampleWidget extends AppWidgetProvider {

    SharedPreferences preferences;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidget : appWidgetIds) {
            preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
            Intent i = new Intent(context, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 20, i, 0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widegt_design);
            remoteViews.setOnClickPendingIntent(R.id.list, pi);
            remoteViews.setTextViewText(R.id.tv, preferences.getString(context.getPackageName(), "no item selected"));
            appWidgetManager.updateAppWidget(appWidget, remoteViews);

        }
    }
}