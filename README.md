Name: Joseph Morris
Project #2, Poker
Date: 11-7-10

# Note:
This code has not been edited or maintained since it was written. It serves as an early example of my explorations in coding.

# Description:
Each component of this program serves a different purpose, FOR INSTRUCTIONS ON USE OF EACH SEE LIST BELOW:

Problem #1 - The object of this problem was to generate an ArrayList or an array that represents a standard deck 52 playing cards. Once the deck is created, it is to be printed out in order, shuffled, and then printed out again. To test this, one must include either “t1” or “test1” in the command line arguments. For this problem, a subclass Card was used. This problem was solved by using an ArrayList to hold the Card objects. To generate the deck, a “for” loop was used to cycle through the suits and values to create Card objects which are then stored in the ArrayList. The showDeck method simply cycles through the ArrayList and prints the toString value of each Card object. The shuffleDeck method uses Collections.shuffle() to randomize the order of the Card objects.

Problem #2 - The object of this problem was to deal two 5-card hands by drawing from the deck, display the hands, and then show the remaining cards in the deck. To test this, one must include either “t2” or “test2” in the command line arguments. An if-statement using a modulus operator was used to simulate the alternating addition of cards to the hand (i.e. the player first, then the deal, then the player, etc.) The card to be added to a hand was always taken from the top of the deck to simulate real life conditions. Since the deck is an ArrayList, Card objects could be easily removed from the deck and added to the user and dealer hands (both of which are also ArrayLists).

Problem #3 – The object of this problem was to enable the program to classify the various possible hands in the game of poker when presented with a hand to evaluate. To do this, an enumeration was used to rank the hand types. Starting with the lowest rank, “High Card”, and moving to the highest rank “Royal Flush”. The program first counts the number of each suit in the hand to be evaluated. It then checks for a Royal Flush, followed by a Straight Flush, Straight, Four of a Kind, Full House, and finally a Flush. If none of these are found the determineHandMatches method is called to check for pairs and Three of a Kind. To test this, one must include either “t3” or “test3” in the command line arguments. The following notation is used for inputting a command line string-based hand in the form of: VXVXVXVXVX, where V is the value of the card {2,3,4,5,6,7,8,9,T,t,J,j,Q,q,K,k,A,a} and X is the suit of the card {C,c,H,h,S,s,D,d}.

Problem #4 – The object of this problem was to enable the program to compare two hands and determine which hand “wins”.  This is done in the compareHands method where the hands are first evaluated for the hand_type enumeration. If both are ONE_PAIR, the program then compares the rank of the pairs to determine which hand has the higher ranking pair. If the user and dealer have the same hand_type then the program determines the high card to determine the winner. If the initial high cards are equal, the program loops to find the next high card until a winner is determined. Otherwise, if the user and dealer have different hand_type, the ordinal properties of the two hand_type are compared. The winner is determined by who has the highest hand_type ordinal. To test this, one must include either “t4” or “test4” in the command line arguments. The following notation is used for inputting two command line string-based hands (separated by a space in the command line argument list) in the form of: VXVXVXVXVX, where V is the value of the card {2,3,4,5,6,7,8,9,T,t,J,j,Q,q,K,k,A,a} and X is the suit of the card {C,c,H,h,S,s,D,d}.

Problem #5 – The object of this program was to create the actual playing procedure for the poker game. The command line arguments “o” and “open” can be user to allow the player to see the dealer’s cards. The following requirements for the procedure were specified: user starts will $100 credit, asks if the user wants to play another hand, if the user does not want to it prints the user’s credit, if the user does play it takes a $5 buy in (deviated from the $1 buy-in for aesthetic reasons), the amount of the pot is displayed. The program first generates a deck, shuffles the deck, and then deals two hands.  The user then has the opportunity to raise or fold. After which, the user has the chance to exchange 3 to 4 cards (4 only if the user has an Ace). The user can then fold, raise, or check. The showdown then occurs where the winner is determined.

