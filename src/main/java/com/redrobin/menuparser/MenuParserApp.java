package com.redrobin.menuparser;

public class MenuParserApp {
	public static void main(String[] args) {
		MenuParser mp = new MenuParser();

//		System.out.println("************ get all restaurants *************");
//		for (Restaurant r : mp.getAllRestaurants()) {
//		    System.out.println(r.toString());
//		}
//
		System.out.println();
		System.out.println("************ get single restaurant *************");
		System.out.println(mp.getSingleRestarurant("211").toString());
        
		
//		System.out.println();
//        System.out.println("************ get all menu items *************");
//		for (MenuItem menuItem : mp.getAllMenuItems()) {
//            System.out.println(menuItem.toString());
//		}

//        System.out.println();
//        System.out.println("************ get single menu item : dc321d4c-38f4-4346-ab47-b08c1321aada *************");
//        System.out.println(mp.getSingleMenuItem("dc321d4c-38f4-4346-ab47-b08c1321aada"));
//        
//        System.out.println();
//        System.out.println("************ get parent of menu item dc321d4c-38f4-4346-ab47-b08c1321aada *************");
//        System.out.println(mp.getParentOfMenuItem("dc321d4c-38f4-4346-ab47-b08c1321aada"));
        
        
        
//        System.out.println();
//        System.out.println("************ get all categories *************");
//		for (Category category : mp.getAllCategories()) {
//		    System.out.println(category.toString());
//		}
//
//        System.out.println();
//        System.out.println("************ get single category *************");
//        System.out.println(mp.getSingleCategory("75604365-ff60-4236-abaf-119a2c3e4031"));
//
//        System.out.println();
//        System.out.println("************ get parent of category *************");
//        System.out.println(mp.getParentOfCategory("75604365-ff60-4236-abaf-119a2c3e4031"));
		

        System.out.println();
        System.out.println("************* get all franchises ***************");
        for (Franchise f : mp.getAllFranchises()) {
            System.out.println(f.toString());

        }

        System.out.println();
        System.out.println("************* get all menu codes ***************");
        for (MenuCode mc : mp.getAllMenuCodes()) {
            System.out.println(mc.toString());

        }
	}
}
