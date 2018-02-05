#ifndef BST_H
#define BST_H

#include <vector>
#include <stdexcept>
#include <iostream>
#include <string>
using namespace std;

template<typename T>
class TreeNode
{
public:
  T element; // Element contained in the node
  TreeNode<T>* parent;
  TreeNode<T>* left; // Pointer to the left child
  TreeNode<T>* right; // Pointer to the right child

  TreeNode(T element) // Constructor
  {
    this->element = element;
    left = NULL;
    right = NULL;
  }
  ~TreeNode()
  {
	  element = NULL;
	  parent = NULL;
	  left = NULL;
	  right = NULL;
  }
};

template <typename T>
class Iterator : public std::iterator<std::forward_iterator_tag, T>
{
public:
  Iterator(TreeNode<T>* p)
  {
    if (p == NULL)
      current = -1; // The end
    else
    {
      // Get all the elements in inorder
		pos = farLeft(p);
      current = 0;
    }
  }

  Iterator operator++(int dum)
  {
	   if (pos->right != NULL && pos != NULL) {
		  pos = farLeft(pos->right);
		  
	  }
	  else if (pos->right == NULL) {
		  if (LastValue()) { pos = NULL; current = -1; }
		  else {
			  if (pos->element > pos->parent->element && pos != NULL) {
				  while (pos->element > pos->parent->element) {
					  pos = pos->parent;
				  }
				  pos = pos->parent;
			  }
			  else if (pos->element < pos->parent->element && pos != NULL) {
				  pos = pos->parent;
			  }
		  }
	  }	  
	  return *this;
  }

  T &operator*()
  {
	  if (pos != NULL)
    return pos->element;
  }

  bool operator == (const Iterator<T> &iterator) const
  {
    return current == iterator.current;
  }

  bool operator != (const Iterator<T> &iterator) const
  {
    return current != iterator.current;
  }

  TreeNode<T>* farLeft(TreeNode<T>* p) {
	  TreeNode<T>* temp = p;
	  while (temp->left != NULL) {
		  temp = temp->left;
	  }
	  return temp;
  }

  bool LastValue() {
	  TreeNode<T>* temp = pos;
	  while (temp->parent != NULL) {
		  if (temp->element > temp->parent->element) {
			  temp = temp->parent;
		  } else {
			  return false;
		  }
	  }
	  return true;
  }

private:
	int current;
	TreeNode<T>* pos;
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
  virtual bool add(T element);
  virtual bool remove(T element);
  void printInOrder() const;
  void printPreOrder() const;
  void printPostOrder() const;
  int getSize() const;
  void print() const;
  int height() const;
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
  void print(TreeNode<T>* t) const;
  int height(TreeNode<T>* node) const;
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
    add(elements[i]);
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
  clear(root);
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
bool BST<T>::add(T element)
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
void BST<T>::printInOrder() const
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
void BST<T>::printPostOrder() const
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
void BST<T>::printPreOrder() const
{
  preorder(root);
}

template <typename T>
int BST<T>::height() const
{
	return height(root);
}

template <typename T>
int BST<T>::height(TreeNode<T>* node) const
{
	if (node == NULL)
	{
		return 0;
	}

	int left = height(node->left);
	int right = height(node->right);

	if (left > right)
		return 1 + left;
	else
		return 1 + right;
}

template <typename T>
void BST<T>::print() const
{
	print(root);
}

template <typename T>
void BST<T>::print(TreeNode<T>* t) const
{
	if (t->left != NULL) print(t->left);
	cout << t->element << " ";
	if (t->right != NULL) print(t->right);
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
void BST<T>::clear(TreeNode<T> *t)
{
	if (t == NULL)
	{

	}
	else if (t->right != NULL)
	{
		clear(t->right);
		delete t;
	}
	else if (t->left != NULL)
	{
		clear(t->left);
		delete t;
	}
	else
	{
		delete t;
	}
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
