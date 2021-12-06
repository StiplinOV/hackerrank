package com.stiplin.hackerrank.tasks.hard;

import java.io.*;
import java.util.*;


class Node {

    private final Map<Character, Edge> children = new HashMap<>();

    private Node suffixLink;

    private final int length;

    private Integer childNumber;

    Node(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public Integer getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    boolean hasChild(char character) {
        return children.containsKey(character);
    }

    Edge getChild(char character) {
        return children.get(character);
    }

    void putChild(char character, int left, int right) {
        if (this.hasChild(character)) {
            throw new IllegalStateException();
        }
        this.putChild(character, new Edge(left, right));
    }

    void putChild(char character, Edge child) {
        this.children.put(character, child);
    }

    boolean hasSuffixLink() {
        return this.suffixLink != null;
    }

    Node getSuffixLink() {
        return this.suffixLink;
    }

    public void setSuffixLink(Node suffixLink) {
        this.suffixLink = suffixLink;
    }

    boolean isTerminal() {
        return this.children.isEmpty();
    }

    public Map<Character, Edge> getChildren() {
        return children;
    }

    public Set<Character> characters() {
        return children.keySet();
    }

    public Collection<Edge> edges() {
        return children.values();
    }

    public int getNumberOfTerminals() {
        Collection<Edge> edges = children.values();
        int result = 0;

        if (edges.isEmpty()) {
            return 1;
        }
        for (Edge edge : edges) {
            result += edge.getNumberOfTerminals();
        }

        return result;
    }

}

class Edge {

    private final int left;

    private int right;

    private Node dest;

    Edge(int left, int right) {
        this(left, right, null);
    }

    Edge(int left, int right, Node dest) {
        if (left > right) {
            throw new IllegalArgumentException();
        }
        this.left = left;
        this.right = right;
        if (dest == null) {
            this.dest = new Node(0);
        } else {
            this.dest = dest;
        }
    }

    public int getLeft() {
        return left;
    }

    public int getLength() {
        return this.getRight() - this.getLeft() + 1;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        if (this.left > right) {
            throw new IllegalArgumentException();
        }
        this.right = right;
    }

    public Node getDest() {
        return dest;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    Node split(char prevCharacter, int position, int newNodeLength) {
        int prevRight = this.getRight();
        Node prevDest = this.getDest();
        Node newDest = new Node(newNodeLength);
        Edge newEdge = new Edge(position + 1, prevRight, prevDest);
        this.setDest(newDest);
        this.setRight(position);
        newDest.putChild(prevCharacter, newEdge);

        return newDest;
    }

    public int getNumberOfTerminals() {
        return this.getDest().getNumberOfTerminals();
    }

}

class Position {

    private final String source;

    private Node node;

    private Character leaveCharacter;

    private int edgePosition;

    Position(String source, Node node, Character leaveCharacter, int edgePosition) {
        this.source = source;
        this.node = node;
        this.leaveCharacter = leaveCharacter;
        this.edgePosition = edgePosition;
    }

    public Node getNode() {
        return node;
    }

    public Character getLeaveCharacter() {
        return leaveCharacter;
    }

    Edge getEdge() {
        return this.node.getChild(leaveCharacter);
    }

    int getEdgePosition() {
        return this.edgePosition;
    }

    boolean isNodePosition() {
        return leaveCharacter == null;
    }

    boolean canMove(char character) {
        if (leaveCharacter == null) {
            return node.hasChild(character);
        } else {
            if (hasNextNode()) {
                return new Position(source, this.getNextNode(), null, 0).canMove(character);
            }
            return source.charAt(edgePosition + 1) == character;
        }
    }

