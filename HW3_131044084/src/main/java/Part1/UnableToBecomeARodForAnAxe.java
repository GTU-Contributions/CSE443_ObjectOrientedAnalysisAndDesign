package Part1;

public class UnableToBecomeARodForAnAxe implements State {
    Student student;

    UnableToBecomeARodForAnAxe(){}
    UnableToBecomeARodForAnAxe(Student student){
        this.student = student;
    }

    public void exercise() {
        System.out.println("Exercise option is not available! You will be unable to become a rod for an axe for a long time.");
    }

    public void perseveranceAndHardWork() {
        System.out.println("Perseverance and hard work option is not available! You will be unable to become a rod for an axe for a long time.");
    }

    public void sleep() {
        System.out.println("Sleep option is not available! You will be unable to become a rod for an axe for a long time.");
    }

    public void outTillLate() {
        System.out.println("Out till late option is not available! You will be unable to become a rod for an axe for a long time.");
    }

    public void cheating() {
        System.out.println("Cheating option is not available! You will be unable to become a rod for an axe for a long time.");
    }

    public void buyingAGTX1080() {
        System.out.println("Buying a GTX1080 option is not available! You will be unable to become a rod for an axe for a long time.");
    }

    public void coffeeAndWork() {
        System.out.println("Coffee and work option is not available! You will be unable to become a rod for an axe for a long time.");
    }
}
