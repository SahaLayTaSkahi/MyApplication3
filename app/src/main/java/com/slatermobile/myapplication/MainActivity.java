package com.slatermobile.myapplication;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Constants.Constants;

public class MainActivity extends AppCompatActivity {
    private static final int SAMPLE_RATE_HZ = 44100;

    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button1);


        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(Constants.Pitches.A4);
            }
        });
    }

    private void playSound(double pitch) {
        // AudioTrack definition
        int mBufferSize = AudioTrack.getMinBufferSize(SAMPLE_RATE_HZ,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_8BIT);

        AudioTrack mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, SAMPLE_RATE_HZ,
                AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                mBufferSize, AudioTrack.MODE_STREAM);

        // Sine wave
        double[] mSound = new double[SAMPLE_RATE_HZ];// 1 second? or half second if stereo buffer...
        short[] mBuffer = new short[SAMPLE_RATE_HZ];

        double factor = 2.0 * Math.PI * pitch/SAMPLE_RATE_HZ;

        float angle = 0;


        for (int i = 0; i < mSound.length; i++) {
            mSound[i] = Math.sin(angle);
            mBuffer[i] = (short) (mSound[i]*Short.MAX_VALUE);
            angle += factor;
        }

        mAudioTrack.setStereoVolume(0.1f, 0.1f);
        mAudioTrack.play();

        mAudioTrack.write(mBuffer, 0, mSound.length);
        mAudioTrack.stop();
        mAudioTrack.release();
    }

/*
    protected Void doInBackground(Void... foo) {
        short[] buffer = new short[1024];
        this.track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize, AudioTrack.MODE_STREAM);
        float increment = (float)(2*Math.PI) * frequency / 44100; // angular increment for each sample
        float angle = 0;
        float samples[] = new float[1024];

        this.track.play();

        while (true) {
            for (int i = 0; i < samples.length; i++) {
                samples[i] = (float) Math.sin(angle);   //the part that makes this a sine wave....
                buffer[i] = (short) (samples[i] * Short.MAX_VALUE);
                angle += increment;
            }
            this.track.write( buffer, 0, samples.length );  //write to the audio buffer.... and start all over again!

        }
    }
*/
}