    public void setNode(Node node) {
        this.node = node;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public boolean canSplit() {
        Edge edge = this.getNode().getChild(leaveCharacter);
        return edge.getRight() > edgePosition;
    }

    public Node split() {
        return this.getEdge().split(
                source.charAt(this.getEdgePosition() + 1),
                this.getEdgePosition(),
                this.node.getLength() + (this.getEdgePosition() - this.getEdge().getLeft() + 1)
        );
    }

    boolean hasNextNode() {
        if (leaveCharacter == null) {
            return false;
        }
        Edge edge = this.getNode().getChild(leaveCharacter);
        return edge.getRight() == edgePosition;
    }

    Node getNextNode() {
        if (leaveCharacter == null) {
            throw new IllegalStateException();
        }
        Edge edge = this.getNode().getChild(leaveCharacter);
        if (edge.getRight() == edgePosition) {
            return edge.getDest();
        }
        throw new IllegalStateException();
    }

    public void toSuffixLink() {
        this.setNode(this.getNode().getSuffixLink());
    }

    void putChild(int index) {
        Node result = this.getNode();
        if (hasNextNode()) {
            result = getNextNode();
        } else if (!isNodePosition()) {
            throw new IllegalStateException();
        }

        result.putChild(source.charAt(index), index, source.length() - 1);
    }

    void moveTo(int indexFrom, int indexTo) {
        int remainingShifts = indexTo - indexFrom + 1;
        int currentCharPosition = indexFrom;
        while (remainingShifts != 0) {
            if (leaveCharacter == null) {
                this.move(source.charAt(currentCharPosition));
                remainingShifts -= 1;
            } else {
                Edge edge = node.getChild(leaveCharacter);
                if (edge.getRight() == edgePosition) {
                    this.move(source.charAt(currentCharPosition));
                    remainingShifts -= 1;
                } else {
                    if (edge.getRight() - edgePosition > remainingShifts) {
                        edgePosition += remainingShifts;
                        return;
                    } else {
                        remainingShifts -= edge.getRight() - edgePosition;
                        edgePosition = edge.getRight();
                    }
                }
            }
            currentCharPosition = indexTo - remainingShifts + 1;
        }
    }

    void move(char character) {
        if (leaveCharacter == null) {
            leaveCharacter = character;
            edgePosition = node.getChild(character).getLeft();
        } else {
            Edge edge = node.getChild(leaveCharacter);
            if (edge.getRight() > edgePosition) {
                edgePosition++;
            } else {
                setNode(edge.getDest());
                leaveCharacter = character;
                edgePosition = edge.getDest().getChild(leaveCharacter).getLeft();
            }
        }
    }

}

class SuffixTreeFactory {

    public Node buildSuffixTree(String str) {
        return buildSuffixTree(str + '$', '$');
    }

    public Node buildSuffixTree(String str, Object o) {
        Node root = new Node(0);
        Position currentPosition = new Position(str, root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            addSymbol(str, i, root, currentPosition);
        }

        return root;
    }

    public void addSymbol(String source, int symbolPosition, Node root, Position currentPosition) {
        char currentChar = source.charAt(symbolPosition);
        if (currentPosition.canMove(currentChar)) {
            currentPosition.move(currentChar);
            if (currentPosition.hasNextNode()) {
                currentPosition.setNode(currentPosition.getNextNode());
            }
        } else {
            if (currentPosition.hasNextNode()) {
                currentPosition.setNode(currentPosition.getNextNode());
            }
            if (currentPosition.isNodePosition()) {
                currentPosition.putChild(symbolPosition);
                while (currentPosition.getNode().hasSuffixLink()) {
                    currentPosition.toSuffixLink();
                    if (currentPosition.canMove(currentChar)) {
                        currentPosition.move(currentChar);
                        break;
                    } else {
                        currentPosition.putChild(symbolPosition);
                    }
                }
            } else {
                Node newNode = currentPosition.split();
                newNode.putChild(currentChar, symbolPosition, source.length() - 1);
                createSuffixLinks(source, symbolPosition, currentPosition, newNode, root);
            }
        }
    }

    private void createSuffixLinks(String str, int currentIndex, Position currentPosition, Node lastNode, Node root) {
        moveToSuffixLink(currentPosition, root);
        while (!currentPosition.isNodePosition() || currentPosition.getNode() != root) {
            if (currentPosition.canSplit()) {
                Node newNode = currentPosition.split();
                lastNode.setSuffixLink(newNode);
                lastNode = newNode;
                newNode.putChild(str.charAt(currentIndex), currentIndex, str.length() - 1);
                moveToSuffixLink(currentPosition, root);
            } else {
                if (currentPosition.hasNextNode()) {
                    lastNode.setSuffixLink(currentPosition.getNextNode());
                }
                if (currentPosition.canMove(str.charAt(currentIndex))) {
                    currentPosition.move(str.charAt(currentIndex));
                    return;
                }
                currentPosition.putChild(currentIndex);
                if (currentPosition.hasNextNode()) {
                    lastNode = currentPosition.getNextNode();
                }

                moveToSuffixLink(currentPosition, root);
            }
        }
        if (currentPosition.getNode() == root && currentPosition.isNodePosition()) {
            lastNode.setSuffixLink(root);
            if (currentPosition.canMove(str.charAt(currentIndex))) {
                currentPosition.move(str.charAt(currentIndex));
            } else {
                currentPosition.putChild(currentIndex);
            }
        }
        if (currentPosition.hasNextNode()) {
            currentPosition.setNode(currentPosition.getNextNode());
        }
    }

