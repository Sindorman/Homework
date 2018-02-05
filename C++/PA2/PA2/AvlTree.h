#ifndef AVL_TREE_H
#define AVL_TREE_H

#include "dsexceptions.h"
#include <iostream>    // For NULL
#include <queue>  // For level order printout
#include <vector>
#include <algorithm> // For max() function
using namespace std;

// AvlTree class
//
// CONSTRUCTION: with ITEM_NOT_FOUND object used to signal failed finds
//
// ******************PUBLIC OPERATIONS*********************
// int size( )            --> Quantity of elements in tree
// int height( )          --> Height of the tree (null == -1)
// void insert( x )       --> Insert x
// void insert( vector<T> ) --> Insert whole vector of values
// void remove( x )       --> Remove x (unimplemented)
// bool contains( x )     --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted (in) order
// void printPreOrder( )  --> Print tree in pre order
// void printPostOrder( ) --> Print tree in post order
// void printInOrder( )   --> Print tree in *in* order
// ******************ERRORS********************************
// Throws UnderflowException as warranted

template <typename Comparable>
class AvlTree
{
  public:
    AvlTree( ) : root( NULL )
      { }

    AvlTree( const AvlTree & rhs ) : root( NULL )
    {
        *this = rhs;
    }

    ~AvlTree( )
    {
       cout << " [!] Destructor called." << endl;
    }

    /**
     * Find the smallest item in the tree.
     * Throw UnderflowException if empty.
     */
    const Comparable & findMin( ) const
    {
        if( isEmpty( ) )
            throw UnderflowException( );
        return findMin( root )->element;
    }

    /**
     * Find the largest item in the tree.
     * Throw UnderflowException if empty.
     */
    const Comparable & findMax( ) const
    {
        if( isEmpty( ) )
            throw UnderflowException( );
        return findMax( root )->element;
    }

    /**
     * Returns true if x is found in the tree.
     */
    bool contains( const Comparable & x ) const
    {
        return contains( x, root );
    }

    /**
     * Test if the tree is logically empty.
     * Return true if empty, false otherwise.
     *  TODO: Implement
     */
    bool isEmpty( ) const
    {
        return height (root) == -1;  // so not correct
    }

    /**
     * Return number of elements in tree.
     */
    int size( )
    {
      int count = 0;
      return size( root, count );
    }

    /**
     * Return height of tree.
     *  Null nodes are height -1
     */
    int height( )
    {
		return heightHelp( root );
    }
	

    /**
     * Print the tree contents in sorted order.
     */
    void printTree( ) const
    {
        if( isEmpty( ) )
            cout << "Empty tree" << endl;
        else
            printInOrder( root );
    }

    /**
     * Print the tree contents in sorted order.
     */
    void printInOrder( ) const
    {
        if( isEmpty( ) )
            cout << "Empty tree" << endl;
        else
            printInOrder( root );
    }

    /**
     * Print the tree contents in pre order.
     */
    void printPreOrder( ) const
    {
        if( isEmpty( ) )
            cout << "Empty tree" << endl;
        else
            printPreOrder( root );
    }

    /**
     * Print the tree contents in post order.
     */
    void printPostOrder( ) const
    {
        if( isEmpty( ) )
            cout << "Empty tree" << endl;
        else
            printPostOrder( root );
    }

    /**
     * Make the tree logically empty.
     */
    void makeEmpty( )
    {
        makeEmpty( root );
    }

    /**
     * Insert x into the tree; duplicates are ignored.
     */
    void insert( const Comparable & x )
    {
        insert( x, root );
    }

    /**
     * Insert vector of x's into the tree; duplicates are ignored.
     */
    void insert( vector<Comparable> vals)
    {
      for( auto x : vals ) {
        insert( x, root );
      }
    }
     

