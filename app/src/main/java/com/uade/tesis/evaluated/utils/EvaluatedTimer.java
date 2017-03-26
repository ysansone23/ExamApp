package com.uade.tesis.evaluated.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.view.MenuItem;
import android.widget.Toast;

import com.uade.tesis.R;

public class EvaluatedTimer extends CountDownTimer {

    private MenuItem timerText;
    private Context context;

    private static final int MINUTES_LEFT = 10;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     * @param timerText         The menu item where the time left will be shown
     * @param context           The application context
     */
    public EvaluatedTimer(final long millisInFuture, final long countDownInterval, final MenuItem timerText,
                          final Context context) {
        super(millisInFuture, countDownInterval);
        this.timerText = timerText;
        this.context = context;
    }

    @Override
    public void onTick(final long millisUntilFinished) {
        int secondsLeft = (int) Math.round((millisUntilFinished / (double) 1000));
        timerText.setTitle(secondsToString(secondsLeft));
    }

    @Override
    public void onFinish() {
        Toast.makeText(context, R.string.end_of_exam, Toast.LENGTH_LONG).show();
        timerText.setTitle("00:00:00");
        //TODO agregar manejo del final del timer
    }

    private String secondsToString(final int improperSeconds) {
        Time secConverter = new Time();

        secConverter.hour = 0;
        secConverter.minute = 0;
        secConverter.second = 0;

        secConverter.second = improperSeconds;
        secConverter.normalize(true);

        showToastIfNeeded(secConverter);

        String hours = String.valueOf(secConverter.hour);
        String minutes = String.valueOf(secConverter.minute);
        String seconds = String.valueOf(secConverter.second);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        return hours + ":" + minutes + ":" + seconds;
    }

    private void showToastIfNeeded(final Time secConverter) {
        //When there are 10 minutes left we will show a toast
        if (secConverter.minute == MINUTES_LEFT && secConverter.second == 0) {
            Toast.makeText(context, R.string.ten_minutes_left, Toast.LENGTH_LONG).show();
        }
    }
}
