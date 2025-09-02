package smallprojects.notepad.gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import smallprojects.notepad.backend.TextFileManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

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

    // Handles current text file being used.
    TextFileManager currentFileManager;

    // window for notepad app.
    public AppWindow(String title)
    {
        super(title);

        // Is null at first.
        currentFileManager = null;

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

    // contructs currentFileManager with given path.
    private void setCurrentTextFile(Path p) throws Exception
    {
        currentFileManager = new TextFileManager(p);
    }

    // reads file in currentFileManager != null, and prints it.
    private void displayCurrentFile() throws IOException, NullPointerException
    {

        if(currentFileManager != null)
        {
            ArrayList<String> fileLines = currentFileManager.readFiletoStringArrayList(StandardCharsets.UTF_8);
            String content = "";

            if(fileLines.size() > 1){
                for (int i = 0; i < fileLines.size() - 1; i++) {
                content += fileLines.get(i) + "\n";
            }

                content += fileLines.get(fileLines.size()-1);
            }

            notepadArea.setText(content);

        }
        else
        {
            throw new NullPointerException("currentFileManager is null");
        }

    }

    // writes given string to file (Saving)
    private void saveStringToFile(String newContent) throws IOException, NullPointerException
    {
        System.out.println("Not null:"  + (currentFileManager != null));
        if(currentFileManager != null)
        {
            System.out.println("Writing content:\n" + newContent);
            BufferedWriter bw = currentFileManager.writeStringToFile(newContent, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
            bw.close();
            System.out.println("new content: \n" + currentFileManager.readFiletoStringArrayList(StandardCharsets.UTF_8));
        }
        else
        {
            throw new NullPointerException("currentFileManager is null");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(selectFileButton))
        {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("textFiles\\"));

            int response = chooser.showOpenDialog(null); // select file to open.


            if( response == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(chooser.getSelectedFile().getAbsolutePath());
                // transfer to path (java NIO API)
                Path path = file.toPath();
                // print path directory
                System.out.println("Selected File path: "+path);

                try {
                    setCurrentTextFile(path);
                } catch (Exception em) {
                    System.out.println("Selected file was rejected...");
                    System.out.println(em.getMessage());
                }

                
                try {
                    System.out.println("Displaying text file: " + path);
                    displayCurrentFile();
                } 
                catch (IOException ei) {
                    System.out.println(ei.getMessage());
                }
                catch (NullPointerException en) {
                    System.out.println(en.getMessage());
                }
                catch(Exception em)
                {
                    System.out.println(em.getMessage());
                }
            }
        }
        // saving test to button.
        else if(e.getSource().equals(saveButton))
        {
            String currentNoteText = notepadArea.getText();
            
            try {
                //System.out.println("Saving content to file\n:" + currentNoteText);
                this.saveStringToFile(currentNoteText);
                // display to test.
            } catch (Exception em) {
                System.out.println(em.getMessage());
            }

            try {
                displayCurrentFile(); // Updates TextArea, so it displays saved work. 
            } catch (Exception ev) {
                System.out.println(ev.getMessage());
            }

        }
        
    }

    

}
