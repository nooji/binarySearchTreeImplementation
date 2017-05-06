package cs241.project1;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		BinarySearchTree tree = new BinarySearchTree();
		System.out.println("Enter in initial values");
		String input = keyboard.nextLine();
		String[] inputArray = input.split(" ");
		for(int i = 0; i < inputArray.length; i++){
			tree.insert(Integer.parseInt(inputArray[i]));
		}
		displayResults(tree);
		//do while user does not input E for exit
		do{
			System.out.print("Command?");
			input = keyboard.next();
			switch(input.toUpperCase()){
				case "I":
					if(tree.insert(keyboard.nextInt())){
						System.out.print("In-order: ");
						tree.inOrder();
						System.out.println();
					}
					else{
						System.out.println("This tree can't contain duplicates. ");
					}
					
					break;
				case "D":
					if(tree.delete(keyboard.nextInt())){
						System.out.print("In-order: ");
						tree.inOrder();
						System.out.println();
					}
					else{
						System.out.println("This node does not exist in the tree.");
					}
					break;
				case "P":
					tree.predecessor(keyboard.nextInt());
					break;
				case "S":
					tree.successor(keyboard.nextInt());
					break;
				case "E":
					System.out.println("Thank you for using my program!");
					break;
				case "H":
					displayMenu();
					break;
				default:
					System.out.println("Invalid input. Try Again. ");
					break;
			}
		}
		while(!input.equalsIgnoreCase("E"));
		
	}
	/*********************************************************************
	 * Name				: displayResults(BinarySearchTree temp)
	 * Arguments		: BinarySearchTree
	 * Preconditions	: BinarySearchTree is initiated
	 * Results			: display pre in and post order traversal for tree
	 **********************************************************************/
	public static void displayResults(BinarySearchTree temp){
		System.out.print("Pre-order: "); temp.preOrder();
		System.out.println();
		System.out.print("In-order: "); temp.inOrder(); 
		System.out.println();
		System.out.print("Post-order: "); temp.postOrder();
		System.out.println();
	}
	/*********************************************************************
	 * Name				: displayMenu()
	 * Arguments		: none
	 * Preconditions	: none
	 * Results			: display menu options 
	 ********************************************************************/
	public static void displayMenu(){
		System.out.println("I\tInsert a value");
		System.out.println("D\tDelete a value");
		System.out.println("P\tFind predecessor");
		System.out.println("S\tFind successor");
		System.out.println("E\tExit the program");
		System.out.println("H\tDisplay this message");
	}
}
