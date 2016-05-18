package com.thomsvdl.serie.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thomsvdl.serie.R;
import com.thomsvdl.serie.Models.Serie;

import java.util.ArrayList;
import java.util.List;

public class SerieAdapter extends ArrayAdapter<Serie>{

    public SerieAdapter(Context context) {
        super(context, 0, new ArrayList<Serie>());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Serie serie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.serie_item, parent, false);
        }

        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.title);
        TextView itemEpisode = (TextView) convertView.findViewById(R.id.episodePicker);
        TextView itemSeason = (TextView) convertView.findViewById(R.id.seasonPicker);

        TextView button =(TextView) convertView.findViewById(R.id.addEpisodeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOneEpisode(position);
            }
        });

        itemName.setText(serie.getName());
        itemEpisode.setText(String.valueOf(serie.getEpisode()));
        itemSeason.setText(String.valueOf(serie.getSeason()));

        // Return the completed view to render on screen
        return convertView;
    }

    public void addOneEpisode(int position) {

        this.getItem(position).setEpisode(getItem(position).getEpisode() + 1);
        this.notifyDataSetChanged();
    }

    public List<Serie> getList(){
        ArrayList<Serie> list = new ArrayList<>();
        for (int i = 0; i < this.getCount(); i++) {
            list.add(getItem(i));
        }
        return list;
    }
}
