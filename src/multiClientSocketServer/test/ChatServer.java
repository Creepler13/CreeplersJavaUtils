package multiClientSocketServer.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import multiClientSocketServer.code.main.Client;
import multiClientSocketServer.code.main.ClientConnection;
import multiClientSocketServer.code.main.Server;

import javax.swing.JTextField;
import javax.swing.ListModel;

public class ChatServer extends JFrame {

	private JPanel contentPane;
	private static JTextField inputField;

	private static Server s;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServer frame = new ChatServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatServer() {

		JTextArea textArea = new JTextArea();

		s = new Server("localhost", 3000, new ServerConnectionManger(listModel, textArea));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!s.started) {
					s.start();
					((ServerConnectionManger) s.getClientManager()).broadcastMessage("Server Started");
				}

			}
		});
		startBtn.setBounds(437, 212, 140, 23);
		contentPane.add(startBtn);

		JList<String> userList = new JList<String>();
		userList.setBounds(437, 16, 140, 185);
		contentPane.add(userList);
		userList.setModel(listModel);

		
		
	

		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 417, 239);
		contentPane.add(scrollPane);

		JButton sendButton = new JButton("Send");
		sendButton.setBounds(338, 285, 89, 23);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});
		contentPane.add(sendButton);

		inputField = new JTextField();
		inputField.setBounds(10, 286, 318, 20);
		contentPane.add(inputField);
		inputField.setColumns(10);
		inputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					send();
			}
		});
	}

	private static void send() {

		if (s != null) {

			((ServerConnectionManger) s.getClientManager()).broadcastMessage("Server: " + inputField.getText());

			inputField.setText("");
		}
	}

}
