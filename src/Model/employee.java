package Model;

public class employee extends Person {

    private String address;
    private String salary;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public void job(Job job) {
        setJob(job);

    }

}
