import java.util.Scanner;
import java.util.Random;

public class Play {

		public static int[] dealCardPlayer(int turn, int[] score, int player){

			/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			//			distribution des cartes			      |
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
			
			//initialisation des tableaux
			String[] cards = {"A","2","3","4","5","6","7","8","9","10","V","D","R","A"};
			int[] cardsValue = {1,2,3,4,5,6,7,8,9,10,10,10,10,11};
			Random randomNumber = new Random();
			String[][] randomCards = new String[2][2];
			int[][] randomCardsValue = new int [2][2];
			int randomCheck;			
			int[] randomResult = new int[3];
			String yourMain = "					BlackJack						\n";
	     		yourMain = yourMain + "--------------------------------------------------------------------------------------------- \n|Votre main:      ";
			System.out.println(turn);
			if (turn == 0){	
				for (int i = 0 ; i <= 1; i++){
					for(int j = 0; j < randomCards[i].length; j++){ 
					
						//calcul des mains aléatoires du joueur et de la banque

						randomNumber = new Random();
						randomCheck = randomNumber.nextInt(13);				
						randomCards[i][j] = cards[randomCheck];
						randomCardsValue[i][j] = cardsValue[randomCheck];
						
						randomResult[i] = randomResult[i] + cardsValue[randomCheck];
					}
				}

				//print de la main du joueur

				for (int i = 0 ; i < randomCards[1].length; i++){
					
					yourMain = yourMain + randomCards[0][i] + " | ";  										
				}

				yourMain = yourMain.substring(0, yourMain.length()-3);
				System.out.println(yourMain + "            		|| Banque: " + randomCards[1][0] + " | ? \n ---------------------------------------------------------------------------------------------");

			}
			else {
								
				System.out.println("tu tires une carte? (y n)");
				Scanner sc = new Scanner(System.in);
				String answer = sc.nextLine();
				
				if (answer.equals("y")){

					randomNumber = new Random();
					randomCheck = randomNumber.nextInt(13);
					System.out.println("carte tirée:" +cards[randomCheck] + ", votre score: " + (score[0] + cardsValue[randomCheck]));
					randomCards[0][0] = cards[randomCheck];
					randomCardsValue[0][0] = cardsValue[randomCheck];
					randomResult[0] = cardsValue[randomCheck];
				}
				else{	
					player =1;
					int checkBq = score[1];
					
					while (checkBq < 17){					
			
						//tirage d'une carte	
						randomNumber = new Random();
						randomCheck = randomNumber.nextInt(13);
						System.out.println("carte tirée:" +cards[randomCheck] + ", score de la banque: " + (checkBq + cardsValue[randomCheck]));
						randomCards[1][0] = cards[randomCheck];
						randomCardsValue[1][0] = cardsValue[randomCheck];
						randomResult[1] = cardsValue[randomCheck];

						checkBq = checkBq + randomResult[1];

						score[1] = score[1] + randomResult[1];
						
					}
					randomResult[2] = player;
				}			
			}

			//test pour le joueur0
			if ((randomResult[0] == 21) || (randomCardsValue[0][0] == 1 && (randomResult[0] == 11))){
				randomResult[0]= 21;

			}
			else{
				//appel de chekcAss pour modification des valeurs AS
				//System.out.println("checkAssCards" + randomResult[0] + ","+ randomResult[1]);
				//System.out.println("Avant checkAssCards randomCardsValue[i]:" + randomCardsValue[0][1]);
				
					int[] check = checkAssCards(randomCardsValue, randomResult);				
					//System.out.println(check[0]);
					for (int i = 0 ; i < randomResult.length -1 ; i ++){					
						randomResult[i] = check[i];
					}	
			} 			
			
			if (score[0] == 0){
				//System.out.println("1er tour");
			}
/*			
			else{
				
				//System.out.println("+1 tour" + score[0]);
				//System.out.println("newrandomresult" + randomResult[0] +", score:" + score[0]);
				//randomResult[0] = score[0] + randomResult[0];
				//System.out.println("newrandomresult" + randomResult[0] +", score:" + score[0]);
			}
		//System.out.println("newrandomresult avant return" + randomResult[0]);		
*/		
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
					else if ((randomCardsValue[i][0] == 1 || randomCardsValue[i][0] == 11) && randomCardsValue[i][1]!=10) {
						message();
						Scanner sc = new Scanner(System.in);
						String answer = sc.nextLine();
						randomResult[i]= randomResult[i] - (randomCardsValue[i][0] - Integer.parseInt(answer));			
					} 
					else if ((randomCardsValue[i][1] == 1 || randomCardsValue[i][1] == 11) && randomCardsValue[i][0]!=10) {
						message();
						Scanner sc = new Scanner(System.in);
						String answer = sc.nextLine();
						randomResult[i]= randomResult[i] - (randomCardsValue[i][1] - Integer.parseInt(answer));									
					}
					else if (((randomCardsValue[i][1] == 1 || randomCardsValue[i][1] == 11) && randomCardsValue[i][0]==10) || ((randomCardsValue[i][0] == 1 || randomCardsValue[i][0] == 11) &&
						   randomCardsValue[i][1]==10)){
						randomResult[i] = 21;
						
											
					}
					else{
					}
				}			
			}
			return randomResult;
		}

		public static boolean replay(){
			Scanner sc = new Scanner(System.in);
			String answer = sc.nextLine();
			
			if (answer.equals("y")){
				return true;
			}
			else {
				return false;			
			}	
		}
		
		public static void main(String[] args){

		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//			début programme				      |
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

		//Demande Take a Card		
		Scanner sc = new Scanner(System.in);		
		System.out.println("We play? (yes no)");
		String answer = sc.nextLine();				
		int[] score = new int[3];
		boolean finish = false; 
		int compteur = 0;
		int player = 0;
		boolean replay;
		
		while (!answer.equals("no") && !answer.equals("yes")){				

			System.out.println("I don't understand?");
			System.out.println("We play? (yes no)");
			answer = sc.nextLine();		
					
		}
		
		if (answer.equals("yes")){ 
			//on rentre dans le game
			
			//appel methode tirage de carte turn = 0
			
			while (finish == false) {			
				
				int[] newscore = dealCardPlayer(compteur, score, player);
				player = newscore[2];
				score[0] = score[0] + newscore[0];
				if (compteur == 0){
					score[1] = newscore[1]	;			
				}

				//System.out.println("Score du joueur:" + score[0]);				

				
				if (player == 1) {	
				
					if ((((score[0] < score[1]) || score[0]>21) && score[1] <=21)){
						System.out.println("Score du joueur:" + score[0] + ", Score de la banque:" + score[1]);						
						System.out.println("Perdu! On rejoue?(y or n)");
						replay = replay();
						if (replay == true){
							compteur = 0;
							score = new int[3];
							player = 0;
						}
						else{						
							finish = true;
						}								
					}

					else if ((score[0] > score[1]) || score[1]>21){	
						System.out.println("Score du joueur:" + score[0] + ", Score de la banque:" + score[1]);						
						System.out.println("Gagné! On rejoue?(y or n)");
						replay = replay();
						System.out.println(replay);
						if (replay == true){
							compteur = 0;
							score = new int[3];
							player = 0;
						}
						else{						
							finish = true;
						}								
					}
					else if ((score[0] == score[1]) && score[0]<=21 && score[1] <=21){	
						System.out.println("Score du joueur:" + score[0] + ", Score de la banque:" + score[1]);						
						System.out.println("Gagné! On rejoue?(y or n)");

						replay = replay();
						if (replay == true){
							compteur = 0;
							score = new int[3];
							player = 0;
						}
						else{						
							finish = true;
						}								
					}
				}			
				else{
					if (score[0] == 21 && compteur ==0){
						System.out.println("BlackJack!!! vous avez gagné!");			
						System.out.println("On rejoue?");				
						replay = replay();
						if (replay == true){
							compteur = -1;
							score = new int[3];
							player = 0;
						}
						else{						
							finish = true;
						}				
					}
					else if (score[0]>21){
						System.out.println("Perdu!, On rejoue?");
						
						replay = replay();
						if (replay == true){
							compteur = -1;
							score = new int[3];
							player = 0;
						}
						else{						
							finish = true;
						}				
					}
					compteur++;
				}
			}				
		}
		
		else if (answer.equals("no")){
			System.out.println("Bye bye");
		}
									
	}
}
