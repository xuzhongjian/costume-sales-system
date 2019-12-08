package com.zjxu97.costume.utils;

public class Location {
    private String id;
    private String parentId;
    private String levelType;
    private String name;
    private String province;
    private String city;
    private String district;

    public Location() {
        this("", "", "", "", "", "", "");
    }

    public Location(String id, String parentId, String levelType, String name, String province, String city, String district) {
        this.id = id;
        this.parentId = parentId;
        this.levelType = levelType;
        this.name = name;
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getLevelType() {
        return levelType;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", levelType='" + levelType + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
