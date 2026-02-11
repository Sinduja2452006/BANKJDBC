package com.wipro.bank.main;

import java.sql.SQLException;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;

public class BankMain {
   public static void main(String[] args) throws SQLException
   {
       BankService bankService=new BankService();

       System.out.println(bankService.checkBalance("717823"));
       System.out.println(bankService.checkBalance("717821"));

       TransferBean transferBean=new TransferBean();
       transferBean.setFromAccountNumber("717823");
       transferBean.setAmount(400);
       transferBean.setToAccountNumber("717821");
       transferBean.setDateOFTransaction(new java.util.Date());

       System.out.println(bankService.transfer(transferBean));
   }
}
