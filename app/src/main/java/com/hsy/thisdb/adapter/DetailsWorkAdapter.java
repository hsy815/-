package com.hsy.thisdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.DetailsWorkExperience;

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
public class DetailsWorkAdapter extends RecyclerView.Adapter<DetailsWorkAdapter.ViewHolder> {

    private List<DetailsWorkExperience> detailsWorkExperiences = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public DetailsWorkAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.work_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailsWorkExperience detailsWorkExperience = detailsWorkExperiences.get(position);
        holder.mData.setText(detailsWorkExperience.getDetails_data());
        holder.mContent.setText(detailsWorkExperience.getDetails_content());
    }

    @Override
    public int getItemCount() {
        return detailsWorkExperiences.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mData;
        private final TextView mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mData = itemView.findViewById(R.id.work_item_data);
            mContent = itemView.findViewById(R.id.work_item_content);
        }
    }

    public void setDetailsWorkExperiences(List<DetailsWorkExperience> detailsWorkExperiences) {
        this.detailsWorkExperiences = detailsWorkExperiences;
    }
}
