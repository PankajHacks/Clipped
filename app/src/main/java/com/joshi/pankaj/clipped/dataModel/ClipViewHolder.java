/*
 * Copyright 2016 Pankaj Joshi (pankaj.joshi95@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
