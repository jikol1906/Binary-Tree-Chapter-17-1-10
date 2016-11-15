/**
 * Created by borisgrunwald on 04/10/2016.
 */
public class IntTreeNode {

    public int data;
    IntTreeNode left;
    IntTreeNode right;


    public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {

        this.data = data;
        this.left = left;
        this.right = right;

    }

    public IntTreeNode(int data) {

        this(data, null, null);

    }


}
