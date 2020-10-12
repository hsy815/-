package com.hsy.thisdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.FamilyMember;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2018/11/27 16:25
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/27 16:25
 * @修改描述:
 */
public class DetailsFamilyAdapter extends RecyclerView.Adapter<DetailsFamilyAdapter.ViewHolder> {

    private List<FamilyMember> familyList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public DetailsFamilyAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.family_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FamilyMember familyMemb = familyList.get(position);
        holder.mChengwei.setText(familyMemb.getRelation());
        holder.mXingming.setText(familyMemb.getFMembeName());
        holder.mChushengriqi.setText(familyMemb.getFmBirthday());
        holder.mMianmiao.setText(familyMemb.getPoliticsStatus());
        holder.mDanweiZhiwu.setText(familyMemb.getWorkAndDuty());
    }

    @Override
    public int getItemCount() {
        return familyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mChengwei;
        private final TextView mXingming;
        private final TextView mChushengriqi;
        private final TextView mMianmiao;
        private final TextView mDanweiZhiwu;

        public ViewHolder(View itemView) {
            super(itemView);
            mChengwei = itemView.findViewById(R.id.family_item_chengwei);
            mXingming = itemView.findViewById(R.id.family_item_xingming);
            mChushengriqi = itemView.findViewById(R.id.family_item_chushengriqi);
            mMianmiao = itemView.findViewById(R.id.family_item_mianmiao);
            mDanweiZhiwu = itemView.findViewById(R.id.family_item_danwei_zhiwu);
        }
    }

    public void setFamilyList(List<FamilyMember> familyList) {
        this.familyList = familyList;
        notifyDataSetChanged();
    }
}
