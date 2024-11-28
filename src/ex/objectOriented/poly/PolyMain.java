package ex.objectOriented.poly;

public class PolyMain {
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.method();
        Parent parent1 = new Child();
        parent1.method();
        Parent parent2 = new GrandSon();
        parent2.method();

        Child child = new Child();
        Parent parent3 = child;
        parent3.method();

        Parent parent4 = new Child();
        Child child1 = (Child) parent4;
        child1.method();
    }

}
