package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;

public class WordAdaptor extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdaptor(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
                mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwoktv = (TextView) listItemView.findViewById(R.id.lvtv1);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwoktv.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaulttv = (TextView) listItemView.findViewById(R.id.lvtv2);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaulttv.setText(currentWord.getDefaultTranslation());

        ImageView imgv=(ImageView)listItemView.findViewById(R.id.lviv1);
        if (currentWord.hasImage()) {
                        // If an image is available, display the provided image based on the resource ID
                        imgv.setImageResource(currentWord.getImageResourceId());
                        // Make sure the view is visible
                        imgv.setVisibility(View.VISIBLE);
                    } else {
                        // Otherwise hide the ImageView (set visibility to GONE)
                        imgv.setVisibility(View.GONE);
                    }

        View textContainer = listItemView.findViewById(R.id.Llcolor);
                // Find the color that the resource ID maps to
                       int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);
        // Find the ImageView in the list_item.xml layout with the ID list_item_icon

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
