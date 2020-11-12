////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: Main
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package VendingMachineSystem.view;

import VendingMachineSystem.Factory.SnackVendingMachineImpl;
import VendingMachineSystem.Factory.VendingMachine;
import VendingMachineSystem.Factory.VendingMachineFactory;
import VendingMachineSystem.model.Bucket;
import VendingMachineSystem.model.Coin;
import VendingMachineSystem.model.Index;
import VendingMachineSystem.model.Item;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting.....");
        VendingMachine vendingMachine = new VendingMachineFactory().createSnackVendingMachine() ;

        while(true){
        ((SnackVendingMachineImpl) vendingMachine).display();

        System.out.println("Please Select an Item ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        Index index = new Index(row, col);

        Item currentItem = null;
        for (Item item : Item.values()) {
            if (item.getIndex().equals(index)) {
                currentItem = item;
                break;
            }
        }

        long price = vendingMachine.selectItemAndGetPrice(currentItem);
        System.out.println("Selected item : " + currentItem);
        System.out.println("Price : " + price);
        System.out.println(("Quantity : " + ((SnackVendingMachineImpl) vendingMachine).display(currentItem)));

        System.out.println("Insert coins now");
        int value = -1;
        while (value != 0) {
            value = scanner.nextInt();
            Coin insertedCoin;
            switch (value) {
                case 10:
                    insertedCoin = Coin.DIME;
                    break;
                case 20:
                    insertedCoin = Coin.TWO_DIME;
                    break;
                case 50:
                    insertedCoin = Coin.HALF_DOLLAR;
                    break;
                case 100:
                    insertedCoin = Coin.DOLLAR;
                    break;
                default:
                    insertedCoin = Coin.ZERO;
                    break;
            }
            vendingMachine.insertCoin(insertedCoin);
        }

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();


        System.out.println("accumulated Money : " + bucket.getItem2());
        ((SnackVendingMachineImpl) vendingMachine).display();

    }

    }
}
