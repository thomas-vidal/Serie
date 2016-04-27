package com.thomsvdl.serie.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.thomsvdl.serie.R;

public class EditSerieDialog extends DialogFragment{

    public interface SerieDialogListener {
        public void onEditSerieClick(int episode, int season);
    }

    public SerieDialogListener mListener;

    private TextView dialogTitle;
    private NumberPicker episodePicker;
    private NumberPicker seasonPicker;

    private String title;
    private int episodeValue;
    private int seasonValue;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.edit_serie_dialog, null);
        builder.setView(view);

        dialogTitle = (TextView)view.findViewById(R.id.titleDialog);
        episodePicker = (NumberPicker)view.findViewById(R.id.episodePicker);
        seasonPicker = (NumberPicker)view.findViewById(R.id.seasonPicker);

        dialogTitle.setText(title);
        episodePicker.setMinValue(1);
        episodePicker.setMaxValue(500);
        episodePicker.setWrapSelectorWheel(false);
        episodePicker.setValue(episodeValue);
        seasonPicker.setMinValue(1);
        seasonPicker.setMaxValue(500);
        seasonPicker.setWrapSelectorWheel(false);
        seasonPicker.setValue(seasonValue);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mListener.onEditSerieClick(episodePicker.getValue(), seasonPicker.getValue());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditSerieDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEpisodeValue(int episodeValue) {
        this.episodeValue = episodeValue;
    }

    public void setSeasonValue(int seasonValue) {
        this.seasonValue = seasonValue;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (SerieDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }


}