    /**
     * Remove x from the tree. Nothing is done if x is not found.
     *  TODO: Implement
     */
    bool remove( const Comparable & element )
    {
		if (root == NULL)
			 return false; // Element is not in the tree
		
			 // Locate the node to be deleted and also locate its parent node
		AvlNode *parent = NULL;
		 AvlNode *current = root;
		 while (current != NULL)
			 {
			 if (element < current->element)
				 {
				 parent = current;
				 current = current->left;
				}
			 else if (element > current->element)
				 {
				 parent = current;
				 current = current->right;
				 }
			 else
				 break; // Element is in the tree pointed by current
			 }
		
			 if (current == NULL)
			 return false; // Element is not in the tree
		
			
			 // Case 1: current has no left children (See Figure 23.6)
			 if (current->left == NULL)
			 {
			 // Connect the parent with the right child of the current node
				 if (parent == NULL)
				 root = current->right;
			 else
				 {
				 if (element < parent->element)
					 parent->left = current->right;
				 else
					 parent->right = current->right;
				
					 // Balance the tree if necessary
					 parent = balance(parent);
				 }
			 }
			 else
			 {
				 // Case 2: The current node has a left child
					// Locate the rightmost node in the left subtree of
					 // the current node and also its parent
				 AvlNode *parentOfRightMost = current;
				 AvlNode *rightMost = current->left;

				 while (rightMost->right != NULL)
				 {
					 parentOfRightMost = rightMost;
					 rightMost = rightMost->right; // Keep going to the right
				 }

				 // Replace the element in current by the element in rightMost
				 current->element = rightMost->element;

				 // Eliminate rightmost node
				 if (parentOfRightMost->right == rightMost)
					 parentOfRightMost->right = rightMost->left;
				 else
					 // Special case: parentOfRightMost is current
					 parentOfRightMost->left = rightMost->left;

				 // Balance the tree if necessary
				 parentOfRightMost = balance(parentOfRightMost);
			 }
    }


    /**
     * Deep copy. - or copy assignment operator
     *  Will be in part II
     */
    const AvlTree & operator=( const AvlTree & rhs )
    {
      cout << " [!] Copy *assignment* operator called." << endl;
      return *this;
    }


/*****************************************************************************/
  private:
    struct AvlNode
    {
        Comparable element;
        AvlNode   *left;
        AvlNode   *right;
        int       height;

        AvlNode( const Comparable & theElement, AvlNode *lt,
                                                AvlNode *rt, int h = 0 )
          : element( theElement ), left( lt ), right( rt ), height( h ) { }
    };

    AvlNode *root;

    /**
     * Internal method to count nodes in tree
     *  TODO: Implement
     */
    int size( AvlNode * & t, int & count )
    {
		if (t == NULL)
			return 0;
		else
			return(size(t->left, count) + 1 + size(t->right, count));
	}

    /**
     * Internal method to insert into a subtree.
     * x is the item to insert.
     * t is the node that roots the subtree.
     * Set the new root of the subtree.
     *  TODO: Implement
     */
    AvlNode *insert( const Comparable & x, AvlNode * & t )
    {
		if (t == NULL)
		{
			t = new AvlNode(x, NULL, NULL, 0);
			updateHeight(t);
			return t;
		}
		else if (x < t->element)
		{
			t->left = insert(x, t->left);
			t = balance(t);
		}
		else if (x >= t->element)
		{
			t->right = insert(x, t->right);
			t = balance(t);
		}
		return t;
    }
	int heightHelp(AvlNode *temp)
	{
		int h = -1;
		if (temp != NULL)
		{
			int l_height = heightHelp(temp->left);
			int r_height = heightHelp(temp->right);
			int max_height = max(l_height, r_height);
			h = max_height + 1;
		}
		return h;
	}
    /**
     * Internal method to find the smallest item in a subtree t.
     * Return node containing the smallest item.
     *  You'll need this for deletes
     *  TODO: Implement
     */
    AvlNode * findMin( AvlNode *t ) const
    {
      return t; // placeholder
    }

    /**
     * Internal method to find the largest item in a subtree t.
     * Return node containing the largest item.
     *  TODO: Implement
     */
    AvlNode * findMax( AvlNode *t ) const
    {
        return t;  // placeholder
    }


    /**
     * Internal method to test if an item is in a subtree.
     * x is item to search for.
     * t is the node that roots the tree.
     *  TODO: Implement
     */
    bool contains( const Comparable & x, AvlNode *t ) const
    {
		bool cont = false;
		if (t == NULL)
		{
			return false;
		}else if (x < t->element)
		{
			cont = contains(x, t->left);
		}
		else if (x > t->element) {
			cont = contains(x, t->right);
		}else if (x == t->element) {
			return true;
		}
		return cont;    // Lolz
    }

/******************************************************/

