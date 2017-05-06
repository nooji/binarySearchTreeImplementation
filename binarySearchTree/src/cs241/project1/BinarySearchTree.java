package cs241.project1;

public class BinarySearchTree {

	private Node root;
	/*********************************************************************
	 * Name				: BinarySearchTree()
	 * Arguments		: none
	 * Preconditions	: none
	 * Results			: default constructor that sets root node to null
	 ********************************************************************/
	public BinarySearchTree(){
		root = null;
	}
	
	/***********************************************************************
	 * Name				: contains(int num)
	 * Arguments		: int
	 * Preconditions	: none
	 * Results			: public function that calls overwritten contains
	 * 					  to give access to and ensure root will always be
	 * 					  first node taken in
	 ***********************************************************************/
	public boolean contains(int num){
		return contains(num, root);
	}
	
	/***********************************************************
	 * Name				: contains(int num, Node temp)
	 * Arguments		: int, Node
	 * Preconditions	: public contains function calls this function
	 * Results			: returns true if the num matches key value in a node
	 * 					  returns false if node doesn't exist in tree
	 ***********************************************************/
	private boolean contains(int num, Node temp){
		boolean result = false;
		//while not at bottom of tree and no match
		while(temp!=null && !result){
			//traverse left
			if(num < temp.getKey()){
				temp = temp.getLeft();
				result = contains(num, temp);
			}
			//traverse right
			else if(num > temp.getKey()){
				temp = temp.getRight();
				result = contains(num, temp);
			}
			//match
			else{
				result = true;
			}
		}
		return result;
	}
	
	/***********************************************************
	 * Name				: insert(int num)
	 * Arguments		: int
	 * Preconditions	: takes in user input for int
	 * Results			: calls overwritten insert function if
	 * 					  the tree does not contain the int 
	 * 					  return true if added false if no change
	 ***********************************************************/
	public boolean insert(int num){
		if(!contains(num)){
			root = insert(num, root);
			return true;
		}
		else{
			return false;
		}
	}
	
	/***********************************************************
	 * Name				: insert(int num, Node tempNode)
	 * Arguments		: int, Node 
	 * Preconditions	: public insert(int num) is called and
	 * 					  tree !contain int num
	 * Results			: returns Node when finished traversing
	 * 					  to correct spot in tree
	 ***********************************************************/
	private Node insert(int num, Node temp){
		//create Node initiated to num if null
		if(temp == null){
			temp = new Node(num);
		}
		//recursive call to left side
		else if(num < temp.getKey()){
			temp.setLeft(insert(num, temp.getLeft()));
		}
		//recursive call to right side
		else{
			temp.setRight(insert(num, temp.getRight()));
		}
		return temp;
	}
	
	/**************************************************************************
	 * Name				: delete(int num)
	 * Arguments		: int num
	 * Preconditions	: takes in user input for int
	 * Results			: if Node contains num and it gets deleted return true
	 * 					  return false if num does not exist in tree
	 *************************************************************************/
	public boolean delete(int num){
		if(contains(num)){
			root = delete(num, root);
			return true;
		}
		else{
			return false;
		}
	}
	
	/***************************************************************************
	 * Name				: delete(int num, Node temp)
	 * Arguments		: int, Node
	 * Preconditions	: public delete(int num) is called and tree contains num
	 * Results			: recursively return nodes back up tree after node is 
	 * 					  deleted 
	 ***************************************************************************/
	public Node delete(int num, Node temp){
		//if num matches Node key
		if(temp.getKey() == num){
			//if both childs are null
			if(temp.getLeft() == null && temp.getRight() == null){
				return null;
			}
			//if left child is null return right
			else if(temp.getLeft() == null){
				return temp.getRight();
			}
			//if right child is null return left
			else if(temp.getRight() == null){
				return temp.getLeft();
			}
			//if two child exists
			else{
				Node tempRight = new Node();
				//traverse to right child
				tempRight = temp.getRight();
				//find minimum value in right child
				while(tempRight.getLeft() !=null){
					tempRight = tempRight.getLeft();
				}
				//set key to min value found
				temp.setKey(tempRight.getKey());
				//recursive call to delete extra(it will be a leaf node)
				temp.setRight(delete(temp.getKey(),temp.getRight()));
				return temp;
			}
		}
		//recursive call to left side
		else if(num < temp.getKey()){
			temp.setLeft(delete(num, temp.getLeft()));
			return temp;
		}
		//recursive call to right side
		else{
			temp.setRight(delete(num, temp.getRight()));
			return temp;
		}
	}
	
	/***************************************************************
	 * Name				: predecessor(int num)
	 * Arguments		: int 
	 * Preconditions	: takes in user input for num
	 * Results			: if the tree is not empty and tree contains
	 * 					  num then call predecessor(num, root, null)
	 **************************************************************/
	public void predecessor(int num){
		if(root!=null && contains(num)){
			predecessor(num, root, null);
		}
	}
	
