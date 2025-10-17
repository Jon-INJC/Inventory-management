package backend;

public class Transaction {
    private String date;
    private int amount;

    public Transaction(){
        this.date = "0/0/0";
        this.amount = 0;
    }

    public Transaction(String date, int amount){
        if(date == null || amount <= 0){
            System.out.println("Error :: Check your inputs");
        }else{
            this.date = date;
            this.amount = amount;
        }
    }

    public Transaction(Transaction transaction){
        if(transaction.date == null || transaction.amount <= 0){
            System.out.println("Error :: Something went wrong");
        }else{
            this.date = transaction.date;
            this.amount = transaction.amount;
        }
    }

    public void setDate(String date){
        this.date = date;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public String getDate(){
        return this.date;
    }
    public int getAmount(){
        return this.amount;
    }
}
