package me.monster.hencoderplusview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.monster.hencoderplusview.fragment.DashFragment;
import me.monster.hencoderplusview.fragment.ImageTextFragment;
import me.monster.hencoderplusview.fragment.PieFragment;
import me.monster.hencoderplusview.fragment.PlusViewFragment;
import me.monster.hencoderplusview.fragment.RoundAvatarFragment;
import me.monster.hencoderplusview.fragment.SportFragment;

/**
 * @author PVer
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tbTabs = findViewById(R.id.tab_main);
        ViewPager vpPager = findViewById(R.id.vp_main);

        vpPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 1:
                        return DashFragment.newInstance();
                    case 2:
                        return ImageTextFragment.newInstance();
                    case 3:
                        return PieFragment.newInstance();
                    case 4:
                        return RoundAvatarFragment.newInstance();
                    case 5:
                        return SportFragment.newInstance();
                    default:
                        return PlusViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 6;
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 1:
                        return getString(R.string.dash_title);
                    case 2:
                        return getString(R.string.image_text_title);
                    case 3:
                        return getString(R.string.pie_title);
                    case 4:
                        return getString(R.string.round_avatar_title);
                    case 5:
                        return getString(R.string.sport_title);
                    default:
                        return "翻转";
                }
            }
        });
        tbTabs.setupWithViewPager(vpPager);
    }
}
