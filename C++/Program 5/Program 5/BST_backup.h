// Code from Liang, C++ Data Structures
// changed slightly by W.P. Iverson
//    Bellevue College, WA
//    Spring 2015

#ifndef BST_H
#define BST_H


#include <iostream>
#include <string>
#include <vector>
using namespace std;

template<typename T>
class TreeNode
{
public:
  T element; // Element contained in the node
  TreeNode<T>* parent; // Pointer to the parent node
  TreeNode<T>* left; // Pointer to the left child
  TreeNode<T>* right; // Pointer to the right child

  TreeNode(T element) // Constructor
  {
    this->element = element;
    left = NULL;
    right = NULL;
  }
};

template <typename T>
class Iterator : public std::iterator<std::forward_iterator_tag, T>
{
public:
	TreeNode<T>* pos;
	TreeNode<T>* root;
  Iterator(TreeNode<T> *p)
  {
	  pos = p;
	  root = p;
	  current = 1;
	  if (p == NULL) {
		  pos = NULL;
		  current = -1;
	  }
  }
  bool hasNext() {
	  return pos != NULL;
  }

  Iterator operator++(int dum)  {	  
	  TreeNode<T> *p;
	  if (pos == NULL)  {		  
		  pos = root;
		  
		  // move to the smallest value in the tree,
		  // which is the first node inorder
		  while (pos->left != NULL) {
			  pos = pos->left;
		  }
	  }
	  else
		  if (pos->right != NULL)
		  {
			  // successor is the furthest left node of
			  // right subtree
			  pos = pos->right;

			  while (pos->left != NULL) {
				  pos = pos->left;
			  }
		  }
		  else
		  {
			  // have already processed the left subtree, and
			  // there is no right subtree. move up the tree,
			  // looking for a parent for which nodePtr is a left child,
			  // stopping if the parent becomes NULL. a non-NULL parent
			  // is the successor. if parent is NULL, the original node
			  // was the last node inorder, and its successor
			  // is the end of the list
			  p = pos->parent;
			  while (p != NULL && pos == p->right)
			  {
				  pos = p;
				  p = p->parent;
			  }

			  // if we were previously at the right-most node in
			  // the tree, nodePtr = NULL, and the iterator specifies
			  // the end of the list
			  pos = p;
		  }

	  return *this;
  
  }

  const TreeNode<T>* getPos() const {
	  return pos;
  }

  T &operator*()
  {
	 if (hasNext())
    return pos->element;
  }

  bool operator == (const Iterator<T> &iterator) const
  {
    return pos->element == iterator.getPos()->element;
  }

  bool operator != (const Iterator<T> &iterator) const
  {
    return pos->element != iterator.getPos()->element;
  }

private:
	int current;
    
};

template <typename T>
class BST
{
public:
  BST();
  BST(T elements[], int arraySize);
  BST(BST<T> &tree);
  ~BST();
  bool search(T element) const;
  virtual bool insert(T element);
  virtual bool remove(T element);
  void inorder() const;
  void preorder() const;
  void postorder() const;
  int getSize() const;
  void clear();
  vector<TreeNode<T>* >* path(T element) const;

  Iterator<T> begin() const
  {
    return Iterator<T>(root);
  };

  Iterator<T> end() const
  {
    return Iterator<T>(NULL);
  };

protected:
  TreeNode<T>* root;
  int size;
  virtual TreeNode<T>* createNewNode(T element);

private:
  void inorder(TreeNode<T>* root) const;
  void postorder(TreeNode<T>* root) const;
  void preorder(TreeNode<T>* root) const;
  void copy(TreeNode<T>* root);
  void clear(TreeNode<T>* root);
};

template <typename T>
BST<T>::BST()
{
  root = NULL;
  size = 0;
}

template <typename T>
BST<T>::BST(T elements[], int arraySize)
{
  root = NULL;
  size = 0;

  for (int i = 0; i < arraySize; i++)
  {
    insert(elements[i]);
  }
}

/* Copy constructor */
template <typename T>
BST<T>::BST(BST<T> &tree)
{
  root = NULL;
  size = 0;
  copy(tree.root); // Recursively copy nodes to this tree
}

/* Copies the element from the specified tree to this tree */
template <typename T>
void BST<T>::copy(TreeNode<T>* root)
{
  if (root != NULL)
  {
    insert(root->element);
    copy(root->left);
    copy(root->right);
  }
}

