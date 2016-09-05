package com.example.cgli.mygamegrouptest.block;

import java.util.ArrayList;

import android.util.Log;


/* The static keyword denotes that a member variable, or method, can be accessed without
 * requiring an instantiation of the class to which it belongs. In simple terms, it means
 * that you can call a method, even if you've never created the object to which it belongs!
 *
 * static means that the variable or method marked as such is available at the class level.
 * In other words, you don't need to create an instance of the class to access it.
 *
 * public class Foo {
 *     public static void doStuff(){
 *          // does stuff
 *     }
 * }
 *
 * So, instead of creating an instance of Foo and then calling doStuff like this:
 *     Foo f = new Foo();
 *     f.doStuff();
 *
 * You just call the method directly against the class, like so:
 *     Foo.doStuff();
 *
 *
 */
public class Block {

    private static String TAG = Block.class.getSimpleName();

    private static ArrayList<BlockPoint> mblockPoints = new ArrayList<BlockPoint>();

    private static int rightBorder = 9;

    private static int bottomBorder = 9;

    private static final int alreadyOccupied = 1;

    private static int mblocktype;

    public Block() {
        super();
    }

    public static boolean generateBlock(int blockType, int posX, int posY) {
        Log.d(TAG, "generateBlock with type: " + blockType + " , X pos:" + posX + " ,Y pos:" + posY);
        ArrayList<BlockPoint> blockPoints = new ArrayList<BlockPoint>();
        switch (blockType) {
                /* [][][][]*/
            case 1:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 2, posY));
                blockPoints.add(new BlockPoint(posX + 3, posY));
                break;

