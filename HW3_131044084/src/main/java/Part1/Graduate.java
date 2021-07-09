package Part1;

public class Graduate implements State {
    Student student;

    Graduate(){}
    Graduate(Student student){
        this.student = student;
    }

    public void exercise() {
        System.out.println("Exercise option is not available! You are graduated, go and live your life!");
    }

    public void perseveranceAndHardWork() {
        System.out.println("Perseverance and hard work option is not available! You are graduated, go and live your life!");
    }

    public void sleep() {
        System.out.println("Sleep option is not available! You are graduated, go and live your life!");
    }

    public void outTillLate() {
        System.out.println("Out till late option is not available! You are graduated, go and live your life!");
    }

    public void cheating() {
        System.out.println("Cheating option is not available! You are graduated, go and live your life!");
    }

    public void buyingAGTX1080() {
        System.out.println("Buying a GTX1080 option is not available! You are graduated, go and live your life!");
    }

    public void coffeeAndWork() {
        System.out.println("Coffee and work option is not available! You are graduated, go and live your life!");
    }
}
