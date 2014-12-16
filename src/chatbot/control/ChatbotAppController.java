package chatbot.control;

import javax.swing.JOptionPane;

import chatbot.model.Chatbot;
import chatbot.view.ChatbotFrame;
import chatbot.view.ChatbotView;
import chatbot.view.ChatbotPanel;

/**
 * Runs chatbot Project. Owns the model and associated views.
 * @author Quinn Andree
 *@version 1.2 10/2/14 - cleaned the quit method.
 */
public class ChatbotAppController	
{
	/**
	 * 
	 */
	private ChatbotView applicationView;
	/**
	 * 
	 */
	private Chatbot mySillyChatbot;
	/**
	 * 
	 */
	private String startMessage;
	/**
	 * 
	 */
	private String quitMessage;
	/**
	 * 
	 */
	private ChatbotFrame appFrame;
	
	/**
	 * 
	 */
	public ChatbotAppController()
	{
		applicationView = new ChatbotView(this);
		mySillyChatbot = new Chatbot("Tu madre ");
		startMessage = "Welcome to the " + mySillyChatbot.getName() + " chatbot. What is your name?";
		quitMessage = "goodbye cuel user:(";
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Chatbot getMySillyChatbot()
	{
		return mySillyChatbot;
		
	}
	
	
	/**
	 * 
	 */
	public void start()
	{
		((ChatbotPanel) appFrame.getContentPane()).showTextMessage(startMessage);

		// ChatbotPanel testPanel = (ChatbotPanel) appFrame.getContentPane();
		// testPanel.showTextMessage(startMessage);
	}
	
	public String getChatbotDialog(String input)
	{
		String result = "";
		
		if(mySillyChatbot.quitChecker(input))
		{
			quit();
		}
		
		result = mySillyChatbot.processText(input);
		
		return result;
	}
	
	/**
	 * Quit method.
	 */
	private void quit()
	{
		applicationView.showChatbotDialog(quitMessage);
		System.exit(0);
	}
}
