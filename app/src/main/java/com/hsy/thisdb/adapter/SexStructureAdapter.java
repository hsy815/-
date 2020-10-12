package com.hsy.thisdb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.GenderStructure;

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
public class SexStructureAdapter extends RecyclerView.Adapter<SexStructureAdapter.ViewHolder> {

    private Context context;
    private List<GenderStructure> genderStructures = new ArrayList<>();
    private LayoutInflater inflater;

    public SexStructureAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sex_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GenderStructure genderStructure = genderStructures.get(position);
        holder.sexItemId.setText(genderStructure.getRowid() + "");
        holder.sexItemWork.setText(genderStructure.getWorkUnit());
        holder.sexItemNan.setText(genderStructure.getMan() + "");
        holder.sexItemNv.setText(genderStructure.getWomen() + "");
    }

    @Override
    public int getItemCount() {
        return genderStructures.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView sexItemId;
        private final TextView sexItemWork;
        private final TextView sexItemNan;
        private final TextView sexItemNv;

        public ViewHolder(View itemView) {
            super(itemView);
            sexItemId = itemView.findViewById(R.id.sex_item_id);
            sexItemWork = itemView.findViewById(R.id.sex_item_work);
            sexItemNan = itemView.findViewById(R.id.sex_item_nan);
            sexItemNv = itemView.findViewById(R.id.sex_item_nv);
        }
    }

    public void setGenderStructures(List<GenderStructure> genderStructures) {
        this.genderStructures = genderStructures;
        notifyDataSetChanged();
    }
}
