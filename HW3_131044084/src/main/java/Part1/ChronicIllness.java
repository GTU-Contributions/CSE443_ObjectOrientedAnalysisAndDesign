package Part1;

public class ChronicIllness implements State{
    Student student;

    ChronicIllness(){}
    ChronicIllness(Student student){
        this.student = student;
    }

    public void exercise() {
        System.out.println("Exercise option is not available! You have a chronic illness. You don't have enough time left!");
    }

    public void perseveranceAndHardWork() {
        System.out.println("Perseverance and hard work option is not available! You have a chronic illness. You don't have enough time left!");
    }

    public void sleep() {
        System.out.println("Sleep option is not available! You have a chronic illness. You don't have enough time left!");
    }

    public void outTillLate() {
        System.out.println("Out till late option is not available! You have a chronic illness. You don't have enough time left!");
    }

    public void cheating() {
        System.out.println("Cheating option is not available! You have a chronic illness. You don't have enough time left!");
    }

    public void buyingAGTX1080() {
        System.out.println("Buying a GTX1080 option is not available! You have a chronic illness. You don't have enough time left!");
    }

    public void coffeeAndWork() {
        System.out.println("Coffee and work option is not available! You have a chronic illness. You don't have enough time left!");
    }
}
