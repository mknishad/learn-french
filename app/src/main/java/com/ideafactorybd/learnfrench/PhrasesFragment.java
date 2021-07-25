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

public class PhrasesFragment extends Fragment {

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

  public PhrasesFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.word_list, container, false);

    mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

    final ArrayList<Word> words = new ArrayList<>();
    words.add(new Word("Hello", "Bonjour", R.raw.phr_hello));
    words.add(new Word("What is your name?", "Comment vous appelez-vous?", R.raw.phr_what_is_your_name));
    words.add(new Word("My name is Hasan.", "Je m'appelle Hasan.", R.raw.phr_my_name_is_hasan));
    words.add(new Word("How are you?", "Comment allez-vous?", R.raw.phr_how_are_you));
    words.add(new Word("I am fine.", "Je vais bien", R.raw.phr_i_am_fine));
    words.add(new Word("What time is it?", "Quelle heure est-il?", R.raw.phr_what_time_is_it));
    words.add(new Word("It is ten o’clock", "Il est dix heures.", R.raw.phr_it_is_ten_oclock));
    words.add(new Word("Where are you from?", "D'où êtes-vous?", R.raw.phr_where_are_you_from));
    words.add(new Word("I am from Dhaka.", "Je viens de Dhaka.", R.raw.phr_i_am_from_dhaka));
    words.add(new Word("Where are you going?", "Où allez-vous?", R.raw.phr_where_are_you_going));
    words.add(new Word("I am going to Paris.", "Je vais a paris.", R.raw.phr_i_am_going_to_paris));
    words.add(new Word("Where do you live?", "Où habite tu?", R.raw.phr_where_do_you_live));
    words.add(new Word("I live in Dhaka.", "J'habite à Dhaka.", R.raw.phr_i_live_in_dhaka));
    words.add(new Word("Come here.", "Venez ici.", R.raw.phr_come_here));
    words.add(new Word("Go there.", "Va là-bas.", R.raw.phr_go_there));
    words.add(new Word("What is the price of this shirt?", "Quel est le prix de cette chemise?", R.raw.phr_what_is_the_price_of_this_shirt));
    words.add(new Word("Do not disturb me.", "Ne me dérange pas.", R.raw.phr_do_not_disturb_me));
    words.add(new Word("I like cricket.", "J'aime le cricket.", R.raw.phr_i_like_cricket));
    words.add(new Word("I do not like football.", "Je n'aime pas le football.", R.raw.phr_i_do_not_like_football));
    words.add(new Word("Do you speak English?", "Parlez vous anglais?", R.raw.phr_do_you_speak_english));
    words.add(new Word("Yes, I speak English.", "Oui, je parle l'anglais.", R.raw.phr_yes_i_speak_english));
    words.add(new Word("What do you do?", "Que faire?", R.raw.phr_what_do_you_do));
    words.add(new Word("I am a student.", "Je suis un étudiant.", R.raw.phr_i_am_a_student));
    words.add(new Word("Who is your favorite singer?", "Qui est votre chanteur préféré?", R.raw.phr_who_is_your_favorite_singer));
    words.add(new Word("My favorite singer is Michale Jackson.", "Mon chanteur préféré est Michael Jackson.", R.raw.phr_my_favority_singer_is_michael_jackson));
    words.add(new Word("Who is your favorite player?", "Qui est ton joueur préféré?", R.raw.phr_who_is_your_favorite_player));
    words.add(new Word("My favorite player is Mashrafe Mortaza.", "Mon joueur préféré est Mashrafe Mortaza.", R.raw.phr_my_favority_player_is_mashrafe_mortaza));
    words.add(new Word("What do you do in leisure?", "Que fais-tu en loisir?", R.raw.phr_what_do_you_do_in_leisure));
    words.add(new Word("I read books in leisure.", "Je lis des livres en loisir.", R.raw.phr_i_read_books_in_leisure));
    words.add(new Word("What do you like to eat?", "Qu'est-ce que tu aimes manger?", R.raw.phr_what_do_you_like_to_eat));
    words.add(new Word("I like to eat pasta.", "J'aime manger des pâtes.", R.raw.phr_i_like_to_eat_pasta));
    words.add(new Word("Who is your best friend?", "Qui est ton meilleur ami?", R.raw.phr_who_is_your_best_friend));
    words.add(new Word("My best friend is Nishad.", "Mon meilleur ami est Nishad.", R.raw.phr_my_best_priend_is_nishad));
    words.add(new Word("May I have your phone number?", "Puis-je avoir votre numéro de téléphone?", R.raw.phr_may_i_have_your_phone_number));
    words.add(new Word("Are you single?", "Es-tu célibataire?", R.raw.phr_are_you_single));
    words.add(new Word("Yes, I am single.", "Oui je suis célibataire.", R.raw.phr_yes_i_am_single));
    words.add(new Word("I love you.", "Je t'aime.", R.raw.phr_i_love_you));
    words.add(new Word("I hate you.", "Je te deteste.", R.raw.phr_i_hate_you));
    words.add(new Word("Goodbye.", "Au Revoir.", R.raw.phr_goodbye));
    words.add(new Word("The End", "La fin", R.raw.phr_the_end));

    WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
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
