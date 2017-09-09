package com.baras.salisoulfood.activities;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.baras.salisoulfood.R;
import com.baras.salisoulfood.tools.Tools;
import com.google.gson.Gson;

public abstract class BaseActivity extends AppCompatActivity {
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setActivityContentView();
        this.setupToolbar();
        this.initializeActivity();
    }

    private void setupToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCB1F")));
        this.gson = new Gson();
    }

    abstract void setActivityContentView();
    abstract void initializeActivity();
    public abstract void _loadWindow();

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return Tools.loadWindowAccordingToLanguageButtonPress(this, getApplicationContext(), item);
    }

    @Override
    public void onResume() {
        super.onResume();
        this._loadWindow();
    }
}
