package com.thomsvdl.serie;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thomsvdl.serie.ArrayAdapter.SerieAdapter;
import com.thomsvdl.serie.Dialog.AddSerieDialog;
import com.thomsvdl.serie.Dialog.EditSerieDialog;
import com.thomsvdl.serie.Models.Serie;
import com.thomsvdl.serie.Serialization.SerieSerialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EditSerieDialog.SerieDialogListener, AddSerieDialog.SerieDialogListener {

    public static SerieAdapter serieAdapter;
    public int currenSeriePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        serieAdapter = new SerieAdapter(this);
        ListView listView = (ListView) findViewById(R.id.serie_list_view);
        listView.setAdapter(serieAdapter);

        try {
            FileInputStream fileInputStream = openFileInput("Serie.xml");

            List<Serie> series = new SerieSerialization<Serie>().Deserialize(fileInputStream);
            if (series != null){
                serieAdapter.addAll(series);
            }

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editSerieDialog(position);
                currenSeriePosition = (int) id;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Vibrator v = (Vibrator) getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
                v.vibrate(200);
                deleteDialog(position);
                currenSeriePosition = (int) id;
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSerieDialog();
            }
        });
    }

    public void addSerieDialog() {
        FragmentManager manager = getFragmentManager();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        AddSerieDialog addSerieDialog = new AddSerieDialog();

        addSerieDialog.show(manager, "addSerieDialog");
    }

    public void deleteDialog(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setMessage("Do you want delete " + serieAdapter.getItem(position).getName())
                .setTitle(serieAdapter.getItem(position).getName())
                .setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                serieAdapter.remove(serieAdapter.getItem(position));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void editSerieDialog(int position) {
        FragmentManager manager = getFragmentManager();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        EditSerieDialog serieDialog = new EditSerieDialog();

        Serie currentSerie = serieAdapter.getItem(currenSeriePosition);

        serieDialog.setTitle(currentSerie.getName());
        serieDialog.setEpisodeValue(currentSerie.getEpisode());
        serieDialog.setSeasonValue(currentSerie.getSeason());

        serieDialog.show(manager, "editSerieDialog");
    }

    @Override
    public void onEditSerieClick(int episode, int season) {
        serieAdapter.getItem(currenSeriePosition).setEpisode(episode);
        serieAdapter.getItem(currenSeriePosition).setSeason(season);
        serieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddSerieClick(String name) {
        serieAdapter.add(new Serie(name));
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            FileOutputStream fileOutputStream = openFileOutput("Serie.xml", Context.MODE_PRIVATE);
            new SerieSerialization<Serie>().Serialize(fileOutputStream, serieAdapter.getList());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
