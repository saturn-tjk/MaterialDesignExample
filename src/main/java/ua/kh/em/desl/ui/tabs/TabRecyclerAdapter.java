package ua.kh.em.desl.ui.tabs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ua.kh.em.desl.R;
import ua.kh.em.desl.model.Station;

// Создание адаптера для RecyclerView.
// Виджет RecyclerView представляет собой расширенную и более гибкую версию ListView.
// RecyclerView является контейнером для отображения больших наборов данных, которые
// можно эффективно прокручивать, сохраняя при этом ограниченное количество представлений.  
public class TabRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

// Объявляем переменные контекста и списка по модели.
    private Context context;
    private List<Station> stationList;

// Конструктор класса
    public TabRecyclerAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

// Создаем новые представления (вызывается менеджером компоновки LayoutManager).
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

// Создаем новое представление        
		View view = LayoutInflater.from(context).inflate(R.layout.item_station, parent, false);
        return new ViewHolder(view);
    }

// Связываем контент с представлением (вызывается менеджером компоновки LayoutManager).
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder != null){
            holder.station_Icon.setImageResource(stationList.get(position).getStationIcon());
            holder.station_Name.setText(stationList.get(position).getStationName());
			
// Реализация нажатия на элемент списка.
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,R.string.item_station_text,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

// Получаем количество элементов в списке (вызывается менеджером компоновки LayoutManager).
    @Override
    public int getItemCount() {
        return stationList.size();
    }

// Вызывается RecyclerView при обзоре адаптера.
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
