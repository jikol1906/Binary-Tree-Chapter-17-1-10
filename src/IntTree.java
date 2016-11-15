/**
 * Created by borisgrunwald on 04/10/2016.
 */
public class IntTree {

    private IntTreeNode overallRoot;
    private int size = 0;
    private int globalCounter = 1;

    private void resetCounter() {globalCounter = 0;}


    //pre : max >= 0 (throws IllegalArgumentException if not)
    //post: constructs a sequential tree with given number of nodes.
    public IntTree(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max: " + max);
        }

        overallRoot = buildTree(1, max);
    }

    //post: returns a sequential tree with n as its root unless
    // n is greater than max, in which case it returns an empty case

    private IntTreeNode buildTree(int n, int max) {

        if(n > max) {
            return null;
        } else {
            IntTreeNode left = buildTree(2*n,max);
            IntTreeNode right = buildTree(2*n+1,max);
            return new IntTreeNode(n, left, right);
        }

    }

    //post: prints the tree contents using preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    //post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if(root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    //post: prints the tree contents using an inorder traversal
    public void printInorder() {

        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();

    }

    //post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {

        if(root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }

    }

    //post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.println("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    //post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if(root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    //post: prints the tree contents, one per line, following an
    // inorder traversal and using indentation to indicate node depth;
    // prints right to left so that it looks correct when the output is rotated
    public void printSideways() {
        printSideways(overallRoot,0);
    }

    //post: prints in reversed preorder the tree with given root,
    // indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if(root != null) {
            printSideways(root.right, level+1);
            for(int i = 0; i < level; i++) {
                System.out.print("     ");
            }
            System.out.println(root.data);
            printSideways(root.left,level+1);
        }
    }

    //___________________________________________EXERCISES_________________________________________//

    public int countLeftNodes() {
        return countLeftNodes(overallRoot);
    }

    private int countLeftNodes(IntTreeNode node) {

        // no tree no left-child nodes
        if(node == null) {
            return 0;
        }

        // left-child count of current node.
        int c = 0;

        // does the current node have a left-child ?
        if (node.left != null){
            c = 1;
        }

        int left = countLeftNodes(node.left);
        int right = countLeftNodes(node.right);


        // return left-child count of current node +
        // left-child count of left and right subtrees
        return c + left + right;
    }

    public int countEmpty() {
        return countEmpty(overallRoot);
    }

    private int countEmpty(IntTreeNode root) {

        if(root == null) {
            return 0;
        }

        int count = 0;

        if(root.left == null) {
            count++;
        }
        if(root.right == null) {
            count++;
        }

        int left = countEmpty(root.left);
        int right = countEmpty(root.right);

        return count + left + right;

    }

    public int countEvenBranches() {
        return countEvenBranches(overallRoot);
    }

    private int countEvenBranches(IntTreeNode root) {

        //Is the current root null? If it is, we don't want to check for root.left and root.right.
        if(root == null) return 0;

        //Is it a leaf node?
        if(root.left == null && root.right == null) return 0;


        int count = 0;

        if(root.data % 2 == 0) {
            count++;
        }

        int left = countEvenBranches(root.left);
        int right = countEvenBranches(root.right);

        return count;

    }

    public void printLevel(int n) {
        if(n < 1) {
            throw new IllegalArgumentException("level: " + n);
        }
        printLevel(overallRoot,n,1);
        resetCounter();
    }

    private void printLevel(IntTreeNode root, int n, int level) {

        if(root != null) {
            if(level == n) {
                System.out.println(root.data);
                return;
            }

            printLevel(root.left,n,level+1);
            printLevel(root.right,n,level+1);

        }




    }

    public void printLeaves() {
        printLeaves(overallRoot);
    }

    private void printLeaves(IntTreeNode root) {

        if(root != null) {
            if(root.left == null && root.right == null) {
                System.out.println(root.data);
            }

            printLeaves(root.left);
            printLeaves(root.right);

        }

    }

    public boolean isFull() {
        return isFull(overallRoot);
    }

    private boolean isFull(IntTreeNode root) {

        if(root != null) {
            if(root.left == null && root.right != null || root.left != null && root.right == null) {
                return false;
            }

            return isFull(root.left) && isFull(root.right);

        }

        return true;







    }


    public String toString() {
        return toString(overallRoot);
    }

    private String toString(IntTreeNode root) {
        if (root != null) {
            if (root.left != null && root.right != null) {
                return "(" + root.data + ", " + toString(root.left) + ", " + toString(root.right) + ")";
            }
            else if (root.left != null) {
                return "(" + root.data + ", " + toString(root.left) + ", empty)";
            }
            else if (root.right != null) {
                return "(" + root.data + ", " + toString(root.right) + ", empty)";
            }
            return "" + root.data;
        }
        return "";
    }

    public boolean equals(IntTree other) {

        if(this.overallRoot == null && other.overallRoot == null) {
            return true;
        } else {
            return equals(other.overallRoot, overallRoot);
        }
    }

    public static boolean equals(IntTreeNode root, IntTreeNode other) {
        // check for reference equality and nulls
        if (root == other) return true; // note this picks up case of two nulls
        if (root == null) return false;
        if (other == null) return false;

        // check for data inequality
        if (root.data != other.data) {
            return false;
        }

        // recursively check branches
        if (!equals(root.left, other.left)) return false;
        if (!equals(root.right, other.right)) return false;

        // we've eliminated all possibilities for non-equality, so trees must be equal
        return true;
    }




}
