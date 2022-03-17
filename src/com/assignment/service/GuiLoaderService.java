package com.assignment.service;

import com.assignment.gui.HomeWindow;

/**
 * An implementation of UserInterfaceLoader for the purpose of graphical UI
 */
public class GuiLoaderService implements UserInterfaceLoader {

    private ChampionshipManager championshipManager;

    public GuiLoaderService() {
        //inject Formula1ChampionshipManager instance
        this.championshipManager = new Formula1ChampionshipManager();
    }

    @Override
    public void doLoadUserInterface() {
        HomeWindow homeWindow = new HomeWindow(championshipManager);
        homeWindow.setVisible(true);
    }
}
