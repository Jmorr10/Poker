import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;



public class Poker {
	
	//Open Game Boolean
	static Boolean openG = false;
	static Boolean action = false;
	
	//Instantiates JFrame and a FlowLayout
	static JFrame application = new JFrame("Poker: 5-Card Draw by Joseph Morris");
    static GridBagConstraints grid = new GridBagConstraints();
	static FlowLayout flow = new FlowLayout();
	
	//Set up activity panel
	static JPanel activity = new JPanel();
	static BoxLayout box = new BoxLayout(activity,BoxLayout.Y_AXIS);
	Border line = BorderFactory.createLineBorder(Color.white);
	static Boolean userWins = false;
	
	//Instantiate pot and userCredit
	static double pot = 0.00;
	static double userCredit = 100.00;
	
	//Give the user status updates...
	static JLabel[] status = new JLabel[3];
	
	//Instantiate deck and two hands
	static ArrayList<Card> deck = new ArrayList<Card>();
	static ArrayList<Card> dealerHand = new ArrayList<Card>();
	static ArrayList<Card> userHand = new ArrayList<Card>();
	
	//Instantiate user panel and graphic array
	static JPanel user = new JPanel(flow);
	static JPanel userSelect = new JPanel(new FlowLayout());
	static JLabel[] userCards = new JLabel[5];
	static JButton exchangeBtn = new JButton("Exchange!");
	static JOptionPane dialog;
	
	// Inform user of what they currently have in their hand.
	static hand_type userHType;
	static JLabel currentHandType;
	
	//Instantiate dealer panel and graphic array
	static JPanel dealer = new JPanel(flow);
	static JLabel[] dealerCards = new JLabel[5];
	
	//Instantiate pot and user credit labels.
	static JLabel potLabel;
	static JLabel ucLabel;
	
	//Instantiate click...
	static Poker poker = new Poker();
	static cardClick click = poker.new cardClick();
	

	//LEFT TO PROGRAM: DEALER A.I. (Exchanging cards)
	//				   Call function (For second round of betting... Ask Lian about this)
	
	
	static enum hand_type {NONE, HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH}
		
	public class Card {
		
		protected String card;
		protected String suit;
		protected int value;
		
		public Card (String suit, int num) {
			
			String number = "";
			String type = "";
			
			if (suit.equalsIgnoreCase("hearts") || suit.equalsIgnoreCase("diamonds") ||
					suit.equalsIgnoreCase("spades") || suit.equalsIgnoreCase("clubs")) {
				
				type = suit;
				
			} else {
				type = "INVALID";
			}
			switch (num) {
			case 1: number = "Ace"; break;
			case 2: number = "Two"; break;
			case 3: number = "Three"; break;
			case 4: number = "Four"; break;
			case 5: number = "Five"; break;
			case 6: number = "Six"; break;
			case 7: number = "Seven"; break;
			case 8: number = "Eight"; break;
			case 9: number = "Nine"; break;
			case 10: number = "Ten"; break;
			case 11: number = "Jack"; break;
			case 12: number = "Queen"; break;
			case 13: number = "King"; break;
			}
			
			card = number + " of " + type;
			this.suit = type;
			this.value = num;
			
			
		}
			
		public String toString() {
			return card;
		}
	}
	
	public class cardClick implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			Component[] selectedCards = userSelect.getComponents();
			
				JLabel dummy = new JLabel();
				if (e.getComponent() == userCards[0]) { dummy = userCards[0]; }
				if (e.getComponent() == userCards[1]) { dummy = userCards[1]; }
				if (e.getComponent() == userCards[2]) { dummy = userCards[2]; }
				if (e.getComponent() == userCards[3]) { dummy = userCards[3]; }
				if (e.getComponent() == userCards[4]) { dummy = userCards[4]; }
				
