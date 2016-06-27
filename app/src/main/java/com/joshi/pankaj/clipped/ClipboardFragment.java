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

package com.joshi.pankaj.clipped;

import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.joshi.pankaj.clipped.dataModel.Clip;
import com.joshi.pankaj.clipped.dataModel.ClipViewHolder;


/**
 * Created by Pankaj on 19-06-2016.
 */
public class ClipboardFragment extends Fragment {


    private EditText editText;
    private Button button;

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ClipboardManager mClipboardManger;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private FirebaseRecyclerAdapter<Clip,ClipViewHolder> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.clipboard_fragment_layout,container,false);
        button = (Button) rootView.findViewById(R.id.button);
        editText = (EditText) rootView.findViewById(R.id.file_contents_edit_text);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.text_recycler_view);


        return rootView;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mClipboardManger = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        layoutManager = new LinearLayoutManager(getActivity());


        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(false);



        Query mQuery = mDatabase.child("clips").child(user.getUid()).orderByKey();

        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new FirebaseRecyclerAdapter<Clip, ClipViewHolder>(Clip.class,R.layout.text_list_item,
                ClipViewHolder.class,mQuery) {
            @Override
            protected void populateViewHolder(final ClipViewHolder viewHolder, Clip model, final int position) {


                viewHolder.clippedTextView.setText(model.text);
                viewHolder.timeStampTextView.setText(Utils.getFormattedTimeStamp(model.timeStamp));
                viewHolder.clippedTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text =viewHolder.clippedTextView.getText().toString();
                        ClipData clip = ClipData.newPlainText(ClipDescription.MIMETYPE_TEXT_PLAIN,text);
                        ClipperService.setLastString(text);
                        mClipboardManger.setPrimaryClip(clip);
                        Toast.makeText( getActivity(), "\""+viewHolder.clippedTextView.getText()+"\" copied to clipboard", Toast.LENGTH_SHORT).show();

                    }
                });
                viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position<getItemCount())  // this check is required if recycler view item
                            // positions haven't  been updated from last delete removing this could lead to app crashing when item delete*/
                            getRef(position).removeValue();
                        else{
                            getRef(getItemCount()-1).removeValue();
                        }
                    }
                });
                viewHolder.shareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                        String shareBody = viewHolder.clippedTextView.getText().toString();
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clipText = editText.getText().toString();
                editText.getText().clear();
                Clip clip = new Clip(clipText, System.currentTimeMillis());
                mDatabase.child("clips").child(user.getUid()).push().setValue(clip);


            }
        });

    }
}
