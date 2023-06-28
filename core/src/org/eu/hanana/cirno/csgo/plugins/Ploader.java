package org.eu.hanana.cirno.csgo.plugins;

import org.eu.hanana.cirno.csgo.Config;
import org.eu.hanana.cirno.csgo.Gobox;
import org.eu.hanana.cirno.csgo.api.BtnCallback;
import org.eu.hanana.cirno.csgo.api.Plugin;
import org.eu.hanana.cirno.csgo.gui.BarScreen;
import org.eu.hanana.cirno.csgo.gui.LoadingScreen;
import org.eu.hanana.cirno.csgo.res.I18n;
import org.eu.hanana.cirno.csgo.screen.Sutil;

import java.net.URISyntaxException;

@Plugin(id = "cirno")
public class Ploader {
    public static String LS;
    public Ploader(){
        Gobox.getInstance().addScreen("bar",new BarScreen());
        Gobox.getInstance().addScreen("loading",new LoadingScreen());
        new Sutil();
        new work().start();
    }
    private static class work extends Thread{
        @Override
        public void run(){
            try {
                LS="starting config";
                Config.Sync();
                LS="starting i18n";
                I18n.init();
                LS="adding menu items";
                BarScreen.getInstance().addbtn(I18n.get("menu.conn"), () -> {
                    System.out.print(21);
                },0.1f);
                BarScreen.getInstance().addbtn("11", () -> {
                    System.out.print(21);
                },0.1f);

                LS="Finishing";
                Gobox.getInstance().rmScreen("loading");
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
