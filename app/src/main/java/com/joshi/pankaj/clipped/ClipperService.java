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

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joshi.pankaj.clipped.dataModel.Clip;

public class ClipperService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {
    private ClipboardManager mClipboardManager;
    private static String lastString ="";

    public ClipperService() {
    }

    public static void setLastString(String text){
        lastString= text;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(ClipperService.this, "Clipper service started", Toast.LENGTH_SHORT).show();
        mClipboardManager= (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
        mClipboardManager.addPrimaryClipChangedListener(this);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(ClipperService.this, "Clipper service stopped", Toast.LENGTH_SHORT).show();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrimaryClipChanged() {

        ClipData.Item clipDataItem = mClipboardManager.getPrimaryClip().getItemAt(0);
        String text = (String) clipDataItem.coerceToText(getApplicationContext());

        if(!text.equals(lastString)&&!text.equals("")) {
            FirebaseDatabase.getInstance().getReference().child("clips")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .push().setValue(new Clip(text, System.currentTimeMillis()));
            Toast.makeText(ClipperService.this, "Added to Clipped", Toast.LENGTH_SHORT).show();
            lastString =text;
        }

    }
}
