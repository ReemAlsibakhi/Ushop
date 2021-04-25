package com.reem.ushop.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Subcategories implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("subcategories")
	private List<Object> subcategories;

	protected Subcategories(Parcel in) {
		image = in.readString();
		name = in.readString();
		id = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeString(name);
		dest.writeInt(id);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Subcategories> CREATOR = new Creator<Subcategories>() {
		@Override
		public Subcategories createFromParcel(Parcel in) {
			return new Subcategories(in);
		}

		@Override
		public Subcategories[] newArray(int size) {
			return new Subcategories[size];
		}
	};

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