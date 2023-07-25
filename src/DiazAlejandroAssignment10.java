/* 
    Alejandro Diaz
    Class: CS1150
    Due: 07/27/23
    Assignment #10

    This assignment serves as practice using objects and arrays. 
    The idea is to create an array of "tree" objects, which will be
    accessed to retrive data pertaining to each tree. 

    Then, a "forest" object will be created to hold each tree object.
    Similar manipulations will be performed on the forest object.
*/

public class DiazAlejandroAssignment10 
{
    public static void main(String[] args)
    {
        // PART 1 ------------------------------ //

        // Creating Tree array & printing results:
        Tree[] treeArray = new Tree[7];
        populateTreeArray(treeArray);
        printTreeArray(treeArray);
        
        // Finding Tallest Tree:
        printTallestTree(treeArray);
        
        // PART 2 ------------------------------ //

        Forest forest1 = new Forest("Redwood National Forest", 200);
        for(int i = 0; i < treeArray.length; i++)
        {
            forest1.addTree(treeArray[i]);
        }
        forest1.displayTrees();
        Tree tallTree = forest1.findTallestTree();

        System.out.println("\n--------------------------------------------");
        System.out.println("Tallest tree in forest");
        System.out.println("--------------------------------------------");
        System.out.printf("Forest: %s \nTree: %s \nHeight: %d feet\n\n", forest1.getName(), tallTree.getType(), tallTree.getHeight());
    }
    
    public static void populateTreeArray(Tree[] trees)
    {
        Tree tree1 = new Tree("Giant Sequoia", 275);
        Tree tree2 = new Tree("Fremont Cottonwood", 68);
        Tree tree3 = new Tree("Sitka Spruce", 330);
        Tree tree4 = new Tree("Coast Douglas Fir", 297);
        Tree tree5 = new Tree("Coastal Redwood", 379);
        Tree tree6 = new Tree("Southern Magnolia", 87);
        Tree tree7 = new Tree("White Oak", 144);

        trees[0] = tree1;
        trees[1] = tree2;
        trees[2] = tree3;
        trees[3] = tree4;
        trees[4] = tree5;
        trees[5] = tree6;
        trees[6] = tree7;
    }

    public static void printTreeArray(Tree[] trees)
    {
        System.out.println("\n********************************************");
        System.out.println("\t\tTrees In Array");
        System.out.println("********************************************\n");

        System.out.println("--------------------------------------------");
        System.out.printf("%-30s%-20s\n", "Tree", "Height");
        System.out.println("--------------------------------------------");

        for(int i = 0; i < trees.length; i++)
        {
            System.out.printf("%-30s%-20d\n", trees[i].getType(), trees[i].getHeight());
        }
    }

    public static void printTallestTree(Tree[] trees)
    {
        Tree tallest = new Tree("NULL", 0);
        for(int i = 0; i < trees.length; i++)
        {
            if(trees[i].getHeight() > tallest.getHeight())
            {
                tallest = trees[i];
            }
        }

        System.out.println("\n--------------------------------------------");
        System.out.println("Tallest tree in the array of trees");
        System.out.println("--------------------------------------------");
        System.out.printf("Tree: %s \nHeight: %d feet\n\n", tallest.getType(), tallest.getHeight());
    }
}

class Tree
{
    private String type;
    private int height;

    // Blueprint constructor:
    public Tree(String inputType, int inputHeight)
    {
        this.type = inputType;
        this.height = inputHeight;
    }

    // Getters:
    public String getType() {   return type;    }
    public int getHeight()  {   return height;  }
}

class Forest
{
    private String name;
    private Tree[] trees;
    private int numTrees;

    // Blueprint constructor:
    public Forest(String inputName, int maxNumTrees)
    {
        this.name = inputName;
        trees = new Tree[maxNumTrees];

        numTrees = 0;
    }

    // Getters:
    public String getName() {   return name;    }
    
    // Setters:
    public void addTree(Tree treeToAdd)
    {
        // Ensure that the forest array of trees is not full:
        if(numTrees < trees.length)
        {
            trees[numTrees] = treeToAdd;
            numTrees++;
        }
        else
        {
            System.out.println("The forest is full!");
        }
    }

    // Extra Methods:
    public Tree findTallestTree()
    {
        Tree tallest = new Tree("NULL", 0);

        for(int i = 0; i < numTrees; i++)
        {
            if(trees[i].getHeight() > tallest.getHeight())
            {
                tallest = trees[i];
            }
        }

        return tallest;
    }

    public void displayTrees()
    {
        System.out.println("\n********************************************");
        System.out.println("\tTrees In Forest Object");
        System.out.println("********************************************\n");
        
        System.out.println("--------------------------------------------");
        System.out.printf("%-30s%-20s\n", "Tree", "Height");
        System.out.println("--------------------------------------------");

        for(int i = 0; i < numTrees; i++)
        {
            System.out.printf("%-30s%-20d\n", trees[i].getType(), trees[i].getHeight());
        }
    }
}