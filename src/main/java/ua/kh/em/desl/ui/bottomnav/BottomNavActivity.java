package ua.kh.em.desl.ui.bottomnav;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;

public class BottomNavActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bn_info_fragment)
    FrameLayout bnInfoFragment;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bn_item_about:
                        updateFragment(new BottomNavInfoFragment());
                        break;
                    case R.id.bn_item_transport:
                        bnInfoFragment.setVisibility(View.GONE);
                        updateFragment(new BottomNavTransportFragment());
                        break;
                    case R.id.bn_item_place:
                        bnInfoFragment.setVisibility(View.GONE);
                        updateFragment(new BottomNavPlaceFragment());
                        break;
                }
                return true;
            }
        });

    }

    private void updateFragment(Fragment fragment) {
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.bnav_frame_container,fragment).commit();
    }

}
