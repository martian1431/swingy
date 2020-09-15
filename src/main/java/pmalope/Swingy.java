package pmalope;

import pmalope.model.GameModel;
import pmalope.utils.Globals;
import pmalope.view.console.ConsoleView;
import pmalope.view.gui.GUIView;
import pmalope.utils.Colors;
import pmalope.utils.Log;

public class Swingy {
    public static void main(String[] args) {
        try {
            String view = args[0];
            GameModel.getInstance().setupDatabase();
            if (view.toLowerCase().equals("console")) {
                ConsoleView.run();
            } else if (view.toLowerCase().equals("gui")) {
                Globals.CONSOLE_MODE = false;
                GUIView.run();
            } else {
                Log.log(Colors.ANSI_RED + ":::ERROR::: choose only console or gui" + Colors.ANSI_RESET);
            }
        } catch (Exception e) {
            Log.log(Colors.ANSI_RED + ":::ERROR::: choose only console or gui" + Colors.ANSI_RESET);
        }
    }
}
