package Part1;

public class Student {
    private State Ready;
    private State Fit;
    private State Graduate;
    private State NeedingSleep;
    private State ChronicIllness;
    private State UnableToBecomeARodForAnAxe;

    private State currentState;

    public Student(){
        Ready = new Ready(this);
        Fit = new Fit(this);
        Graduate = new Graduate(this);
        NeedingSleep = new NeedingSleep(this);
        ChronicIllness = new ChronicIllness(this);
        UnableToBecomeARodForAnAxe = new UnableToBecomeARodForAnAxe(this);

        currentState = this.Ready;
    }

    void setState(State state){
        this.currentState = state;
    }
    void perseveranceAndHardWork(){
        currentState.perseveranceAndHardWork();
    }
    void exercise(){
        currentState.exercise();
    }
    void sleep(){
        currentState.sleep();
    }
    void outTillLate(){
        currentState.outTillLate();
    }
    void cheating(){
        currentState.cheating();
    }
    void buyingAGTX1080(){
        currentState.buyingAGTX1080();
    }
    void coffeeAndWork(){
        currentState.coffeeAndWork();
    }

    public State getReadyState(){ return this.Ready; }
    public State getFitState(){ return this.Fit; }
    public State getGraduateState(){ return this.Graduate; }
    public State getNeedingSleep(){ return this.NeedingSleep; }
    public State getChronicIllness(){ return this.ChronicIllness; }
    public State getUnableToBecomeARodForAnAxe(){ return this.UnableToBecomeARodForAnAxe; }

}
