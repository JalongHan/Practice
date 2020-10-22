package com.jlhan.mvvm.dynamicupdate;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.jlhan.mvvm.BR;

public class ObSwordsman extends BaseObservable {
    private String name;
    private String level;

    public ObSwordsman(String name, String level) {
        this.name = name;
        this.level = level;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getLevel() {
        return level;
    }

    @Bindable
    public void setLevel(String level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }
}