package chatbot.view;

import javax.swing.JOptionPane;

import chatbot.control.ChatbotAppController;

public class ChatbotView
{

	private ChatbotAppController baseController;
	
	public ChatbotView(ChatbotAppController baseController)
	{
		this.baseController = baseController;
	}
	
	public String showChatbotDialog(String currentInput)
	{
		String result = "";
		
		result= JOptionPane.showInputDialog(null, baseController.getMySillyChatbot().getName() + "says:" + currentInput);
		
		return result;
	}
	
	public void showChatbotMessage(String currentInput)
	{
		JOptionPane.showMessageDialog(null, baseController.getMySillyChatbot() + " says" + currentInput);
	}
	
}

