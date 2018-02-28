/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triagegrading;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author user1
 */
public class NavigationMenu extends TreeView {
    public NavigationMenu() {
        createNavigationMenu();
    }
    
    private void createNavigationMenu() { 
        TreeItem<String> root = createRootMenuItem();
        createAndAddIntroductionMenuItem(root);
        createAndAddTrackingMenuItems(root);
        setRoot(root);
    }

    private void createAndAddTrackingMenuItems(TreeItem<String> root) {
        TreeItem<String> trackingItem = new TreeItem<> ("Grade Tracking");
        trackingItem.setExpanded(true);
        TreeItem<String> tabularItem = new TreeItem<> ("Tabular");
        TreeItem<String> graphicalItem = new TreeItem<> ("Graph");
        trackingItem.getChildren().addAll(graphicalItem, tabularItem);
        root.getChildren().addAll(trackingItem);
    }

    private void createAndAddIntroductionMenuItem(TreeItem<String> root) {
        TreeItem<String> introductionItem = new TreeItem<> ("Introduction");
        root.getChildren().addAll(introductionItem);
    }

    private TreeItem<String> createRootMenuItem() {
        TreeItem<String> root = new TreeItem<> ("Navigation Menu");
        root.setExpanded(true);
        return root;
    }
}
