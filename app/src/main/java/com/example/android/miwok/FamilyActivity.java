/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mmediaplayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();

        }
    };
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
                       if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                                       focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                                            mmediaplayer.pause();
                                mmediaplayer.seekTo(0);
                            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                                        mmediaplayer.start();
                            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                                        // Stop playback and clean up resources
                                                releaseMediaPlayer();
                           }
                  }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
                words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
                words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
                words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
                words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
                words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
                words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
                words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
                words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister));
                words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother,R.raw.family_grandmother));
                words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,R.raw.family_grandfather));



        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdaptor itemsAdapter =
                new WordAdaptor(this, words,R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word=words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mmediaplayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioid());

                                                // Start the audio file
                                                        mmediaplayer.start();

                                                // Setup a listener on the media player, so that we can stop and release the
                                                        // media player once the sound has finished playing.
                                                mmediaplayer.setOnCompletionListener(mCompletionListener);
                                    }
            }
        });
    }
    @Override
    protected void onStop() {
                super.onStop();
                // When the activity is stopped, release the media player resources because we won't
                        // be playing any more sounds.
                                releaseMediaPlayer();
            }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mmediaplayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mmediaplayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mmediaplayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
