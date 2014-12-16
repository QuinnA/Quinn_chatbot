package chatbot.view;

import javax.swing.JFrame;

import chatbot.controller.ChatbotAppController;


public class ChatbotFrame extends JFrame
{
	/**
	 * Reference for the ChatbotPanel of the GUI.
	 */
	private ChatbotPanel basePanel;
	
	public ChatbotFrame(ChatbotAppController baseController)
	{
		basePanel = new ChatbotPanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * Helper method to load panel into frame, set size, and visibility.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(400, 400);
		setVisible(true);
	}

}
