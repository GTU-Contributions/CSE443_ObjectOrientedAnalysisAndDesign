package Part1;

public class Fit implements State{
    Student student;

    Fit(){}
    Fit(Student student){
        this.student = student;
    }

    public void exercise() {
        System.out.println("Exercise option is not available! You are already fit! Try something else.");
    }

    public void perseveranceAndHardWork() {
        System.out.println("You are graduated now! Find a job and then a spouse!");
        student.setState(student.getGraduateState());
    }

    public void sleep() {
        System.out.println("Sleep option is not available! You are fit. Try something else.");
    }

    public void outTillLate() {
        System.out.println("Out till late option is not available! You are fit. Try something else.");
    }

    public void cheating() {
        System.out.println("Cheating option is not available! You are fit. Try something else.");
    }

    public void buyingAGTX1080() {
        System.out.println("Buying a GTX1080 option is not available! You are fit. Try something else.");
    }

    public void coffeeAndWork() {
        System.out.println("Coffee and work option is not available! You are fit. Try something else.");
    }
}
