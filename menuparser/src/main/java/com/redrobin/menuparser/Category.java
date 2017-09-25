package com.redrobin.menuparser;

import java.sql.Timestamp;
import java.util.Objects;

public class Category {
    private String id;
    private String parentId;
    private String name;
    private String description;
    private Timestamp lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "NAME=" + name + "  ID=" + id + "  PARENT ID=" + parentId + "  DESCRIPTION=" + description; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Category)) {
            return false;
        }

        Category that = (Category) o;
        return (Objects.equals(this.id, that.getId()) && 
                Objects.equals(this.parentId, that.getParentId()) && 
                Objects.equals(this.name, that.getName()) && 
                Objects.equals(this.lastUpdated.getTime(), that.getLastUpdated().getTime()) && 
                Objects.equals(this.description, that.getDescription())
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, description, lastUpdated);
    }
}
