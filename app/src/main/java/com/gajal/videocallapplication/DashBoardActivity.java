package com.gajal.videocallapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.URL;

public class DashBoardActivity extends AppCompatActivity {
EditText secretcode;
Button join,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        secretcode=findViewById(R.id.secretcode);
        join=findViewById(R.id.join);
        share=findViewById(R.id.share);

        URL serverURL;

        try {

                serverURL=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);
            }

        catch (Exception e){
            e.printStackTrace();
        }
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions options = (new JitsiMeetConferenceOptions.Builder()).
                   setRoom(secretcode.getText().toString()).setAudioMuted(false)
                        .setVideoMuted(false)
                        .setAudioOnly(false)
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(DashBoardActivity.this,options);
            }
        });
    }
}