/*
* Taylor Austin
* Comp 282 Mon / Wed
* Programming Assignment #2
* 3/11/2015
* Inside this file is a AVL Tree creator. You can insert and delete
* items from the tree. It also gives the height, Number of balanced
* nodes, and the number of leaves. This program also gives you the 
* balances of each subtree along with the root. 
*
*/
class StringAVLNode {
    private String val;
    private int balance;
    private StringAVLNode left, right;
    public StringAVLNode(String str) {
        //sets the String to val
        val = str;
    }
    public int getBalance() {
        //Returns the balance of the node
        return balance;
    }
    public void setBalance(int bal) {
        //Sets the balance of the node
        balance = bal;
    }
    public String getItem() {
        //Returns the String Val associated with the node
        return val;
    }
    public StringAVLNode getLeft() {
        //Returns the Left Child Node
        return left;
    }
    public void setLeft(StringAVLNode pt) {
        //Sets the Left Child Node
        left = pt;
    }
    public StringAVLNode getRight() {
        //Returns the Right Child Node
        return right;
    }
    public void setRight(StringAVLNode pt) {
        //Sets the Right Child Node
        right = pt;
    }
}
class StringAVLTree {
    private StringAVLNode root;
    // the one and only constructor
    public StringAVLTree() {}
    public StringAVLNode getRoot() {
        ///Returns the Root of the tree
        return root;
    }
    public StringAVLNode rotateRight(StringAVLNode t) {
        //Sets Temp to the Left of the node thats unbalanced             t=   A
        StringAVLNode temp = t.getLeft(); //                                 /
        //Sets the left of the unbalanced node to temps right node|   temp= B
        t.setLeft(temp.getRight()); //                                     / \
        //Sets temp's right to the t                                      C  null
        temp.setRight(t);
        //Returns the rotated tree
        return temp;
    }
    public StringAVLNode rotateLeft(StringAVLNode t) {
        //Sets Temp to the Right of the node that's unbalanced         t=  A
        StringAVLNode temp = t.getRight(); //                               \
        //Sets the right of the unbalanced node to temps left node|    temp= B
        t.setRight(temp.getLeft()); //                                      / \
        //Sets temp's right to the t                                     null  C
        temp.setLeft(t);
        //Returns the rotated tree
        return temp;
    }
    public int height() {
        //returns the height of the entire tree
        return height(root);
    }
    private static int height(StringAVLNode PT) {
        int result = 0;
        if (PT == null) {
            //if the node is null skip the rest of the code
        } else {
            //recurive call to check which subtrees have the greatest height

            if (height(PT.getLeft()) > height(PT.getRight())) {
                result = height(PT.getLeft()) + 1; //adds a level
            } else { //if the Left tree is <= than the Right tree
                result = height(PT.getRight()) + 1; //adds a level
            }
        }
        //returns the height of the tree
        return result;
    }
    public int leafCt() {
        // Return the number of leaves in the tree
        int result = leafCt(root);
        return result;
    }
    private static int leafCt(StringAVLNode PT) {
        int result = 0;

        if (PT == null) {
            //if the node is null then ignore the rest of the code
        } else {
            //if the node has no children
            if (PT.getLeft() == null && PT.getRight() == null) {
                result = 1; //++ the leaf count
            } else {
                //recusively call the rest of the tree
                result += leafCt(PT.getLeft());
                result += leafCt(PT.getRight());
            }
        }


        return result;
    }
    public int balanced() {
        // Return the number of perfectly balanced AVL nodes
        return balanced(root);
    }
    private static int balanced(StringAVLNode PT) {
        int result = 0;
        if (PT == null) {
            //if the node is null then ignore the rest of the code
        } else {
            if (PT.getBalance() == 0) {
                //increases the balance if the node is balanced
                result++;
            }
            //recusively goes through the entire tree
            result += balanced(PT.getRight());
            result += balanced(PT.getLeft());
        }
        return result;
    }
        // 
    public String successor(String str) {
        //Return the inorder successor or null
        //if there is none or num is not in the tree
        String result=null;
        String InOrder = successor(root, str);
        //Removes the 3 spaces from the end of the string that were added
        //on the the successor method
        InOrder=InOrder.substring(0, InOrder.length()-3);
        //Splits the InOrder string up but the 3 spaces
        String[] InOrderArray = InOrder.split("   ", -1);
        for(int x=0;x<InOrderArray.length;x++){
            if(str.equals(InOrderArray[x])){//found the string in the array
                if((x+1)<InOrderArray.length){//checks if its the last string in array
                    result=InOrderArray[x+1];//Sends back the successor
                }
            }
        }
        return result;
    }
    private String successor(StringAVLNode PT, String str) {
        String result = "";
        if(PT==null){
            
        }   
        else{
            result += successor(PT.getLeft(),str);
            result += PT.getItem()+"   ";
            result += successor(PT.getRight(),str);
            
        }
        return result;
    }