    private void moveToSuffixLink(Position currentPosition, Node root) {
        if (currentPosition.getNode() == root) {
            if (currentPosition.getEdge().getLength() == 1) {
                currentPosition.setNode(root);
            } else {
                int left = currentPosition.getEdge().getLeft() + 1;
                int right = currentPosition.getEdge().getRight();
                currentPosition.setNode(root);
                currentPosition.moveTo(left, right);
            }
        } else {
            int left = currentPosition.getEdge().getLeft();
            int right = currentPosition.getEdge().getRight();
            currentPosition.toSuffixLink();
            currentPosition.moveTo(left, right);
        }
    }

}


public class StringFunctionCalculation {

    /*
     * Complete the 'maxValue' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING t as parameter.
     */

    static int maxValue1(String t) {
        Node root = new SuffixTreeFactory().buildSuffixTree(t);

        int maxValue = t.length();
        Stack<StackFrame> stack = new Stack<>();
        Iterator<Character> currentIterator = root.characters().iterator();
        Node currentNode = root;
        int childNumber = 0;

        boolean breake = false;

        while (currentIterator.hasNext()) {
            char nextChar = currentIterator.next();
            Node child = currentNode.getChild(nextChar).getDest();
            if (child.isTerminal()) {
                childNumber++;
            } else if (child.getChildNumber() != null) {
                childNumber += child.getChildNumber();
            } else {
                stack.push(new StackFrame(currentNode, currentIterator, childNumber, child));
                stack.push(new StackFrame(child, child.characters().iterator(), 0, null));
                breake = true;
                break;
            }
        }
        if (!breake) {
            currentNode.setChildNumber(childNumber);
            if (maxValue < currentNode.getChildNumber() * currentNode.getLength()) {
                maxValue = currentNode.getChildNumber() * currentNode.getLength();
            }
        }

        while (!stack.isEmpty()) {
            breake = false;
            StackFrame stackFrame = stack.pop();
            currentIterator = stackFrame.iterator;
            currentNode = stackFrame.node;
            childNumber = stackFrame.childNumber;
            Node currentChild = stackFrame.getCurrentNode();

            if (currentChild != null) {
                childNumber += currentChild.getChildNumber();
            }

            while (currentIterator.hasNext()) {
                char nextChar = currentIterator.next();
                Node child = currentNode.getChild(nextChar).getDest();
                if (child.isTerminal()) {
                    childNumber++;
                } else if (child.getChildNumber() != null) {
                    childNumber += child.getChildNumber();
                } else {
                    stack.push(new StackFrame(currentNode, currentIterator, childNumber, child));
                    stack.push(new StackFrame(child, child.characters().iterator(), 0, null));
                    breake = true;
                    break;
                }
            }
            if (!breake) {
                currentNode.setChildNumber(childNumber);
                if (maxValue < currentNode.getChildNumber() * currentNode.getLength()) {
                    maxValue = currentNode.getChildNumber() * currentNode.getLength();
                }
            }
        }
        return maxValue;
    }

    private static int maxValue(Node node, int maxValueParam, int initialLength) {
        int maxValue = maxValueParam;

        for (Edge edge : node.edges()) {
            int edgeMaxValue = maxValue(edge, initialLength);
            if (maxValue < edgeMaxValue) {
                maxValue = edgeMaxValue;
            }
        }

        return maxValue;
    }

    private static int maxValue(Edge edge, int initialLength) {
        int edgeLength = edge.getLength();
        if (edge.getDest().isTerminal()) {
            edgeLength -= 1;
        }
        int rootToDestLength = initialLength + edgeLength;
        int initialMaxValue = (rootToDestLength) * edge.getNumberOfTerminals();

        return maxValue(edge.getDest(), initialMaxValue, rootToDestLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String t = bufferedReader.readLine();

        int result = StringFunctionCalculation.maxValue1(t);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}

class StackFrame {
    Node node;
    Iterator<Character> iterator;
    int childNumber;
    Node currentNode;

    StackFrame(Node node, Iterator<Character> iterator, int childNumber, Node currentNode) {
        this.node = node;
        this.iterator = iterator;
        this.childNumber = childNumber;
        this.currentNode = currentNode;
    }

    public Node getNode() {
        return node;
    }

    public Iterator<Character> getIterator() {
        return iterator;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public Node getCurrentNode() {
        return currentNode;
    }
}
