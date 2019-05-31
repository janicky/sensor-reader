import javax.swing.*;
import java.awt.event.ActionListener;

public class View {

    private JFrame frame;
    private JPanel mainPanel;
    private JTable table;
    private JMenuItem file_item_1;

    public View(String title) {
        frame = new JFrame(title);
        createMenu();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

//        File
        JMenu file = new JMenu("File");
        menuBar.add(file);

        file_item_1 = new JMenuItem("Select source");

        file.add(file_item_1);
        frame.setJMenuBar(menuBar);
    }

    public void addSelectSourceListener(ActionListener listener) {
        file_item_1.addActionListener(listener);
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