NOTES ON GRAPHICS:  For the sake of higher learning and practice in Java programming, I decided to make this a graphics-based game. The “cards” are JLabels that have been assigned an image. The images for the cards are open source and therefore can be used freely in the program. Multiple JPanels are used and positioned on the JFrame. Various layouts (especially GridBagLayout) are used to positions the elements on the content pane. An activity log is displayed to keep the user informed of the activity of the program. In addition, when the user goes to exchange cards, a mouseListener is added to the JLabels to enable the user to click on the cards that they want to exchange. SEE NOTES SECTION FOR MORE INFORMATION ON EXTRA FEATURES!


# Included Files and INSTRUCTIONS FOR USAGE OF FILES:
README.txt
Poker.java
Poker$1.class
Poker$2.class
Poker$3.class
Poker$Card.class
Poker$cardClick.class
Poker$hand_type.class
Poker.class
Poker.java
Screenshot.jpg
cardImages <Folder>
Ace of clubs.gif
Ace of diamonds.gif
Ace of hearts.gif
Ace of spades.gif
Back.gif
Eight of clubs.gif
Eight of diamonds.gif
Eight of hearts.gif
Eight of spades.gif
Five of clubs.gif
Five of diamonds.gif
Five of hearts.gif
Five of spades.gif
Four of clubs.gif
Four of diamonds.gif
Four of hearts.gif
Four of spades.gif
Jack of clubs.gif
Jack of diamonds.gif
Jack of hearts.gif
Jack of spades.gif
King of clubs.gif
King of diamonds.gif
King of hearts.gif
King of spades.gif
Nine of clubs.gif
Nine of diamonds.gif
Nine of hearts.gif
Nine of spades.gif
Queen of clubs.gif
Queen of diamonds.gif
Queen of hearts.gif
Queen of spades.gif
Seven of clubs.gif
Seven of diamonds.gif
Seven of hearts.gif
Seven of spades.gif
Six of clubs.gif
Six of diamonds.gif
Six of hearts.gif
Six of spades.gif
Ten of clubs.gif
Ten of diamonds.gif
Ten of hearts.gif
Ten of spades.gif
Three of clubs.gif
Three of diamonds.gif
Three of hearts.gif
Three of spades.gif
Two of clubs.gif
Two of diamonds.gif
Two of hearts.gif
Two of spades.gif

README.txt:
This file.

Poker.java:
This class contains the methods and subclasses needed for the poker game to function.

Parts Implemented:
All but that which is included in Parts Not Implemented.

Parts Not Implemented:
During an open game, the user cannot see the remaining cards of the deck. This was NOT intentionally left out. I accidentally forgot to add this feature.

# Notes:
In addition to the graphical interface, the dealer has been programmed with a basic artificial intelligence concerning betting, folding, checking, and exchanging cards.

# Known Bugs:
There are no known bugs so long as the user enters the correct form and content for command line arguments during test cases 3 and 4.

Extra Credit:
See Notes.

Sample Output:
 
Problem #1:
Ace of hearts
Two of hearts
Three of hearts
Four of hearts
Five of hearts
Six of hearts
Seven of hearts
Eight of hearts
Nine of hearts
Ten of hearts
Jack of hearts
Queen of hearts
King of hearts
Ace of diamonds
Two of diamonds
Three of diamonds
Four of diamonds
Five of diamonds
Six of diamonds
Seven of diamonds
Eight of diamonds
Nine of diamonds
Ten of diamonds
Jack of diamonds
Queen of diamonds
King of diamonds
Ace of spades
Two of spades
Three of spades
Four of spades
Five of spades
Six of spades
Seven of spades
Eight of spades
Nine of spades
Ten of spades
Jack of spades
Queen of spades
King of spades
Ace of clubs
Two of clubs
Three of clubs
Four of clubs
Five of clubs
Six of clubs
Seven of clubs
Eight of clubs
Nine of clubs
Ten of clubs
Jack of clubs
Queen of clubs
King of clubs

