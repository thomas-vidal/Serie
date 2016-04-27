package com.thomsvdl.serie.Filter;


import android.widget.Filter;

import com.thomsvdl.serie.Models.Serie;

import java.util.ArrayList;

public class SerieFilter<T> extends Filter {

    @Override
    protected Filter.FilterResults performFiltering(CharSequence chars) {
        chars = chars.toString().toLowerCase();
        FilterResults result = new FilterResults();

        return result;
    }

    //    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

    }




}
