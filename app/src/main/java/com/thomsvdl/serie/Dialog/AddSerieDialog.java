package com.thomsvdl.serie.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.thomsvdl.serie.R;

/**
 * Created by Thomas vidal on 26/04/2016.
 */
public class AddSerieDialog extends DialogFragment {

    public interface SerieDialogListener {
        public void onAddSerieClick(String name);
    }

    public SerieDialogListener mListener;

    private TextView dialogTitle;
    private EditText serieEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.add_serie_dialog, null);
        builder.setView(view);

        serieEditText = (EditText)view.findViewById(R.id.editText);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (!serieEditText.getText().toString().isEmpty())
                    mListener.onAddSerieClick(serieEditText.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddSerieDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
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