				if (e.getComponent() == dummy) {
					if (selectedCards.length == 0) {
						user.remove(dummy);
						user.validate();
						user.repaint();
						userSelect.add(dummy);
						userSelect.validate();
						userSelect.repaint();
						userSelect.setVisible(true);
					}
					else {
						
						if (Collections.frequency(Arrays.asList(selectedCards), dummy) >= 1) {
							
							userSelect.remove(dummy);
							selectedCards = userSelect.getComponents();
							if (selectedCards.length == 0) {
								userSelect.setVisible(false);
							}
							userSelect.validate();
							userSelect.repaint();
							user.add(dummy);
							user.validate();
							user.repaint();
							
						} else {
							
							if ((selectedCards.length == 3) && determineHighCard(userHand) != 1) {
								JOptionPane.showMessageDialog(application, "You can only exchange a maximum of three cards!");
							} else if((selectedCards.length == 3 || selectedCards.length == 4) && (dummy.getIcon().toString().equalsIgnoreCase("cardImages/Ace of spades.gif") || dummy.getIcon().toString().equalsIgnoreCase("cardImages/Ace of clubs.gif") || dummy.getIcon().toString().equalsIgnoreCase("cardImages/Ace of diamonds.gif") || dummy.getIcon().toString().equalsIgnoreCase("cardImages/Ace of hearts.gif"))) {
								JOptionPane.showMessageDialog(application, "You cannot include an ace in your four card exchange!");
							} else if (selectedCards.length == 4) {
								JOptionPane.showMessageDialog(application, "You can only exchange a maximum of four cards!");
							} else {
							
								user.remove(dummy);
								user.validate();
								user.repaint();
								userSelect.add(dummy);
								userSelect.validate();
								userSelect.repaint();
								userSelect.setVisible(true);
								
							}
						}
						
					}

				}
		
			}
					

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
			
		}
		
		
		
	}

	public static void main(String[] args) {
		
		Boolean openGame = false;
		
		if (args.length == 0) {
			openGame = false;
			application.setLayout(new GridBagLayout());
			startGame(openGame, 100.00, false);
			return;
		}
		
		else {
		
			if (args[0].equalsIgnoreCase("t1") || args[0].equalsIgnoreCase("test1")) {
			ArrayList<Card> deck = new ArrayList<Card>();
			deck = generateDeck();
			showDeck(deck);
			shuffleDeck(deck);
			System.out.println();
			showDeck(deck);
			}
			
			if (args[0].equalsIgnoreCase("t2") || args[0].equalsIgnoreCase("test2")) {
				ArrayList<Card> deck = new ArrayList<Card>();
				deck = generateDeck();
				showDeck(deck);
				shuffleDeck(deck);
				System.out.println();
				showDeck(deck);
				
				ArrayList<Card> dealerHand = new ArrayList<Card>();
				ArrayList<Card> userHand = new ArrayList<Card>();
				
				dealHands(deck, userHand, dealerHand);
				showHands(userHand, dealerHand);
				System.out.println();
				System.out.println("Cards remaining:");
				showDeck(deck);
				}
			
			if (args[0].equalsIgnoreCase("t3") || args[0].equalsIgnoreCase("test3")) {
				Poker poker = new Poker();
				ArrayList<Card> testHand = new ArrayList<Card>();
				stringToHand(testHand, args[1], poker);
				showUserHand(testHand);
				System.out.println(evaluateHand(testHand));
			}
			
			if (args[0].equalsIgnoreCase("t4") || args[0].equalsIgnoreCase("test4")) {
				Poker poker = new Poker();
				Boolean userWins = false;
				ArrayList<Card> userHand = new ArrayList<Card>();
				ArrayList<Card> dealerHand = new ArrayList<Card>();
				stringToHand(userHand, args[1], poker);
				stringToHand(dealerHand, args[2], poker);
				showHands(userHand, dealerHand);
				userWins = compareHands(userHand, dealerHand);
				System.out.println((userWins? "User" : "Dealer") + " wins!");
			}
			
			if (args[0].equalsIgnoreCase("o") || args[0].equalsIgnoreCase("open")) {
				openGame = true;
				application.setLayout(new GridBagLayout());
				startGame(openGame,100.00, false); 
				return;
			}
		}
	}
	
	public static ArrayList<Card> generateDeck() {
		
		ArrayList<Card> deck = new ArrayList<Card>();
		Poker poker = new Poker();
		
		for (int suit = 1; suit <= 4; suit++)  {
			
			for (int num = 1; num <= 13; num++) {
				if (suit == 1) {
					Card temp = poker.new Card("hearts", num);  
					deck.add(temp);
				}
				if (suit == 2) {
					Card temp = poker.new Card("diamonds", num);  
					deck.add(temp);
				}
				if (suit == 3) {
					Card temp = poker.new Card("spades", num);  
					deck.add(temp);
				}
				if (suit == 4) {
					Card temp = poker.new Card("clubs", num);  
					deck.add(temp);
				}
				
			}
			
		}
		
		return deck;
		
	}
	
	public static void shuffleDeck(ArrayList<Card> deck) {
			//Shuffle deck twice for good measure. (Uses pre-defined Collection.shuffle() method)
			Collections.shuffle(deck);
			Collections.shuffle(deck);
		}

	public static void showDeck(ArrayList<Card> deck) {
		for (Card card : deck) {
			System.out.println(card.toString());
		}
	}

	public static void dealHands(ArrayList<Card> deck, ArrayList<Card> userHand, ArrayList<Card> dealerHand) {
		
		for (int i = 1; i <= 10; i++) {
			
			if (i%2 != 0) {
				Card temp;
				temp = deck.get(0);
				deck.remove(0);
				userHand.add(temp);
			} 
			else {
				Card temp;
				temp = deck.get(0);
				deck.remove(0);
				dealerHand.add(temp);
			}		
		}	
	}

	public static void showHands(ArrayList<Card> userHand, ArrayList<Card> dealerHand) {
		
		System.out.println("User hand:");
		for (Card x : userHand) {
			System.out.println("\t" + x.toString());
		}
		System.out.println();
		System.out.println("Dealer hand:");
		for (Card y : dealerHand) {
			System.out.println("\t" + y.toString());
		}
	}

	public static void showUserHand(ArrayList<Card> userHand){
		System.out.println("User hand:");
		for (Card x : userHand) {
			System.out.println("\t" + x.toString());
		}
	}

	public static void showDealerHand(ArrayList<Card> dealerHand){
		System.out.println("Dealer hand:");
		for (Card x : dealerHand) {
			System.out.println("\t" + x.toString());
		}
	}

	public static hand_type evaluateHand(ArrayList<Card> hand) {
		
		hand_type handStatus = hand_type.NONE;
		String [] sHand = new String[5];
		for (int i = 0; i <= 4; i++) {
			sHand[i] = (hand.get(i)).toString();
		}
		
//--------DETERMINE NUMBER OF EACH SUIT!----------------------------------------------------------------------------
		
		int hearts = 0;
		int diamonds = 0;
		int spades = 0;
		int clubs = 0;
		for (String x : sHand) {
			if (x.contains("hearts")) {
				hearts++;
			} else if (x.contains("diamonds")) {
				diamonds++;
			} else if (x.contains("spades")) {
				spades++;
			} else if (x.contains("clubs")) {
				clubs++;
			}
		}
		
		if (hearts == 5 || diamonds == 5 || spades == 5 || clubs == 5) {
			int royalFlush = 0;
			Boolean oneTen = false;
			Boolean oneJack = false;
			Boolean oneQueen = false;
			Boolean oneKing = false;
			Boolean oneAce = false;
			for (Card x : hand) {
				if ((x.card).contains("Ten")) {
					if (oneTen) {
					} else {
					royalFlush++;
					oneTen = true; 
					}
				}
				if ((x.card).contains("Jack")) {
					if (oneJack) {
					} else {
					royalFlush++;
					oneJack = true; 
					}
				}
				if ((x.card).contains("Queen")) {
					if (oneQueen) {
					} else {
					royalFlush++;
					oneQueen = true; 
					}
				}
				if ((x.card).contains("King")) {
					if (oneKing) {
					} else {
					royalFlush++;
					oneKing = true; 
					}
				}
				if ((x.card).contains("Ace")) {
					if (oneAce) {
					} else {
					royalFlush++;
					oneAce = true; 
					}
					
				}
				
				if (royalFlush == 5) {
					return hand_type.ROYAL_FLUSH;
				}
			}
		}		
		
		int[] cardValues = new int[5];
		for (int i = 0; i <=4; i++) {
			cardValues[i] = (hand.get(i)).value;
		}
		bubbleSort(cardValues);
		int[] royalStraight = {1,10,11,12,13};
		if (Arrays.equals(cardValues, royalStraight) && (hearts == 5 || diamonds == 5 || spades == 5 || clubs == 5)) {
			return hand_type.STRAIGHT_FLUSH;
		} else if (Arrays.equals(cardValues, royalStraight)) {
			return hand_type.STRAIGHT;
		}
		if (cardValues[0] + 1 == cardValues[1]) {
			if (cardValues[1] + 1 == cardValues[2]) {
				if (cardValues[2] + 1 == cardValues[3]) {
					if (cardValues[3] + 1 == cardValues[4]) {
						if (hearts == 5 || diamonds == 5 || spades == 5 || clubs == 5) {
							return hand_type.STRAIGHT_FLUSH;
						} else {
							if (hand_type.FOUR_KIND == determineHandMatches(hand)) {
								return hand_type.FOUR_KIND;
							} else if (hand_type.FULL_HOUSE == determineHandMatches(hand)) {
								return hand_type.FULL_HOUSE;
							} else {
							return hand_type.STRAIGHT;
							}
						}
					}	
				}	
			}
		}
		if (hearts == 5 || diamonds == 5 || spades == 5 || clubs == 5) {
			return hand_type.FLUSH;
		}
		
		handStatus = determineHandMatches(hand);
		
		if(handStatus == hand_type.NONE) {
			handStatus = hand_type.HIGH_CARD;
		}
		
		
		return handStatus;
	}
	
	public static hand_type determineHandMatches(ArrayList<Card> hand) {
		int twoPair = 0;
		Boolean twoOfAKind = false;
		Boolean threeOfAKind = false;
		Boolean fourOfAKind = false;
		ArrayList<Integer> valuesChecked = new ArrayList<Integer>();
		int match = 0;
		for (Card x : hand) {
			valuesChecked.add((x.value));
		}
		for ( Card y : hand) {
			match = Collections.frequency(valuesChecked, y.value);
			if (match == 4) {
				fourOfAKind = true;
			} else if (match == 3) {
				threeOfAKind = true;
			} else if (match == 2) {
				twoPair++;
				twoOfAKind = true;
			}
		}
		
		
		
		if (fourOfAKind){ return hand_type.FOUR_KIND; }
		if (threeOfAKind && twoOfAKind) { return hand_type.FULL_HOUSE; }
		if (threeOfAKind) { return hand_type.THREE_KIND; }
		if (twoPair == 4) { return hand_type.TWO_PAIR; }
		if (twoOfAKind) { return hand_type.ONE_PAIR; }
		
		return hand_type.NONE;
		
		
	}
	
	public static int determineHighCard(ArrayList<Card> hand) {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int x = 0; x <=hand.size()-1; x++) {
			values.add(hand.get(x).value);
		}
		
		for (int i = 0; i <=values.size()-1; i++) {
			if (Collections.frequency(values, values.get(i)) >= 2) {
			} else {
				result.add(values.get(i));			
			}
		}
		
		if (result.isEmpty()) {
			return Collections.max(values);
		}
		
		Collections.sort(result);
		
		if (result.get(0) == 1) { return 1; }
		else { return result.get(result.size()-1); }
		
	}
	
	public static int determinePairRank(ArrayList<Card> hand) {
		int rank = 0;
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card a : hand) {
			values.add(a.value);
		}
		Collections.sort(values);
		for (int num : values) {
			if (Collections.frequency(values, num) == 2) {
				rank = num;
			}
		}
		return rank;	
	}
	
	public static int determineNextHighCard(ArrayList<Card> hand, int index) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card a : hand) {
			values.add(a.value);
		}
		HashSet<Integer> hash = new HashSet<Integer>(values);
		ArrayList<Integer> result = new ArrayList<Integer>(hash);
		Collections.sort(result);
		return result.get((result.size()- 1) - index);
	}
	
	public static void stringToHand (ArrayList<Card> testHand, String args, Poker poker) {
			for(int i = 0; i < args.length()-1; i += 2){
				if (args.charAt(i) == 'T' || args.charAt(i) == 't') {
					if (args.charAt(i+1) == 'C' || args.charAt(i+1) == 'c') {
						testHand.add(poker.new Card("clubs", 10));
					}
					if (args.charAt(i+1) == 'H' || args.charAt(i+1) == 'h') {
						testHand.add(poker.new Card("hearts", 10));
					}
					if (args.charAt(i+1) == 'S' || args.charAt(i+1) == 's') {
						testHand.add(poker.new Card("spades", 10));
					}
					if (args.charAt(i+1) == 'D' || args.charAt(i+1) == 'd') {
						testHand.add(poker.new Card("diamonds", 10));
					}
				}
				
				else if (args.charAt(i) == 'J' || args.charAt(i) == 'j') {
					if (args.charAt(i+1) == 'C' || args.charAt(i+1) == 'c') {
						testHand.add(poker.new Card("clubs", 11));
					}
					if (args.charAt(i+1) == 'H' || args.charAt(i+1) == 'h') {
						testHand.add(poker.new Card("hearts", 11));
					}
					if (args.charAt(i+1) == 'S' || args.charAt(i+1) == 's') {
						testHand.add(poker.new Card("spades", 11));
					}
					if (args.charAt(i+1) == 'D' || args.charAt(i+1) == 'd') {
						testHand.add(poker.new Card("diamonds", 11));
					}
				}
				
				else if (args.charAt(i) == 'Q' || args.charAt(i) == 'q') {
					if (args.charAt(i+1) == 'C' || args.charAt(i+1) == 'c') {
						testHand.add(poker.new Card("clubs", 12));
					}
					if (args.charAt(i+1) == 'H' || args.charAt(i+1) == 'h') {
						testHand.add(poker.new Card("hearts", 12));
					}
					if (args.charAt(i+1) == 'S' || args.charAt(i+1) == 's') {
						testHand.add(poker.new Card("spades", 12));
					}
					if (args.charAt(i+1) == 'D' || args.charAt(i+1) == 'd') {
						testHand.add(poker.new Card("diamonds", 12));
					}
				}
				
				else if (args.charAt(i) == 'K' || args.charAt(i) == 'k') {
					if (args.charAt(i+1) == 'C' || args.charAt(i+1) == 'c') {
						testHand.add(poker.new Card("clubs", 13));
					}
					if (args.charAt(i+1) == 'H' || args.charAt(i+1) == 'h') {
						testHand.add(poker.new Card("hearts", 13));
					}
					if (args.charAt(i+1) == 'S' || args.charAt(i+1) == 's') {
						testHand.add(poker.new Card("spades", 13));
					}
					if (args.charAt(i+1) == 'D' || args.charAt(i+1) == 'd') {
						testHand.add(poker.new Card("diamonds", 13));
					}
				}
				
				else if (args.charAt(i) == 'A' || args.charAt(i) == 'a') {
					if (args.charAt(i+1) == 'C' || args.charAt(i+1) == 'c') {
						testHand.add(poker.new Card("clubs", 1));
					}
					if (args.charAt(i+1) == 'H' || args.charAt(i+1) == 'h') {
						testHand.add(poker.new Card("hearts", 1));
					}
					if (args.charAt(i+1) == 'S' || args.charAt(i+1) == 's') {
						testHand.add(poker.new Card("spades", 1));
					}
					if (args.charAt(i+1) == 'D' || args.charAt(i+1) == 'd') {
						testHand.add(poker.new Card("diamonds", 1));
					}
				}
				
				else {
					if (args.charAt(i+1) == 'C' || args.charAt(i+1) == 'c') {
						testHand.add(poker.new Card("clubs", Character.getNumericValue(args.charAt(i))));
					}
					if (args.charAt(i+1) == 'H' || args.charAt(i+1) == 'h') {
						testHand.add(poker.new Card("hearts", Character.getNumericValue(args.charAt(i))));
					}
					if (args.charAt(i+1) == 'S' || args.charAt(i+1) == 's') {
						testHand.add(poker.new Card("spades", Character.getNumericValue(args.charAt(i))));
					}
					if (args.charAt(i+1) == 'D' || args.charAt(i+1) == 'd') {
						testHand.add(poker.new Card("diamonds", Character.getNumericValue(args.charAt(i))));
					}
				}				
			}
		}

	public static Boolean compareHands(ArrayList<Card> userHand, ArrayList<Card> dealerHand) {
		
		Boolean userWins = false;
		hand_type user = evaluateHand(userHand);
		hand_type dealer = evaluateHand(dealerHand);
		int userHigh = 0, dealerHigh = 0;
		
		if (user == dealer) {
			if (user == hand_type.ONE_PAIR) {
				
				int userRank = determinePairRank(userHand);
				int dealerRank = determinePairRank(dealerHand);
				
				if (userRank > dealerRank || (userRank != dealerRank && userRank == 1)) {
					userWins = true;
					return userWins;
				} else if (userRank < dealerRank || (dealerRank != userRank && dealerRank == 1)) {
					userWins = false;
					return userWins;
				} 
				
			}
			userHigh = determineHighCard(userHand);
			dealerHigh = determineHighCard(dealerHand);
			System.out.println();
			System.out.println("User has " + handTypeToString(user) + "\nDealer has " + handTypeToString(dealer));
			System.out.println();
			System.out.println("User high card: " + highCardToString(userHigh) + "\nDealer high card: " + highCardToString(dealerHigh));
			if (userHigh == dealerHigh) { 
				int userHighCard2 = 0;
				int dealerHighCard2 = 0;
				int index = 0;
				do {
					userHighCard2 = determineNextHighCard(userHand, index);
					dealerHighCard2 = determineNextHighCard(dealerHand, index);
					index++;
				} while (userHighCard2 == dealerHighCard2);
				
				System.out.println();
				System.out.println("User's next high card: " + highCardToString(userHighCard2));
				System.out.println("Dealer's next high card: " + highCardToString(dealerHighCard2));
				System.out.println();
				
				if (userHighCard2 > dealerHighCard2) {
					userWins = true;
					return userWins;
				} else if (userHighCard2 < dealerHighCard2) {
					userWins = false;
					return userWins;
				}
			}
			if (userHigh > dealerHigh || userHigh == 1) {
				userWins = true;
				return userWins;
			} else if (userHigh < dealerHigh || dealerHigh == 1) {
				userWins = false;
				return userWins;
			}
			
			
		} else {
			System.out.println();
			System.out.println("User has " + handTypeToString(user) + "\nDealer has " + handTypeToString(dealer));
			if (user.ordinal() > dealer.ordinal()) {
				userWins = true;
				return userWins;
			} else if (user.ordinal() < dealer.ordinal()) {
				userWins = false;
				return userWins;
			}
		}
		
		return userWins;
	}
		

	public static Boolean compareHandsGraphics(ArrayList<Card> userHand, ArrayList<Card> dealerHand) {
		
		Boolean userWins = false;
		hand_type user = evaluateHand(userHand);
		hand_type dealer = evaluateHand(dealerHand);
		int userHigh = 0, dealerHigh = 0;
		
		if (user == dealer) {
			if (user == hand_type.ONE_PAIR) {
				
				int userRank = determinePairRank(userHand);
				int dealerRank = determinePairRank(dealerHand);
				
				if (userRank > dealerRank) {
					userWins = true;
					return userWins;
				} else if (userRank < dealerRank) {
					userWins = false;
					return userWins;
				}
			}
			userHigh = determineHighCard(userHand);
			dealerHigh = determineHighCard(dealerHand);
			JLabel highCardUser = new JLabel("User high card: " + highCardToString(userHigh));
			JLabel highCardDealer = new JLabel("Dealer high card: " + highCardToString(dealerHigh));
			highCardUser.setForeground(Color.white);
			highCardDealer.setForeground(Color.white);
			activity.add(highCardUser);
			activity.add(highCardDealer);
			if (userHigh == dealerHigh) { 
				int userHighCard2 = 0;
				int dealerHighCard2 = 0;
				int index = 0;
				do {
					userHighCard2 = determineNextHighCard(userHand, index);
					dealerHighCard2 = determineNextHighCard(dealerHand, index);
					index++;
				} while (userHighCard2 == dealerHighCard2);
				
				JLabel highCard2User = new JLabel("User's next high card: " + highCardToString(userHighCard2));
				JLabel highCard2Dealer = new JLabel("Dealer's next high card: " + highCardToString(dealerHighCard2));
				highCard2User.setForeground(Color.white);
				highCard2Dealer.setForeground(Color.white);
				activity.add(highCard2User);
				activity.add(highCard2Dealer);
				
				if (userHighCard2 > dealerHighCard2) {
					userWins = true;
					return userWins;
				} else if (userHighCard2 < dealerHighCard2) {
					userWins = false;
					return userWins;
				}
			}
			else if (userHigh == 1) {
				userWins = true;
				return userWins;
			}
			else if (dealerHigh == 1) {
				userWins = false;
				return userWins;
			}
			else if (userHigh > dealerHigh) {
				userWins = true;
				return userWins;
			}
			else if (dealerHigh < userHigh) {
				userWins = false;
				return userWins;
			}
		}  else {
			if (user.ordinal() > dealer.ordinal()) {
				userWins = true;
				return userWins;
			} else if (user.ordinal() < dealer.ordinal()) {
				userWins = false;
				return userWins;
			}
		}
		
		return userWins;
	}
	

	public static String handTypeToString(hand_type type) {
		
		String hand = "";
		switch (type) {
		case HIGH_CARD: hand = "High Card"; break;
		case ONE_PAIR: hand = "One Pair"; break;
		case TWO_PAIR: hand = "Two Pair"; break;
		case THREE_KIND: hand = "3 of a Kind"; break;
		case STRAIGHT: hand = "a Straight"; break;
		case FLUSH: hand = "a Flush"; break;
		case FULL_HOUSE: hand = "a Full House"; break;
		case FOUR_KIND: hand = "4 of a Kind"; break;
		case STRAIGHT_FLUSH: hand = "a Straight Flush"; break;
		case ROYAL_FLUSH: hand = "a Royal Flush"; break;
		}
		
		return hand;
	}
	
	public static String highCardToString(int high) {
		String highCard = "";
		switch (high) {
		case 1: highCard = "Ace"; break;
		case 2: highCard = "Two"; break;
		case 3: highCard = "Three"; break;
		case 4: highCard = "Four"; break;
		case 5: highCard = "Five"; break;
		case 6: highCard = "Six"; break;
		case 7: highCard = "Seven"; break;
		case 8: highCard = "Eight"; break;
		case 9: highCard = "Nine"; break;
		case 10: highCard = "Ten"; break;
		case 11: highCard = "Jack"; break;
		case 12: highCard = "Queen"; break;
		case 13: highCard = "King"; break;
		
		}
		
		return highCard;
	}
	
	public static void swapper(int[] a, int e1, int e2) {
		
		int temp = 0;
		temp = a[e1];
		a[e1] = a[e2];
		a[e2] = temp;
	}
	
	public static void bubbleSort(int[] a) {
		
		for (int i = 0; i <= a.length-1; i++) {
			
			for (int j = 0; j < a.length-1; j++){
				if (a[j] > a[j+1]) {
					swapper(a, j, j+1);
				}
			}		
			
		}
		
	}
	

	public static void startGame(Boolean openGame, double credit, Boolean reprompt) {
		if (!reprompt) {
		application.repaint();
	    
		//Instantiates JFrame and a FlowLayout
		application.setBackground(new Color(0,128,0));
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
		openG = openGame;
		
		
		//Instantiate game status
		userWins = false;
		
		//Instantiate pot and userCredit
		pot = 0.00;
		userCredit = credit;
		
		//Give the user status updates...
		status[0] = new JLabel("Dealer adds $5 to the pot...");
		status[1] = new JLabel("Shuffling the deck...");
		status[2] =	new JLabel("Dealing cards...");
		for (JLabel x : status) {
			x.setForeground(Color.white);
			activity.add(x);
		}
		
		//Set up activity panel
		activity.setLayout(box);
		Border line = BorderFactory.createLineBorder(Color.white);
		TitledBorder border;
		border = BorderFactory.createTitledBorder(line, "Activity Log");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitleColor(Color.white);
		border.setTitleFont(new Font("Times New Roman", Font.BOLD, 15));
	    activity.setBorder(border);
	    activity.setPreferredSize(new Dimension(250,600));
	    activity.setBackground(new Color(0,128,0));
	   
		//Show the user the Pot and Credit information...
		potLabel = new JLabel("Pot: " + pot);
		potLabel.setForeground(Color.white);
		activity.add(potLabel);
		ucLabel = new JLabel("User Credit: " + userCredit);
		ucLabel.setForeground(Color.white);
		activity.add(ucLabel);
	
		
		//Ask user if they want to buy in to the game for $5... If yes, continue... If no, exit with dialog.
		int option = JOptionPane.showConfirmDialog(application,"The buy-in for this game is $5. Do you accept?"," Buy-In", JOptionPane.YES_NO_OPTION); 
		if (option == 1) {
			JOptionPane.showMessageDialog(application, "The user walks away from the table with: $" + userCredit);
			System.exit(0);
		}else if (option == 0) {
			if ((userCredit-5)> 0){
				userCredit -= 5;
				ucLabel.setText("User Credit: $" + userCredit);
				pot += 10;
				potLabel.setText("Pot: $" + pot);
			} else {
				JOptionPane.showMessageDialog(application, "The user is turned away from the table with: $" + userCredit);
				System.exit(0);
			}
		} else {
			JOptionPane.showMessageDialog(application, "The user walks away from the table with: $" + userCredit);
			System.exit(1);
		}
		
		
		
		//Create a deck and shuffle it
		
		deck = generateDeck();
		shuffleDeck(deck);
		
		//Deal hands
		dealHands(deck, userHand, dealerHand);
	
		//Show Graphic User Cards...
		flow.setAlignment(FlowLayout.RIGHT);
	    user = new JPanel(flow);
		user.setBackground(new Color(0,128,0));
		userCards = new JLabel[5];
		for (int x = 0; x <= 4; x++) {
			userCards[x]  = new JLabel(new ImageIcon("cardImages/" + userHand.get(x).card + ".gif"));
			userCards[x].setName(userHand.get(x).card);
			user.add(userCards[x]);
		}
		
		// Inform user of what they currently have in their hand.
		userHType = evaluateHand(userHand);
		currentHandType = new JLabel("You currently have " + handTypeToString(userHType));
		currentHandType.setForeground(Color.white);
		activity.add(currentHandType);
		
		//Show user hand (and dealer hand if open game)
		dealer = new JPanel(flow);
		if (openGame) {
			
			dealer.setBackground(new Color(0,128,0));
			dealerCards = new JLabel[5];
			for (int x = 0; x <= 4; x++) {
				dealerCards[x]  = new JLabel(new ImageIcon("cardImages/" + dealerHand.get(x).card + ".gif"));
				dealerCards[x].setName(dealerHand.get(x).card);
				dealer.add(dealerCards[x]);
			}		
		} else {
			dealer.setBackground(new Color(0,128,0));
			dealerCards = new JLabel[5];
			for (int x = 0; x <= 4; x++) {
				dealerCards[x]  = new JLabel(new ImageIcon("cardImages/Back.gif"));
				dealerCards[x].setName(dealerHand.get(x).card);
				dealer.add(dealerCards[x]);
			}
		}
		
		
		
		grid.anchor = GridBagConstraints.PAGE_END;
		grid.gridx = 2;
		grid.gridy = 4;
		application.add(user, grid);
		
		grid.anchor = GridBagConstraints.CENTER;
		grid.gridx = 2;
		grid.gridy = 3;
		grid.fill = GridBagConstraints.HORIZONTAL;
		userSelect.setSize(user.getSize());
		userSelect.setVisible(false);
		userSelect.setBackground(new Color(0,128,0));
		application.add(userSelect, grid);
		
		grid.anchor = GridBagConstraints.CENTER;
		grid.gridx = 2;
		grid.gridy = 4;
		grid.fill = GridBagConstraints.NONE;
		exchangeBtn.setVisible(false);
		ActionListener trap = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource().equals(exchangeBtn)) {
					action = true;	
				}
				
			}
		};
		exchangeBtn.addActionListener(trap);
		exchangeBtn.setMultiClickThreshhold(2000l);
		MouseListener listen = new MouseListener() {

			public void mouseClicked(MouseEvent mouse) {
				
				if (mouse.getSource().equals(exchangeBtn) && action == true) {
					action = false;
					exchangeCards();
	        	}
			}

			public void mousePressed(MouseEvent e) {
			
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			 
		};
		exchangeBtn.setEnabled(false);
		exchangeBtn.addMouseListener(listen);
		application.add(exchangeBtn, grid);
		
		
		grid.gridx = 2;
		grid.gridy = 0;
		grid.anchor = GridBagConstraints.PAGE_START;
		application.add(dealer, grid);
		
		grid.gridx = 0;
		grid.gridy = 0;
		grid.gridheight = 5;
		grid.fill = GridBagConstraints.BOTH;
		grid.anchor = GridBagConstraints.FIRST_LINE_START;
		application.add(activity, grid);
		grid = new GridBagConstraints();
		
		
		
		
	    application.getContentPane().setBackground(new Color(0,128,0));
		application.setSize(application.getSize());
		application.pack();
	    application.repaint();
		application.setVisible(true);
		
	}
		//First round of betting...
		Object[] options = {"Raise", "Fold"};
		Boolean valid = false;
		int confirm = 0;
		do {
			int selection = JOptionPane.showOptionDialog(application, "Please select an action","Select Action", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (selection == 0) {
				valid = true;	
			} else if (selection == 1) {
				valid = false;
				confirm = 1;
				JOptionPane.showMessageDialog(application, "You fold... Dealer collects a pot of $" + pot);
				dealerHand.clear();
				userHand.clear();
				user.removeAll();
				dealer.removeAll();
				application.remove(user);
				application.remove(dealer);
				activity.removeAll();
				userWins = false;
				exchangeBtn.setVisible(false);
				startGame(openGame,userCredit, false);
				return;
			}
		} while (valid == false && confirm != 1);
		if (valid) {
			continueGame();
		}
		
	    
	}

	public static void continueGame() {
		Boolean infinity = false;
		Boolean back2 = false;
		do {
			//Raise prompt
			Boolean validRaise = false;
			int raise = 0;
			do {
			String raiseString = (String) JOptionPane.showInputDialog(application, "Please input the amount you want to raise (in whole dollars):", "Raise Amount", JOptionPane.QUESTION_MESSAGE, null, null, null );	
			if (raiseString == null) {
				back2 = true;
				break;
			}
			try {
			raise = Integer.parseInt(raiseString);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(application, "The amount you entered was not valid!");
				continue;
			}
			if (raise > userCredit) {
				JOptionPane.showMessageDialog(application, "You do not have enough money for the specified raise.");
			} else {
				validRaise = true;
			}
			
			} while (!validRaise);
			
			if (back2) { back2 = false; startGame(openG, userCredit, true); return; }
			JLabel raiseStatus = new JLabel("You raise $" + raise + ".00...");
			raiseStatus.setForeground(Color.white);
			
			//Dealer action sequence #1 (70% call, 20% bluff, 10% fold)
			
			Random rand = new Random();
			int dealerAction = rand.nextInt(101);
			
			if (dealerAction <= 70) {
				
				//Dealer call
				JLabel dealerCall = new JLabel("Dealer sees your raise...");
				dealerCall.setForeground(Color.white);
				userCredit -= raise;
				pot += raise*2;
				ucLabel.setText("User Credit: $" + userCredit);
				potLabel.setText("Pot: $" + pot);
				activity.add(raiseStatus);
				activity.add(dealerCall);
				infinity = true;			
				
			} else if (dealerAction <= 90 && dealerAction >= 71) {
				
				//Dealer Bluff ( 1/3 = 60%, 1/2 = 30%, 3/4 = 10%)
				int dealerRand = rand.nextInt(101);
				double dealerRaise = 0;
				if (dealerRand <= 60) {
					dealerRaise = userCredit * (1.0/3.0);
				} else if (dealerRand <= 90 && dealerRand >= 61) {
					dealerRaise = userCredit * (1.0/2.0);
				} else if (dealerRand <= 100 && dealerRand >=91) {
					dealerRaise = userCredit * (3.0/4.0);
				}
				JLabel dealerR = new JLabel("Dealer raises $" + Math.floor(dealerRaise) + "...");
				dealerR.setForeground(Color.white);
				userCredit -= raise;
				pot += raise + Math.floor(dealerRaise);
				ucLabel.setText("User Credit: $" + userCredit);
				potLabel.setText("Pot: $" + pot);
				activity.add(raiseStatus);
				activity.add(dealerR);
				infinity = true;			
				
			} else if (dealerAction <= 100 && dealerAction >= 91) {
				//Dealer fold...
				JOptionPane.showMessageDialog(application, "Dealer folds... You collect a pot of $" + (pot + raise));
				userCredit += pot;
				dealerHand.clear();
				userHand.clear();
				user.removeAll();
				dealer.removeAll();
				application.remove(user);
				application.remove(dealer);
				activity.removeAll();
				userWins = false;
				exchangeBtn.setVisible(false);
				startGame(openG,userCredit, false);
				return;
				
			}
		} while (!infinity);	
	
		//Drawing and Replacement phase...
		for (int x = 0; x <= 4; x++) {
			userCards[x].addMouseListener(click);
		}
		exchangeBtn.setVisible(true);
		exchangeBtn.setEnabled(true);
	}
	
	public static void exchangeCards() {
		
		int select = JOptionPane.showConfirmDialog(application, "Is that selection final?");
		if (select == JOptionPane.NO_OPTION || select == JOptionPane.CLOSED_OPTION || select == JOptionPane.CANCEL_OPTION ) { exchangeBtn.setEnabled(true); return; }
		int dealerExchanged = 0;
		Component[] selectedCards = userSelect.getComponents();
		int exchanged = selectedCards.length;
		if (selectedCards.length == 4 && (selectedCards[0].getName().contains("Ace") || selectedCards[1].getName().contains("Ace") || selectedCards[2].getName().contains("Ace") || selectedCards[3].getName().contains("Ace"))) {
			JOptionPane.showMessageDialog(application, "You cannot include an ace in your four card exchange!");
			return;
		} else {
			
			//Exchange the selected cards by first removing them from the user's hand and then draw from the top of the deck to replace them.
			for (Component x : selectedCards) {
				
				for (int i = 0; i <= 4; i++){
					if (userHand.get(i).card.equals(x.getName())) {
						userHand.remove(i);
						userHand.add(deck.get(0));
						deck.remove(0);
					}
				}	
			}
			
			userCards = new JLabel[5];
			user.removeAll();
			for (int x = 0; x <= 4; x++) {
				userCards[x]  = new JLabel(new ImageIcon("cardImages/" + userHand.get(x).card + ".gif"));
				userCards[x].setName(userHand.get(x).card);
				user.add(userCards[x]);
			}
			
			user.validate();
			user.repaint();
			userSelect.removeAll();
			userSelect.validate();
			userSelect.repaint();
			userSelect.setVisible(false);
			exchangeBtn.setVisible(false);
			selectedCards = userSelect.getComponents();
	
			//Remove mouseListener for the JLabel cards.
			for (int x = 0; x <= 4; x++) {
				userCards[x].removeMouseListener(click);
			}
			
			//DEALER A.I. FOR EXCHANGING CARDS!!!
			
			hand_type dealerHandType = evaluateHand(dealerHand);
			
			//ANYTHING ABOVE STRAIGHT
			if (dealerHandType.ordinal() >= 5) {
				dealerExchanged = 0;
			}
			
			//TWO PAIR
			if (dealerHandType.ordinal() ==  3) {
				
				int dHigh = determineHighCard(dealerHand);
				if (dHigh == 1) {
					dealerExchanged = 0;
				} else {
					dealerExchanged = 1;
					Component[] hand = dealer.getComponents();
					for (Component x : hand) {
						for (int i = 0; i <= 4; i++){
							if (x.getName().contains(highCardToString(determineHighCard(dealerHand)))) {
								if (dealerHand.get(i).card.contains(highCardToString(determineHighCard(dealerHand)))) {
									dealerHand.remove(dealerHand.get(i));
									dealerHand.add(deck.get(0));
									deck.remove(0);
								}
							}
						}		
					}
				}	
			}
			
			//ONE PAIR
			if (dealerHandType.ordinal() ==  2) {
				@SuppressWarnings("unused")
				int e1 = 0;
				ArrayList<String> valuesChecked = new ArrayList<String>();
				ArrayList<Integer> vc2 = new ArrayList<Integer>();
				int match = 0;
				int z = 0;
				Card[] cards = new Card[2];
				for (Card x : dealerHand) {
					valuesChecked.add((x.card));
					vc2.add((x.value));
				}
				for ( Card y : dealerHand) {
					match = Collections.frequency(vc2, y.value);
					
					if (match == 2) {
						
						e1 = Collections.binarySearch(valuesChecked, y.card);
						String[] extract = y.card.split(" ");
						cards[z] = poker.new Card(extract[2], y.value);
						z++;
						
					
					}
				}
				int dHigh = determineHighCard(dealerHand);
				if (dHigh == 1) {
					dealerExchanged = 2;
					ArrayList<Card> removeCards = new ArrayList<Card>();
						for (Card a : dealerHand){
							
							if (!( a.card.equals(cards[0].card) || a.card.equals(cards[1].card) || a.card.contains("Ace")) ) {
								removeCards.add(a);
							}
						}
						dealerHand.removeAll(removeCards);
						for (int i = 1; i <= removeCards.size(); i++) {
							dealerHand.add(deck.get(0));
							deck.remove(0);	
						}
						
				} 
				 else {
					dealerExchanged = 3;
					ArrayList<Card> removeCards = new ArrayList<Card>();
						for (Card a : dealerHand){
							if (!(a.card.equals(cards[0].card) || a.card.equals(cards[1].card))) {
								removeCards.add(a);
							}
						}
						dealerHand.removeAll(removeCards);
						for (int i = 1; i <= removeCards.size(); i++) {
							dealerHand.add(deck.get(0));
							deck.remove(0);	
						}
					}
					
		
				}
			
			//THREE OF A KIND 
			if (dealerHandType.ordinal() ==  4) {
				@SuppressWarnings("unused")
				int e1 = 0;
				ArrayList<String> valuesChecked = new ArrayList<String>();
				ArrayList<Integer> vc2 = new ArrayList<Integer>();
				int match = 0;
				int z = 0;
				Card[] cards = new Card[3];
				for (Card x : dealerHand) {
					valuesChecked.add((x.card));
					vc2.add((x.value));
				}
				for ( Card y : dealerHand) {
					match = Collections.frequency(vc2, y.value);
					
					if (match == 3) {
						
						e1 = Collections.binarySearch(valuesChecked, y.card);
						String[] extract = y.card.split(" ");
						cards[z] = poker.new Card(extract[2], y.value);
						z++;
						
					
					}
				}
				int dHigh = determineHighCard(dealerHand);
				if (dHigh == 1) {
					dealerExchanged = 1;
					ArrayList<Card> removeCards = new ArrayList<Card>();
						for (Card a : dealerHand){
							
							if (!( a.card.equals(cards[0].card) || a.card.equals(cards[1].card) || a.card.equals(cards[2].card) || a.card.contains("Ace")) ) {
								removeCards.add(a);
							}
						}
						dealerHand.removeAll(removeCards);
						for (int i = 1; i <= removeCards.size(); i++) {
							dealerHand.add(deck.get(0));
							deck.remove(0);	
						}
						
				} 
				 else {
					dealerExchanged = 2;
					ArrayList<Card> removeCards = new ArrayList<Card>();
						for (Card a : dealerHand){
							if (!(a.card.equals(cards[0].card) || a.card.equals(cards[1].card) || a.card.equals(cards[2].card))) {
								removeCards.add(a);
							}
						}
						dealerHand.removeAll(removeCards);
						for (int i = 1; i <= removeCards.size(); i++) {
							dealerHand.add(deck.get(0));
							deck.remove(0);	
						}
					}
					
		
				}
			
			//HIGH-CARD
			
			if (dealerHandType.ordinal() == 1) {
				Boolean Straight = false;
				Boolean Flush = false;
				String [] sHand = new String[5];
				for (int i = 0; i <= 4; i++) {
					sHand[i] = (dealerHand.get(i)).toString();
				}
				
				int hearts = 0;
				int diamonds = 0;
				int spades = 0;
				int clubs = 0;
				for (String x : sHand) {
					if (x.contains("hearts")) {
						hearts++;
					} else if (x.contains("diamonds")) {
						diamonds++;
					} else if (x.contains("spades")) {
						spades++;
					} else if (x.contains("clubs")) {
						clubs++;
					}
				}
				//TRY FOR STRAIGHT?
				
				int[] cardValues = new int[5];
				for (int i = 0; i <=4; i++) {
					cardValues[i] = dealerHand.get(i).value;
				}
				bubbleSort(cardValues);
				for (int b = 0; b <= 2; b++) { 
				if (cardValues[b] + 1 ==  cardValues[b + 1] && cardValues[b+1]+ 1 == cardValues[b+2]) {
					ArrayList<String> valuesChecked = new ArrayList<String>();
					ArrayList<Integer> vc2 = new ArrayList<Integer>();
					int z = 0;
					Card[] cards = new Card[3];
					for (Card x : dealerHand) {
						valuesChecked.add((x.card));
						vc2.add((x.value));
					}
					Collections.sort(vc2);
					for ( Card y : dealerHand) { 
							if (!( y.value == vc2.get(b) - 1 || y.value == vc2.get(b) || y.value == vc2.get(b+1) || y.value == vc2.get(b+2) || y.value == vc2.get(b+2) + 1)) {
							String[] extract = y.card.split(" ");
							cards[z] = poker.new Card(extract[2], y.value);
							z++;
						}
					}
					
					ArrayList<Card> removeCards = new ArrayList<Card>();
					if (cards[1] == null) {
						//Dummy Card
						cards[1] = poker.new Card("hearts", 1);
					}
					for (Card a : dealerHand){
						
						if (( a.card.equals(cards[0].card) || a.card.equals(cards[1].card)) ) {
							removeCards.add(a);
							
						}
					}
					
					for (Card ctr : removeCards) {
						dealerHand.remove(ctr);
					}
					
					for (int i = 1; i <= removeCards.size(); i++) {
						dealerHand.add(deck.get(0));
						deck.remove(0);	
					}
					
					Straight = true;
					dealerExchanged = removeCards.size();
					break;
							
				}
				
			} //END TRY FOR STRAIGHT!	
				
				
				//TRY FOR FLUSH!!!!
				if ((hearts >= 3 || diamonds >= 3 || spades >= 3 || clubs >= 3) && (!Straight)) {
					@SuppressWarnings("unused")
					int e1 = 0;
					ArrayList<String> valuesChecked = new ArrayList<String>();
					ArrayList<String> vc2 = new ArrayList<String>();
					int match = 0;
					int z = 0;
					Card[] cards = new Card[4];
					for (Card x : dealerHand) {
						valuesChecked.add((x.card));
						vc2.add((x.suit));
					}
					
					for ( Card y : dealerHand) {
						match = Collections.frequency(vc2, y.suit);
						
						if (match == 3 || match == 4) {
							
							String[] extract = y.card.split(" ");
							cards[z] = poker.new Card(extract[2], y.value);
							z++;
							
						
						}
					}
					//Dummy Card
					if (z == 3) {
					cards[3] = poker.new Card("hearts", 1);
					}
					
						ArrayList<Card> removeCards = new ArrayList<Card>();
							for (Card a : dealerHand){
								
								if (!( a.card.equals(cards[0].card) || a.card.equals(cards[1].card) || a.card.equals(cards[2].card) || a.card.equals(cards[3].card)) ) {
									removeCards.add(a);
									
								}
							}
							dealerHand.removeAll(removeCards);
							for (int i = 1; i <= removeCards.size(); i++) {
								dealerHand.add(deck.get(0));
								deck.remove(0);	
							}
							Flush = true;
							dealerExchanged = removeCards.size();
						
				}// END TRY FOR FLUSH
				
				if (!(Straight) && !(Flush)) {
					cardValues = new int[5];
					for (int i = 0; i <=4; i++) {
						cardValues[i] = (dealerHand.get(i)).value;
					}
					bubbleSort(cardValues);
					ArrayList<Card> removeCards = new ArrayList<Card>();
					for (Card x : dealerHand) {
						if (!(x.value == cardValues[cardValues.length - 1] || x.value == cardValues[cardValues.length-2] || x.value == 1)) {
							removeCards.add(x);
						
						}
					}
					
					dealerHand.removeAll(removeCards);
					for (int i = 1; i <= removeCards.size(); i++) {
						dealerHand.add(deck.get(0));
						deck.remove(0);	
					}
					dealerExchanged = removeCards.size();	
				}
			
			
			}
			
			dealerCards = new JLabel[5];
			dealer.removeAll();
			for (int x = 0; x <= 4; x++) {
				if (openG) {
					dealerCards[x]  = new JLabel(new ImageIcon("cardImages/" + dealerHand.get(x).card + ".gif"));
				} else {
					dealerCards[x]  = new JLabel(new ImageIcon("cardImages/Back.gif"));
				}
				dealerCards[x].setName(dealerHand.get(x).card);
				dealer.add(dealerCards[x]);
			}
			
			dealer.validate();
			dealer.repaint();
			
			
		}
		continueGamePartTwo(exchanged, dealerExchanged);
	}
	
	public static void continueGamePartTwo(int exchanged, int dealerExchanged) {

		status = new JLabel[3];
		status[0] = new JLabel("You exchange " + exchanged + ((exchanged == 1) ? " card..." : " cards..."));
		status[1] = new JLabel("Dealer deals you " + exchanged + ((exchanged == 1) ? " card..." : " cards..."));
		userHType = evaluateHand(userHand);
		status[2] =	new JLabel("Dealer takes " + dealerExchanged  + ((dealerExchanged == 1) ? " card..." : " cards..."));
		for (JLabel x : status) {
			x.setForeground(Color.white);
			activity.add(x);
		}
		
		JLabel Hand = new JLabel("You currently have " + handTypeToString(userHType));
		Hand.setForeground(Color.white);
		activity.add(Hand);
		
		//Second round of betting...
		Boolean infinite = false;
		Boolean back = false;
		Boolean validRaise = false;
		int raise = 0;
		do {
			Object[] options = {"Raise","Check", "Fold"};
			Boolean valid = false;
			int confirm = 0;
			do {
				int selection = JOptionPane.showOptionDialog(application, "Please select an action","Select Action", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (selection == 0) {
					valid = true;	
				} else if (selection == 1) {
					JLabel check = new JLabel("You check...");
					check.setForeground(Color.white);
					activity.add(check);
					hand_type dealerHandType = evaluateHand(dealerHand);
					if (dealerHandType.ordinal() >= 3 ) {
						Random rand = new Random();
						int dealerRand = rand.nextInt(101);
						double raiseAmount = 0;
						if (dealerRand <= 60) {
							raiseAmount = userCredit * (1.0/3.0);
						} else if (dealerRand <= 90 && dealerRand >= 61) {
							raiseAmount = userCredit * (1.0/2.0);
						} else if (dealerRand <= 100 && dealerRand >=91) {
							raiseAmount = userCredit * (3.0/4.0);
						}
					
						JLabel dealerRaise2 = new JLabel("Dealer raises $" + Math.floor(raiseAmount) + "...");
						dealerRaise2.setForeground(Color.white);
						pot += Math.floor(raiseAmount);
						potLabel.setText("Pot: $" + pot);
						potLabel.setForeground(Color.white);
						activity.add(dealerRaise2);
					} else { 
						
						Random rand = new Random();
						int foldRand = rand.nextInt(101);
						if (foldRand <= 20) {
							JOptionPane.showMessageDialog(application, "Dealer folds... You collect a pot of $" + (pot + raise));
							userCredit += pot;
							dealerHand.clear();
							userHand.clear();
							user.removeAll();
							dealer.removeAll();
							application.remove(user);
							application.remove(dealer);
							activity.removeAll();
							userWins = false;
							exchangeBtn.setVisible(false);
							startGame(openG,userCredit, false);
							return;
						} else {
						JLabel dealerRaise2 = new JLabel("Dealer checks...");
						dealerRaise2.setForeground(Color.white);
						
						activity.add(dealerRaise2);
						}
					}
					
					//Reveal dealer hand...
					if (openG) {
					
					} else {
						dealer.removeAll();
						dealerCards = new JLabel[5];
						for (int x = 0; x <= 4; x++) {
							dealerCards[x]  = new JLabel(new ImageIcon("cardImages/" + dealerHand.get(x).card + ".gif"));
							dealerCards[x].setName(dealerHand.get(x).card);
							dealer.add(dealerCards[x]);
						}
						dealer.validate();
						dealer.repaint();
					}
					
					hand_type dealerHType = evaluateHand(dealerHand);
					JLabel dealerReveal =	new JLabel("Dealer has " + handTypeToString(dealerHType));
					userHType = evaluateHand(userHand);
					JLabel userReveal =	new JLabel("You have " + handTypeToString(userHType));
					dealerReveal.setForeground(Color.white);
					userReveal.setForeground(Color.white);
					activity.add(dealerReveal);
					activity.add(userReveal);
					
					userWins = compareHandsGraphics(userHand,dealerHand);
					
					JLabel winner = new JLabel(((userWins) ? "You win!" : "Dealer wins!"));
					JLabel collectPot = new JLabel((userWins ? ("You collect a pot of $" + pot + "!") : ("Dealer collects a pot of $" + pot + "!")));
					winner.setForeground(Color.white);
					collectPot.setForeground(Color.white);
				
					if (userWins) {
						userCredit += pot;	
					}
					
					ucLabel.setText("User Credit: $" + userCredit);
					pot = 0.00;
					potLabel.setText("Pot: $" + pot);
	
					activity.add(winner);
					activity.add(collectPot);
					if (userWins) {
						JOptionPane.showMessageDialog(application, "Congratulations! You've won!");	
					} else {
						JOptionPane.showMessageDialog(application, "Unfortunately, you lost...");
					}
					dealerHand.clear();
					userHand.clear();
					user.removeAll();
					dealer.removeAll();
					application.remove(user);
					application.remove(dealer);
					activity.removeAll();
					userWins = false;
					exchangeBtn.setVisible(false);
					startGame(openG, userCredit, false);
					return;
				}
				
				else if (selection == 2) {
					valid = false;
					confirm = 1;
					JOptionPane.showMessageDialog(application, "You fold... Dealer collects a pot of $" + pot);
					dealerHand.clear();
					userHand.clear();
					user.removeAll();
					dealer.removeAll();
					application.remove(user);
					application.remove(dealer);
					activity.removeAll();
					userWins = false;
					exchangeBtn.setVisible(false);
					startGame(openG,userCredit, false);
					return;
					
				}
			} while (valid == false && confirm != 1);
			if (valid) {
				
				//Raise prompt
				validRaise = false;
				raise = 0;
				do {
				String raiseString = (String) JOptionPane.showInputDialog(application, "Please input the amount you want to raise (in whole dollars):", "Raise Amount", JOptionPane.QUESTION_MESSAGE, null, null, null );	
				if (raiseString == null) {
					back = true;
					break;	
				}
				try {
				raise = Integer.parseInt(raiseString);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(application, "The amount you entered was not valid!");
					continue;
				}
				if (raise > userCredit) {
					JOptionPane.showMessageDialog(application, "You do not have enough money for the specified raise.");
				} else {
					validRaise = true;
				}
				
				} while (!validRaise);

			if (back) {  back = false; continue; }	
			JLabel raise2Status = new JLabel("You raise $" + (double) raise + "...");
			raise2Status.setForeground(Color.white);
			activity.add(raise2Status);
			Random rand = new Random();
			
			//Dealer Actions Sequence (50% call, 40% check, 10% fold) 
			int dealerAction = rand.nextInt(101);
			if (dealerAction <= 50) {
				
				//Dealer call
				JLabel dealerCall = new JLabel("Dealer sees your raise...");
				dealerCall.setForeground(Color.white);
				userCredit -= raise;
				pot += raise*2;
				ucLabel.setText("User Credit: $" + userCredit);
				potLabel.setText("Pot: $" + pot);
				activity.add(dealerCall);
				
			} else if (dealerAction <= 90 && dealerAction >= 51) {
				
				JLabel dealerC = new JLabel("Dealer checks...");
				dealerC.setForeground(Color.white);
				activity.add(dealerC);		
				
			} else if (dealerAction <= 100 && dealerAction >= 91) {
				//Dealer fold...
				JOptionPane.showMessageDialog(application, "Dealer folds... You collect a pot of $" + (pot + raise));
				userCredit += pot;
				dealerHand.clear();
				userHand.clear();
				user.removeAll();
				dealer.removeAll();
				application.remove(user);
				application.remove(dealer);
				activity.removeAll();
				userWins = false;
				exchangeBtn.setVisible(false);
				startGame(openG,userCredit, false);
				return;
				
			}
			
			//Reveal dealer hand...
			if (openG) {
			
			} else {
				dealer.removeAll();
				dealerCards = new JLabel[5];
				for (int x = 0; x <= 4; x++) {
					dealerCards[x]  = new JLabel(new ImageIcon("cardImages/" + dealerHand.get(x).card + ".gif"));
					dealerCards[x].setName(dealerHand.get(x).card);
					dealer.add(dealerCards[x]);
				}
				dealer.validate();
				dealer.repaint();
			}
			
			hand_type dealerHType = evaluateHand(dealerHand);
			JLabel dealerReveal =	new JLabel("Dealer has " + handTypeToString(dealerHType));
			userHType = evaluateHand(userHand);
			JLabel userReveal =	new JLabel("You have " + handTypeToString(userHType));
			dealerReveal.setForeground(Color.white);
			userReveal.setForeground(Color.white);
			activity.add(dealerReveal);
			activity.add(userReveal);
			
			userWins = compareHandsGraphics(userHand,dealerHand);
			
			JLabel winner = new JLabel(((userWins) ? "You win!" : "Dealer wins!"));
			JLabel collectPot = new JLabel((userWins ? ("You collect a pot of $" + pot + "!") : ("Dealer collects a pot of $" + pot + "!")));
			winner.setForeground(Color.white);
			collectPot.setForeground(Color.white);
		
			if (userWins) {
				userCredit += pot;	
			}
			
			ucLabel.setText("User Credit: $" + userCredit);
			pot = 0.00;
			potLabel.setText("Pot: $" + pot);

			activity.add(winner);
			activity.add(collectPot);
			if (userWins) {
				JOptionPane.showMessageDialog(application, "Congratulations! You've won!");	
			} else {
				JOptionPane.showMessageDialog(application, "Unfortunately, you lost...");
			}
			dealerHand.clear();
			userHand.clear();
			user.removeAll();
			dealer.removeAll();
			application.remove(user);
			application.remove(dealer);
			activity.removeAll();
			userWins = false;	
			exchangeBtn.setVisible(false);
			startGame(openG, userCredit, false);
			return;
			
		}
	} while(!infinite);		
	}	
}

