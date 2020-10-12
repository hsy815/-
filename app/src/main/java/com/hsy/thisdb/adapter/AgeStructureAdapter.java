package com.hsy.thisdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.AgeStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2018/11/28 10:26
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/28 10:26
 * @修改描述:
 */
public class AgeStructureAdapter extends RecyclerView.Adapter<AgeStructureAdapter.ViewHolder> {

    private Context context;
    private List<AgeStructure> ageStructureList = new ArrayList<>();
    private LayoutInflater inflater;

    public AgeStructureAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.age_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AgeStructure ageStructure = ageStructureList.get(position);
        holder.ageItemId.setText(ageStructure.getRowid() + "");
        holder.ageItemWork.setText(ageStructure.getWorkUnit());
        holder.ageItem35.setText(ageStructure.getUnder35() + "");
        holder.ageItem45.setText(ageStructure.getBetween36_45() + "");
        holder.ageItem55.setText(ageStructure.getBetween46_55() + "");
        holder.ageItem56.setText(ageStructure.getUp56() + "");
    }

    @Override
    public int getItemCount() {
        return ageStructureList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView ageItemId;
        private final TextView ageItemWork;
        private final TextView ageItem35;
        private final TextView ageItem45;
        private final TextView ageItem55;
        private final TextView ageItem56;

        public ViewHolder(View itemView) {
            super(itemView);
            ageItemId = itemView.findViewById(R.id.age_item_id);
            ageItemWork = itemView.findViewById(R.id.age_item_work);
            ageItem35 = itemView.findViewById(R.id.age_item_35);
            ageItem45 = itemView.findViewById(R.id.age_item_45);
            ageItem55 = itemView.findViewById(R.id.age_item_55);
            ageItem56 = itemView.findViewById(R.id.age_item_56);
        }
    }

    public void setAgeStructureList(List<AgeStructure> ageStructureList) {
        this.ageStructureList = ageStructureList;
        notifyDataSetChanged();
    }
}
