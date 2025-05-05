package Rushabh_Shah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}

public class Login extends JFrame implements ActionListener {
    JLabel lblUsername, lblPassword, lblMessage;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin, btnClear;
    int attempts = 0;

    public Login() {
        setTitle("Swing Login Screen");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the frame

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 40, 100, 30);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 40, 150, 30);
        add(txtUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 80, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 80, 150, 30);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(80, 130, 80, 30);
        btnLogin.addActionListener(this);
        add(btnLogin);

        btnClear = new JButton("Clear");
        btnClear.setBounds(180, 130, 80, 30);
        btnClear.addActionListener(this);
        add(btnClear);

        lblMessage = new JLabel("");
        lblMessage.setBounds(50, 170, 300, 30);
        lblMessage.setForeground(Color.RED);
        add(lblMessage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            try {
                String user = txtUsername.getText();
                String pass = new String(txtPassword.getPassword());

                if (attempts >= 3) {
                    lblMessage.setText("Max attempts reached. Access Denied.");
                    btnLogin.setEnabled(false);
                    return;
                }

                if (!user.equals(pass)) {
                    attempts++;
                    throw new LoginException("Username and password must be the same.");
                }

                lblMessage.setForeground(Color.GREEN);
                lblMessage.setText("Login successful!");
                btnLogin.setEnabled(false);

            } catch (LoginException le) {
                lblMessage.setForeground(Color.RED);
                lblMessage.setText(le.getMessage() + " Attempt " + attempts + "/3.");
            }
        }

        if (e.getSource() == btnClear) {
            txtUsername.setText("");
            txtPassword.setText("");
            lblMessage.setText("");
            lblMessage.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