                /* []
                   []
                   []
                   []  */
            case 2:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX, posY + 2));
                blockPoints.add(new BlockPoint(posX, posY + 3));
                break;

                /* [][]
                     [][]  */
            case 3:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX + 2, posY + 1));
                break;

                /*   []
                   [][]
                   []    */
            case 4:
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX, posY + 2));
                break;

                /*   [][]
                   [][]   */
            case 5:
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 2, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                break;

                /* []
                   [][]
                     []   */
            case 6:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 2));
                break;

                /* [][][]
                       []*/
            case 7:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 2, posY));
                blockPoints.add(new BlockPoint(posX + 2, posY + 1));
                break;

                /*   []
                     []
                   [][]  */
            case 8:
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX, posY + 2));
                blockPoints.add(new BlockPoint(posX + 1, posY + 2));
                break;

                /* []
                   [][][]  */
            case 9:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX + 2, posY + 1));
                break;

                /* [][]
                   []
                   []    */
            case 10:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX, posY + 2));
                break;

                /* [][][]
                   []     */
            case 11:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 2, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                break;

                /* [][]
                     []
                     []   */
            case 12:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 2));
                break;

                /*     []
                   [][][]     */
            case 13:
                blockPoints.add(new BlockPoint(posX + 2, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                blockPoints.add(new BlockPoint(posX + 2, posY + 1));
                break;

                /* []
                   []
                   [][]     */
            case 14:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX, posY + 2));
                blockPoints.add(new BlockPoint(posX + 1, posY + 2));
                break;

                /* [][]
                   [][] */
            case 15:
            default:
                blockPoints.add(new BlockPoint(posX, posY));
                blockPoints.add(new BlockPoint(posX + 1, posY));
                blockPoints.add(new BlockPoint(posX, posY + 1));
                blockPoints.add(new BlockPoint(posX + 1, posY + 1));
                break;

        }

        mblockPoints = blockPoints;
        mblocktype = blockType;
        return true;
    }


    /**
     * transfer
     */
        /* type 1 : [][][][]      type 2: []   
                                          []
                                          []
                                          []


           type 3: [][]           type 4:   []
                     [][]                 [][]
                                          []


           type 5:   [][]         type 6: []
                   [][]                   [][]
                                            []


           type 7: [][][]         type 8:   []        type 9: []             type 10: [][]
                       []                   []                [][][]                  []
                                          [][]                                        []


           type 11: [][][]        type 12: [][]       type 13:     []        type 14: []
                    []                       []                [][][]                 []
                                             []                                       [][]


           type 15(default):  [][]
                              [][]  

         * The transfer will happen in this way,  1->2->1, 3->4->3, 5->6->5,
           7->8->9->10->7,  11->12->13->14->11, 15->15
         */
    public static boolean turn(int blockType) {

        int firstX = mblockPoints.get(0).getX();
        int firstY = mblockPoints.get(0).getY();

        //Log.d(TAG, "turn with type:" + blockType);
        //Log.d(TAG, "turn with first X = " + firstX + " , first Y = " + firstY);

        switch (blockType) {
            case 1:
                if ((firstY + 3) >= bottomBorder ||
                        BlockGameView.map[firstX][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 3] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 2;
                break;

            case 2:
                if ((firstX + 3) >= rightBorder ||
                        BlockGameView.map[firstX + 1][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX + 2][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX + 3][firstY] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 1;
                break;

            case 3:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 4;
                break;

            case 4:
                if ((firstX + 1) >= rightBorder ||
                        BlockGameView.map[firstX - 1][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX + 1][firstY + 1] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 3;
                firstX = firstX - 1;
                break;

            case 5:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX - 1][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 6;
                firstX = firstX - 1;
                break;

            case 6:
                if ((firstX + 2) >= rightBorder ||
                        BlockGameView.map[firstX + 1][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX + 2][firstY] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 5;
                break;

            case 7:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX + 1][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX + 1][firstY + 2] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 8;
                break;

            case 8:
                if ((firstX + 1) >= rightBorder ||
                        BlockGameView.map[firstX - 1][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX - 1][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX + 1][firstY + 1] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 9;
                firstX = firstX - 1;
                break;

            case 9:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX + 1][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 10;
                break;

            case 10:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 7;
                break;

            case 11:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX + 1][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX + 1][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 12;
                break;

            case 12:
                if ((firstX + 2) >= rightBorder ||
                        BlockGameView.map[firstX][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX + 2][firstY + 1] == alreadyOccupied ||
                        BlockGameView.map[firstX + 2][firstY] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 13;
                break;

            case 13:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX][firstY] == alreadyOccupied ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied ||
                        BlockGameView.map[firstX + 1][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 14;
                break;

            case 14:
                if ((firstY + 2) >= bottomBorder ||
                        BlockGameView.map[firstX][firstY + 2] == alreadyOccupied ||
                        BlockGameView.map[firstX + 1][firstY + 2] == alreadyOccupied) {
                    Log.d(TAG, "can not turn type: " + blockType);
                    return false;
                }
                blockType = 11;
                break;

            case 15:
            default:
                Log.d(TAG, "directly return for type 15 and default ");
                break;
        }
        Log.d(TAG, "change block type to " + blockType);
        mblocktype = blockType;
        generateBlock(blockType, firstX, firstY);
        return true;
    }


    public static boolean moveDown() {

        for (BlockPoint blockPoint : mblockPoints) {
            if ((blockPoint.getY() + 1) >= bottomBorder) {
                Log.d(TAG, "get to bottom, can not move down");
                //Log.d(TAG, "map x = " + blockPoint.getX() + " , y = " + blockPoint.getY());
                return false;
            } else if ((blockPoint.getY() + 1) < bottomBorder &&
                    BlockGameView.map[blockPoint.getX()][blockPoint.getY() + 1] == 1) {
                Log.d(TAG, "find existing block, can not move down");
                //Log.d(TAG,"existing block x = " + blockPoint.getX() + " , y = " + blockPoint.getY());
                return false;
            }
        }

        //Log.d(TAG, "to move down");
        for (BlockPoint blockPoint : mblockPoints) {
            blockPoint.moveDown();
        }
        return true;
    }


    public static boolean moveLeft() {

        for (BlockPoint blockPoint : mblockPoints) {
            if (blockPoint.getX() == 0) {
                Log.d(TAG, "get to left border, can not move left");
                return false;
            } else if ((blockPoint.getX() - 1) > 0 &&
                        BlockGameView.map[blockPoint.getX()-1][blockPoint.getY()] == 1) {
                Log.d(TAG, "find existing block, can not move left");
                //Log.d(TAG,"existing block x = " + blockPoint.getX() + " , y = " + blockPoint.getY());
                return false;
            }
        }

        //Log.d(TAG, "to moveLeft");
        for (BlockPoint blockPoint : mblockPoints) {
            blockPoint.moveLeft();
        }
        return true;
    }

    public static boolean moveRight() {

        for (BlockPoint blockPoint : mblockPoints) {
            if ((blockPoint.getX()+1) == rightBorder) {
                Log.d(TAG, "get to right border, can not move right");
                return false;
            } else if ((blockPoint.getX() + 1) < rightBorder &&
                        BlockGameView.map[blockPoint.getX() + 1][blockPoint.getY()] == 1) {
                Log.d(TAG, "find existing block, can not move right");
                //Log.d(TAG,"existing block x = " + blockPoint.getX() + " , y = " + blockPoint.getY());
                return false;
            }
        }

        //Log.d(TAG, "to move right");
        for (BlockPoint blockPoint : mblockPoints) {
            blockPoint.moveRight();
        }
        return true;
    }

    public static void setBottomBorder(int bottomLineNum) {
        //Log.d(TAG, "setBottomBorder to " + bottomLineNum);
        bottomBorder = bottomLineNum;
    }

    public static void setRightBorder(int mostRightLineNum) {
        //Log.d(TAG, "setRightBorder to " + mostRightLineNum);
        rightBorder = mostRightLineNum;
    }

    public static ArrayList<BlockPoint> getBlockPoints() {
        return mblockPoints;
    }

    public static int getBlockType() {
        return mblocktype;
    }
}
