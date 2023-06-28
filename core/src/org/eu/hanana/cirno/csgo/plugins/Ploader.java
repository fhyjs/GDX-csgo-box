package org.eu.hanana.cirno.csgo.plugins;

import org.eu.hanana.cirno.csgo.Gobox;
import org.eu.hanana.cirno.csgo.api.Plugin;
import org.eu.hanana.cirno.csgo.gui.BarScreen;
import org.eu.hanana.cirno.csgo.gui.LoadingScreen;

@Plugin(id = "loader")
public class Ploader {
    public Ploader(){
        Gobox.getInstance().addScreen("bar",new BarScreen());
        Gobox.getInstance().addScreen("loading",new LoadingScreen());
    }
}
