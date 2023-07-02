package com.example.app3;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "phones")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "manufacturer")
    @NonNull
    private String manufacturer;
    @ColumnInfo(name = "model")
    @NonNull
    private String model;
    @ColumnInfo(name = "android_version")
    @NonNull
    private String androidVersion;
    @ColumnInfo(name = "web_site")
    @NonNull
    private String webSite;

    public Phone(@NonNull String manufacturer, @NonNull String model, @NonNull String androidVersion, @NonNull String webSite) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.webSite = webSite;
    }
    @Ignore
    public Phone(long id, @NonNull String manufacturer, @NonNull String model, @NonNull String androidVersion, @NonNull String webSite) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.webSite = webSite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String wenSite) {
        this.webSite = webSite;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }
}
