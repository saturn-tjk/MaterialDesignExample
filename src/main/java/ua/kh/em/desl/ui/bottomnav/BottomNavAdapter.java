package ua.kh.em.desl.ui.bottomnav;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.kh.em.desl.R;
import ua.kh.em.desl.model.Metro;


public class BottomNavAdapter extends RecyclerView.Adapter<BottomNavAdapter.BnavViewHolder> {

    private List<Metro> metros;
    private Context context;

    public BottomNavAdapter(List<Metro> metros, Context context) {
        this.metros = metros;
        this.context = context;
    }

    @Override
    public BottomNavAdapter.BnavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transport,parent,false);
        return new BnavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BottomNavAdapter.BnavViewHolder holder, int position) {

        if (holder.transportItem != null) {
            holder.transportItem.setText(metros.get(position).getMetro());
        }

        holder.transportItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,R.string.item_collaps_text,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return metros.size();
    }

    public class BnavViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.bnav_item)
        TextView transportItem;
        public BnavViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
