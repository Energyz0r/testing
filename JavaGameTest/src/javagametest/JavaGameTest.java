package javagametest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaGameTest extends JFrame {

    public JavaGameTest() {
        setTitle("Game Menu");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, spacing

        // Create buttons
        JButton playButton = new JButton("Play");
        JButton optionsButton = new JButton("Options");
        JButton quitButton = new JButton("Quit");

        // Style buttons a bit
        Font font = new Font("Arial", Font.BOLD, 24);
        playButton.setFont(font);
        optionsButton.setFont(font);
        quitButton.setFont(font);

        // Add actions (mouse clicks)
        playButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Starting game..."));
        optionsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Options menu..."));
        quitButton.addActionListener(e -> System.exit(0));

        // Add buttons to window
        add(playButton);
        add(optionsButton);
        add(quitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaGameTest::new);
    }
}
