package com.hsy.thisdb.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.NewSysDepartment;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2019/2/20 13:48
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/2/20 13:48
 * @修改描述:
 */
public class SysDepartmentAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;

    private Context context;
    private List<NewSysDepartment> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private int mLastPosition = -1;

    public SysDepartmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0 && list.get(position).getId() < 10000) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == TYPE_ONE) {
            view = inflater.inflate(R.layout.sysdepartment_item_view_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.sysdepartment_new_item_view, parent, false);
            return new SysDepartmentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final NewSysDepartment newSysDepartment = list.get(position);

        if (holder instanceof ViewHolderOne) {
            ((ViewHolderOne) holder).mSysOneText.setText(newSysDepartment.getName());
        }
        if (holder instanceof SysDepartmentViewHolder) {
            ((SysDepartmentViewHolder) holder).mSysText.setText(newSysDepartment.getName());
            if (newSysDepartment.isSelect()) mLastPosition = position;
            if (position == mLastPosition) {
                ((SysDepartmentViewHolder) holder).mSysText.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                ((SysDepartmentViewHolder) holder).mSysText.setTextColor(Color.WHITE);
            } else {
                ((SysDepartmentViewHolder) holder).mSysText.setBackgroundColor(Color.WHITE);
                ((SysDepartmentViewHolder) holder).mSysText.setTextColor(ContextCompat.getColor(context, R.color.coloText));
            }
            ((SysDepartmentViewHolder) holder).mSysText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.Item(position, newSysDepartment);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        private TextView mSysOneText;

        public ViewHolderOne(View itemView) {
            super(itemView);
            mSysOneText = itemView.findViewById(R.id.sysdepartment_new_one_text);
        }
    }

    public static class SysDepartmentViewHolder extends RecyclerView.ViewHolder {

        private TextView mSysText;

        public SysDepartmentViewHolder(View itemView) {
            super(itemView);
            mSysText = itemView.findViewById(R.id.sysdepartment_new_text);
        }
    }

    public void changeImageVisable(int position) {
        mLastPosition = position;
        for (NewSysDepartment department :
                list) {
            department.setSelect(false);
        }
        list.get(position).setSelect(true);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void Item(int i, NewSysDepartment newSysDepartment);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(List<NewSysDepartment> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
