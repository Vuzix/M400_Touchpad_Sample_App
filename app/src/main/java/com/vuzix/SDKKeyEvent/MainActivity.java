/*
Copyright (c) 2017, Vuzix Corporation
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

*  Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.

*  Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.

*  Neither the name of Vuzix Corporation nor the names of
   its contributors may be used to endorse or promote products derived
   from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.vuzix.SDKKeyEvent;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.TextView;

import com.vuzix.hud.resources.Utils;

/**
 * Main activity for touchpad sample
 */
public class MainActivity extends Activity {

    private TextView mTextView;

    /**
     * When created we setup the layout and the touchpad recognition
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=(TextView)findViewById(R.id.outputTextView);

    }

    /**
     *Utility to create an input event in the corresponding font color to the designated action.
     */
    private void addText(String text,int i) {
        String styledText;
        switch(i){
            case 1:

                 //Case 1 demonstrates functions for the devices buttons.
                 //KEYCODE_DPAD_CENTER, KEYCODE_DPAD_LEFT, KEYCODE_DPAD_RIGHT

                styledText= "<font color='red'>"+text+"</font><br>";
                break;
            case 2:

                 //Case 2 demonstrates functions for tapping and two finger swiping on the touchpad.
                 //KEYCODE_DPAD_CENTER, KEYCODE_BACK, KEYCODE_VOLUME_DOWN,
                 //KEYCODE_VOLUME_UP, KEYCODE_DEL, KEYCODE_FORWARD_DEL

                styledText= "<font color='blue'>"+text+"</font><br>";
                break;
            case 3:

                 //Case 3 demonstrates functions for one finger swipe feature on the touchpad.
                 //KEYCODE_DPAD_LEFT, KEYCODE_DPAD_RIGHT, KEYCODE_DPAD_UP, KEYCODE_DPAD_DOWN

                styledText= "<font color='green'>"+text+"</font><br>";
                break;
            default:
                styledText= "<font color='black'>"+text+"</font><br>";
                break;
        }

        mTextView.append(Html.fromHtml(styledText+"\r\n"));
        while (mTextView.canScrollVertically(1)) {
            mTextView.scrollBy(0, 10);
        }

    }

    /**
     *Displays the input event created.
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(Utils.isInputDeviceButtons(event)){
            addText("Input Event from Buttons: "+event+" Device: "+event.getDevice().getName(),1);
        }else if(Utils.isInputDeviceTouchpad(event)){
            addText("Input Event from Touchpad: "+event+" Device: "+event.getDevice().getName(),2);
        }else{
            addText("Input Event from double secret source: "+event+" Device: "+event.getDevice().getName(),3);
        }

        return super.dispatchKeyEvent(event);
    }
}