package com.redrobin.menuparser;

import java.sql.Timestamp;
import java.util.Objects;

public class Restaurant {
    private String storeGuid; // guid that identifies this restaurant
    private String franchiseGuid; // guid of the franchise associated with the restaurant
    private String storeNumber; // Red Robin store number
    private String name; // Red Robin store name
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    private String latitude;
    private String longitude;
    private String phone;
    private String fax;
    private String email;
    private Timestamp openDate;
    private Timestamp closeDate;
    private Timestamp lastUpdated;
    private Boolean togoIndicator;
    private Boolean activeIndicator;
    private String mainMenuId;
    private String kidsMenuId;
    private String drinksMenuId;
    
    public String getStoreGuid() {
        return storeGuid;
    }

    public void setStoreGuid(String storeId) {
        this.storeGuid = storeId;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Timestamp openDate) {
        this.openDate = openDate;
    }

    public Timestamp getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getFranchiseGuid() {
        return franchiseGuid;
    }

    public void setFranchiseGuid(String franchiseId) {
        this.franchiseGuid = franchiseId;
    }

    public Boolean getTogoIndicator() {
        return togoIndicator;
    }

    public void setTogoIndicator(Boolean togoIndicator) {
        this.togoIndicator = togoIndicator;
    }

    public Boolean getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(Boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    public String getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(String mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    public String getKidsMenuId() {
        return kidsMenuId;
    }

    public void setKidsMenuId(String kidsMenuId) {
        this.kidsMenuId = kidsMenuId;
    }

    public String getDrinksMenuId() {
        return drinksMenuId;
    }

    public void setDrinksMenuId(String drinksMenuId) {
        this.drinksMenuId = drinksMenuId;
    }

    @Override
    public String toString() {
        return "NAME=" + name + "  STORE NUMBER=" + storeNumber + "  STORE GUID=" + storeGuid + "  FRANCHISE GUID=" + franchiseGuid + "  MENU CODE ID=" + mainMenuId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            
        if (!(o instanceof Restaurant)) {
            return false;
        }
        
        Restaurant that = (Restaurant) o;
        return (
                Objects.equals(this.activeIndicator, that.getActiveIndicator()) &&
                Objects.equals(this.togoIndicator, that.getTogoIndicator()) &&
                Objects.equals(this.address1, that.getAddress1()) &&
                Objects.equals(this.address2, that.getAddress2()) &&
                Objects.equals(this.city, that.getCity()) &&
                Objects.equals(this.email, that.getEmail()) &&
                Objects.equals(this.fax, that.getFax()) &&
                Objects.equals(this.franchiseGuid, that.getFranchiseGuid()) &&
                Objects.equals(this.latitude, that.getLatitude()) &&
                Objects.equals(this.longitude, that.getLongitude()) &&
                Objects.equals(this.name, that.getName()) &&
                Objects.equals(this.phone, that.getPhone()) &&
                Objects.equals(this.state, that.getState()) &&
                Objects.equals(this.storeGuid, that.getStoreGuid()) &&
                Objects.equals(this.storeNumber, that.getStoreNumber()) &&
                Objects.equals(this.zipcode, that.getZipcode()) &&
                Objects.equals(this.closeDate.getTime(), getCloseDate().getTime()) &&
                Objects.equals(this.lastUpdated.getTime(), that.getLastUpdated().getTime())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeIndicator, togoIndicator, address1, address2, city, email, fax, franchiseGuid, latitude, longitude, name, phone, state, storeGuid, storeNumber, zipcode, closeDate, lastUpdated);
    }
}
