class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // Search a key in the BST
    Node search(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root;

        // Key is greater than root's key
        if (root.key < key)
            return search(root.right, key);

        // Key is smaller than root's key
        return search(root.left, key);
    }

    // Insert a key into the BST
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        // Return the (unchanged) node pointer
        return root;
    }

    // Delete a key from the BST
    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        // Base Case: If the tree is empty
        if (root == null) return root;

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);

            // If the key is the same as root's key, then this is the node to be deleted
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    // Inorder traversal of the tree
    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Main method to test the above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Insert keys
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Print inorder traversal of the BST
        System.out.println("Inorder traversal of the given tree");
        tree.inorder();

        // Search for a key
        System.out.println("\n\nSearch for key 40: " + (tree.search(tree.root, 40) != null ? "Found" : "Not Found"));

        // Delete key 20
        System.out.println("\nDelete key 20");
        tree.delete(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        // Delete key 30
        System.out.println("\n\nDelete key 30");
        tree.delete(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        // Delete key 50
        System.out.println("\n\nDelete key 50");
        tree.delete(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
    }
}
