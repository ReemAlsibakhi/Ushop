package com.reem.ushop.pojo;

import android.os.Parcel;
import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class Subcategories implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("subcategories")
	private ArrayList<Subcategories> subcategories;

	protected Subcategories(Parcel in) {
		image = in.readString();
		name = in.readString();
		id = in.readInt();
	}

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

	public void setSubcategories(ArrayList<Subcategories> subcategories){
		this.subcategories = subcategories;
	}

	public ArrayList<Subcategories> getSubcategories(){
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