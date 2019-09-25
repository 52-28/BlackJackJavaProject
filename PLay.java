import java.util.Scanner;
import java.util.Random;

public class PLay {
	
		public static int dealCardPlayer(int turn){
			
			//initialisation des tableaux
			String[] cards = {"A","2","3","4","5","6","7","8","9","10","V","D","R","A"};
			int[] cardsValue = {1,2,3,4,5,6,7,8,9,10,10,10,10,11};
			
			Random randomNumber = new Random();
			String[] randomCards = new String[3];			
			int randomCheck;			
			int randomResult = 0;
			String yourMain = "Votre main: ";

			if (turn == 0){	
				for (int i = 0 ; i <= 1; i++){
					randomNumber = new Random();
					randomCheck = randomNumber.nextInt(13);				
					randomCards[i] = cards[randomCheck]; 		 
					randomResult = randomResult + cardsValue[randomCheck];

					/* V0					
					System.out.println(randomCards[i]);
					randomResult = randomResult + randomCards[i];
					*/
				}
				
				for (int i = 0 ; i < randomCards.length -1; i++){
					
					yourMain = yourMain + randomCards[i] + " | ";  										
				}
				yourMain = yourMain.substring(0, yourMain.length() - 3);
				System.out.println(yourMain);
/*
				for (i = 0 ; i == randomCards.length(); i++){
					if ((randomCards[i] == 1 || randomCards[i] == 11) && (randomResult == 2 || randomResult == 22)){
						randomResult =12;
					}
					else if ((randomCards[i] == 1 || randomCards[i] == 11) && (randomResult == 12)){					
					}
					else if ((randomCards[i] == 1 || randomCards[i] == 11) && (randomResult == 21 || randomResult == 11)){
						randomResult =21;
						System.out.println("Blackjack");					
					}
										
				}

				randomCards[2] = randomResult;
*/
			}
			return randomResult;
		}

		public static void main(String[] args){

		//Demande Take a Card		
		Scanner sc = new Scanner(System.in);		
		System.out.println("We play? (yes no)");
		String answer = sc.nextLine();				

		while (!answer.equals("no") && !answer.equals("yes")){				

				System.out.println("I don't understand?");
				System.out.println("We play? (yes no)");
				answer = sc.nextLine();		
					
		}
		
		if (answer.equals("yes")){ 
			//on rentre dans le game
			//appel methode tirage de carte turn = 0
			System.out.println("Votre score:" + dealCardPlayer(0));								
		}		
		else if (answer.equals("no")){
			//System.out.println("Bye bye");
			//break;
		}

		// tirage des cartes joueur
			//boucle tirer carte? + condition <=21 + == 21

		//tirage des cartes dealer								
	}
}