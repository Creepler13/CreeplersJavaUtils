package multiClientSocketServer.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import multiClientSocketServer.code.main.Client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6614928380010512602L;
	private JPanel contentPane;
	private static JTextField inputField;
	private static Client c;

	private static Boolean joined = false;
	private static JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient frame = new ChatClient();
					frame.setVisible(true);

					frame.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							c.disconnect();
						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		inputField = new JTextField();
		inputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					send();
			}
		});
		inputField.setBounds(10, 230, 308, 20);
		contentPane.add(inputField);
		inputField.setColumns(10);

		textPane = new JTextPane();
		textPane.setBounds(10, 11, 414, 174);
		contentPane.add(textPane);

		JButton SendButton = new JButton("Send");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});
		SendButton.setBounds(328, 229, 89, 23);
		contentPane.add(SendButton);

		JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 407, 208);
		contentPane.add(scrollPane);

	}

	private static void send() {
		if (!joined && inputField.getText().startsWith("join")) {

			c = new Client(new ClientConnectionManger(textPane));
			try {
				c.connect(inputField.getText().split("join")[1].trim(), 3000);
				joined = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inputField.setText("");
			return;
		}
		if (c != null && c.isConnected()) {
			c.send(inputField.getText());
			inputField.setText("");
		}
	}

	public JTextPane getTextPane() {
		return textPane;
	}
}
