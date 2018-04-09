package com.supralabs.cahier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.supralabs.cahier.Messaging.Chat;

/**
 * Created by SONY on 18/03/2018.
 */

public class ScreenPrincipal extends AppCompatActivity
        implements EcoAdapter.ListItemClickListener{

    private static final int NUM_LIST_ITEMS = 100;

    private EcoAdapter mAdapter;
    private RecyclerView mMsjMemTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_screen);

        mMsjMemTextView = (RecyclerView) findViewById(R.id.rv_contacts);
/*
        String[] mChats = Chat.getMensajes();
        for (String mChat : mChats){
            mMsjMemTextView.append(mChat+ "\n\n\n");
        }*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMsjMemTextView.setLayoutManager(layoutManager);

        mMsjMemTextView.setHasFixedSize(true);

        mAdapter = new EcoAdapter(NUM_LIST_ITEMS, this);
        mMsjMemTextView.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //dsfsd

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.chat);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Context context = ScreenPrincipal.this;
                Class destinationActivity = Chat.class;
                Intent startChildActivity = new Intent(context, destinationActivity);
                startActivity(startChildActivity);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemInd