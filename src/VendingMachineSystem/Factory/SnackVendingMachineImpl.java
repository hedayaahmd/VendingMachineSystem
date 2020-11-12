////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: SnackVendingMachineImpl
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package VendingMachineSystem.Factory;

import VendingMachineSystem.Errors.NotFullPaidException;
import VendingMachineSystem.Errors.NotSufficientChangeException;
import VendingMachineSystem.Errors.SoldOutException;
import VendingMachineSystem.model.Bucket;
import VendingMachineSystem.model.Coin;
import VendingMachineSystem.model.Inventory;
import VendingMachineSystem.model.Item;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SnackVendingMachineImpl implements VendingMachine{
    private Inventory<Coin> cashInventory = new Inventory<>();
    private Inventory<Item> itemInventory = new Inventory<>() ;
    private long totalSales ; 
    private Item currentItem ; 
    private long currentBalance ;


    public SnackVendingMachineImpl(){
        initialize() ;
    }

    private void initialize() {
        for(Coin coin :Coin.values()){
            cashInventory.put(coin,5);
        }

        for(Item item : Item.values()){
            itemInventory.put(item,5);
        }
    }

    @Override
    public long selectItemAndGetPrice(Item item) {
        if(itemInventory.hasItem(item)){
            currentItem = item ;
            return  currentItem.getPrice() ;
        }
        throw new SoldOutException(" this item is sold item try another one ") ;
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getDenomination() ;
        cashInventory.add(coin);
    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {
        Item item = collectItem() ;
        totalSales = totalSales + currentItem.getPrice() ;
        List<Coin> change = collectChange() ;
        return new Bucket<Item,List<Coin>>(item , change);
    }

    private List<Coin> collectChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change) ;
        currentBalance = 0 ;
        currentItem =null ;
        return change ;
    }

    private void updateCashInventory(List<Coin> change) {
        for(Coin coin : change){
            cashInventory.deduct(coin);
        }
    }

    private Item collectItem() throws NotSufficientChangeException,
            NotFullPaidException {
        if(isFullPaid()){
            if(hasSufficientChange()){
                itemInventory.deduct(currentItem);
                return  currentItem ;
            }
            throw new NotSufficientChangeException("Not sufficient Change in Inventory") ;
        }
        long remainingBalance =  currentItem.getPrice() - currentBalance ;
        throw  new NotFullPaidException("Money is not enough you need  remaining Balance : ", remainingBalance ) ;

    }

    private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance-currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount) {
        boolean hasChange = true ;
        try {
            getChange(amount);
        }catch(NotSufficientChangeException nsce){
            return hasChange = false ;
        }
        return hasChange ;
    }

    private List<Coin> getChange(long amount) {
        List<Coin> changes = Collections.emptyList();
        if(amount > 0 ){
            changes = new ArrayList<Coin>() ;
            long balance = amount ;
            while(balance > 0){
                if(balance >= Coin.DOLLAR.getDenomination() && cashInventory.hasItem(Coin.DOLLAR)){
                    changes.add(Coin.DOLLAR);
                    balance = balance - Coin.DOLLAR.getDenomination() ;
                    continue;
                }
                else if(balance >= Coin.HALF_DOLLAR.getDenomination() && cashInventory.hasItem(Coin.HALF_DOLLAR)){
                    changes.add(Coin.HALF_DOLLAR);
                    balance = balance - Coin.HALF_DOLLAR.getDenomination() ;
                    continue;
                }
                else if(balance >= Coin.TWO_DIME.getDenomination() && cashInventory.hasItem(Coin.TWO_DIME)){
                    changes.add(Coin.TWO_DIME);
                    balance = balance - Coin.TWO_DIME.getDenomination() ;
                    continue;
                }
                else if(balance >= Coin.DIME.getDenomination() && cashInventory.hasItem(Coin.DIME)){
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getDenomination() ;
                    continue;
                }
                else{
                    throw new NotSufficientChangeException("Not sufficient Change , please try another Product ") ;
                }

            }
        }
        return changes ;
    }

    private boolean isFullPaid() {
        boolean isFullPaid =false ;
        if(currentBalance >= currentItem.getPrice()) isFullPaid = true  ;
        return isFullPaid;
    }

    @Override
    public List<Coin> refund() {
        List <Coin> refund = getChange(currentBalance) ;
        updateCashInventory(refund);
        currentItem = null ;
        currentBalance = 0 ;
        return refund ;
    }

    @Override
    public void reset() {
        cashInventory.clear() ;
        itemInventory.clear();
        totalSales = 0 ;
        currentItem = null ;
        currentBalance = 0 ;
    }

    public void printStatus(){
        System.out.println("Total Sales : " + totalSales );
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory  : "+ cashInventory);
    }

    public void display(){
        System.out.println("Total Sales : "+ totalSales);
        System.out.println(itemInventory.display());
    }

    public int display(Item currentItem){
       return  itemInventory.getQuantity(currentItem);
    }
    public long getTotalSales(){
        return totalSales ;
    }


}
