/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import Utils.SchoolUtils;

/**
 * Class that contains the code to generate the Main Menu of the application.
 *
 * @author Nikos Syrios
 */
public class MainMenu {

    /**
     * It prints the initial dialogue for the application. It asks the user
     * whether they want to add data or use some mock data
     */
    public static void generateMenu() {

        System.out.println("REMINDER: Before you run this app you need to have the appropriate schema and the appropriate entries in your local DB.");
        System.out.println("DON'T forget to change the DB credentials in the Utils.DBUtils.java");
        System.out.println("Are you ready to start? y/n");
        while (!SchoolUtils.getPolarDialogue()) {
            System.out.println("You know there is no other option, right? Just hit y.");
        }
        DataViewAddMenu.generateMenu();

    }

}
