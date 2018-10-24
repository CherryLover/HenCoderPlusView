package me.monster.hencoderplusview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.monster.hencoderplusview.fragment.CircleViewFragment;
import me.monster.hencoderplusview.fragment.DashFragment;
import me.monster.hencoderplusview.fragment.ImageTextFragment;
import me.monster.hencoderplusview.fragment.ImageViewerFragment;
import me.monster.hencoderplusview.fragment.MaterialEditFragment;
import me.monster.hencoderplusview.fragment.PieFragment;
import me.monster.hencoderplusview.fragment.PlusViewFragment;
import me.monster.hencoderplusview.fragment.RadarFragment;
import me.monster.hencoderplusview.fragment.RoundAvatarFragment;
import me.monster.hencoderplusview.fragment.SportFragment;
import me.monster.hencoderplusview.fragment.SquareFragment;
import me.monster.hencoderplusview.fragment.TagLayoutFragment;
import me.monster.hencoderplusview.view.RadarView;

/**
 * @author PVer
 */
public class MainActivity extends AppCompatActivity {

    private List<Fragment> allFragmentList = new ArrayList<>();
    private List<String> allTitleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        allFragmentList.add(RadarFragment.newInstance());
        allFragmentList.add(ImageViewerFragment.newInstance());
        allFragmentList.add(TagLayoutFragment.newInstance());
        allFragmentList.add(CircleViewFragment.newInstance());
        allFragmentList.add(SquareFragment.newInstance());
        allFragmentList.add(MaterialEditFragment.newInstance());
        allFragmentList.add(PlusViewFragment.newInstance());
        allFragmentList.add(DashFragment.newInstance());
        allFragmentList.add(ImageTextFragment.newInstance());
        allFragmentList.add(PieFragment.newInstance());
        allFragmentList.add(RoundAvatarFragment.newInstance());
        allFragmentList.add(SportFragment.newInstance());

        allTitleList.add("雷达图");
        allTitleList.add(getString(R.string.imageViwer_title));
        allTitleList.add(getString(R.string.taglayout_title));
        allTitleList.add(getString(R.string.round_avatar));
        allTitleList.add(getString(R.string.square_image));
        allTitleList.add(getString(R.string.custom_material_title));
        allTitleList.add(getString(R.string.filpboard_title));
        allTitleList.add(getString(R.string.dash_title));
        allTitleList.add(getString(R.string.image_text_title));
        allTitleList.add(getString(R.string.pie_title));
        allTitleList.add(getString(R.string.round_avatar_title));
        allTitleList.add(getString(R.string.sport_title));

        TabLayout tbTabs = findViewById(R.id.tab_main);
        ViewPager vpPager = findViewById(R.id.vp_main);

        vpPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return allFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return allFragmentList.size();
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                return allTitleList.get(position);
            }
        });
        tbTabs.setupWithViewPager(vpPager);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
}