/* Destructor */
template <typename T>
BST<T>::~BST()
{
  clear();
}

/* Return true if the element is in the tree */
template <typename T>
bool BST<T>::search(T element) const
{
  TreeNode<T>* current = root; // Start from the root

  while (current != NULL)
    if (element < current->element)
    {
      current = current->left; // Go left
    }
    else if (element > current->element)
    {
      current = current->right; // Go right
    }
    else // Element matches current.element
      return true; // Element is found

  return false; // Element is not in the tree
}

template <typename T>
TreeNode<T>*  BST<T>::createNewNode(T element)
{
  return new TreeNode<T>(element);
}

/* Insert element into the binary tree
 * Return true if the element is inserted successfully
 * Return false if the element is already in the list
 */
template <typename T>
bool BST<T>::insert(T element)
{
	if (root == NULL) {
		root = createNewNode(element); // Create a new root
		root->parent = NULL;
	}
  else
  {
    // Locate the parent node
    TreeNode<T>* parent = NULL;
    TreeNode<T>* current = root;
    while (current != NULL)
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
        return false; // Duplicate node not inserted

    // Create the new node and attach it to the parent node
	if (element < parent->element) {
		parent->left = createNewNode(element);
		parent->left->parent = parent;
	}
	else {
		parent->right = createNewNode(element);
		parent->right->parent = parent;
	}
  }

  size++;
  return true; // Element inserted
}

/* Inorder traversal */
template <typename T>
void BST<T>::inorder() const
{
  inorder(root);
}

/* Inorder traversal from a subtree */
template <typename T>
void BST<T>::inorder(TreeNode<T>* root) const
{
  if (root == NULL) return;
  inorder(root->left);
  cout << root->element << " ";
  inorder(root->right);
}

/* Postorder traversal */
template <typename T>
void BST<T>::postorder() const
{
  postorder(root);
}

/** Inorder traversal from a subtree */
template <typename T>
void BST<T>::postorder(TreeNode<T>* root) const
{
  if (root == NULL) return;
  postorder(root->left);
  postorder(root->right);
  cout << root->element << " ";
}

/* Preorder traversal */
template <typename T>
void BST<T>::preorder() const
{
  preorder(root);
}

/* Preorder traversal from a subtree */
template <typename T>
void BST<T>::preorder(TreeNode<T>* root) const
{
  if (root == NULL) return;
  cout << root->element << " ";
  preorder(root->left);
  preorder(root->right);
}

/* Get the number of nodes in the tree */
template <typename T>
int BST<T>::getSize() const
{
  return size;
}

/* Remove all nodes from the tree */
template <typename T>
void BST<T>::clear()
{
  // Left as exercise
}

/* Return a path from the root leading to the specified element */
template <typename T>
vector<TreeNode<T>*>* BST<T>::path(T element) const
{
  vector<TreeNode<T>* >* v = new vector<TreeNode<T>* >();
  TreeNode<T>* current = root;

  while (current != NULL)
  {
    v->push_back(current);
    if (element < current->element)
      current = current->left;
    else if (element > current->element)
      current = current->right;
    else
      break;
  }

  return v;
}

/* Delete an element from the binary tree.
 * Return true if the element is deleted successfully
 * Return false if the element is not in the tree */
template <typename T>
bool BST<T>::remove(T element)
{
  // Locate the node to be deleted and also locate its parent node
  TreeNode<T>* parent = NULL;
  TreeNode<T>* current = root;
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

  // Case 1: current has no left children
  if (current->left == NULL)
  {
    // Connect the parent with the right child of the current node
    if (parent == NULL)
    {
      root = current->right;
    }
    else
    {
      if (element < parent->element)
        parent->left = current->right;
      else
        parent->right = current->right;
    }

    delete current; // Delete current
  }
  else
  {
    // Case 2: The current node has a left child
    // Locate the rightmost node in the left subtree of
    // the current node and also its parent
    TreeNode<T>* parentOfRightMost = current;
    TreeNode<T>* rightMost = current->left;

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
      // Special case: parentOfRightMost->right == current
      parentOfRightMost->left = rightMost->left;

    delete rightMost; // Delete rightMost
  }

  size--;
  return true; // Element inserted
}

#endif
