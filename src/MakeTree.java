public class MakeTree {

    private Node root;
    public void insert(double x , double y){

        root = insert(x , y , root);

    }


    public Node insert(double x , double y, Node t)

    {


        if (t == null)
            t = new Node(x , y);



        if (x < t.x)

        {

            t.setLeft(insert(x , y , t.getLeft()));

            if( height( t.getLeft() ) - height( t.getRight() ) == 2 )

                if( x < t.getLeft().x )

                    t = rotateWithLeftChild( t );

                else

                    t = doubleWithLeftChild( t );

        }

        else if( x > t.x )

        {

            t.setRight(insert(x , y , t.getRight()));

            if( height( t.getRight() ) - height( t.getLeft() ) == 2 )

                if( x > t.getRight().x)

                    t = rotateWithRightChild( t );

                else

                    t = doubleWithRightChild( t );

        }

        else

            ;  // Duplicate; do nothing

        t.height = max( height( t.getLeft() ), height( t.getRight() ) ) + 1;


        return t;

    }


    private Node rotateWithLeftChild(Node k2)

    {

        Node k1 = k2.getLeft();

        k2.setLeft(k1.getRight());

        k1.setRight(k2);

        k2.height = max( height( k2.getLeft() ), height( k2.getRight() ) ) + 1;

        k1.height = max( height( k1.getLeft() ), k2.height ) + 1;

        return k1;

    }


    private Node rotateWithRightChild(Node k1)

    {

        Node k2 = k1.getRight();

        k1.setRight(k2.getLeft());

        k2.setLeft(k1);

        k1.height = max( height( k1.getLeft() ), height( k1.getRight() ) ) + 1;

        k2.height = max( height( k2.getRight() ), k1.height ) + 1;

        return k2;

    }


    private int height(Node node){
        return node == null ? -1 : node.height;
    }

    private int max(int lhs, int rhs)

    {

        return lhs > rhs ? lhs : rhs;

    }



    private Node doubleWithLeftChild(Node k3)

    {

        k3.setLeft(rotateWithRightChild( k3.getLeft() ));

        return rotateWithLeftChild( k3 );

    }



    private Node doubleWithRightChild(Node k1)

    {

        k1.setRight(rotateWithLeftChild( k1.getRight() ));

        return rotateWithRightChild( k1 );

    }
}