Six of clubs
Nine of diamonds
Jack of clubs
Queen of clubs
Queen of diamonds
Two of spades
Five of clubs
Four of diamonds
Eight of clubs
Two of hearts
Eight of hearts
King of hearts
Three of clubs
Nine of hearts
Two of diamonds
Nine of clubs
Six of spades
Three of diamonds
Four of clubs
Four of spades
King of spades
Jack of spades
Ace of clubs
King of clubs
Jack of hearts
Six of hearts
Four of hearts
Five of spades
Five of hearts
Nine of spades
Ace of diamonds
Queen of spades
King of diamonds
Ten of clubs
Ace of spades
Jack of diamonds
Seven of hearts
Two of clubs
Ten of hearts
Ten of diamonds
Seven of spades
Six of diamonds
Five of diamonds
Queen of hearts
Seven of diamonds
Eight of diamonds
Three of spades
Seven of clubs
Three of hearts
Ace of hearts
Ten of spades
Eight of spades

Problem #2:
Ace of hearts
Two of hearts
Three of hearts
Four of hearts
Five of hearts
Six of hearts
Seven of hearts
Eight of hearts
Nine of hearts
Ten of hearts
Jack of hearts
Queen of hearts
King of hearts
Ace of diamonds
Two of diamonds
Three of diamonds
Four of diamonds
Five of diamonds
Six of diamonds
Seven of diamonds
Eight of diamonds
Nine of diamonds
Ten of diamonds
Jack of diamonds
Queen of diamonds
King of diamonds
Ace of spades
Two of spades
Three of spades
Four of spades
Five of spades
Six of spades
Seven of spades
Eight of spades
Nine of spades
Ten of spades
Jack of spades
Queen of spades
King of spades
Ace of clubs
Two of clubs
Three of clubs
Four of clubs
Five of clubs
Six of clubs
Seven of clubs
Eight of clubs
Nine of clubs
Ten of clubs
Jack of clubs
Queen of clubs
King of clubs

Two of hearts
Three of clubs
Seven of diamonds
Jack of clubs
Nine of clubs
Eight of clubs
Six of spades
Ten of clubs
Ten of spades
Queen of clubs
Four of hearts
Seven of clubs
Five of diamonds
Two of clubs
Five of clubs
Nine of diamonds
King of hearts
Eight of diamonds
Ten of diamonds
Three of spades
King of spades
Queen of hearts
Nine of hearts
Jack of hearts
Four of clubs
King of clubs
Three of diamonds
Ace of hearts
Ace of spades
Ten of hearts
Nine of spades
Four of diamonds
Four of spades
Six of clubs
Eight of spades
Seven of spades
Five of spades
Two of spades
Ace of clubs
Six of hearts
Ace of diamonds
Eight of hearts
Seven of hearts
Jack of spades
King of diamonds
Queen of spades
Three of hearts
Six of diamonds
Two of diamonds
Jack of diamonds
Queen of diamonds
Five of hearts

User hand:
	Two of hearts
	Seven of diamonds
	Nine of clubs
	Six of spades
	Ten of spades

Dealer hand:
	Three of clubs
	Jack of clubs
	Eight of clubs
	Ten of clubs
	Queen of clubs

Cards remaining:
Four of hearts
Seven of clubs
Five of diamonds
Two of clubs
Five of clubs
Nine of diamonds
King of hearts
Eight of diamonds
Ten of diamonds
Three of spades
King of spades
Queen of hearts
Nine of hearts
Jack of hearts
Four of clubs
King of clubs
Three of diamonds
Ace of hearts
Ace of spades
Ten of hearts
Nine of spades
Four of diamonds
Four of spades
Six of clubs
Eight of spades
Seven of spades
Five of spades
Two of spades
Ace of clubs
Six of hearts
Ace of diamonds
Eight of hearts
Seven of hearts
Jack of spades
King of diamonds
Queen of spades
Three of hearts
Six of diamonds
Two of diamonds
Jack of diamonds
Queen of diamonds
Five of hearts

Problem #3:
User hand:
	Ten of hearts
	King of hearts
	Queen of hearts
	Jack of hearts
	Ace of hearts

ROYAL_FLUSH

Problem #4:

User hand:
	Ten of hearts
	King of hearts
	Queen of hearts
	Jack of hearts
	Ace of hearts

Dealer hand:
	Five of hearts
	Five of spades
	Five of clubs
	Two of diamonds
	Two of hearts

User has a Royal Flush
Dealer has a Full House
User wins!

Problem #5:

See included screenshot for output



