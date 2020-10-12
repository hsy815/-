package com.hsy.thisdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;

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
public class HomeTitleAdapter extends RecyclerView.Adapter<HomeTitleAdapter.ViewHolder> {

    private List<String> stringList;
    private Context context;
    private LayoutInflater inflater;
    private OnHomeTitleListener onHomeTitleListener;
    private int mLastPosition = -1;

    public HomeTitleAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_title_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final String content = stringList.get(position);
        holder.mTitleText.setText(content);
        holder.mTitleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHomeTitleListener != null)
                    onHomeTitleListener.title_text(position, holder.itemView, content);
            }
        });
        if (position == mLastPosition) {
            holder.mTitleText.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            holder.mTitleText.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleText;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.home_title_item_text);
        }
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    public String getStopTitle() {
        return this.stringList.get(mLastPosition);
    }

    public interface OnHomeTitleListener {
        void title_text(int position, View view, String text);
    }

    public void setOnHomeTitleListener(OnHomeTitleListener onHomeTitleListener) {
        this.onHomeTitleListener = onHomeTitleListener;
    }

    public void changeImageVisable(int position) {
        mLastPosition = position;
        notifyDataSetChanged();
    }

    public int getmLastPosition() {
        return mLastPosition;
    }
}