    /**
     * Internal method to make subtree empty.
     * 
     */
    void makeEmpty( AvlNode * & t )
    {
       // Will be in part II
    }

    /**
     * Internal method to print a subtree rooted at t in sorted order.
     *  TODO: Implement
     */
    void printInOrder( AvlNode *t ) const
    {
		if (t == NULL) return;
		printInOrder(t->left);
		cout << t->element << " ";
		printInOrder(t->right);
    }

    /**
     * Internal method to print a subtree rooted at t in pre order.
     *  TODO: Implement
     */
    void printPreOrder( AvlNode *t ) const
    {
		if (t == NULL) return;
		cout << t->element << " ";
		printPreOrder(t->left);
		printPreOrder(t->right);
    }

    /**
     * Internal method to print a subtree rooted at t in post order.
     *  TODO: Implement
     */
    void printPostOrder( AvlNode *t ) const
    {
		if (t == NULL) return;
		printPostOrder(t->left);
		printPostOrder(t->right);
		cout << t->element << " ";
    }

    /**
     * Internal method to clone subtree.
     */
    AvlNode * clone( AvlNode *t ) const
    {
        if( t == NULL )
            return NULL;
        else
            return new AvlNode( t->element, clone( t->left ), clone( t->right ), t->height );
    }


    // Avl manipulations
    /**
     * Return the height of node t or -1 if NULL.
     *  TODO: Implement
     */
    int height( AvlNode *t ) const
    {
		if (t == NULL)
		{
			return -1;
		}
		else
		{
			return t->height;
		}
    }


    int max( int lhs, int rhs ) const
    {
        return lhs > rhs ? lhs : rhs;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then set new root.
     *  TODO: Implement
     */
    AvlNode *rotateWithLeftChild( AvlNode * & k2 )
    {
		AvlNode *t;
		t = k2->right;
		k2->right = t->left;
		t->left = k2;
		updateHeight(k2);
		updateHeight(t);
		return t;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then set new root.
     *  TODO: Implement
     */
	AvlNode *rotateWithRightChild( AvlNode * & k1 )
    {
		AvlNode *t;
		t = k1->left;
		k1->left = t->right;
		t->right = k1;
		updateHeight(k1);
		updateHeight(t);
		return k1;
    }

    /**
     * Double rotate binary tree node: first left child.
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then set new root.
     *  TODO: Implement
     */
	AvlNode *doubleWithLeftChild( AvlNode * & k3 )
    {
		AvlNode *t;
		t = k3->left;
		k3->left = rotateWithLeftChild(t);
		return rotateWithRightChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child.
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then set new root.
     *  TODO: Implement
     */
	AvlNode *doubleWithRightChild( AvlNode * & k1 )
    {
		AvlNode *t;
		t = k1->right;
		k1->right = rotateWithRightChild(t);
		return rotateWithLeftChild(k1);
    }
	int diff(AvlNode * t)
	{
		int l_height = height(t->left);
		int r_height = height(t->right);
		return l_height - r_height;
	}
	AvlNode *balance(AvlNode * t)
	{
		int factor = diff(t);
		if (factor > 1) 
		{
			if (diff(t->left) > 0)
			{
				t = rotateWithLeftChild(t);
			}
			else
			{
				t = doubleWithLeftChild(t);
			}
		}
		else if (factor < -1)
		{
			if (diff (t->right) > 0)
			{
				t = doubleWithRightChild(t);
			}
			else
			{
				t = rotateWithRightChild(t);
			}
		}
		return t;
	}
	void updateHeight(AvlNode *node)
	{
		if (node->left == NULL && node->right == NULL) // node is a leaf
			node->height = 0;
		else if (node->left == NULL) // node has no left subtree
			node->height = 1 + node->right->height;
		else if (node->right == NULL) // node has no right subtree
			node->height = 1 + node->left->height;
		else
			node->height = 1 + max(node->right->height, node->left->height);
	}
};

#endif
