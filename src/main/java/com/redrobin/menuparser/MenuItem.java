package com.redrobin.menuparser;

import java.sql.Timestamp;
import java.util.Objects;

public class MenuItem {
    private String menuItemId;
    private String menuItemAltId;
    private String menuItemParentId;
    private String name;
    private String description;
    private String shortDescription;
    private Boolean advisoryIndicator;
    private Timestamp lastUpdated;

    public String getMenuItemAltId() {
        return menuItemAltId;
    }

    public void setMenuItemAltId(String menuItemAltId) {
        this.menuItemAltId = menuItemAltId;
    }

    public String getMenuItemParentId() {
        return menuItemParentId;
    }

    public void setMenuItemParentId(String menuItemParentId) {
        this.menuItemParentId = menuItemParentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String id) {
        this.menuItemId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Boolean getAdvisoryIndicator() {
        return advisoryIndicator;
    }

    public void setAdvisoryIndicator(Boolean advisoryIndicator) {
        this.advisoryIndicator = advisoryIndicator;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdate) {
        this.lastUpdated = lastUpdate;
    }
    
    @Override
    public String toString() {
        return "NAME=" + name + "  ID=" + menuItemId + "  ALT ID=" + menuItemAltId + "  PARENT ID=" + menuItemParentId + "  DESCRIPTION=" + description + "  SHORT DESCRIPTION=" + shortDescription + "  ADVISORY INDICATOR=" + advisoryIndicator;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            
        if (!(o instanceof MenuItem)) {
            return false;
        }
        
        MenuItem that = (MenuItem) o;
        return (
                Objects.equals(this.advisoryIndicator, that.getAdvisoryIndicator()) &&
                Objects.equals(this.description, that.getDescription()) &&
                Objects.equals(this.menuItemId, that.getMenuItemId()) &&
                Objects.equals(this.menuItemParentId, that.getMenuItemParentId()) &&
                Objects.equals(this.lastUpdated.getTime(), that.getLastUpdated().getTime()) &&
                Objects.equals(this.name, that.getName()) &&
                Objects.equals(this.shortDescription, that.getShortDescription()) &&
                Objects.equals(this.menuItemAltId, that.getMenuItemAltId())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(advisoryIndicator, description, menuItemId, menuItemAltId, menuItemParentId, lastUpdated.getTime(), name, shortDescription);
    }
}
