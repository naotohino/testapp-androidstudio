package com.example.naoto.testapp;

/**
 * Created by Naoto on 2015/01/02.
 */
public class Item {

    private CharSequence mTitle;

    private CharSequence mDescription;

    public Item(){
        this(null,null);
    }

    public Item(CharSequence title, CharSequence description){
        mTitle = title;
        mDescription = description;
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

    public String toString(){
        return "Title:"+mTitle+","+"Description:"+mDescription;
    }
}
