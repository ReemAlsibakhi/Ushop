package com.reem.ushop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.reem.ushop.databinding.SubCategoryItemBinding;
import com.reem.ushop.pojo.Subcategories;
import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    public ArrayList<Subcategories> dataList;
    private Context mContext;
    private OnItemClickListener mListener;
    private int selectedPos = 0;

    public void filterList(ArrayList<Subcategories> filteredList) {
        dataList = filteredList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClicked(int position, Subcategories category);
    }
    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public SubCategoryAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SubCategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder holder, final int position) {
        Subcategories subcategory = dataList.get(position);
        Glide.with(mContext).load(subcategory.getImage()).override(200, 200).into(holder.binding.imgSubCat);
        holder.binding.tvName.setText(subcategory.getName());
        holder.itemView.setOnClickListener(view -> {
            notifyDataSetChanged();
            if (mListener != null) {
                mListener.onClicked(position, dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void setList(ArrayList<Subcategories> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SubCategoryItemBinding binding;

        public ViewHolder(SubCategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

