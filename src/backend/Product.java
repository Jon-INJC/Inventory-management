package backend;
import java.util.Scanner;
public class Product{
   private String pName, pId, batchNo;
   private int  sAmount;
   private String sDate, expDate;
   private String sUpDate;
   private int sNetAmount, sVariableAmount;

   Scanner acc = new Scanner(System.in);

   public Product(){
    this.pName = "no name product";
    this.pId = "000000";
    this.batchNo = "0000";
    this.sDate = "0/0/0";
    this.expDate = "0/0/0";
    this.sAmount= 0;
    this.sUpDate = "0/0/0";
    this.sNetAmount = 0;
    this.sVariableAmount = 0;

   }

   public Product(String pName,String pId, String batchNo,int sAmount, String sDate, String expDate){
    if(pName == null || pId == null || batchNo == null || sAmount <= 0 || sDate == null || expDate == null){
        System.out.println("Error :: Check your inputs");
    }else{
        this.pName = pName;
        this.pId = pId;
        this.batchNo = batchNo;
        this.sDate = sDate;
        this.expDate = expDate;
        this.sAmount = sAmount;
    }
   }

   public Product(Product product){
    if(product.pName == null || product.pId == null || product.batchNo == null || product.sAmount <= 0 || product.sDate == null || product.expDate == null){
        System.out.println("Error :: Something went wrong");
    }else{
        this.pName = product.pName;
        this.pId = product.pId;
        this.batchNo = product.batchNo;
        this.sDate = product.sDate;
        this.expDate = product.expDate;
        this.sAmount = product.sAmount;
    }
   }

   public void setProductName(String pName){
    this.pName = pName;
   }
   public void setProductId(String pId){
    this.pId = pId;
   }
   public void setProductBatchNo(String batchNo){
    this.batchNo = batchNo;
   }
   public void setProductAmount(int sAmount){
    this.sAmount = sAmount;
   }
   // acc.nextLine();
   public void setProductSDate(String sDate){
    this.sDate = sDate;
   }
   public void setProductExpDate(String expDate){
    this.expDate = expDate;
   }

   public void setProductUpdate(String sUpDate){
    this.sUpDate = sUpDate;
   }
   public void setProductNetAmount(int sNetAmount){
    this.sNetAmount = sNetAmount;
   }

   public void setProductVariable(int sVariableAmount){
    this.sVariableAmount = sVariableAmount;
   }
   public String getName(){
    return pName;
   }

   public String getId(){
    return pId;
   }

   public String getBatchNo(){
    return batchNo;
   }

   public String getStockDate(){
    return sDate;
   }

   public String getExpDate(){
    return expDate;
   }
   public int getSAmount(){
    return sAmount;
   }
   
}