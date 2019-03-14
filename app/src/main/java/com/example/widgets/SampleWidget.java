package com.example.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class SampleWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidget : appWidgetIds) {
            Intent i = new Intent(context, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 20, i, 0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widegt_design);
            remoteViews.setOnClickPendingIntent(R.id.widgets, pi);
            appWidgetManager.updateAppWidget(appWidget, remoteViews);
        }
    }
}