package backend;
import java.util.Scanner;
public class Stock extends Product {

    private String sUpDate;
    private int sNetAmount, sVariableAmount;
    Scanner acc = new Scanner(System.in);

    public Stock(){
        this.sUpDate = "0/0/0";
        this.sNetAmount = 0;
        this.sVariableAmount = 0;
    }

    public Stock(String sInDate, String sOutDate, int sNetAmount, int sVariableAmount){
        if(sInDate == null || sOutDate == null || sNetAmount <= 0 || sVariableAmount <= 0){
            System.out.println("Error :: Check your inputs");
        }else{
            this.sUpDate = sInDate;
            this.sNetAmount = sNetAmount;
            this.sVariableAmount = sVariableAmount;
        }
    }

    public Stock(Stock stock){
        if(stock.sUpDate == null || stock.sNetAmount <= 0 || stock.sVariableAmount <= 0){
            System.out.println("Error :: Something went wrong");
        }else{
            this.sUpDate = stock.sUpDate;
            this.sNetAmount = stock.sNetAmount;
            this.sVariableAmount = stock.sVariableAmount;
        }
    }

    public void setStockInDate(){
        System.out.println("Enter Stock entery date: ");
        this.sUpDate = acc.nextLine();   
    }

    public void setNetStock(){
        this.sNetAmount =this.getSAmount() - sVariableAmount;   
    }
    public void setVariableAmount(int sVariableAmount){
        if(sVariableAmount < 0){
            System.out.println("Error::");
        }else{
            this.sVariableAmount = sVariableAmount;
        }
    }

    public String getStockInDate(){
        return sUpDate;
    }

    public int getNetStock(){
        return sNetAmount;
    }

    public int getVariableAmount(){
        return sVariableAmount;
    }

    public int addStock(){
        return this.getSAmount() + sVariableAmount;
    }

    public int subStock(){
        return this.getSAmount() - sVariableAmount;
    }
    
}