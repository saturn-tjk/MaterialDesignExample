package ua.kh.em.desl.ui.tabs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;


// Наш класс наследует (extends) AppCompatActivity, базовый класс для активити,
// которое использует Support Library (библиотеки поддержки) в части свойств Action Bar
// (панели действий).
public class TabActivity extends AppCompatActivity {

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

// Создаем наш экран через метод onCreate().
// Устанавливаем вид (View) из ресурса R.layout.*,
// т.е. UI из XML файла через метод setContentView(). 	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        
// Непосредсвенная привязка переменных и методов к представлению/виду (View)		
		ButterKnife.bind(this);

// Устанавливаем для toolbar выполнение функций ActionBar.
        setSupportActionBar(toolbar);
		
// Устанавливаем ViewPager.
// ViewPager - менеджер компоновок, который позволяет пользователю листать влево и вправо через страницы данных.
// Метод setupWithViewPager() связывает TabLayout tabLayout и ViewPager viewPager.		
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
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
        viewPager.setAdapter(pagerAdapter);
    }
}
