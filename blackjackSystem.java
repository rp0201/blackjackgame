import java.util.Random;
import java.util.Scanner;

class suitedCard{ // --> Object for any playing card
    /*SuitedCard: an object for describing any playing card.
    contains int rankID, which is an integer used to encode which of the 52 playing cards it actually is.  It has values between 0 and 51.
    contains int serialNumber, which is an integer that describes which card it is within the multideck system.  This is any positive integer.
    Contains void method printSuit(), which prints “not a suited card!”.  This is because the method is intended to be overridden.
    Contains method suitID() which returns int, and in the case of suitedCard, returns -1, because it is meant to be overridden.
    Contains method cardID(), which returns String.  Returns English words two, three, .. up to 10, when rankID%13 >=1 and <= 9.  Returns English word “ace” when rankID==0, and “jack”, “queen”, or “king” when rankID is 10, 11, or 12, respectively.
    */

    public int rankID; // --> Represents playing card type (0-51)
    public int serialNumber; // --> Unique serial number for ALL cards

    public suitedCard(int rankID, int serialNumber){ // Initializes rankID and serialNumber
        this.rankID = rankID;
        this.serialNumber = serialNumber;
    }

    public void printSuit(){ // Prints a default statement
        System.out.print("not a suited card!");
    }

    public int suitID(){ // Returns a default suitID
        return -1;
    }

    public String cardID(){ // Returns the name of a card based on rank
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        int rank = rankID % 13; // Calculates rank for each suit
        return ranks[rank];
    }
}

class diamondCard extends suitedCard{ // Diamond suited cards

    /*
    Contains the method printSuit(), which overrides the method from SuitedCard, to print “of Diamonds”
    Contains method suitID() which returns int, and returns 0, for diamonds.
    Constructor accepts the rankID and serial number.
    Prints an error if the range of the rank ID is out of range (i.e. not 0-12).
    Sets rankID to -1, serial number to -1 if an error.
     */

    public diamondCard(int rankID, int serialNumber){ // Checking if rankID falls in an interval, if it doesn't return an error 
        super(rankID, serialNumber); // Calling parent constructor
        if (rankID < 0 || rankID > 12){
            rankID = -1;
            serialNumber = -1;
            System.out.println("Error [Diamond]: Out of range ");
        }
    }

    @Override
    public void printSuit(){
        System.out.print("of Diamonds "); // Override to print "of Diamonds"
    }

    public int suitID(){
        return 0; // Returns 0 for diamond suited cards
    }
}

class clubCard extends suitedCard{ // Club suited cards
    /*
    Contains the method printSuit(), which overrides the method from SuitedCard, to print “of Clubs”
    Contains method suitID() which returns int, and returns 1, for clubs.
    Constructor accepts the rankID and serial number.
    Prints an error if the range of the rank ID is out of range (i.e. not 13-25).
    Sets rankID to -1, serial number to -1 if an error.
    */
    @Override
    public void printSuit(){ // Override to print "of Clubs"
        System.out.print("of Clubs ");
    }

    public int suitID(){ // Returns 1 for club cards
        return 1;
    }

    public clubCard(int rankID, int serialNumber){ // Checking if rankID falls in an interval, if it doesn't, return an error
        super(rankID, serialNumber); // Calling parent constructor
        if (rankID < 13 || rankID > 25){
            rankID = -1;
            serialNumber = -1;
            System.out.println("Error [Clubs]: Out of range");
        }
    }
}

class heartCard extends suitedCard{ // Heart suited cards
    /*
    Contains the method printSuit(), which overrides the method from SuitedCard, to print “of hearts”
    Contains method suitID() which returns int, and returns 2, for hearts.
    Constructor accepts the rankID and serial number.  
    Prints an error if the range of the rank ID is out of range (i.e. not 26-38). 
    Sets rankID to -1, serial number to -1 if an error.
    */
    @Override
    public void printSuit(){ // Override to print "of Hearts"
        System.out.print("of Hearts ");
    }

    public int suitID(){ // Returns 2 if a heart suited card
        return 2;
    }

    public heartCard(int rankID, int serialNumber){ // Checking if rankID falls within an interval, if it doesn't, return an error
        super(rankID, serialNumber); // Calling parent constructor
        if (rankID < 26 || rankID > 38){
            rankID = -1;
            serialNumber = -1;
            System.out.print("Error [Hearts]: Out of range");
        }
    }
}

class spadeCard extends suitedCard{ // Spade suited cards

    /*
    Contains the method printSuit(), which overrides the method from SuitedCard, to print “of Spades”
    Contains method suitID() which returns int, and returns 3, for spades.
    Constructor accepts the rankID and serial number.  
    Prints an error if the range of the rank ID is out of range (i.e. not 39-51).  
    Sets rankID to -1, serial number to -1 if an error.
     */

    public spadeCard(int rankID, int serialNumber){ // Checking if rankID falls within an interval, if it doesn't, return an error
        super(rankID, serialNumber);
        if (rankID < 39 || rankID > 51){
            rankID = -1;
            serialNumber = -1;
            System.out.println("Error [Spades]: Out of range");
        }
    }
    @Override // Override to print "of Spades"
    public void printSuit(){
        System.out.print("of Spades ");
    }

    public int suitID(){ // Returns 3 for spades
        return 3;
    }
}

public class blackjackSystem{ // Runs blackjackSystem, a program which generates the first 2 cards drawn in a blackjack game
    // Contains the main method.

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); // Scanner for user input
        Random rand = new Random(); // Random object for shuffling cards
        int numberOfDecks = 0;

        // Step 1 -->
        while (true){ // Input validation , checking for positive integer, and also integer itself
            System.out.print("How many decks do you want to play with?: ");

            if (scan.hasNextInt()){
                numberOfDecks = scan.nextInt();
                if (numberOfDecks <= 0){ // If not positive, generate error
                    System.out.print("Error. Number of decks must be positive. ");
                } else{
                    break;
                }
            } else{ // If not a number, generate error
                System.out.print("Error. enter a positive integer. ");
                scan.next();
            }
        }

        // Step 2 -->
        suitedCard[] cards = new suitedCard[numberOfDecks * 52]; // Array to hold all the cards
        int[] rankIDs = new int[numberOfDecks * 52]; // Array to hold cards rankIDs

        for (int i = 0; i < numberOfDecks * 52; i++){ // Storing ranks in rankID
            rankIDs[i] = i % 52;
        }

        for (int i = 0; i < numberOfDecks * 52; i++){ // Assigning cards to appropriate suit based on rankID
            int rankID = rankIDs[i];
            int serialNumber = i;
            if (rankID < 13){ // RankID: 0-12
                cards[i] = new diamondCard(rankID, serialNumber);
            } else if (rankID < 26){ // RankID: 13-25
                cards[i] = new clubCard(rankID, serialNumber);
            } else if (rankID < 39){ // RankID: 26-38
                cards[i] = new heartCard(rankID, serialNumber);
            } else{ // RankID: 39-51 
                cards[i] = new spadeCard(rankID, serialNumber);
            }
        }

        // shuffle vv

        // Step 2a -->
        for (int i = 0; i < cards.length - 1; i++){ // Shuffling ALL cards
            int swapVal = rand.nextInt(numberOfDecks * 52);
            suitedCard temp = cards[i];
            cards[i] = cards[swapVal];
            cards[swapVal] = temp;
        }
        

        // Step 3 ->
        System.out.println("Dealing first two cards: "); // dealing first two cards, randomly shuffled
        for (int i = 0; i < 2; i++){
            System.out.print(cards[i].cardID() + " ");
            cards[i].printSuit();
            System.out.println();
        }
    }
}