package com.ideafactorybd.learnfrench;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Nishad on 5/8/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

  /**
   * Context of the app
   */
  private Context mContext;

  public CategoryAdapter(Context context, FragmentManager fm) {
    super(fm);
    mContext = context;
  }

  @Override
  public Fragment getItem(int position) {
    if (position == 0) {
      return new NumbersFragment();
    } else if (position == 1) {
      return new FamilyFragment();
    } else if (position == 2) {
      return new ColorsFragment();
    } else if (position == 3) {
      return new AnimalsFragment();
    } else {
      return new PhrasesFragment();
    }
  }

  @Override
  public int getCount() {
    return 5;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    if (position == 0) {
      return mContext.getString(R.string.category_numbers);
    } else if (position == 1) {
      return mContext.getString(R.string.category_family);
    } else if (position == 2) {
      return mContext.getString(R.string.category_colors);
    } else if (position == 3) {
      return mContext.getString(R.string.category_animals);
    } else {
      return mContext.getString(R.string.category_phrases);
    }
  }
}
