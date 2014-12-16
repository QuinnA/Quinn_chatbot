package chatbot.model;

import ChatbotUser;

import java.util.ArrayList;

/**
 * The chatbot model class. Used for checking and manipulating Strings.
 * @author qand5129
 *
 */

public class Chatbot
{
	/**
	 * 
	 */
	private ArrayList<String> memeList;
	/**
	 * 
	 */
	private String name;
	/**
	 * The programmer specified content area for the contentChecker method.
	 */
	private String contentArea;
	/**
	 * The current user of the Chatbot object.
	 */
	private ChatbotUser myUser;
	/**
	 * 
	 */
	private int chatCount;
	/**
	 * The list of user input for the Chatbot
	 */
	private ArrayList<String> userInputList;
	
	/**
	 * Creates Chatbot object with the supplied name and initializes the current number of chats to zero.
	 * @param name The supplied name for the Chatbot.
	 */
	
	public Chatbot(String name)
	{
		memeList = new ArrayList<String>();
		userInputList = new ArrayList<String>();
		this.name = name;
		contentArea = "";
		chatCount = 0;
		myUser = new ChatbotUser();
		fillTheMemeList();
	}
	
	
	/**
	 * Returns thDe name of the Chatbot object.
	 * @return The current name of the Chatbot.
	 */
	
	public String getName()
	{
		return name;
	}
	
	
	/**
	 * Returns chat count.
	 * @return
	 */
	
	public int getChatCount()
	{
		return chatCount;
	}
	
	/**
	 * Getter method for the ChatbotUser object.
	 * 
	 * @return The current ChatbotUser.
	 */
	public ChatbotUser getMyUser()
	{
		return myUser;
	}	
	
	/**
	 * Sets name of Chatbot.
	 * @param name
	 */
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	private void fillTheMemeList()
	{
		memeList.add("kitties");
		memeList.add("go away");
		memeList.add("haha");
		memeList.add("tu madre");
		memeList.add("yup");
		memeList.add("no");
	}

	private boolean memeChecker(String input)
	{
		boolean isAMeme = false;
		
		for (String currentMeme : memeList)
		{
			if(input.equalsIgnoreCase(currentMeme))
			{
				isAMeme = true;
			}
		}
		
		for(int loopCounter = 0; loopCounter < memeList.size(); loopCounter++)
		{
			if(input.equalsIgnoreCase(memeList.get(loopCounter)))
			{
				isAMeme = true;
			}
		}
		
		return isAMeme;
	}
	
	/**
	 * Processes input from the user against the checker methods. Returns the
	 * next output for the view.
	 * 
	 * @param currentInput
	 *            The supplied text.
	 * @return The processed text based on checker or other methods.
	 */
	public String processText(String currentInput)
	{
		String result = "";

		if (getChatCount() < 5)
		{
			result = introduceUser(currentInput);
		}
		else if (currentInput != null && currentInput.length() > 0)
		{
			result = randomChatConversation(currentInput);
		}
		else
		{
			result = "use words!!!!";
			chatCount--;
		}
		updateChatCount();
		return result;
	}
	
	/**
	 * Introduces the user of the Chatbot and gathers information about them to
	 * be used later in the project.
	 * 
	 * @param input
	 *            The supplied answers to user information questions.
	 * @return The next question for the user of the Chatbot.
	 */
	private String introduceUser(String input)
	{
		String userQuestion = "";

		if (getChatCount() == 0)
		{
			myUser.setUserName(input);
			userQuestion = "Good name " + myUser.getUserName() + " how old are you?";
		}
		else if (getChatCount() == 1)
		{
			int userAge = Integer.parseInt(input);
			myUser.setAge(userAge);
			userQuestion = "Garsh, you are really old " + myUser.getUserName() + " how much do you weigh?";
		}
		else if (getChatCount() == 2)
		{
			double userWeight = Double.parseDouble(input);
			myUser.setWeight(userWeight);
			userQuestion = "Yikes that is a lot less than a dwarf star " + myUser.getUserName() + " do you have tattoos?";
		}
		else if (getChatCount() == 3)
		{
			boolean userTatts = Boolean.parseBoolean(input);
			myUser.setHasTattoos(userTatts);
			userQuestion = "Some comment about tattoos" + myUser.getUserName() + " do you have corrective lenses?";
		}
		else
		{
			boolean userLenses = Boolean.parseBoolean(input);
			myUser.setNeedsCorrectiveLenses(userLenses);
			userQuestion = "I love my glasses :D " + myUser.getUserName() + " What do you want to talk about????????";
		}

		return userQuestion;
	}
	
