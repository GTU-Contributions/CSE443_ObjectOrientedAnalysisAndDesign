package Part1;

public class NeedingSleep implements State {
    Student student;

    NeedingSleep(){}
    NeedingSleep(Student student){
        this.student = student;
    }

    public void exercise() {
        System.out.println("Exercise option is not available! You don't have any energy left. You need to sleep!");
    }

    public void perseveranceAndHardWork() {
        System.out.println("Perseverance and hard work option is not available! You don't have any energy left. You need to sleep!");
    }

    public void sleep() {
        System.out.println("You have slept very well! You have enough energy now to do other stuff.");
        student.setState(student.getReadyState());
    }

    public void outTillLate() {
        System.out.println("Out till late option is not available! You have just been out and don't have any energy left. You need to sleep!");
    }

    public void cheating() {
        System.out.println("Cheating option is not available! You don't have any energy left. You need to sleep!");
    }

    public void buyingAGTX1080() {
        System.out.println("Buying a GTX1080 option is not available! You don't have any energy left. You need to sleep!");
    }

    public void coffeeAndWork() {
        System.out.println("You have drunk a cup of coffee and worked while needing sleep! You have chronic illness now!");
        student.setState(student.getChronicIllness());
    }
}
