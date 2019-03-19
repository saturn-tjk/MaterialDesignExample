package ua.kh.em.desl.ui.collaps;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;
import ua.kh.em.desl.model.Metro;

// Наш класс наследует (extends) AppCompatActivity, базовый класс для активити,
// которое использует Support Library (библиотеки поддержки) в части свойств Action Bar
// (панели действий).
public class CollapsActivity extends AppCompatActivity {

// Предопределение контекста, т.е. согласно документации
//  интерфейса об информационной среде приложения.
// Context позволяет получать доступ конкретным ресурсам.
// Определяем контекст нашего активити.
    final Context context = this;

//  Массив символов для получения элементов списка
    private String [] metroz;

//  Список согласно модели, определенной в классе Metro.
//  List<T> является простейшим списком однотипных объектов
    private List<Metro> metroList;

    @BindView(R.id.collaps_appbar)
    AppBarLayout collapsAppbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.collaps_toolbar)
    Toolbar collapsToolbar;
    @BindView(R.id.metro_list)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaps);
        ButterKnife.bind(this);

// Устанавливаем для collapsToolbar выполнение функций ActionBar.
// Если actionBar не null, устанавливаем навигацию вверх.
// Наша стрелочка налево в меню.
        setSupportActionBar(collapsToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

// Устанавливаем для сворачивающегося тулбара заголовок из ресурсов.
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.collapsing_toolbar_title));

 // Массив символов наполняем из ресурсов strings.
// Списочный массив ArrayList через цикл for наполняем
// согласно модели Metro.
        metroz = getResources().getStringArray(R.array.metros);
        metroList = new ArrayList<>();

        for (String metro : metroz){
            Metro station = new Metro(metro);
            metroList.add(station);
        }

// Используется, когда размер списка постоянный для лучшей производительности
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

// Разделительная линия между элементами RecyclerView
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

// Определяем и подключаем адаптер к списку, реализованному через RecyclerView
        CollapsAdapter adapter = new CollapsAdapter(metroList,context);
        recyclerView.setAdapter(adapter);

// Реализуем установку стилей текста для разных состояний Toolbar через ресурс styles
        toolbarTextAppearance();

    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppbarCollaps);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.AppbarExpand);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collaps, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Toast.makeText(context,"HOME",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}