package com.example.appunipar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WeatherFragment();  // Primeira aba
            case 1:
                return new AboutFragment();  // Segunda aba
            default:
                return new WeatherFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // Número total de abas
    }
}