	/******************************************************************
	 * Name				: predecessor(int num, Node start, Node target)
	 * Arguments		: int, Node search, Node for result
	 * Preconditions	: public predecessor(int num) is called
	 * Results			: void function that prints out predecessor for
	 * 					  in-order traversal 
	 ******************************************************************/
	private void predecessor(int num, Node start, Node target){
		//if node is null break
		if(start == null){
			return;
		}
		//if num matches Node key
		if(start.getKey() == num){
			//if left node is not null then predecessor will be right most 
			//Node of the left Node
			if(start.getLeft()!= null){
				Node tempNode = new Node();
				tempNode = start.getLeft();
				while(tempNode.getRight()!=null){
					tempNode = tempNode.getRight();
				}
				target = tempNode;
			}
			//print results
			System.out.println("The predecessor is : " + target.getKey());
		}
		//traverse left
		if(num < start.getKey()){
			predecessor(num, start.getLeft(), target);
		}
		//traverse right
		else if( num > start.getKey()){
			target = start;
			predecessor(num,start.getRight(), target);
		}
	}
	
	/***************************************************************
	 * Name				: successor(int num)
	 * Arguments		: int 
	 * Preconditions	: takes in user input for num
	 * Results			: if the tree is not empty and tree contains
	 * 					  num then call successor(num, root, null)
	 **************************************************************/
	public void successor(int num){
		if(root!=null && contains(num)){
			successor(num, root, null);
		}
	}
	
	/******************************************************************
	 * Name				: successor(int num, Node start, Node target)
	 * Arguments		: int, Node search, Node for result
	 * Preconditions	: public successor(int num) is called
	 * Results			: void function that prints out successor for
	 * 					  in-order traversal 
	 ******************************************************************/
	private void successor(int num, Node start, Node target){
		if(start == null){
			return;
		}
		if(start.getKey() == num){
			//if right subtree is not null successor will be left most
			//Node of the right Node
			if(start.getRight()!= null){
				Node tempNode = new Node();
				tempNode = start.getRight();
				while(tempNode.getLeft()!=null){
					tempNode = tempNode.getLeft();
				}
				target = tempNode;
			}
			//print results
			System.out.println("The successor is : " + target.getKey());
		}
		//recursive call to traverse left
		if(num < start.getKey()){
			target = start;
			successor(num,start.getLeft(), target);
		}
		//recursive call to traverse right
		else if( num > start.getKey()){
			successor(num, start.getRight(), target);
		}
	}
	
	/***********************************************************************
	 * Name				: inOrder()
	 * Arguments		: none
	 * Preconditions	: tree is initialized
	 * Results			: calls  overwritten inOrder(root) to make sure the 
	 * 					  traversal always starts from root node
	 **********************************************************************/
	public void inOrder(){
		inOrder(root);
	}
	
	/***********************************************************
	 * Name				: Order(Node temp)
	 * Arguments		: Node
	 * Preconditions	: public Order() is called 
	 * Results			: recursive function that traverses tree
	 * 					  and prints out Order
	 ***********************************************************/
	private void inOrder(Node temp){
		if(temp!=null){
			inOrder(temp.getLeft());
			System.out.print(temp.getKey() + " ");
			inOrder(temp.getRight());
		}
	}
	
	/***********************************************************************
	 * Name				: preOrder()
	 * Arguments		: none
	 * Preconditions	: tree is initialized
	 * Results			: calls  overwritten preOrder(root) to make sure the 
	 * 					  traversal always starts from root node
	 **********************************************************************/
	public void preOrder(){
		preOrder(root);
	}
	
	/***********************************************************
	 * Name				: preOrder(Node temp)
	 * Arguments		: Node
	 * Preconditions	: public preOrder() is called 
	 * Results			: recursive function that traverses tree
	 * 					  and prints out preOrder
	 ***********************************************************/
	private void preOrder(Node temp){
		if(temp!=null){
			System.out.print(temp.getKey()+" ");
			preOrder(temp.getLeft());
			preOrder(temp.getRight());
		}
	}
	
	/***********************************************************************
	 * Name				: postOrder()
	 * Arguments		: none
	 * Preconditions	: tree is initialized
	 * Results			: calls  overwritten postOrder(root) to make sure the 
	 * 					  traversal always starts from root node
	 **********************************************************************/
	public void postOrder(){
		postOrder(root);
	}
	
	/***********************************************************
	 * Name				: postOrder(Node temp)
	 * Arguments		: Node
	 * Preconditions	: public postOrder() is called 
	 * Results			: recursive function that traverses tree
	 * 					  and prints out postOrder
	 ***********************************************************/
	private void postOrder(Node temp){
		if(temp!= null){
			postOrder(temp.getLeft());
			postOrder(temp.getRight());
			System.out.print(temp.getKey()+" ");
		}
	}
	
	
	//inner class Node
	class Node{
		private Node left;
		private Node right;
		private int key;
		
		//default constructor
		public Node(){
			this.left = null;
			this.right = null;
			this.key = 0;
		}
		
		//constructor that passes in key value
		public Node(int num){
			this.left = null;
			this.right = null;
			this.key = num;
		}
		
		//setters and getters for Node variables
		public void setLeft(Node tempNode){
			this.left = tempNode;
		}
		
		public void setRight(Node tempNode){
			this.right = tempNode;
		}
		
		public void setKey(int num){
			this.key = num;
		}
		
		public Node getLeft(){
			return this.left;
		}
		
		public Node getRight(){
			return this.right;
		}
		
		public int getKey(){
			return this.key;
		}
	}
}
