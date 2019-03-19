package ua.kh.em.desl.ui.bottomnav;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;
import ua.kh.em.desl.model.Metro;

public class BottomNavTransportFragment extends Fragment {

    private String [] metroz;
    private List<Metro> metroList;
    @BindView(R.id.station_list)
    RecyclerView recyclerView;

    public BottomNavTransportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_nav_transport, container, false);
        ButterKnife.bind(this, view);

        metroz = getResources().getStringArray(R.array.metros);
        metroList = new ArrayList<>();

        for (String metro : metroz){
            Metro station = new Metro(metro);
            metroList.add(station);
        }

// Используется, когда размер списка постоянный для лучшей производительности
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

// Разделительная линия между элементами RecyclerView
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        BottomNavAdapter adapter = new BottomNavAdapter(metroList,getActivity());
        recyclerView.setAdapter(adapter);

        return view;

    }

}
