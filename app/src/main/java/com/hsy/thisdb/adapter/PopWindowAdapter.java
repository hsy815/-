package com.hsy.thisdb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class PopWindowAdapter extends RecyclerView.Adapter<PopWindowAdapter.ViewHolder> {

    private List<String> stringList;
    private Context context;
    private LayoutInflater inflater;
    private OnPopwinListener onPopwinListener;

    public PopWindowAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.popwin_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String content = stringList.get(position);
        holder.mPopText.setText(content);
        holder.mPopText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPopwinListener != null)
                    onPopwinListener.popwin(content);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mPopText;

        public ViewHolder(View itemView) {
            super(itemView);
            mPopText = itemView.findViewById(R.id.pop_item_text);
        }
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    public interface OnPopwinListener {
        void popwin(String text);
    }

    public void setOnPopwinListener(OnPopwinListener onPopwinListener) {
        this.onPopwinListener = onPopwinListener;
    }
}
