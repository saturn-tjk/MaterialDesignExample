package ua.kh.em.desl.ui.navview;


import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;

// Наш класс наследует (extends) AppCompatActivity, базовый класс для активити,
// которое использует Support Library (библиотеки поддержки) в части свойств Action Bar
// (панели действий).
public class NavActivity extends AppCompatActivity {

// Объявляем соответствующие переменные через библиотеку ButterKnife.
// R.id.* - идентификатор играет ключевую роль.
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.about_fragment)
    FrameLayout aboutFragment;

// Создаем наш экран через метод onCreate().
// Устанавливаем вид (View) из ресурса R.layout.*,
// т.е. UI из XML файла через метод setContentView().
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

// Непосредсвенная привязка переменных и методов к представлению/виду (View)
        ButterKnife.bind(this);

// Устанавливаем для toolbar выполнение функций ActionBar.
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
// Устанавливаем иконку в ActionBar.
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
// Установка true для выбора «home». Используем для показа меню.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//Контроль отображения элемента меню как отмеченного.
                menuItem.setChecked(true);
// Закрываются все открытые в настоящее время drawer views.
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()){
                    case R.id.nav_city_about:
                        updateFragment(new AboutFragment());
                        break;
                    case R.id.nav_city_name:
                        aboutFragment.setVisibility(View.GONE);
                        updateFragment(new NameFragment());
                        break;
                    case R.id.nav_city_map:
                        aboutFragment.setVisibility(View.GONE);
                        updateFragment(new MapFragment());
                        break;
                    default:
                        Toast.makeText(NavActivity.this,R.string.nav_item_chosen,Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private  void updateFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frame_container,fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
// Открываем drawer с левой стороны, поскольку установлено GravityCompat.START.
// Также можно установить GravityCompat.LEFT.
// Для открытия справа -  Gravity.RIGHT или GravityCompat.END.
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

