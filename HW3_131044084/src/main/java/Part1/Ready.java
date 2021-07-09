package Part1;


public class Ready implements State {
    Student student;

    Ready(){}
    Ready(Student student){
        this.student = student;
    }

    public void exercise() {
        System.out.println("You are fit now! Continue exercising for even better results!");
        student.setState(student.getFitState());
    }

    public void perseveranceAndHardWork() {
        System.out.println("You are graduated now! Find a job and then a spouse!");
        student.setState(student.getGraduateState());
    }

    public void sleep() {
        System.out.println("Sleep option is not available! You have already slept! You are full of energy. Try something else.");
    }

    public void outTillLate() {
        System.out.println("You had a really great time out, but you are exhausted and needing sleep!");
        student.setState(student.getNeedingSleep());
    }

    public void cheating() {
        System.out.println("You have cheated! Now you are unable to become a rod for an axe!");
        student.getUnableToBecomeARodForAnAxe();
    }

    public void buyingAGTX1080() {
        System.out.println("You bought a GTX1080 and spent so much money on buying this stuff! Now you are unable to become a rod for an axe!");
        student.getUnableToBecomeARodForAnAxe();
    }

    public void coffeeAndWork() {
        System.out.println("Coffee and work option is not available! Try something else.");
    }
}