    public void insert(String str) {
        root = insert(str, root);
    }
    private StringAVLNode insert(String str, StringAVLNode t) {
        int oldBalance, newBalance;

        if (t == null) { // easiest case – inserted node goes here
            t = new StringAVLNode(str); //creates a new node with the given String

        } else if (t.getItem().equals(str)) { 
            // already in the tree – do nothing
        } 
        // str is smaller than this node – go left
        else if (str.compareTo(t.getItem()) < 0)
        {
            // Gets the old balance of the left child 
            if (t.getLeft() == null) {
                //If the Left is null then thats where we insert
                //So we subtract 1 to the balance becuase we are adding a
                //node to the left of the parent.
                t.setBalance(t.getBalance() - 1);
                oldBalance = 0; 
            } else {
                //Gets the Balance of the left node so check if the height changed
                oldBalance = t.getLeft().getBalance();
            }
            //Recursive call down the tree to find a spot for the item to go
            //and inserts the returned tree to the left of the node based
            //on what is returned (After rebalancing/rotating)
            t.setLeft(insert(str, t.getLeft()));
            //Gets the new height of the Left node to see if it changed
            newBalance = t.getLeft().getBalance();
            //NEED TO FIX HEIGHT
            if (oldBalance == 0 && newBalance != 0) { // did the height increase?
                // fix the balance value
                t.setBalance(t.getBalance() - 1);
                //Do we have to rotate? (Checks the Balance)
                if (t.getBalance() == -2) {
                    // out of balance – must rotate
                    if (t.getLeft().getBalance() == -1) {
                        t = rotateRight(t);             
                        t.setBalance(0);            
                        t.getRight().setBalance(0);
                        // single rotation and balance update
                        //       A(-2)           B(0)
                        //       /      ->      /   \
                        //     B(-1)          C(0)  A(0)
                        //     /
                        //   C(0)
                    } else { // double rotation
                        //Double rotate = Rotate Left -> Rotate Right
                        t.setLeft(rotateLeft(t.getLeft()));
                        t = rotateRight(t);
                        //EXAMPLE
                        //        A(-2)                   E(+-1)
                        //       /   \                    /   \
                        //     C(1)  B(0)              A(1)  C(-2)
                        //     /  \                   /  \    /  \
                        //  D(0) E(+-1)             B(0)  y  x  E(0)
                        //         / \
                        //        y   x
                        // Balances and rotation is based on E after inserting x or y
                        if (t.getBalance() == 1) {  // Diagram: x     
                            t.getLeft().setBalance(-1); 
                            t.getRight().setBalance(0);
                        } else if (t.getBalance() == -1) {// Diagram: y
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(1);
                        } else { //(t.getBalance()==0) Special Case
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(0);
                            //        A(-1)                       A(-1)
                            //         / \                        /  \
                            //     B(-2) C(0)                   x(0) C(0) 
                            //       /                         /   \
                            //      D(1)                     D(1) B(-2)
                            //        \
                            //         x    Balance Based on x
                        }
                        t.setBalance(0);

                        // and balance update
                        // once you get it right here, basically the
                        // same code can be used in other places,
                        // including delete
                    }
                }
            }
        } else {//// str is bigger than this node
            // Gets the old balance of the right child 
            if (t.getRight() == null) {
                //If the Right is null then thats where we insert
                //So we add 1 to the balance because we are adding a
                //node to the right of the parent.
                t.setBalance(t.getBalance() + 1);
                oldBalance = 0; 
            } else {
                //Gets the Balance of the right node so check if the height changed
                oldBalance = t.getRight().getBalance();
            }
            //Recursive call down the tree to find a spot for the item to go
            //and inserts the returned tree to the left of the node based
            //on what is returned (After rebalancing/rotating)
            t.setRight(insert(str, t.getRight()));
            //Gets the new height of the Left node to see if it changed
            newBalance = t.getRight().getBalance();
            //Need to fix Height
            if (oldBalance == 0 && newBalance != 0) { // did the height increase?
                // fix the balance value
                t.setBalance(t.getBalance() + 1);
                if (t.getBalance() == 2) {
                    // out of balance – must rotate
                    if (t.getRight().getBalance() == 1) {
                        t = rotateLeft(t);
                        t.setBalance(0);
                        t.getLeft().setBalance(0);
                        // single rotation and balance update
                        //    A(2)               B(0)
                        //       \      ->      /   \
                        //       B(1)          A(0)  C(0)
                        //         \
                        //         C(0)

                    } else { // double rotation
                        t.setRight(rotateRight(t.getRight()));
                        t = rotateLeft(t);
                        //EXAMPLE
                        //      A(2)                   D(+-1)
                        //     /   \                    /   \
                        //   B(0)  C(-1)              A(2)  C(-1)
                        //         /  \              /  \    /  \
                        //     D(+-1) E(0)         B(0)  x  y  E(0)
                        //      / \
                        //     x  y
                        // Balances and rotation is based on D after inserting x or y
                        if (t.getBalance() == -1) { // Diagram: y
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(1);
                        } else if (t.getBalance() == 1) {// Diagram: x
                            t.setBalance(0);
                            t.getLeft().setBalance(-1);
                            t.getRight().setBalance(0);
                        } else { //special case //good
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(0);
                            //     A(1)                        A(1)
                            //      / \                        /  \
                            //   C(0) B(2)                   C(0) x(0) 
                            //          \                         /   \
                            //          D(-1)                    B(2) D(-1)
                            //           /
                            //          x    Balance Based on x
                        }
                    }
                }
            }
        }
        //Returns the fixed tree with the inserted String
        return t;
    }
    public void delete(String str) {
        root = delete(root, str);
    }
    private StringAVLNode delete(StringAVLNode t, String str) {
        int OldBalance, NewBalance;//Set Old/New Balance as ints
        if (t == null) { // Do nothing if it is not in the tree
        } 
        // str is smaller than this node – go left
        else if (str.compareTo(t.getItem()) < 0) {
            // get the old balance.
            if (t.getLeft() == null) {
                OldBalance = 0;
                t.setBalance(t.getBalance() - 1);
                //Decrease the Balance of t because your going to be deleted for
                //a left side of the tree
            } else {
                //Gets the Left nodes Old Balance
                OldBalance = t.getLeft().getBalance();
            }
            //Recursive call down the tree to find a spot for the item to be
            //deleted and sets the returned tree to the left of the node based
            //on what is returned (After rebalancing/rotating)
            t.setLeft(delete(t.getLeft(), str));
            // get the new balance
            if (t.getLeft() == null) {
                NewBalance = 0;
            } else {
                //Gets the Left nodes New Balance
                NewBalance = t.getLeft().getBalance();
            }
            //Did the Height change and did the Left node get deleted
            //If the left is null then increase the value and check the
            //balances
            if (OldBalance != 0 && NewBalance == 0 || t.getLeft() == null) { 
                // correct the balance
                t.setBalance(t.getBalance() + 1); //LEFT
                if (t.getBalance() == 2) { // need to rotate?
                    // there are now actually 3 cases because t.getRight.getBalance()
                    // could be -1, 0, or 1.
                    if (t.getRight().getBalance() == 1) {
                        t = rotateLeft(t);
                        t.setBalance(0);
                        t.getLeft().setBalance(0);
                        //     A(1)                 A(2)               B(1)
                        //      / \                    \               /  \
                        //   C(0) B(1)   DEL C  ->    B(1)    ->    A(2)  D(0)
                        //          \                    \
                        //          D(0)                D(0)
                        //    Fix Balance Based on B
                    } else if (t.getRight().getBalance() == 0) {
                        t = rotateLeft(t);
                        t.setBalance(-1);
                        t.getLeft().setBalance(1);
                        //     A(1)                 A(2)               B(0)
                        //      / \                    \               /  \
                        //   C(0) B(0)   DEL C  ->    B(0)    ->    A(2)  D(0)
                        //        /  \                /  \            \
                        //      E(0) D(0)           E(0) D(0)         E(0)
                        //    Fix Balance Based on B
                    } else { // double rotation case
                        //t.getRight().getBalance() == -1
                        //if left and right are null
                        
                        t.setRight(rotateRight(t.getRight()));
                        t = rotateLeft(t);
                        //     A(1)                         A(2)
                        //    /    \                       /    \
                        //  B(-1)   C(-1)                 B(-1)  C(-1)
                        //   /      /    \     DEL D ->          /    \
                        //  D(0) E(0,+-1) F(0)                E(0,+-1) F(0) 
                        //        /  \                        /  \
                        //       x    y                      x    y
                        //   After Double Rotate.. As Explained in insert
                        //                   E(0,-+1)            
                        //                   /    \           
                        //                B(-1)   C(-1)      
                        //               /   \    /    \     
                        //             D(0)   x  y    F(0)                    
                        //    Fix Balance Based on E's balance based on x and y
                        if (t.getBalance() == -1) { //Diagram: only x
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(1);
                        } else if (t.getBalance() == 1) { //Diagram: only y
                            t.setBalance(0);
                            t.getLeft().setBalance(-1);
                            t.getRight().setBalance(0);
                        } else { //Diagram: both x and y
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(0);
                        }
                    }
                }
            }
        } 
        // str is smaller than this node – go right
        else if (str.compareTo(t.getItem()) > 0) {
            // get the old balance.
            if (t.getRight() == null) {
                OldBalance = 0;
                t.setBalance(t.getBalance() + 1);
                //Decrease the Balance of t because your going to be deleted for
                //a right side of the tree
            } else {
                //Gets the Right nodes Old Balance
                OldBalance = t.getRight().getBalance();
            }
            //Recursive call down the tree to find a spot for the item to be
            //deleted and sets the returned tree to the right of the node based
            //on what is returned (After rebalancing/rotating)
            t.setRight(delete(t.getRight(), str));
            // get the new balance
            if (t.getRight() == null) {
                NewBalance = 0;
            } else {
                //Gets the Left nodes New Balance
                NewBalance = t.getRight().getBalance();
            }
            //Did the Height change and did the right node get deleted?
            //If the Right and height change is null then increase the 
            //value and check the balances
            if (OldBalance != 0 && NewBalance == 0 || t.getRight() == null) {
                // correct the balance
                t.setBalance(t.getBalance() - 1);//Right
                if (t.getBalance() == -2) { // need to rotate?
                    // there are now actually 3 cases because t.getLeft.getBalance()
                    // could be -1, 0, or 1.
                    
                    if (t.getLeft().getBalance() == -1) {
                        t = rotateRight(t);
                        t.setBalance(0);
                        t.getRight().setBalance(0);
                        //     A(-1)                 A(-2)           C(-1)
                        //      / \                  /               /  \
                        //   C(-1) B(0)   DEL B  -> C(-1)    ->   D(0)  A(-2)
                        //    /                    /
                        //  D(0)                  D(0)
                        //    Fix Balance Based on C
                    } else if (t.getLeft().getBalance() == 0) {
                        t = rotateRight(t);
                        t.setBalance(1);
                        t.getRight().setBalance(-1);
                        //     A(-1)                   A(-2)           C(0)
                        //      / \                    /               /  \
                        //   C(0) B(0)   DEL B  ->   C(0)    ->    E(0)   A(-2)
                        //   /  \                    /  \                 /
                        // E(0) D(0)              E(0) D(0)             D(0)
                        //    Fix Balance Based on B

                    } else { // double rotation case
                        //t.getLeft().getBalance() == 1
                        //if left and right are null of deleted
                        
                        t.setLeft(rotateLeft(t.getLeft()));
                        t = rotateRight(t);
                        //     A(-1)                        A(-2)
                        //    /    \                       /    \
                        //  B(1)    C(1)                 B(1)  C(0)
                        //  /   \       \     DEL F ->   /  \    
                        // D(0) E(0,+-1) F(0)          D(0) E(0,+-1)  
                        //       /  \                        /  \
                        //      x    y                      x    y
                        //   After Double Rotate.. As Explained in insert
                        //                   E(0,-+1)            
                        //                   /    \           
                        //                B(1)   A(-2)      
                        //               /   \    /    \     
                        //             D(0)   x  y    C(0)                    
                        //    Fix Balance Based on E's balance based on x and y
                        if (t.getBalance() == -1) { //Diagram: only x
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(1);
                        } else if (t.getBalance() == 1) { //Diagram: only y
                            t.setBalance(0);
                            t.getLeft().setBalance(-1);
                            t.getRight().setBalance(0);
                        } else { //Diagram: both x and y
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(0);
                        }


                    }
                }
            }
        } else { // t is the node to be deleted
            //If the Node has no children
            if (t.getLeft() == null && t.getRight() == null) { 
                t = null; //Sets the node to null
            } 
            //If the Node has 1 child
            else if (t.getLeft() != null && t.getRight() == null ||
                t.getLeft() == null && t.getRight() != null) { 
                if (t.getLeft() != null) {
                    t = t.getLeft(); // Sets the replacement to the left
                } else {
                    t = t.getRight(); // Sets the replacement to the right
                }
            } else { //The Node has 2 Children
                //Get the Old Balance by getting the balance of the Left tree
                OldBalance = t.getLeft().getBalance();
                //Find the predecessor by calling replace
                t = replace(t, null, t.getLeft());
                //Checks if the nodes left is null
                if (t.getLeft() != null) {
                    //Get the New Balance by getting the balance of the Left tree again
                    NewBalance = t.getLeft().getBalance();
                } else {
                    NewBalance = 0;
                }
                //Did the Height change and did the Left node get deleted
                //If the left is null then increase the value and check the
                //balances
                if (OldBalance != 0 && NewBalance == 0 || t.getLeft() == null) {
                    //Increase the balance because your deleting Left
                    t.setBalance(t.getBalance() + 1);
                    
                    if (t.getBalance() == 2) { // need to rotate?
                        // there are now actually 3 cases because t.getRight.getBalance()
                        // could be -1, 0, or 1.
                        //      A(1)                        C(0,1)
                        //     /   \            DEL A       /   \
                        //   B(0)  C(0,1)  After Rotate   B(0)   y    
                        //        /   \          -->        \
                        //       x    y                      x
                        //The Balance of the children is based on the parent C
                        //and what the balances are after inserting x or (x and y)
                        if (t.getRight().getBalance() == 1) {//Diagram: only y
                            t = rotateLeft(t);
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                        } else if (t.getRight().getBalance() == 0) {//Diagram: both x&y
                            t = rotateLeft(t);
                            t.setBalance(-1);
                            t.getLeft().setBalance(1);

                        } else {//double rotate
                            
                            t.setRight(rotateRight(t.getRight()));
                            t = rotateLeft(t);
                            System.out.println("Test23\n" +t.getItem());
                            //      A(1)                         E(0,+-1)
                            //    /    \                         /    \
                            //  B(-1)   C(-1)  Double Rotate  B(-1)   C(-1)     
                            //   /      /    \     DEL A ->   /   \   /  \
                            //  D(0) E(0,+-1) F(0)           D(0)  x y    F(0)
                            //        /  \                        
                            //       x    y          
                            //Fix Balance Based on E's balance based on 
                            //interting x,y,x&y
                            if (t.getBalance() == -1) {//Diagram: only x
                                t.setBalance(0);
                                t.getLeft().setBalance(0);
                                t.getRight().setBalance(1);
                            } else if (t.getBalance() == 1) {//Diagram: only y
                                t.setBalance(0);
                                t.getLeft().setBalance(-1);
                                t.getRight().setBalance(0);
                            } else {//Diagram: both x and y
                                t.setBalance(0);
                                t.getLeft().setBalance(0);
                                t.getRight().setBalance(0);
                            }


                        }
                    }
                }
            }
        }
        //Returns the new Balanced tree
        return t;
    }

