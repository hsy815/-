package com.hsy.thisdb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.Personal;

import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb
 * @创始人: hsy
 * @创建时间: 2018/11/20 10:45
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/20 10:45
 * @修改描述:
 */
public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.PersonalViewHolder> {

    private Context context;
    private List<Personal> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public PersonalAdapter(Context context, List<Personal> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.personal_item_view, parent, false);
        return new PersonalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalViewHolder holder, final int position) {
        final Personal personal = list.get(position);
        holder.mPersonalId.setText(((position + 1) + ""));
        holder.mPersonalFullname.setText(personal.getFullname());
        holder.mPersonalSex.setText(personal.getGender());
        holder.mPersonalWorkUnit.setText((personal.getWorkUnit() + " " + personal.getSectionName() + personal.getDuty()));
//        holder.mPersonalZhiWu.setText((personal.getWorkUnit() + personal.getSectionName() + personal.getDuty()));
        holder.mPersonalRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.Item(position, personal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PersonalViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mPersonalRel;
        private TextView mPersonalId;
        private TextView mPersonalFullname;
        private TextView mPersonalWorkUnit;
        private TextView mPersonalZhiWu;
        private TextView mPersonalSex;

        public PersonalViewHolder(View itemView) {
            super(itemView);
            mPersonalRel = itemView.findViewById(R.id.personal_rel);
            mPersonalId = itemView.findViewById(R.id.personal_id);
            mPersonalFullname = itemView.findViewById(R.id.personal_fullname);
            mPersonalWorkUnit = itemView.findViewById(R.id.personal_work_unit);
            mPersonalZhiWu = itemView.findViewById(R.id.personal_zhi_wu);
            mPersonalSex = itemView.findViewById(R.id.personal_sex);
        }
    }

    public interface OnItemClickListener {
        void Item(int i, Personal personal);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
