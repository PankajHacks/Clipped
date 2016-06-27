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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {


    private final String LOG_TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(this,SignInActivity.class));
            finish();
        }else{
            startService(new Intent(getBaseContext(),ClipperService.class));
        }
        setContentView(R.layout.activity_main);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_sign_out:
                stopService(new Intent(getBaseContext(),ClipperService.class));
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(this,SignInActivity.class));
                finish();
                return true;
            case R.id.action_clear_bucket:
                FirebaseDatabase.getInstance().getReference().child("clips")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getRef().removeValue();

                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }


}
