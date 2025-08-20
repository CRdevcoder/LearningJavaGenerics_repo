package smallprojects.notepad;

import smallprojects.notepad.gui.AppWindow;

// App summary:
// App that allows you to open text files and edit them or create new text files.
// User Inputs: Open file, saving work onto file, saving work AS a new file.
// User Output: New txt file or modified txt file.

public class NotePadMain {

    public static void main(String[] args) {
        AppWindow app = new AppWindow("Java Text NotePad");
        app.setVisible(true);
    }
}
