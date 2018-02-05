
public class IntTree {
	class IntTreeNode {
	    public int data;
	    public IntTreeNode left;
	    public IntTreeNode right;
	                                        
	    // constructs a branch node with given data, left subtree,
	    // right subtree
	    public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
	        this.data = data;
	        this.left = left;
	        this.right = right;
	    }
	}
	
	private IntTreeNode overallRoot;
	
    // post: value is added to overall tree so as to preserve the
    //       binary search tree property
    public void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value is added to given tree so as to preserve the
    //       binary search tree property
    private IntTreeNode add(IntTreeNode root, int value) {
        if (root == null) {
            root = new IntTreeNode(value, null, null);
        } else if (value <= root.data) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }
    
    // post: returns true if overall tree contains value
    public boolean contains(int value) {
        return contains(overallRoot, value);
    }   

    // post: returns true if given tree contains value
    private boolean contains(IntTreeNode root, int value) {
        if (root == null) {
            return false;
        } else if (value == root.data) {
            return true;
        } else if (value < root.data) {
            return contains(root.left, value);
        } else {  // value > root.data
            return contains(root.right, value);
        }
    }
    
    public String toString() {
        return toString(overallRoot);
    }
    private String toString(IntTreeNode root) {
        if (root == null) {
            return "empty";
        } else if (root.left == null && root.right == null) {
            return "" + root.data;
        } else {
            return "(" + root.data + ", " + toString(root.left) +
                    ", " + toString(root.right) + ")";
        }
    }
    
    private boolean equals(IntTreeNode rootOne, IntTreeNode rootTwo) {
    	if (rootOne == null) {
    		return false;
    	}
    	if (rootOne.data != rootTwo.data) {
    		return false;
    	}else if (rootOne.right.data != rootTwo.right.data) {
    		return false;
    	}else if (rootOne.right.data == rootTwo.right.data) {
    		equals(rootOne.right, rootTwo.right);
    	}else if (rootOne.left.data != rootTwo.left.data) {
    		return false;
    	}else if (rootOne.left.data == rootTwo.left.data) {
    		equals(rootOne.left, rootTwo.left);
    	}
    	return true;
    }
    public boolean equals(IntTree t) {
    	return equals(overallRoot, t.overallRoot);
    }
    
    public void doublePositives() {
    	if (overallRoot != null) {
    		doublePositives(overallRoot);
    	}
    }
    
    private void doublePositives(IntTreeNode root) {
    	if (root.data > 0) {
    		root.data *= 2;
    	}  
    	if (root.right != null) {    		
    		doublePositives(root.right);
    	}
    	if (root.left != null) {
    		doublePositives(root.left);
    	}    	  	
    }
    
}
