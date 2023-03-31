import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ShutdownTimerGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField minutesField;
    private JButton submitButton;
    private JButton cancelButton;

    public ShutdownTimerGUI() {
        super("Shutdown Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter number of minutes:");
        minutesField = new JTextField(10);
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        panel.add(label);
        panel.add(minutesField);
        panel.add(submitButton);
        panel.add(cancelButton);
        
        submitButton.setBackground(new Color(255, 182, 193));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(100, 40));
        submitButton.setUI(new RoundButtonUI());

        cancelButton.setBackground(new Color(255, 182, 193));
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(100, 40));
        cancelButton.setUI(new RoundButtonUI());

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            int minutes = Integer.parseInt(minutesField.getText());
            String command = "shutdown -s -t " + (minutes * 60);
            try {
                Process process = Runtime.getRuntime().exec(command);
                JOptionPane.showMessageDialog(null, "Shutdown timer set for " + minutes + " minutes.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new ShutdownTimerGUI();
    }
}
