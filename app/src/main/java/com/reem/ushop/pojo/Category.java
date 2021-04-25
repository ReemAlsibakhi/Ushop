package com.reem.ushop.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Category {

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("subcategories")
	private List<Subcategories> subcategories;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSubcategories(List<Subcategories> subcategories){
		this.subcategories = subcategories;
	}

	public List<Subcategories> getSubcategories(){
		return subcategories;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",subcategories = '" + subcategories + '\'' + 
			"}";
		}
}