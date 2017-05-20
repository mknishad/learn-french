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

public class NumbersFragment extends Fragment {

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

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Zero", "Zéro", R.raw.num_zero));
        words.add(new Word("One", "Un", R.raw.num_one));
        words.add(new Word("Two", "Deux", R.raw.num_two));
        words.add(new Word("Three", "Trois", R.raw.num_three));
        words.add(new Word("Four", "Quatre", R.raw.num_four));
        words.add(new Word("Five", "Cinq", R.raw.num_five));
        words.add(new Word("Six", "Six", R.raw.num_six));
        words.add(new Word("Seven", "Sept", R.raw.num_seven));
        words.add(new Word("Eight", "Huit", R.raw.num_eight));
        words.add(new Word("Nine", "Neuf", R.raw.num_nine));
        words.add(new Word("Ten", "Dix", R.raw.num_ten));
        words.add(new Word("Eleven", "Onze", R.raw.num_eleven));
        words.add(new Word("Twelve", "Douze", R.raw.num_twelve));
        words.add(new Word("Thirteen", "Treize", R.raw.num_thirteen));
        words.add(new Word("Fourteen", "Quatorze", R.raw.num_fourteen));
        words.add(new Word("Fifteen", "Quinze", R.raw.num_fifteen));
        words.add(new Word("Sixteen", "Seize", R.raw.num_sixteen));
        words.add(new Word("Seventeen", "Dix-sept", R.raw.num_seventeen));
        words.add(new Word("Eighteen", "Dix-huit", R.raw.num_eighteen));
        words.add(new Word("Nineteen", "Dix-neuf", R.raw.num_nineteen));
        words.add(new Word("Twenty", "Vingt", R.raw.num_twenty));
        words.add(new Word("Twenty One", "Vingt et un", R.raw.num_twenty_one));
        words.add(new Word("Twenty Two", "Vingt-deux", R.raw.num_twenty_two));
        words.add(new Word("Twenty Three", "Vingt Trois", R.raw.num_twenty_three));
        words.add(new Word("Twenty Four", "Vingt Quatre", R.raw.num_twenty_four));
        words.add(new Word("Twenty Five", "Vingt Cinq", R.raw.num_twenty_five));
        words.add(new Word("Twenty Six", "Vingt-six", R.raw.num_twenty_six));
        words.add(new Word("Twenty Seven", "Vingt sept", R.raw.num_twenty_seven));
        words.add(new Word("Twenty Eight", "Vingt-huit", R.raw.num_twenty_eight));
        words.add(new Word("Twenty Nine", "Vingt-neuf", R.raw.num_twenty_nine));
        words.add(new Word("Thirty", "Trente", R.raw.num_thirty));
        words.add(new Word("Thirty One", "Trente et un", R.raw.num_thirty_one));
        words.add(new Word("Thirty Two", "Trente deux", R.raw.num_thirty_two));
        words.add(new Word("Thirty Three", "Trente trois", R.raw.num_thirty_three));
        words.add(new Word("Thirty Four", "Trente quatre", R.raw.num_thirty_four));
        words.add(new Word("Thirty Five", "Trente cinq", R.raw.num_thirty_five));
        words.add(new Word("Thirty Six", "Trente-six", R.raw.num_thirty_six));
        words.add(new Word("Thirty Seven", "Trente sept", R.raw.num_thirty_seven));
        words.add(new Word("Thirty Eight", "Trente-huit", R.raw.num_thirty_eight));
        words.add(new Word("Thirty Nine", "Trente neuf", R.raw.num_thirty_nine));
        words.add(new Word("Forty", "Quarante", R.raw.num_forty));
        words.add(new Word("Forty One", "Quarante-et-un", R.raw.num_forty_one));
        words.add(new Word("Forty Two", "Quarante-deux", R.raw.num_forty_two));
        words.add(new Word("Forty Three", "Quarante trois", R.raw.num_forty_three));
        words.add(new Word("Forty Four", "Quarante-quatre", R.raw.num_forty_four));
        words.add(new Word("Forty Five", "Quarante cinq", R.raw.num_forty_five));
        words.add(new Word("Forty Six", "Quarante-six", R.raw.num_forty_six));
        words.add(new Word("Forty Seven", "Quarante-sept", R.raw.num_forty_seven));
        words.add(new Word("Forty Eight", "Quarante huit", R.raw.num_forty_eight));
        words.add(new Word("Forty Nine", "Quarante-neuf", R.raw.num_forty_nine));
        words.add(new Word("Fifty", "Cinquante", R.raw.num_fifty));
        words.add(new Word("Fifty One", "Cinquante et un", R.raw.num_fifty_one));
        words.add(new Word("Fifty Two", "Cinquante-deux", R.raw.num_fifty_two));
        words.add(new Word("Fifty Three", "Cinquante trois", R.raw.num_fifty_three));
        words.add(new Word("Fifty Four", "Cinquante quatre", R.raw.num_fifty_four));
        words.add(new Word("Fifty Five", "Cinquante cinq", R.raw.num_fifty_five));
        words.add(new Word("Fifty Six", "Cinquante six", R.raw.num_fifty_six));
        words.add(new Word("Fifty Seven", "Cinquante sept", R.raw.num_fifty_seven));
        words.add(new Word("Fifty Eight", "Cinquante huit", R.raw.num_fifty_eight));
        words.add(new Word("Fifty Nine", "Cinquante neuf", R.raw.num_fifty_nine));
        words.add(new Word("Sixty", "Soixante", R.raw.num_sixty));
        words.add(new Word("Sixty One", "Soixante-et-un", R.raw.num_sixty_one));
        words.add(new Word("Sixty Two", "Soixante-deux", R.raw.num_sixty_two));
        words.add(new Word("Sixty Three", "Soixante trois", R.raw.num_sixty_three));
        words.add(new Word("Sixty Four", "Soixante-quatre", R.raw.num_sixty_four));
        words.add(new Word("Sixty Five", "Soixante-cinq", R.raw.num_sixty_five));
        words.add(new Word("Sixty Six", "Soixante six", R.raw.num_sixty_six));
        words.add(new Word("Sixty Seven", "Soixante-sept", R.raw.num_sixty_seven));
        words.add(new Word("Sixty Eight", "Soixante-huit", R.raw.num_sixty_eight));
        words.add(new Word("Sixty Nine", "Soixante neuf", R.raw.num_sixty_nine));
        words.add(new Word("Seventy", "Soixante-dix", R.raw.num_seventy));
        words.add(new Word("Seventy One", "Soixante et onze", R.raw.num_seventy_one));
        words.add(new Word("Seventy Two", "Soixante-douze", R.raw.num_seventy_two));
        words.add(new Word("Seventy Three", "Soixante-treize", R.raw.num_seventy_three));
        words.add(new Word("Seventy Four", "Soixante quatorze", R.raw.num_seventy_four));
        words.add(new Word("Seventy Five", "Soixante-quinze", R.raw.num_seventy_five));
        words.add(new Word("Seventy Six", "Soixante seize", R.raw.num_seventy_six));
        words.add(new Word("Seventy Seven", "Soixante-dix sept", R.raw.num_seventy_seven));
        words.add(new Word("Seventy Eight", "Soixante dix huit", R.raw.num_seventy_eight));
        words.add(new Word("Seventy Nine", "Soixante-dix-neuf", R.raw.num_seventy_nine));
        words.add(new Word("Eighty", "Quatre-vingts", R.raw.num_eighty));
        words.add(new Word("Eighty One", "Quatre vingt un", R.raw.num_eighty_one));
        words.add(new Word("Eighty Two", "Quatre-vingt deux", R.raw.num_eighty_two));
        words.add(new Word("Eighty Three", "Quatre vingt trois", R.raw.num_eighty_three));
        words.add(new Word("Eighty Four", "Quatre-vingt-quatre", R.raw.num_eighty_four));
        words.add(new Word("Eighty Five", "Quatre-vingt-cinq", R.raw.num_eighty_five));
        words.add(new Word("Eighty Six", "Quatre-vingt six", R.raw.num_eighty_six));
        words.add(new Word("Eighty Seven", "Quatre-vingt sept", R.raw.num_eighty_seven));
        words.add(new Word("Eighty Eight", "Quatre vingt huit", R.raw.num_eighty_eight));
        words.add(new Word("Eighty Nine", "Quatre vingt neuf", R.raw.num_eighty_nine));
        words.add(new Word("Ninety", "Quatre vingt dix", R.raw.num_ninety));
        words.add(new Word("Ninety One", "Quatre vingt onze", R.raw.num_ninety_one));
        words.add(new Word("Ninety Two", "Quatre-vingt douze", R.raw.num_ninety_two));
        words.add(new Word("Ninety Three", "Quatre vingt treize", R.raw.num_ninety_three));
        words.add(new Word("Ninety Four", "Quatre-vingt quatorze", R.raw.num_ninety_four));
        words.add(new Word("Ninety Five", "Quatre vingt quinze", R.raw.num_ninety_five));
        words.add(new Word("Ninety Six", "Quatre vingt seize", R.raw.num_ninety_six));
        words.add(new Word("Ninety Seven", "Quatre-vingt-dix-sept", R.raw.num_ninety_seven));
        words.add(new Word("Ninety Eight", "Quatre-vingt-dix-huit", R.raw.num_ninety_eight));
        words.add(new Word("Ninety Nine", "Quatre-vingt-dix-neuf", R.raw.num_ninety_nine));
        words.add(new Word("One Hundred", "Cent", R.raw.num_one_hundred));
        words.add(new Word("One Thousand", "Mille", R.raw.num_one_thousand));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
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
