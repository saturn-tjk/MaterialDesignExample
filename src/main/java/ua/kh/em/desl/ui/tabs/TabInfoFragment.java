package ua.kh.em.desl.ui.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.kh.em.desl.R;

// Наш класс наследует Fragment
// Fragment - часть пользовательского интерфейса, который может быть помещен в Activity.
public class TabInfoFragment extends Fragment {

// Конструктор класса
    public TabInfoFragment() {
        
    }

// Первоначальное создание фрагмента.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
	
// Наполняем наш фрагмент из XML файла.
// Методом onCreateView() создаем экземпляр пользовательского интерфейса (UI) фрагмента.
// LayoutInflater inflater компонует из XML файла в соответствующий объект View,
// т.е. наполняем определенный фрагмент представлением (View).
// Контейнер ViewGroup содержит дочерние элементы (другие View).
// ViewGroup container является родительским представлением,
// куда наполняются представления фрагмента.
// Bundle savedInstanceState для перестроения предыдущего, сохраненного состояния.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Возвращаем наполненный фрагмент из ресурса R.layout.*
        return inflater.inflate(R.layout.fragment_tab_info, container, false);
    }


}
