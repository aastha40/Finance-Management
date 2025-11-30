package com.example.financemanagement.model;

public class Category {
    private String id;
    private String name;
    private String type;
    private String iconName;
    private String colorHex;

    public Category() {}

    public Category(String id, String name, String type, String iconName, String colorHex) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.iconName = iconName;
        this.colorHex = colorHex;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getIconName() { return iconName; }
    public void setIconName(String iconName) { this.iconName = iconName; }
    public String getColorHex() { return colorHex; }
    public void setColorHex(String colorHex) { this.colorHex = colorHex; }
}