    // The code to find and replace the node being deleted must be recursive
    // so that we have easy access to the nodes that might have balance changes
    private StringAVLNode replace(StringAVLNode t, StringAVLNode prev,
            StringAVLNode replacement) {
            int OldBalance,NewBalance;//New in named old/new Balance
            if (replacement.getRight() == null) {
                // at the node that will replace the deleted node
                if (prev != null) {
                    prev.setRight(replacement.getLeft());
                    replacement.setLeft(t.getLeft());
                    replacement.setRight(t.getRight());
                    replacement.setBalance(t.getBalance());
                    t = replacement;
                    // (A,B,D) <--> (t,prev,replacement)
                    //     A(-1)                D(-1)
                    //     /  \                 /  \
                    //  B(1)  C(0) DEL a ->   B(1) C(0)
                    //    \                      \
                    //    D(0)                   null
                    //    /
                    //  null
                    
                } else {
                    replacement.setRight(t.getRight());
                    replacement.setBalance(t.getBalance());
                    t = replacement;
                    // (A,null,B) <--> (t,prev,replacement)
                    //     A(-1)                B(-1)
                    //     /  \                 /  \
                    //  B(0)  C(0) DEL a ->   null C(0)
                    //
                }

                // move the replacement node – Recall there is no setVal
            } else {
                //Get the Old Balance by getting the balance of the nodes Left tree
                OldBalance = replacement.getRight().getBalance();
                //Reculsively goes right until it finds the farthest right
                //node which is the predecessor
                t = replace(t, replacement, replacement.getRight());
                //Get the New Balance
                if (replacement.getRight() == null) {
                    NewBalance = 0;
                } else {
                    //Get New Balance by getting the balance of replacements right node
                    NewBalance = replacement.getRight().getBalance();
                }
                //Did the Height change and did the right node get deleted?
                //If the Right and height change is null then decrease the 
                //value and check the balances
                if (OldBalance != 0 && NewBalance == 0 
                        || replacement.getRight() == null) {
                    //correct the balance
                    //subtract one from replacement because your deleteing right
                    replacement.setBalance(replacement.getBalance() - 1);
                    if (replacement.getBalance() == -2) { // need to rotate?
                        // there are now actually 3 cases because t.getLeft.getBalance()
                        // could be -1, 0, or 1.
                        
                        if (replacement.getLeft().getBalance() == -1) {
                            //checks replacement left's balance to check if
                            //it needs to be rotated because replacement is -2
                            replacement = rotateRight(replacement);
                            //Sets new Balances
                            replacement.setBalance(0);
                            replacement.getRight().setBalance(0);


                        } else if (replacement.getLeft().getBalance() == 0) {
                            //checks replacement left's balance to check if
                            //it needs to be rotated because replacement is -2
                            replacement = rotateRight(replacement);
                            //Sets new Balances
                            replacement.setBalance(1);
                            replacement.getRight().setBalance(-1);


                        } else { // double rotation case
                            //t.getLeft().getBalance() == 1
                            replacement.setLeft(rotateLeft(replacement.getLeft()));
                            replacement = rotateRight(replacement);
                            //           A(-1)                    E(-1)
                            //          /    \                    /   \
                            //     B(-1)      C(+1)           I(+-1)  C(+1)
                            //     /   \      /   \           /   \    /   \      
                            //   D(1)  E(-1) F(0)  G(1)     D(1) B(-1)F(0)  G(1)
                            //  /   \    /           \      /  \    / \       \
                            //H(0)I(+-1)J(0)         K(0) H(0) x  y   J(0    K(1)                 
                            //     /  \
                            //    x    y
                            // The Replacment is I so the balance of the Left and
                            // right is based on the balance of I which is relying
                            // on wether x or y is the one inserted
                            if (replacement.getBalance() == -1) {//Diagram: only x
                                replacement.setBalance(0);
                                replacement.getLeft().setBalance(0);
                                replacement.getRight().setBalance(1);
                            } else if (replacement.getBalance() == 1) {//Diagram: only x
                                replacement.setBalance(0);
                                replacement.getLeft().setBalance(-1);
                                replacement.getRight().setBalance(0);
                            } else {
                                replacement.setBalance(0);
                                replacement.getLeft().setBalance(0);
                                replacement.getRight().setBalance(0);
                                //         A(-1)                       E(-1)
                                //        /    \    After Double      /    \
                                //     B(-1)    C(1)  Rotate ->     F(0)   C(1)
                                //    /   \        \               /   \     \    
                                //  D(1)  E(0)     H(0)          D(1)  B(-1)  H(0)
                                //   \
                                //    F(0)
                                //This case handles the balance according to F
                                // After Rotate Delete handles the balance of E
                                // But replace handle the balance of the left subtree
                            }



                        }
                        if (prev != null) {
                            //If prev is not null then set prev's right to replacement
                            prev.setRight(replacement);
                        }
                    }
                }
                //Set t's left to the newly balanced/rotated replacement.
                t.setLeft(replacement);
                // find the new balance
                // update balance and rotate if needed
            }
            return t;
        }
        //Thats My Name!
    public static String myName() {
        return "Taylor M. Austin";
    }
}