package com.ideafactorybd.learnfrench;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Nishad on 5/8/2017.
 */

public class AnimalsFragment extends Fragment {

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

  public AnimalsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.word_list, container, false);

    mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

    final ArrayList<Word> words = new ArrayList<>();
    words.add(new Word("Ant", "Fourmi", R.raw.ani_ant));
    words.add(new Word("Bat", "Chauve Souris", R.raw.ani_bat));
    words.add(new Word("Bear", "Ours", R.raw.ani_bear));
    words.add(new Word("Bee", "Abeille", R.raw.ani_bee));
    words.add(new Word("Buffalo", "Buffle", R.raw.ani_buffalo));
    words.add(new Word("Butterfly", "Papillon", R.raw.ani_butterfly));
    words.add(new Word("Camel", "Chameau", R.raw.ani_camel));
    words.add(new Word("Cat", "Chat", R.raw.ani_cat));
    words.add(new Word("Cock", "Coq", R.raw.ani_cock));
    words.add(new Word("Cockroach", "Cafard", R.raw.ani_cockroach));
    words.add(new Word("Crab", "Crabe", R.raw.ani_crab));
    words.add(new Word("Crocodile", "Crocodile", R.raw.ani_crocodile));
    words.add(new Word("Crow", "Corbeau", R.raw.ani_crow));
    words.add(new Word("Deer", "Cerf", R.raw.ani_deer));
    words.add(new Word("Dog", "Chien", R.raw.ani_dog));
    words.add(new Word("Donkey", "Âne", R.raw.ani_donkey));
    words.add(new Word("Duck", "Canard", R.raw.ani_duck));
    words.add(new Word("Elephant", "Éléphant", R.raw.ani_elephant));
    words.add(new Word("Fox", "Renard", R.raw.ani_fox));
    words.add(new Word("Frog", "Grenouille", R.raw.ani_frog));
    words.add(new Word("Giraffe", "Girafe", R.raw.ani_giraffe));
    words.add(new Word("Goat", "Chèvre", R.raw.ani_goat));
    words.add(new Word("Hen", "Poule", R.raw.ani_hen));
    words.add(new Word("Horse", "Cheval", R.raw.ani_horse));
    words.add(new Word("Lion", "Lion", R.raw.ani_lion));
    words.add(new Word("Monkey", "Singe", R.raw.ani_monkey));
    words.add(new Word("Mosquito", "Moustique", R.raw.ani_mosquito));
    words.add(new Word("Owl", "Hibou", R.raw.ani_owl));
    words.add(new Word("Peacock", "Paon", R.raw.ani_peacock));
    words.add(new Word("Pigeon", "Pigeon", R.raw.ani_pigeon));
    words.add(new Word("Python", "Python", R.raw.ani_python));
    words.add(new Word("Rabbit", "Lapin", R.raw.ani_rabbit));
    words.add(new Word("Rat", "Rat", R.raw.ani_rat));
    words.add(new Word("Ram", "Bélier", R.raw.ani_ram));
    words.add(new Word("Snake", "Serpent", R.raw.ani_snake));
    words.add(new Word("Tiger", "Tigre", R.raw.ani_tiger));
    words.add(new Word("Tortoise", "Tortue", R.raw.ani_tortoise));
    words.add(new Word("Wolf", "Loup", R.raw.ani_wolf));
    words.add(new Word("Zebra", "Zèbre", R.raw.ani_zebra));

    WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_animals);
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
