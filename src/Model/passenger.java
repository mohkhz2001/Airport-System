package Model;

public class passenger extends Person{

    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


    @Override
    public void job(Job job ) {
        setJob(job);

    }
}
