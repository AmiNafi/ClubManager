package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;
import util.LoginConfirm;
import util.Transfer;

import java.io.File;
import java.io.IOException;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }
    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginConfirm) {
                        LoginConfirm loginConfirm = (LoginConfirm) o;

                        /*
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        */
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginConfirm.getStatus()) {

                                    try {
                                        main.setClientName(loginConfirm.getClubName());
                                        String fName = loginConfirm.getClubName();
                                        fName = fName.replaceAll("\\s+", "");
                                        fName = fName.toLowerCase();
                                        fName += ".png";
                                        //System.out.println("here fname " + fName);
                                        Image image = new Image(getClass().getResourceAsStream("/img/" + fName));
                                        main.setClientLogo(image);
                                        main.showHomePage(loginConfirm.getClubName(), loginConfirm.getMarketPlayers(), loginConfirm.getCurrentClubPlayers());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (!loginConfirm.getLogout()){
                                    main.showAlertIncorrectPassword();
                                }

                            }
                        });
                    }
                    else if (o instanceof Transfer) {
                        Transfer t= (Transfer) o;
                       // System.out.println(t.getFrom() + " " + t.getPlayer().getMarket() + " " + t.getCurrentClubPlayers().size() + " read thread " + t.getMarketPlayers().size() + " this " + main.getNetworkUtil());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.getHomeController().setData(main.getClientName(), t.getCurrentClubPlayers(), t.getMarketPlayers(), main.getHomeController().getState());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



