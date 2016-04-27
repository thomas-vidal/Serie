package com.thomsvdl.serie.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thomsvdl.serie.R;
import com.thomsvdl.serie.Models.Serie;

import java.util.ArrayList;

public class SerieAdapter extends ArrayAdapter<Serie>{

    public SerieAdapter(Context context) {
        super(context, 0, new ArrayList<Serie>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Serie serie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.serie_item, parent, false);
        }

        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.title);
        TextView itemEpisode = (TextView) convertView.findViewById(R.id.episodePicker);
        TextView itemSeason = (TextView) convertView.findViewById(R.id.seasonPicker);

        itemName.setText(serie.getName());
        itemEpisode.setText(String.valueOf(serie.getEpisode()));
        itemSeason.setText(String.valueOf(serie.getSeason()));

        // Return the completed view to render on screen
        return convertView;
    }
}
