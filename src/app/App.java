package app;

import javax.swing.SwingUtilities;
import ui.presenters.MainViewPresenter;
import ui.views.MainView;


public class App {

    public App() {

        MainView mv = new MainView();
        new MainViewPresenter(mv);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}

