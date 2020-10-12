package com.hsy.thisdb.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.SysDepartment;

import java.util.List;
import java.util.Map;

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
public class HomeLeftAdapter extends RecyclerView.Adapter<HomeLeftAdapter.ViewHolder> {

    private Context context;
    private List<SysDepartment> sysDepartmentList;
    private LayoutInflater inflater;
    private OnHomeLeftListener onHomeLeftListener;
    private Map<Object, Boolean> mMapSignLeft;

    public HomeLeftAdapter(Context context, List<SysDepartment> sysDepartmentList) {
        this.context = context;
        this.sysDepartmentList = sysDepartmentList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_left_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final SysDepartment sysDepartment = sysDepartmentList.get(position);
        if (mMapSignLeft != null) {
            if (mMapSignLeft.get((sysDepartment.getId() + "")) != null && mMapSignLeft.get((sysDepartment.getId() + ""))) {
                holder.mLeftText.setTextColor(Color.RED);
            } else {
                holder.mLeftText.setTextColor(Color.BLACK);
            }
        }
        holder.mLeftId.setText((position + 1) + "");
        holder.mLeftText.setText(sysDepartment.getName());
        holder.mLeftLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHomeLeftListener != null)
                    onHomeLeftListener.left_text(sysDepartment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sysDepartmentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mLeftLin;
        private final TextView mLeftId;
        private final TextView mLeftText;

        public ViewHolder(View itemView) {
            super(itemView);
            mLeftLin = itemView.findViewById(R.id.home_left_item_lin);
            mLeftId = itemView.findViewById(R.id.home_left_item_id);
            mLeftText = itemView.findViewById(R.id.home_left_item_text);
        }
    }

    public void setSysDepartmentList(List<SysDepartment> sysDepartmentList) {
        this.sysDepartmentList = sysDepartmentList;
        notifyDataSetChanged();
    }

    public interface OnHomeLeftListener {
        void left_text(SysDepartment sysDepartment);
    }

    public void setOnHomeLeftListener(OnHomeLeftListener onHomeLeftListener) {
        this.onHomeLeftListener = onHomeLeftListener;
    }

    public void setmMapSignLeft(Map<Object, Boolean> mMapSignLeft) {
        this.mMapSignLeft = mMapSignLeft;
        for (Map.Entry<Object, Boolean> m : mMapSignLeft.entrySet()) {
            Log.e("tag", "key=" + m.getKey() + ",value" + m.getValue());
        }
        notifyDataSetChanged();
    }
}
