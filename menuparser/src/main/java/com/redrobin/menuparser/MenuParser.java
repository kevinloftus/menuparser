package com.redrobin.menuparser;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuParser {
    private Document xmlDoc;

    private List<Restaurant> allRestaurants = new ArrayList<Restaurant>();
    private List<MenuItem> allMenuItems = new ArrayList<MenuItem>();
    private List<Category> allCategories = new ArrayList<Category>();
    private List<Franchise> allFranchises = new ArrayList<Franchise>();
    private List<MenuCode> allMenuCodes = new ArrayList<MenuCode>();

    public MenuParser() {
        loadXmlDoc("mainmenu.xml");
        getAllRestaurants();
        getAllCategories();
        getAllFranchises();
        getAllMenuItems();
        getAllMenuCodes();
    }

    public void loadXmlDoc(String fileName) {
        try {
            File inFile = new File(this.getClass().getClassLoader().getResource(fileName).toURI());

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = dBuilder.parse(inFile);
        }
        catch (Exception e) {
        }
    }

    public void resetRestaurants() {
        allRestaurants = new ArrayList<Restaurant>();
    }

    public void resetMenuItems() {
        allMenuItems = new ArrayList<MenuItem>();
    }

    public void resetCategories() {
        allCategories = new ArrayList<Category>();
    }

    public void resetFranchises() {
        allFranchises = new ArrayList<Franchise>();
    }

    public void resetMenuCodes() {
        allMenuCodes = new ArrayList<MenuCode>();
    }

    public List<Restaurant> getAllRestaurants() {
        if (allRestaurants.size() == 0) {
            NodeList restaurantElements = xmlDoc.getElementsByTagName("unit");
            for (int i = 0; i < restaurantElements.getLength(); i++) {
                Node restaurantNode = restaurantElements.item(i);
                if (restaurantNode.getNodeType() == Node.ELEMENT_NODE) {
                    Restaurant restaurant = new Restaurant();
                    Node franchiseNode = restaurantNode.getParentNode().getParentNode().getParentNode();
                    restaurant.setFranchiseGuid(franchiseNode.getAttributes().getNamedItem("franchise-guid").getNodeValue());

                    MenuCode menuCode = findMenuCodeForRestaurant(restaurant, restaurantNode.getParentNode().getParentNode());
                    restaurant.setMainMenuId(menuCode.getId());
                    parseRestaurantAttributes(restaurant, restaurantNode);
                    parseRestaurantData(restaurant, restaurantNode);
                    allRestaurants.add(restaurant);
                }
            }
        }
        return allRestaurants;
    }

    private MenuCode findMenuCodeForRestaurant(Restaurant restaurant, Node menuCodeNode) {
        MenuCode menuCode = new MenuCode();
        for (MenuCode mc : getAllMenuCodes()) {
            if (mc.getId().equals(menuCodeNode.getAttributes().getNamedItem("menu-code-guid").getNodeValue())) {
                return mc;
            }
        }
        return menuCode;
    }

    private void parseRestaurantData(Restaurant restaurant, Node restaurantNode) {
        NodeList childNodes = restaurantNode.getChildNodes();
        for (int j = 0; j < childNodes.getLength(); j++) {
            Node child = childNodes.item(j);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                switch (child.getNodeName().toLowerCase()) {
                case "name":
                    restaurant.setName(extractNodeText(child));
                    break;
                case "address1":
                    restaurant.setAddress1(extractNodeText(child));
                    break;
                case "address2":
                    restaurant.setAddress2(extractNodeText(child));
                    break;
                case "city":
                    restaurant.setCity(extractNodeText(child));
                    break;
                case "zip":
                    restaurant.setZipcode(extractNodeText(child));
                    break;
                case "latitude":
                    restaurant.setLatitude(extractNodeText(child));
                    break;
                case "longitude":
                    restaurant.setLongitude(extractNodeText(child));
                    break;
                case "phone":
                    restaurant.setPhone(extractNodeText(child));
                    break;
                case "fax":
                    restaurant.setFax(extractNodeText(child));
                    break;
                case "email":
                    restaurant.setEmail(extractNodeText(child));
                    break;
                case "togo-ind":
                    restaurant.setTogoIndicator(extractNodeBoolean(child));
                    break;
                case "active-ind":
                    restaurant.setActiveIndicator(extractNodeBoolean(child));
                    break;
                case "open-date":
                    restaurant.setOpenDate(convertDateStringToTimestamp(child.getTextContent()));
                    break;
                case "close-date":
                    restaurant.setCloseDate(convertDateStringToTimestamp(child.getTextContent()));
                    break;
                case "update-dt-tm":
                    restaurant.setLastUpdated(convertDateStringToTimestamp(child.getTextContent()));
                    break;
                case "store-id":
                    restaurant.setStoreNumber(extractNodeText(child));
                    break;
                }
            }
        }
    }

    private void parseRestaurantAttributes(Restaurant restaurant, Node restaurantNode) {
        NamedNodeMap attrib = restaurantNode.getAttributes();
        for (int j = 0; j < attrib.getLength(); j++) {
            if (attrib.item(j).getNodeName().toLowerCase().equals("unit-guid")) {
                restaurant.setStoreGuid(attrib.item(j).getNodeValue());
            }
        }
    }

    public Restaurant getSingleRestarurant(String storeId) {
        for (Restaurant r : getAllRestaurants()) {
            if (r.getStoreNumber().equals(storeId)) {
                return r;
            }
        }
        return new Restaurant();
    }

    public List<Restaurant> getAllRestarurantsForFranchise(String franchiseId) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        for (Restaurant r : getAllRestaurants()) {
            if (r.getFranchiseGuid().equals(franchiseId)) {
                restaurants.add(r);
            }
        }
        return restaurants;
    }

    public List<MenuItem> getAllMenuItems() {
        if (allMenuItems.size() == 0) {
            NodeList onlineItemsNodeList = xmlDoc.getElementsByTagName("online-items");
            Node onlineItemsNode = onlineItemsNodeList.item(0);

            NodeList allOnlineItems = onlineItemsNode.getChildNodes();
            for (int i = 0; i < allOnlineItems.getLength(); i++) {
                Node onlineItem = allOnlineItems.item(i);
                if (onlineItem.getNodeType() == Node.ELEMENT_NODE) {
                    MenuItem menuItem = new MenuItem();
                    parseMenuItemAttributes(onlineItem, menuItem);
                    parseMenuItemData(onlineItem, menuItem);
                    allMenuItems.add(menuItem);
                }
            }
        }
        return allMenuItems;
    }

    public MenuItem getSingleMenuItem(String menuItemId) {
        for (MenuItem mi : getAllMenuItems()) {
            if (mi.getMenuItemId().equals(menuItemId)) {
                return mi;
            }
        }
        return new MenuItem();
    }

    public MenuItem getParentOfMenuItem(String menuItemId) {
        MenuItem foundMenuItem = getSingleMenuItem(menuItemId);
        if (!foundMenuItem.getMenuItemParentId().isEmpty()) {
            return getSingleMenuItem(foundMenuItem.getMenuItemParentId());
        }
        return new MenuItem();
    }

    private void parseMenuItemData(Node onlineItem, MenuItem menuItem) {
        NodeList childNodeList = onlineItem.getChildNodes();
        for (int j = 0; j < childNodeList.getLength(); j++) {
            Node cNode = childNodeList.item(j);
            switch (cNode.getNodeName().trim().toLowerCase()) {
            case "name":
                menuItem.setName(extractNodeText(cNode));
                break;
            case "description":
                menuItem.setDescription(extractNodeText(cNode));
                break;
            case "short-description":
                menuItem.setShortDescription(extractNodeText(cNode));
                break;
            case "advisory-ind":
                menuItem.setAdvisoryIndicator(extractNodeBoolean(cNode));
                break;
            case "update-dt-tm":
                menuItem.setLastUpdated(convertDateStringToTimestamp(cNode.getTextContent()));
                break;
            }

        }
    }

    private void parseMenuItemAttributes(Node onlineItem, MenuItem menuItem) {
        NamedNodeMap onlineItemAttribs = onlineItem.getAttributes();
        for (int attribCount = 0; attribCount < onlineItemAttribs.getLength(); attribCount++) {
            switch (onlineItemAttribs.item(attribCount).getNodeName().toLowerCase()) {
            case "online-item-guid":
                menuItem.setMenuItemId(onlineItemAttribs.item(attribCount).getNodeValue());
                break;
            case "menu-item-variant-guid":
                menuItem.setMenuItemAltId(onlineItemAttribs.item(attribCount).getNodeValue());
                break;
            case "parent-online-item-guid":
                menuItem.setMenuItemParentId(onlineItemAttribs.item(attribCount).getNodeValue());
                break;
            }
        }
    }

    public List<Category> getAllCategories() {
        if (allCategories.size() == 0) {
            NodeList onlineCategoriesNodeList = xmlDoc.getElementsByTagName("online-categories");
            Node onlineCategoriesNode = onlineCategoriesNodeList.item(0);

            NodeList allOnlineCategories = onlineCategoriesNode.getChildNodes();
            for (int i = 0; i < allOnlineCategories.getLength(); i++) {
                Node onlineCategory = allOnlineCategories.item(i);
                if (onlineCategory.getNodeType() == Node.ELEMENT_NODE) {
                    Category category = new Category();
                    parseCategoryAttributes(onlineCategory, category);
                    parseCategoryData(onlineCategory, category);
                    allCategories.add(category);
                }
            }
        }
        return allCategories;
    }

    public Category getSingleCategory(String categoryId) {
        for (Category c : getAllCategories()) {
            if (c.getId().equals(categoryId)) {
                return c;
            }
        }
        return new Category();
    }

    public Category getParentOfCategory(String categoryId) {
        Category foundCategory = getSingleCategory(categoryId);

        if (!foundCategory.getParentId().isEmpty()) {
            return getSingleCategory(foundCategory.getParentId());
        }

        return new Category();
    }

    private void parseCategoryAttributes(Node onlineCategory, Category category) {
        NamedNodeMap onlineCategoryAttribs = onlineCategory.getAttributes();
        for (int attribCount = 0; attribCount < onlineCategoryAttribs.getLength(); attribCount++) {
            switch (onlineCategoryAttribs.item(attribCount).getNodeName().toLowerCase()) {
            case "online-category-guid":
                category.setId(onlineCategoryAttribs.item(attribCount).getNodeValue());
                break;
            case "parent-online-category-guid":
                category.setParentId(onlineCategoryAttribs.item(attribCount).getNodeValue());
                break;
            }
        }

    }

    private void parseCategoryData(Node onlineCategory, Category category) {
        NodeList childNodeList = onlineCategory.getChildNodes();
        for (int j = 0; j < childNodeList.getLength(); j++) {
            Node cNode = childNodeList.item(j);
            switch (cNode.getNodeName().trim().toLowerCase()) {
            case "name":
                category.setName(extractNodeText(cNode));
                break;
            case "description":
                category.setDescription(extractNodeText(cNode));
                break;
            case "update-dt-tm":
                category.setLastUpdated(convertDateStringToTimestamp(cNode.getTextContent()));
                break;
            }
        }
    }

    public List<Franchise> getAllFranchises() {
        if (allFranchises.size() == 0) {
            NodeList franchiseElements = xmlDoc.getElementsByTagName("franchise");
            for (int i = 0; i < franchiseElements.getLength(); i++) {
                Node franchiseNode = franchiseElements.item(i);
                if (franchiseNode.getNodeType() == Node.ELEMENT_NODE) {
                    Franchise franchise = new Franchise();
                    franchise.setId(franchiseNode.getAttributes().getNamedItem("franchise-guid").getNodeValue());
                    parseFranchiseData(franchise, franchiseNode);
                    allFranchises.add(franchise);
                }
            }
        }
        return allFranchises;
    }

    private void parseFranchiseData(Franchise franchise, Node franchiseNode) {
        NodeList childNodeList = franchiseNode.getChildNodes();
        for (int j = 0; j < childNodeList.getLength(); j++) {
            Node cNode = childNodeList.item(j);
            switch (cNode.getNodeName().trim().toLowerCase()) {
            case "name":
                franchise.setName(extractNodeText(cNode));
                break;
            case "update-dt-tm":
                franchise.setLastUpdated(convertDateStringToTimestamp(cNode.getTextContent()));
                break;
            }
        }

    }

    public List<MenuCode> getAllMenuCodes() {
        if (allMenuCodes.size() == 0) {
            NodeList menuCodeElements = xmlDoc.getElementsByTagName("menu-code");
            for (int i = 0; i < menuCodeElements.getLength(); i++) {
                Node menuCodeNode = menuCodeElements.item(i);
                if (menuCodeNode.getNodeType() == Node.ELEMENT_NODE) {
                    MenuCode menuCode = new MenuCode();
                    menuCode.setId(menuCodeNode.getAttributes().getNamedItem("menu-code-guid").getNodeValue());
                    parseMenuCodeData(menuCode, menuCodeNode);
                    allMenuCodes.add(menuCode);
                }
            }

        }
        return allMenuCodes;
    }

    private void parseMenuCodeData(MenuCode menuCode, Node menuCodeNode) {
        NodeList childNodeList = menuCodeNode.getChildNodes();
        for (int j = 0; j < childNodeList.getLength(); j++) {
            Node cNode = childNodeList.item(j);
            switch (cNode.getNodeName().trim().toLowerCase()) {
            case "name":
                menuCode.setName(extractNodeText(cNode));
                break;
            }
        }

    }
    // --------------------------------------------------------------------------------------------------------
    private Boolean extractNodeBoolean(Node child) {
        return Boolean.valueOf(child.getTextContent().length() > 0 ? child.getTextContent().trim() : "false");
    }

    private String extractNodeText(Node child) {
        return child.getTextContent().length() > 0 ? child.getTextContent().trim() : "";
    }

    private Timestamp convertDateStringToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(dateString);
            return new java.sql.Timestamp(parsedDate.getTime());
        }
        catch (Exception e) {
            return null;
        }
    }
    // --------------------------------------------------------------------------------------------------------
}
