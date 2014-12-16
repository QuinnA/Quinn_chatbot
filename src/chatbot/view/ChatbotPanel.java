package chatbot.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import chatbot.controller.ChatbotAppController;
import javax.swing.*;

public class ChatbotPanel extends JPanel
{
	private ChatbotAppController baseController;
	
	private JButton firstButton;
	private JTextField firstTextField;
	private SpringLayout baseLayout;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	
	public ChatbotPanel(ChatbotAppController baseController)
	{
		this.baseController = baseController;
		
		firstButton = new JButton("click the button... it is so clicky :D");
		firstTextField = new JTextField(25);
		baseLayout = new SpringLayout();
		chatArea = new JTextArea(10, 25);
		chatPane = new JScrollPane(chatArea);
		
		setupPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPane()
	{
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.MAGENTA);
		this.setSize(400, 400);
		this.setLayout(baseLayout);
		this.add(firstButton);
		this.add(firstTextField);
		this.add(chatPane);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 50, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextField, 23, SpringLayout.SOUTH, firstButton);
		baseLayout.putConstraint(SpringLayout.WEST, firstButton, 69, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextField, 43, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstButton, 20, SpringLayout.SOUTH, chatPane);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 100, SpringLayout.NORTH, this);
		
	}
	
	private void setupListeners()
	{
		firstButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String currentInput = firstTextField.getText();
				String result = baseController.getChatbotDialog(currentInput);
				showTextMessage(currentInput);
				showTextMessage(result);
				firstTextField.setText("");
				firstTextField.requestFocus();
			}
		});	
	}
	
	public void showTextMessage(String userInput)
	{
		chatArea.append("\n" + userInput);
	}
}
