package com.example.naoto.testapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Naoto on 2015/01/02.
 */
public class Item implements Parcelable{

    private CharSequence mTitle;

    private CharSequence mDescription;

    private CharSequence mEnclosure;

    public Item(){
        this(null,null,null);
    }

    public Item(CharSequence title, CharSequence description, CharSequence enclosure){
        mTitle = title;
        mDescription = description;
        mEnclosure = enclosure;
    }

    public void setTitle(CharSequence title){
        mTitle = title;
    }

    public CharSequence getTitle(){
        return mTitle;
    }

    public void setDescription(CharSequence description){
        mDescription = description;
    }

    public CharSequence getDescription(){
        return mDescription;
    }

    public void setEnclosure(CharSequence enclosure){
        mEnclosure = enclosure;
    }

    public CharSequence getEnclosure(){
        return mEnclosure;
    }

    public String toString(){
        return "Title:"+mTitle+","+"Description:"+mDescription;
    }

    public static final Parcelable.Creator<Item> CREATOR
            = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Item(Parcel in){
        mTitle = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        mDescription = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(mTitle,dest,flags);
        TextUtils.writeToParcel(mDescription,dest,flags);
    }
}
