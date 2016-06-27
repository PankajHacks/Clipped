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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pankaj on 25-06-2016.
 */
public class Clip {
    public String text;
    public long timeStamp;

    public Clip(){

    }

    public Clip(String text, long timeStamp) {
        this.text = text;
        this.timeStamp = timeStamp;
    }


}
