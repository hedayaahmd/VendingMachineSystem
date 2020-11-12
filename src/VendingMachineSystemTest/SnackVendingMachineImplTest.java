package VendingMachineSystemTest;

import VendingMachineSystem.Errors.NotSufficientChangeException;
import VendingMachineSystem.Errors.SoldOutException;
import VendingMachineSystem.Factory.SnackVendingMachineImpl;
import VendingMachineSystem.Factory.VendingMachine;
import VendingMachineSystem.model.Bucket;
import VendingMachineSystem.model.Coin;
import VendingMachineSystem.model.Inventory;
import VendingMachineSystem.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: SnackVendingMachineImplTest
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class SnackVendingMachineImplTest {
    public static VendingMachine vendingMachine ;

    @BeforeEach
    void setUp() {
        vendingMachine = new SnackVendingMachineImpl();
    }

    @AfterEach
    void tearDown() {
        vendingMachine =null ;
    }
    @Test
    public void testBuyItemWithExactPrice(){
        // Item item = Item.CHOCOLATE ;
        //check availability
        // and get price
        long price = vendingMachine.selectItemAndGetPrice(Item.CHOCOLATE) ;

        assertEquals(Item.CHOCOLATE.getPrice(),price);
        vendingMachine.insertCoin(Coin.HALF_DOLLAR);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.CHOCOLATE,bucket.getItem1());
        assertTrue(bucket.getItem2().isEmpty());


    }

    @Test
    public void testBuyItemWithMorePrice(){
        long price = vendingMachine.selectItemAndGetPrice(Item.BUBBLEGUM);
        assertEquals(Item.BUBBLEGUM.getPrice(),price);

        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.DIME);
        Bucket<Item,List<Coin>> bucket = vendingMachine.collectItemAndChange();
        assertEquals(Item.BUBBLEGUM,bucket.getItem1());
        assertTrue(!bucket.getItem2().isEmpty());

        assertEquals(20- bucket.getItem1().getPrice(),getTotal(bucket.getItem2()));

    }

    @Test
    public void refund(){
        long price = vendingMachine.selectItemAndGetPrice(Item.BUBBLEGUM);
        assertEquals(Item.BUBBLEGUM.getPrice(), price);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.TWO_DIME);
        vendingMachine.insertCoin(Coin.HALF_DOLLAR);
        vendingMachine.insertCoin(Coin.DOLLAR);
        assertEquals(180, getTotal(vendingMachine.refund()));
    }

    @Test
    public void testSoldOut(){
        Exception exception = assertThrows(SoldOutException.class, () -> {
            for (int i = 0; i <= 5; i++) {
                vendingMachine.selectItemAndGetPrice(Item.CHOCOLATE);
                vendingMachine.insertCoin(Coin.HALF_DOLLAR);
                vendingMachine.collectItemAndChange();
            }
        });
        assertEquals("this item is sold item try another one",exception.getMessage().trim());
    }
    @Test
    public void testNotSufficientChange(){
        Exception exception = assertThrows(NotSufficientChangeException.class,() ->{
            for(int i= 0 ;i<5 ;i++){
                vendingMachine.selectItemAndGetPrice(Item.CHOCOLATE);//45
                vendingMachine.insertCoin(Coin.QUARTER);
                vendingMachine.insertCoin(Coin.QUARTER);
                vendingMachine.collectItemAndChange() ;

                vendingMachine.selectItemAndGetPrice(Item.BUBBLEGUM);//35
                vendingMachine.insertCoin(Coin.QUARTER);
                vendingMachine.insertCoin(Coin.QUARTER);
                vendingMachine.collectItemAndChange();
            }
        });
        assertTrue(exception.getMessage().trim().contains("Not sufficient Change"));
    }

    private long getTotal(List<Coin> item2) {
        long total = 0 ;
        for(Coin coin : item2){
            total += coin.getDenomination();
        }
        return total;
    }




}