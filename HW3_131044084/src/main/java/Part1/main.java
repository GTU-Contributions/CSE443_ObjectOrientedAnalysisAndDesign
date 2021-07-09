package Part1;

public class main {
    public static void main(String[] args){
        Student student;

        student = new Student();
        System.out.println("The student is in Ready state now!");
        System.out.println("-------------------- Go to graduate state --------------------");
        student.perseveranceAndHardWork();
        System.out.println();

        // Go to fit then graduate state
        student = new Student();
        System.out.println("The student is in Ready state now!");
        System.out.println("---------------- Go to fit then graduate state ----------------");
        student.exercise();
        student.perseveranceAndHardWork();
        System.out.println();

        // Go to unable to become a rod for an axe state by cheating
        student = new Student();
        System.out.println("The student is in Ready state now!");
        System.out.println("---------------- Go to unable to become a rod for an axe state by cheating ----------------");
        student.cheating();
        System.out.println();

        // Go to unable to become a rod for an axe state by buying GTX1080
        student = new Student();
        System.out.println("The student is in Ready state now!");
        System.out.println("------------ Go to unable to become a rod for an axe state by buying GTX1080 ------------");
        student.buyingAGTX1080();
        System.out.println();

        // Go to needing sleep state then chronic illness state
        student = new Student();
        System.out.println("The student is in Ready state now!");
        System.out.println("-------------------- Go to needing sleep then chronic illness state --------------------");
        student.outTillLate();
        student.coffeeAndWork();
        System.out.println();

        // Go to needing sleep then ready then again in needing sleep state
        student = new Student();
        System.out.println("The student is in Ready state now!");
        System.out.println("------------ Go to needing sleep then ready then again in needing sleep state ------------");
        student.outTillLate();
        student.sleep();
        student.outTillLate();
        System.out.println();

    }
}
