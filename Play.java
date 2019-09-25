import java.util.Scanner;
import java.util.Random;

public class Play {

		public static int[] dealCardPlayer(int turn, int[] score){

			/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			//			distribution des cartes			      |
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
			
			//initialisation des tableaux
			String[] cards = {"A","2","3","4","5","6","7","8","9","10","V","D","R","A"};
			int[] cardsValue = {1,2,3,4,5,6,7,8,9,10,10,10,10,11};
			
			//System.out.println("5e passage" + score[0]);
			Random randomNumber = new Random();

			String[][] randomCards = new String[2][2];
			int[][] randomCardsValue = new int [2][2];
			
			int randomCheck;			
			int[] randomResult = new int[2];
			//System.out.println("6e passage" + score[0]);

			String yourMain = "					BlackJack						\n";
	     		yourMain = yourMain + "--------------------------------------------------------------------------------------------- \n|Votre main:      ";

			if (turn == 0){	
				for (int i = 0 ; i <= 1; i++){
					for(int j = 0; j < randomCards[i].length; j++){ 
					
						//calcul des mains aléatoires du joueur et de la banque

						randomNumber = new Random();
						randomCheck = randomNumber.nextInt(13);				
						randomCards[i][j] = cards[randomCheck];
						randomCardsValue[i][j] = cardsValue[randomCheck];

						//debug						
						System.out.println("j" +i + " ,carte"+ j + ": " + randomCards[i][j]); 		 
						
						//fin debug
						
						randomResult[i] = randomResult[i] + cardsValue[randomCheck];
						System.out.println("résultat j" + i + ": " +randomResult[i]);
					}

					/* V0					
					System.out.println(randomCards[i]);
					randomResult = randomResult + randomCards[i];
					*/
				}

				//print de la main du joueur

				for (int i = 0 ; i < randomCards[1].length; i++){
					
					yourMain = yourMain + randomCards[0][i] + " | ";  										
				}

				yourMain = yourMain.substring(0, yourMain.length()-3);
				System.out.println(yourMain + "            || Banque: " + randomCards[1][0] + " | ? ");

			}
			else {
				System.out.println("tu tires une carte? (y n)");
				Scanner sc = new Scanner(System.in);
				String answer = sc.nextLine();
				
				if (answer.equals("y")){

					randomNumber = new Random();
					randomCheck = randomNumber.nextInt(13);
					System.out.println("carte tirée:" +cards[randomCheck] + ", votre score: " + (score[0] + cardsValue[randomCheck]));
					
				}
				else{
					//tour de la banque	
					System.out.println("tes une tata, ta peur?)");			
				}			
			}

			//test blackjack

			//test pour le joueur0
			if ((randomResult[0] == 21) || (randomCardsValue[0][0] == 1 && (randomResult[0] == 11))){
				randomResult[0]= 21;

			}
			else{
				//appel de chekcAss pour modification des valeurs AS
				randomResult = checkAssCards(randomCardsValue, randomResult);
			} 			

			//
			// int[][] connard = {{1,4},{0,0}};
			// int[] connard2 =  {5,0};
			// checkAssCards(connard, connard2);
			
			if (score[0] == 0){
				//System.out.println("1er tour");
			}
			else{
				
				//System.out.println("+1 tour" + score[0]);
				randomResult[0] = score[0] + randomResult[0];
			}
		return randomResult;
		
		}

		public static void message(){
			System.out.println("Vous avez 1 as, merci de renseigner une valeur (1 ou 11)");		
		}

		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//			cherche As				      |
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

		public static int[] checkAssCards(int[][]randomCardsValue , int[]randomResult){
			for (int i = 0 ; i<randomCardsValue.length ; i++){
				if (i == 0){
					if ((randomCardsValue[i][0] == 1 || randomCardsValue[i][0] == 11) && (randomCardsValue[i][1] == 1 || randomCardsValue[i][1] == 11)){
						System.out.println("Vous avez 2 as, merci de choisir une valeur pour chacun d'entre eux (ex :1 11):");
						Scanner sc = new Scanner(System.in);
						String answer = sc.nextLine();					
					}
					else if (randomCardsValue[i][0] == 1 || randomCardsValue[i][0] == 11) {
						message();
						Scanner sc = new Scanner(System.in);
						String answer = sc.nextLine();
						//TO DO: modifier le resultat total
						randomResult[i]= randomResult[i] - (randomCardsValue[i][0] - Integer.parseInt(answer));	
						//System.out.println(randomResult[i]);				
					} 
					else if (randomCardsValue[i][1] == 1 || randomCardsValue[i][1] == 11) {
						message();
						Scanner sc = new Scanner(System.in);
						String answer = sc.nextLine();
						//TO DO: modifier le resultat total
						randomResult[i]= randomResult[i] - (randomCardsValue[i][1] - Integer.parseInt(answer));	
						//System.out.println(randomResult[i]);									
					}
 				
				}			
			}
			return randomResult;
		}

		public static void main(String[] args){

		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//			début programme				      |
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

		//Demande Take a Card		
		Scanner sc = new Scanner(System.in);		
		System.out.println("We play? (yes no)");
		String answer = sc.nextLine();				
		int[] score = new int[2];
		boolean finish = false; 
		int compteur = 0;
		
		while (!answer.equals("no") && !answer.equals("yes")){				

			System.out.println("I don't understand?");
			System.out.println("We play? (yes no)");
			answer = sc.nextLine();		
					
		}
		
		if (answer.equals("yes")){ 
			//on rentre dans le game
			
			//appel methode tirage de carte turn = 0
			
			while (finish == false) {			
				
				//faut faire une boucle!
				int[] newscore = dealCardPlayer(compteur, score);
				for (int i = 0 ; i < score.length ; i++){
	
					score[i] = score[i] + newscore[i];			
				}

				//System.out.println("toto passage" + score[0]);	
				if (score[0] == 21){
					System.out.println("BlackJack!!! vous avez gagné!");			
					finish = true;			
				}
				else if (score[0]>21){
					System.out.println("Perdu!");
					finish = true;				
				}	
				else{
					compteur++;
				}
				//System.out.println("2e passage" + score[0]);
			}				
		}
	
		//System.out.println("Score de la banque:" + dealCardPlayer(0));	
		else if (answer.equals("no")){
			System.out.println("Bye bye");
		}
									
	}
}
