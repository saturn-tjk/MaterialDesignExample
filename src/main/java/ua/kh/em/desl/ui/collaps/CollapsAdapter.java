package ua.kh.em.desl.ui.collaps;

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


public class CollapsAdapter extends RecyclerView.Adapter<CollapsAdapter.CollapsViewHolder> {

    private List<Metro> metros;
    private Context context;

    public CollapsAdapter(List<Metro> metros, Context context) {
        this.metros = metros;
        this.context = context;
    }

    @Override
    public CollapsAdapter.CollapsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collaps,parent,false);
        return new CollapsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollapsAdapter.CollapsViewHolder holder, int position) {

        if (holder.collapsItem != null) {
            holder.collapsItem.setText(metros.get(position).getMetro());
        }

        holder.collapsItem.setOnClickListener(new View.OnClickListener() {
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

    public class CollapsViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.collaps_item)
        TextView collapsItem;
        public CollapsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
