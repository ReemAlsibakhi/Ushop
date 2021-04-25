package com.reem.ushop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.reem.ushop.R;
import com.reem.ushop.databinding.CategoryItemBinding;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.utils.ToolUtils;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    public ArrayList<Category> dataList;
    private Context mContext;
    private OnItemClickListener mListener;
    private int selectedPos = 0;


    public interface OnItemClickListener {
        void onClicked(int position, Category category);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public CategoryAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, final int position) {
        Category category = dataList.get(position);
        Glide.with(mContext).load(category.getImage()).override(80, 80).into(holder.binding.imgCategory);
        holder.binding.tvName.setText(category.getName());
        holder.itemView.setSelected(selectedPos == position);
        holder.itemView.setOnClickListener(view -> {
            selectedPos = position;
            notifyDataSetChanged();
            if (mListener != null) {
                mListener.onClicked(selectedPos, dataList.get(position));
            }
        });
        holder.binding.linear.setBackgroundColor(selectedPos == position ? mContext.getResources().getColor(R.color.colorPrimary) : mContext.getResources().getColor(R.color.tranparent));
        holder.binding.tvName.setTextColor(selectedPos == position ? mContext.getResources().getColor(R.color.white) : mContext.getResources().getColor(R.color.black_light));
        ToolUtils.setColorIcon(mContext.getApplicationContext(), holder.binding.imgCategory, (selectedPos == position) ? R.color.white : R.color.black_light);

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void setList(ArrayList<Category> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CategoryItemBinding binding;

        public ViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

