package com.ideafactorybd.learnfrench;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Nishad on 5/8/2017.
 */

public class FamilyFragment extends Fragment {

  private MediaPlayer mMediaPlayer;
  private AudioManager mAudioManager;

  private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
      new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
          if (focusChange == AudioManager.AUDIOFOCUS_LOSS ||
              focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
          } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            mMediaPlayer.start();
          } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            releaseMediaPlayer();
          }
        }
      };

  MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mp) {
      releaseMediaPlayer();
    }
  };

  public FamilyFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.word_list, container, false);

    mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

    final ArrayList<Word> words = new ArrayList<>();
    words.add(new Word("Father", "Père", R.raw.fam_father));
    words.add(new Word("Mother", "Mère", R.raw.fam_mother));
    words.add(new Word("Brother", "Frère", R.raw.fam_brother));
    words.add(new Word("Sister", "Sœur", R.raw.fam_sister));
    words.add(new Word("Grand Father", "Grand-père", R.raw.fam_grand_father));
    words.add(new Word("Grand Mother", "Grand-mère", R.raw.fam_grand_mother));
    words.add(new Word("Son", "Fils", R.raw.fam_son));
    words.add(new Word("Daughter", "Fille", R.raw.fam_daughter));
    words.add(new Word("Uncle", "Oncle", R.raw.fam_uncle));
    words.add(new Word("Aunt", "Tante", R.raw.fam_aunt));
    words.add(new Word("Husband", "Mari", R.raw.fam_husband));
    words.add(new Word("Wife", "Femme", R.raw.fam_wife));
    words.add(new Word("Lover", "Amoureux", R.raw.fam_lover));
    words.add(new Word("Father In Law", "Beau-père", R.raw.fam_father_in_law));
    words.add(new Word("Mother In Law", "Belle-mère", R.raw.fam_mother_in_law));
    words.add(new Word("Brother In Law ", "Beau-frère", R.raw.fam_brother_in_law));
    words.add(new Word("Sister In Law", "Belle-soeur", R.raw.fam_sister_in_law));
    words.add(new Word("Son In Law", "Beau fils", R.raw.fam_son_in_law));
    words.add(new Word("Daughter In Law", "Belle-fille", R.raw.fam_daughter_in_law));
    words.add(new Word("Nephew", "Neveu", R.raw.fam_nephew));
    words.add(new Word("Niece", "Nièce", R.raw.fam_niece));
    words.add(new Word("Friend", "Ami", R.raw.fam_friend));

    WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
    ListView listView = (ListView) rootView.findViewById(R.id.list);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Word word = words.get(position);
        releaseMediaPlayer();

        // Request audio focus for playback
        int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
            // Use the music stream.
            AudioManager.STREAM_MUSIC,
            // Request permanent focus.
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
          // Now we have audio focus
          mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
          mMediaPlayer.start();
          mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
      }
    });

    // Enable scrolling in the list view
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      listView.setNestedScrollingEnabled(true);
    }

    return rootView;
  }

  @Override
  public void onStop() {
    super.onStop();
    // When the activity is stopped, release the media player resources because we won't
    // be playing any more sounds.
    releaseMediaPlayer();
  }

  /**
   * Clean up the media player by releasing its resources.
   */
  private void releaseMediaPlayer() {
    // If the media player is not null, then it may be currently playing a sound.
    if (mMediaPlayer != null) {
      // Regardless of the current state of the media player, release its resources
      // because we no longer need it.
      mMediaPlayer.release();

      // Set the media player back to null. For our code, we've decided that
      // setting the media player to null is an easy way to tell that the media player
      // is not configured to play an audio file at the moment.
      mMediaPlayer = null;

      // Abandon audio focus when playback complete
      mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
    }
  }
}
