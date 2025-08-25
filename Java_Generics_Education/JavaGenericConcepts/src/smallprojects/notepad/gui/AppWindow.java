package smallprojects.notepad.gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Class Summary: Window class that creates and controls all GUI components for notepad app.
// Composition: 1 JFileChooser, 3 JButtons, 2 JPanels, 1 JTextArea

public class AppWindow extends JFrame implements ActionListener{

    //Components:
    JFileChooser chooser;
    JButton saveButton;
    JButton selectFileButton;
    JButton saveAsButton;

    // text area
    JTextArea notepadArea;

    // Jpanels:
    JPanel buttonPanel;
    JPanel notePanel;

    // window for notepad app.
    public AppWindow(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BoxLayout(null,BoxLayout.Y_AXIS)); // layout
        this.setLayout(new BorderLayout());

        this.setUpComponents();

        this.pack();
        
    }

    private void setUpComponents()
    {
        // button set up.
        saveButton = new JButton("SAVE");
        saveAsButton = new JButton("SAVE AS");
        selectFileButton = new JButton("Open File");
        // text Area.
        notepadArea = new JTextArea(30,40);

        // this window class handles all action events.
        saveButton.addActionListener(this);
        saveAsButton.addActionListener(this);
        selectFileButton.addActionListener(this);
        
        // panels
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notePanel = new JPanel();

        // adding to button panel.
        buttonPanel.add(saveButton);
        buttonPanel.add(saveAsButton);
        buttonPanel.add(selectFileButton);
        // adding to note panel.
        notePanel.add(notepadArea);
        // adding to window.
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(notePanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(selectFileButton))
        {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("notepadAppStorage\\"));

            int response = chooser.showOpenDialog(null); // select file to open.


            if( response == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(chooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        }

        if(e.getSource().equals(saveButton))
        {
            System.out.println(notepadArea.getText());

        }
        
    }

    

}
