package com.example.appunipar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WeatherFragment(); // Primeira aba
            case 1:
                return new AboutFragment(); // Segunda aba
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2; // Quantidade de abas
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Previsão";
            case 1:
                return "Sobre";
            default:
                return null;
        }
    }
}
