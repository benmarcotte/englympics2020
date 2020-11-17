import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AVLTree implements java.io.Serializable {
    public Node NameHead = null;
    public Node PhoneHead = null;
    public String[] files = {"supplierlist1.csv", "supplierlist2.csv"};
    public Scanner scan;
    public ObjectOutputStream outputStream;
    public ArrayList<Node> nodes;

    public class Node implements java.io.Serializable {
        public String name;
        public String sic4;
        public String sic8;
        public String fileOrigin;
        public String phone;
        public int line;
        public Node left;
        public Node right;
        public int height;
        public int balance;
        


        public Node(String fileOrigin, String name, String sic4, String sic8, String phone, int line){
            this.name = name;
            this.sic4 = sic4;
            this.sic8 = sic8;
            this.fileOrigin = fileOrigin;
            this.phone = phone;
            this.line = line;
            //this.parent = parent;
            this.left = null;
            this.right = null;
            this.height = 0;
            this.balance = 0;
        }



    }

    public AVLTree(String[] files) throws Exception{
        this.files = files;
        outputStream = new ObjectOutputStream(new FileOutputStream("tree.ser"));
        nodes = new ArrayList<Node>();
        String line;
        String[] args;
        for(int i = 0; i < files.length; i++){
            scan = new Scanner(new FileInputStream(files[i]));
            scan.nextLine();
            int nline = 3;
            if(i == 0){
                line = scan.nextLine();
                args = line.split(",\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                args[0] = args[0].replaceAll("[^a-zA-Z0-9]", "");
                args[0] = args[0].toLowerCase();
                NameHead = new Node(files[i], args[0], args[1], args[2], args[3], 2);
                PhoneHead = new Node(files[i], args[0], args[1], args[2], args[3], 2);
                nodes.add(NameHead);
                System.out.println("processed node");
            }
            while(scan.hasNextLine()){
                line = scan.nextLine();
                args = line.split(",\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                insert(new Node(files[i], args[0], args[1], args[2], args[3], nline), NameHead, null);
                insert(new Node(files[i], args[0], args[1], args[2], args[3], nline), PhoneHead, null);
                nodes.add(NameHead);
                nline++;
                System.out.println("processed node " + i + " " + nline);
            }
        }
        //outputStream.writeObject(this);
    }

    public void insert(Node n, Node current, Node prev){
        if(n.name.compareTo(current.name) < 0){
            if(current.left == null){
                //Node temp = new Node(key, car);
                //n.prev = current;
                current.left = n;
                balance(current, prev);
            } else {
                insert(n, current.left, current);
            }
        } else {
            if(current.right == null){
                //Node temp = new Node(key, car);
                //n.prev = current;
                current.right = n;
                balance(current, prev);
            } else{
                insert(n, current.right, current);
            }
        }
        //n++;
    

        //int compare = n.name.compareTo(head.name);

        // while(true){    
        //     compare = n.name.compareTo(head.name);
        //     if(compare < 0){
        //         if(current.left == null){
        //             current.left = n;
        //             //balance(current, prev);
        //             return;
        //         }
        //         else {
        //             //prev = current;
        //             current = current.left;
        //         }
        //     } else {
        //         if(current.right == null){
        //             current.right = n;
        //             //balance(current, prev);
        //             return;
        //         }
        //         else {
        //             //prev = current;
        //             current = current.right;
        //         }
        //     }
        // }
    }

    public void update(Node n){
        int lh = -1;
        int rh = -1;
        if(n.left != null)
            lh = n.left.height;
        if(n.right != null)
            rh = n.right.height;
        
        if(rh > lh)
            n.height = 1 + rh;
        else 
            n.height = 1 + lh;

        n.balance = rh-lh;
    }

    public Node balance(Node n, Node prev){
        if(n.balance == -2){
            if (n.left.balance <= 0)
                return leftLeft(n, prev); 
            else
                return leftRight(n, prev);
        }
        else if (n.balance == +2) {
            if(n.right.balance >= 0)
                return rightRight(n, prev);
            else
                return rightLeft(n, prev);
        }
        return n;
    }

    public Node leftLeft(Node n, Node prev){
        return rightRotation(n, prev);
    }

    public Node leftRight(Node n, Node prev){
        n.left = leftRotation(n.left, prev);
        return rightRotation(n, prev);
    }

    public Node rightRight(Node n, Node prev){
        return leftRotation(n, prev);
    }

    public Node rightLeft(Node n, Node prev){
        n.right = rightRotation(n.right, prev);
        return leftRotation(n, prev);
    }

    public Node leftRotation(Node n, Node prev){
        Node nright = n.right;
        n.right = nright.left;
        nright.left = n;
        if(prev != null){
            if(n == prev.left)
            prev.left = nright;
            else
            prev.right = nright;
        }
        update(n);
        update(nright);
        return nright;
    }


    public Node rightRotation(Node n, Node prev){
        Node nleft = n.left;
        n.left = nleft.right;
        nleft.right = n;
        if(prev != null){
            if(n == prev.left)
            prev.left = nleft;
            else
            prev.right = nleft;
        }
        update(n);
        update(nleft);
        return nleft;
    }

    public Node searchName(String name){
        name = name.replaceAll("[^a-zA-Z0-9]", "");
        name = name.toLowerCase();
        Node current = NameHead;
        int compare;
        while(current != null){
            compare = current.name.compareTo(name);
            if(compare == 0){
                return current;
            }
            else if (compare < 0){
                current = current.left;
            }
            else if (compare > 0){
                current = current.right;
            }
        }
        return null;
    }

    public Node searchNumber(String n){
        Node current = PhoneHead;

        int compare;
        while(current != null){
            compare = current.phone.compareTo(n);
            if(compare == 0){
                return current;
            }
            else if (compare < 0){
                current = current.left;
            }
            else if (compare > 0){
                current = current.right;
            }
        }
        return null;
    }

}