/*
 * Function contructs a pyramid on n levels
 */
package Practice;

/**
 *
 * @author ricks
 */
public class PyramidMaker {
    public void pyramid(int levels){
        char symbol = '*';
        for(int spaces = levels-1, repitition = 1; levels > 0 ; spaces--, repitition+=2, levels--){
            //print spaces
            System.out.print(" ".repeat(spaces));
            //print symbols
            System.out.print((symbol+"").repeat(repitition));
            //next level
            System.out.println("");
        }
        
    }
}
