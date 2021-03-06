package com.example.commerce.model.product;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class CategoriesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("slug")
	private String slug;

	@SerializedName("image")
	private ImagesItem mImagesItem;

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

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public ImagesItem getImagesItem() {
		return mImagesItem;
	}

	public void setImagesItem(ImagesItem imagesItem) {
		mImagesItem = imagesItem;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}