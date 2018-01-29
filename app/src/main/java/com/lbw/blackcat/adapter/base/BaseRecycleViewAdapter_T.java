package com.lbw.blackcat.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lin on 16/12/28.
 */
public abstract class BaseRecycleViewAdapter_T<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private int layoutId;
    private List<T> data;
    private Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    /**
     *
     * @param context //上下文
     * @param layoutId  //布局id
     * @param data  //数据源
     */
    public BaseRecycleViewAdapter_T(Context context, int layoutId, List<T> data) {
        this.layoutId = layoutId;
        this.data = data;
        this.context = context;
    }



    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        final BaseViewHolder holder = new BaseViewHolder(v, context);
        //单击事件回调
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onItemClickListner!=null ) {
                    onItemClickListner.onItemClickListner(v,holder.getLayoutPosition(),data.get(holder.getLayoutPosition()));
                }
                clickFlag = true;
            }
        });
        //单击长按事件回调
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListner != null )
                {
                    onItemLongClickListner.onItemLongClickListner(v,holder.getLayoutPosition());
                }
                clickFlag = false;
                return false;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder,position, data.get(position));
    }

    protected abstract  void convert(BaseViewHolder holder, int position, T bean);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }
    public  void setOnItemClickListner(OnItemClickListner onItemClickListner,OnItemLongClickListner onItemLongClickListner) {
        this.onItemClickListner = onItemClickListner;
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position, Object t);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }


    public void updaterall(List<T> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        if (position != data.size())
            notifyItemRangeChanged(position, data.size() - position);
    }

    public void add(T t, int position) {
        data.add(position,t);
        notifyItemInserted(position);
    }


}
