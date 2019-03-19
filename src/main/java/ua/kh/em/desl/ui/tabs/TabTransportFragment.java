package ua.kh.em.desl.ui.tabs;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;
import ua.kh.em.desl.model.Station;

// Наш класс наследует Fragment
public class TabTransportFragment extends Fragment {

// Объявляем соответствующие переменные через библиотеку ButterKnife
    @BindView(R.id.station_list)
    RecyclerView recyclerView;
    @BindArray(R.array.metro_icons)
    TypedArray station_iconz;
    @BindArray(R.array.metros)
    String [] stationz;


// Конструктор класса	
    public TabTransportFragment() {
       
    }

// Формируем фрагмент.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Наполняем наш фрагмент из XML файла.        
        View view = inflater.inflate(R.layout.fragment_tab_transport, container, false);
		
// Непосредсвенная привязка переменных и методов к представлению/виду (View)        
		ButterKnife.bind(this, view);
		
// Список согласно модели, определенной в классе Station.
// List<T> является простейшим списком однотипных объектов.
// Инициализируем списочный массив ArrayList.
        List<Station> stationList = new ArrayList<>();
		
// Через цикл for формируем наш список.
// Методом getResourceId() получаем идентификатор ресурса по индексу.
// 1 - значение, возвращаемое, если атрибут не определен или не является ресурсом.
        for (int i = 0; i < stationz.length; i++){
            Station station = new Station(station_iconz.getResourceId(i,1), stationz[i]);
            stationList.add(station);
        }

// Используется, когда размер списка постоянный для лучшей производительности
        recyclerView.setHasFixedSize(true);
		
// Предопределяем линейное представление списка, реализованного через RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

// Определяем и подключаем адаптер к списку, реализованному через RecyclerView
        TabRecyclerAdapter tabRecyclerAdapter = new TabRecyclerAdapter(getActivity(), stationList);
        recyclerView.setAdapter(tabRecyclerAdapter);

        return view;
    }
}
