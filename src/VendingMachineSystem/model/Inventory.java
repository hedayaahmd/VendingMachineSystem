////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: Inventory
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package VendingMachineSystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory <T>{
     private Map<T,Integer> inventory = new HashMap<T,Integer>() ;

     public int getQuantity(T item){
         Integer quantity = inventory.get(item);
         return quantity == null ? 0 : quantity;
     }

     public void add(T item){
         Integer value = inventory.get(item);
         inventory.put(item,value+1) ;
     }

     public void deduct(T item){
         if(hasItem(item)) {
             Integer value = inventory.get(item);
             inventory.put(item, value - 1);
         }
     }

     public HashMap<Item,Integer> display(){
         HashMap<Item,Integer> map  =new HashMap<>() ;
         for(T t :inventory.keySet()){
             map.put((Item) t ,getQuantity(t)) ;
         }
         return map ;
     }

     public boolean hasItem(T item){
         return getQuantity(item) > 0;
     }
     public void clear(){
         inventory.clear();
     }

     public void put(T item ,Integer quantity){
         inventory.put(item,quantity) ;
     }
}