	/**
	 * Selects a random topic for the chatbot to talk about using the user's
	 * input as a comment or reference in the topic.
	 * 
	 * @param input
	 *            The user supplied input.
	 * @return The next Chatbot conversation.
	 */
	private String randomChatConversation(String input)
	{
		String conversation = "";

		int randomPosition = (int) (Math.random() * 7);
		if (randomPosition == 0)
		{
			if (stringLengthChecker(input))
			{
				conversation = "too long";
			}
			else
			{
				conversation = "short words";
			}
		}
		else if (randomPosition == 1)
		{
			if (contentChecker(input))
			{
				conversation = "yup you know the secret";
			}
			else
			{
				conversation = "try again another time";
			}
		}
		else if (randomPosition == 2)
		{
			if (memeChecker(input))
			{
				conversation = "Wow, " + input + " is a meme. Wahoo!";
			}
			else
			{
				conversation = "not a meme, try again";
			}
		}
		else if (randomPosition == 3)
		{
			conversation = userTopic(input);
		}
		else if (randomPosition == 4)
		{
			// add to our list
			userInputList.add(input);
			conversation = "Thank you for the comment";
		}
		else if (randomPosition == 5)
		{
			if (mashChecker(input))
			{
				conversation = mashingDetected(input);
			}
			else
			{
				conversation = noMashingDetected(input);
			}
		}
		else
		{
			if (userInputChecker(input))
			{
				conversation = "That was nice - you removed it from the list";
			}
			else
			{
				conversation = "that wasn't in the conversation before";
			}
		}

		return conversation;
	}

	private String mashingDetected(String input)
	{
		String mashed = "";

		mashed = input.substring(input.length() / 2);
		mashed += input.substring(input.length() / 2);
		mashed += input.substring(input.length() / 2);
		mashed += input.substring(input.length() / 2);
		mashed += input.substring(input.length() / 2);

		return mashed;
	}

	private String noMashingDetected(String input)
	{
		String noMashing = "Thank you for not mashing your keyboard with ";
		if (input.length() > 1)
		{
			noMashing += input.substring(input.length() / 3, input.length() / 2);
		}
		return noMashing;
	}
	
	/**
	 * Checker for keyboard mashing.
	 * 
	 * @param userInput
	 *            The user supplied text.
	 * @return Whether mashing has been detected.
	 */
	private boolean mashChecker(String userInput)
	{
		boolean isMashing = false;

		if (userInput.indexOf("sdf") > -1)
		{
			isMashing = true;
		}

		return isMashing;
	}

	/**
	 * Provides output based on the ChatbotUser object. Uses a switch/case
	 * structure for testing.
	 * 
	 * @param userInput
	 *            The user input
	 * @return Resulting conversation.
	 */
	private String userTopic(String userInput)
	{
		String userBasedResponse = "";

		int randomUserTopic = (int) (Math.random() * 6);

		switch (randomUserTopic)
		{
		case 1:
			userBasedResponse = myUser.hasTattoos() + " is the response to tattoos :D";
			break;
		case 0:
			userBasedResponse = myUser.getUserName() + " is a silly name :P";
			break;
		default:
			userBasedResponse = myUser.getAge() + " is realllly reallllllly old";
			break;
		}

		return userBasedResponse;
	}

	/**
	 * Checks for existing input in the userInputList and removes it.
	 * 
	 * @param userInput
	 *            The current text supplied by the user.
	 * @return Whether it was in the list and removed or not.
	 */
	private boolean userInputChecker(String userInput)
	{
		boolean matchesInput = false;

		for (int loopCount = 0; loopCount < userInputList.size(); loopCount++)
		{
			if (userInput.equalsIgnoreCase(userInputList.get(loopCount)))
			{
				matchesInput = true;
				userInputList.remove(loopCount);
				loopCount--;
			}
		}

		return matchesInput;
	}

	private void updateChatCount()
	{
		chatCount++;
	}

	/**
	 * Checks the input based on the length.
	 * 
	 * @param input
	 *            The user supplied text.
	 * @return Whether it matched the if test for the length.
	 */
	private boolean stringLengthChecker(String input)
	{
		boolean isTooLong = false;

		if (input.length() >= 20)
		{
			isTooLong = true;
		}

		return isTooLong;
	}

	/**
	 * Method checks if the user text contains the Chatbot's special content
	 * area text.
	 * 
	 * @param input
	 *            The user supplied text.
	 * @return Whether the user supplied text contains the content area supplied
	 *         by the user.
	 */
	private boolean contentChecker(String input)
	{
		boolean hasMyContent = false;

		if (input.contains(contentArea))
		{
			hasMyContent = true;
		}

		return hasMyContent;
	}

	
	/**
	 * The quit process or checker.
	 * @param input
	 * @return
	 */
	
	public boolean quitChecker(String input)
	{
		boolean okToQuit = false;
		
		if(input != null && input.equalsIgnoreCase("yes"))
		{
			okToQuit = true;
		}
		
		return okToQuit;
	}
}
