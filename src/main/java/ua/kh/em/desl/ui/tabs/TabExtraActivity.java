package ua.kh.em.desl.ui.tabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;


// Наш класс наследует (extends) AppCompatActivity, базовый класс для активити,
// которое использует Support Library (библиотеки поддержки) в части свойств Action Bar
// (панели действий).
public class TabExtraActivity extends AppCompatActivity {


// Объявляем соответствующие переменные через библиотеку ButterKnife, позволяющей через
// аннотации сделать привязку переменных и методов к представлению в Android.
// А также избежать такой рутины, как findViewById.
// R.id.* - идентификатор играет ключевую роль.
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

// Определяем массив иконок для вкладок	
   final int [] tabIcons = new int[]{
            R.drawable.ic_info_white_24dp,
            R.drawable.ic_transport_white_24dp,
            R.drawable.ic_place_white_24dp,
            R.drawable.ic_name_white_24dp,
            R.drawable.ic_phone_white_24dp
    };

	
// Создаем наш экран через метод onCreate().
// Устанавливаем вид (View) из ресурса R.layout.*,
// т.е. UI из XML файла через метод setContentView().	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_extra);
		
// Непосредсвенная привязка переменных и методов к представлению/виду (View)		
        ButterKnife.bind(this);
		
// Устанавливаем для toolbar выполнение функций ActionBar.
        setSupportActionBar(toolbar);
		
// Устанавливаем ViewPager.
// ViewPager - менеджер компоновок, который позволяет пользователю листать
// влево и вправо через страницы данных.
// Метод setupWithViewPager() связывает TabLayout tabLayout и ViewPager viewPager.		
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

//Получаем определенную вкладку с определенным индексом
// через метод getTabAt().
// Устанавливаем нужную иконку для определенного индекса.
        TabLayout.Tab tabInfo = tabLayout.getTabAt(0);
        if (tabInfo != null) tabInfo.setIcon(tabIcons[0]);
        TabLayout.Tab tabTransport = tabLayout.getTabAt(1);
        if (tabTransport != null) tabTransport.setIcon(tabIcons[1]);
        TabLayout.Tab tabPlace = tabLayout.getTabAt(2);
        if (tabPlace != null) tabPlace.setIcon(tabIcons[2]);
        TabLayout.Tab tabName = tabLayout.getTabAt(3);
        if (tabName != null) tabName.setIcon(tabIcons[3]);
        TabLayout.Tab tabPhone = tabLayout.getTabAt(4);
        if (tabPhone != null) tabPhone.setIcon(tabIcons[4]);

    }

// В setupViewPager() создаем объект PagerAdapter, а также 
// объекты наших фрагментов, которые добавляем в объект PagerAdapter.
// PagerAdapter - базовый класс, обеспечивающий адаптацию для заполнения страниц внутри ViewPager.
// Через getSupportFragmentManager() получаем FragmentManager, который
// обеспечивает взаимодействие с фрагментами, связанными с этим активити.
// Также назначаем титулы (заглавия) для наших вкладок.
    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new TabInfoFragment(),"Info");
        pagerAdapter.addFragment(new TabTransportFragment(),"Transport");
        pagerAdapter.addFragment(new TabPlaceFragment(),"Place");
        pagerAdapter.addFragment(new TabNameFragment(),"Name");
        pagerAdapter.addFragment(new TabPhoneFragment(),"Phone");
        viewPager.setAdapter(pagerAdapter);
    }
}
