package com.joshi.pankaj.clipped.dataModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.joshi.pankaj.clipped.R;

/**
 * Created by Pankaj on 25-06-2016.
 */
public class ClipViewHolder extends RecyclerView.ViewHolder {
    public TextView clippedTextView;
    public TextView timeStampTextView;
    public ImageButton deleteButton;
    public ImageButton shareButton;
    public ClipViewHolder(View itemView) {
        super(itemView);
        clippedTextView = (TextView) itemView.findViewById(R.id.clipped_text_textview);
        timeStampTextView = (TextView) itemView.findViewById(R.id.time_stamp_textview);
        deleteButton = (ImageButton) itemView.findViewById(R.id.clip_delete_button);
        shareButton = (ImageButton) itemView.findViewById(R.id.clip_share_button);
    }
}
