package support;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JCheckBoxMenuItem;

import java.io.File;




// for gui-related
import javax.swing.*;
// for opening/saving files
import javax.swing.filechooser.*;


public class FramePanel implements ActionListener
{
    private Frame window;
    private JPanel panel;
    private JTextArea edit;
    private File openFile;
    private JScrollPane scroll;
    private JFileChooser dialog;
    private Utilities util;

    // instantiate the panel
    public FramePanel(Frame caller)
    {
        // instantiate the utilites class and filechooser for function
        util = new Utilities();
        dialog = new JFileChooser();
        openFile = null;

        // instantiate the panel with borderlayout and frame
        panel = new JPanel();
        window = caller;
        panel.setLayout(new BorderLayout());

        // instantiate the scrollpanel and editarea
        edit = new JTextArea();
        scroll = new JScrollPane(edit);

        // customize editarea
        edit.setFont(new Font("Courier New", Font.PLAIN, 12));
        edit.setTabSize(4);

        // add the scrollpane with editarea in the panel
        panel.add(scroll, BorderLayout.CENTER);
    }

    // pass on panel to frame
    public JPanel getPanel() { return panel; }

    // listen for actions/clicks
    public void actionPerformed(ActionEvent e)
    {
        String query = e.getActionCommand();

        // if new was clicked > erase document to null
        if (query.equals("New")) {
            edit.setText(null);
            window.setName(null);
            openFile = null;
        }

        // if open was clicked > open new file
        else if (query.equals("Open"))
        {
            int result = dialog.showOpenDialog(dialog);

            if (result == JFileChooser.APPROVE_OPTION) {
                String fileName = util.openFile(edit, dialog.getSelectedFile());
                openFile = dialog.getSelectedFile();

                window.setName(fileName);
            }
        }
        // if save was clicked > save if new file; overwrite if existing
        else if (query.equals("Save"))
        {
            if (openFile != null && openFile.exists())
                util.saveFile(edit, openFile, true);
            else
            {
                int ret = dialog.showSaveDialog(dialog);

                if (ret == JFileChooser.APPROVE_OPTION) {
                    String name = util.saveFile(edit, dialog.getSelectedFile(), false);
                    openFile = dialog.getSelectedFile();
                }

                window.setName(openFile.getName());
            }
        }
        // if exit was clicked > exit
        else if (query.equals("Exit"))
            window.dispose();
        // if select all was clicked > select all text
        else if (query.equals("Select All"))
            edit.selectAll();
        // if time/date was clicked > append time and date.
        else if (query.equals("Time/Date"))
            edit.append(util.getDate());
        // if word wrap was selected > set editable to word wrap t/f
        else if (query.equals("Word Wrap"))
        {
            JCheckBoxMenuItem opt = (JCheckBoxMenuItem)e.getSource();
            edit.setLineWrap(opt.isSelected());
        }
        // if font was selected > set editable.font to selected font
        else if (query.equals("Font"))
            util.changeFont(edit);
    }
}