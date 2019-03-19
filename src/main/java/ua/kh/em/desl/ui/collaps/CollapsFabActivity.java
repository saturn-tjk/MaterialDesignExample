package ua.kh.em.desl.ui.collaps;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import butterknife.OnClick;
import ua.kh.em.desl.R;
import ua.kh.em.desl.model.Metro;

// Наш класс наследует (extends) AppCompatActivity, базовый класс для активити,
// которое использует Support Library (библиотеки поддержки) в части свойств Action Bar
// (панели действий).

public class CollapsFabActivity extends AppCompatActivity {

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

// Глобальная переменная типа Menu, которая хранит копию оригинального меню
    private Menu collapsMenu;

//  Инициализируем appBarExpand - булевый (логический) флаг, который указывает на то, когда AppBarLayout
//  будет свернут или развернут. Предустановим true - развернут. Поскольку изначально мы видим
//  развернутую картинку, а не просто Toolbar.
    private  boolean appBarExpand = true;

// Объявляем соответствующие переменные через библиотеку ButterKnife, позволяющей через
//аннотации сделать привязку переменных и методов к представлению в Android.
// А также избежать такой рутины, как findViewById.
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
        setContentView(R.layout.activity_collaps_fab);

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

// Предопределяем линейное представление списка, реализованного через RecyclerView
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

// При сворачивании Appbar (Toolbar) нам необходимо преобразовать FAB, чтобы она не исчезала совсем.
// Поскольку картинка должна полностью свернуться, то иконку FAB разместим в Toolbar, в самом меню.
// Так как размещение самой FAB с иконкой, при свернутом Toolbar, будет не совсем логично и эстетично.
// Здесь применим решение со слушателем. Мы должны слушать (отслеживать) когда AppBarLayout сворачивается
// и разворачивается.
// Для этих целей нам необходим OffsetChangedListener, а точнее
// конструкция AppBarLayout.OnOffsetChangedListener,
// которая представляет собой определение интерфейса для обратного вызова (callback),
// вызываемого при изменении вертикального смещения AppBarLayout.

        collapsAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){

// Вызывается, когда смещение AppBarLayout было изменено. Это позволяет дочерним представлениям реализовать
// пользовательское поведение на основе смещения (например, привязка представления к определенному значению y).
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
// AppBarLayout appBarLayout - AppBarLayout, смещение которого изменилось.
// int verticalOffset - вертикальное смещение для родительского AppBarLayout, в px.
// Если verticalOffset равняется 0, то это говорит о том, что AppBarLayout полностью развернут.
// verticalOffset возвращает отрицательные значения, поэтому мы оборачиваем его в Math.abs().
// Метод Math.abs() — выдает абсолютное значение аргумента, т.е. — модуль числа.
// Значение может быть int, float, long и т.д.
// В нашем случае, int verticalOffset - целочислено.

                if (Math.abs(verticalOffset) > 200){

//  appBarExpand - булевый (логический) флаг, который указывает на то, когда AppBarLayout
//  свернут или развернут.
// Это условие меняет значение appBarExpand на false (изначально оно инициализированно как true)
// при пересечении, так сказать, черты 200, т.е. AppBarLayout - свернут.

                    appBarExpand = false;

// Метод invalidateOptionsMenu() вызывается каждый раз, когда при сворачивании AppBarLayout
// пересекаем, так сказать, черту 200.
// invalidateOptionsMenu() предназначен для того, чтобы оповестить операционную систему о том,
// что что-то изменилось в содержимом меню.
// Здесь, происходит исчезновение самой FAB когда список прокручивается и AppBarLayout сворачивается.
// FAB заменяется в Toolbar иконкой.
// Система перерисовывает меню по сигналу от invalidateOptionsMenu().
// Метод является сигналом для вызова операционной системой onPrepareOptionsMenu(),
// где выполняются необходимые манипуляции с меню.

                    invalidateOptionsMenu();
                } else {

// Иначе, сворачивания AppBarLayout не происходило, и appBarExpand имеет значение true,
// т.е. AppBarLayout - развернут.

                    appBarExpand = true;
                    invalidateOptionsMenu();
                }

            }
        });
    }

// Установка стилей текста для разных состояний Toolbar через ресурс styles
    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppbarCollaps);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.AppbarExpand);
    }

// Реализация действия при нажатии FAB через библиотеку ButterKnife
    @OnClick(R.id.collaps_fab) public void onClick(){
        Toast.makeText(context,R.string.collaps_fab_text,Toast.LENGTH_SHORT).show();
    }

//  Если требуется изменять меню параметров в зависимости от событий, которые возникают в течение
// жизненного цикла операции, сделать это можно в методе onPrepareOptionsMenu().
// Этот метод передает объект Menu в том виде, в котором он в данный момент существует.
// Его-то и можно изменить путем, например, добавления, удаления или отключения пунктов меню.

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if (collapsMenu !=null && (!appBarExpand || collapsMenu.size() != 1)){
// Меню свернуто
// Добавляем наше кастомное динамическое меню
// add("Add") добавляем действие
// setIcon(R.drawable.ic_action_add) устанавливаем иконку из ресурса drawable
// setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM) устанавливаем элемент меню как действие
// с флагом SHOW_AS_ACTION_IF_ROOM, что определяет элемент как кнопку в панели действий,
// если система решит, что для этого есть место.
            collapsMenu.add("Add")
                    .setIcon(R.drawable.ic_action_add)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
// Меню развернуто
// Не прописано действий, поскольку меню развернуто и действие, определенное через Add,
// должно быть удалено, т.е. его в развернутом меню просто не должно быть,
// после того как onCreateOptionsMenu() наполнит оригинальное меню.
        }
        return  super.onPrepareOptionsMenu(collapsMenu);
    }


// Указываем меню параметров для операции (действия или определенный выбор).
// Переопределяем onCreateOptionsMenu().
// В этом методе можно загрузить собственный ресурс меню (определенный в XML) в класс Menu,
// имеющийся в обратном вызове.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collaps, menu);
        collapsMenu = menu;
        return true;
    }

// Метод выбора элемента меню для определенных действий.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Toast.makeText(context,"HOME",Toast.LENGTH_SHORT).show();
                return true;
        }
// При наличии обозначения Add, элемент меню, в случае нажатия на него, запустит соответствующее действие,
// здесь, запуск всплывающего сообщениея Toast.
        if (item.getTitle() == "Add"){
            Toast.makeText(context,"ADD",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}