package com.reem.ushop.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Subcategories {

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("subcategories")
	private List<Object> subcategories;

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

	public void setSubcategories(List<Object> subcategories){
		this.subcategories = subcategories;
	}

	public List<Object> getSubcategories(){
		return subcategories;
	}

	@Override
 	public String toString(){
		return 
			"SubcategoriesItem{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",subcategories = '" + subcategories + '\'' + 
			"}";
		}
}