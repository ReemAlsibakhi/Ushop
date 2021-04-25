package com.reem.ushop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reem.ushop.databinding.CategoryItemBinding;
import com.reem.ushop.pojo.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    public ArrayList<Category> dataList;
    private Context mContext;

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
        Glide.with(mContext).load(category.getImage()) // Image URL
                .centerCrop()
//                .override(800,500) // Resize image
//                .placeholder(R.drawable.ic_photo_black_36dp) // Place holder image
//                .error(R.drawable.ic_error_black_36dp) // On error image
                .into(holder.binding.imgCategory); // ImageView to display image
        holder.binding.tvName.setText(category.getName());
        holder.itemView.setOnClickListener(view -> { Log.i("LOG_TAG", category.getName()); });
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

