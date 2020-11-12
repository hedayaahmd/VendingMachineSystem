package VendingMachineSystem.model;////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//
// File: Item
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public enum Item {
    FUDGE(new Index(0,0),"Fudge",10),
    BUBBLEGUM(new Index(0,1),"Bubblegum",40),
    TRUFFLE(new Index(0,2),"truffle",40),
    CHOCOLATE(new Index(0,3),"Chocolate",50),
    COOKIE(new Index(0,4),"Cookie",100),

    BRITTLE(new Index(1,0),"Brittle",20),
    GEPLAK(new Index(1,1),"Geplak",200),
    JELLY(new Index(1,2),"Jelly",30),
    BONDA(new Index(1,3),"Bonda",40),
    CRACKER_NUTS(new Index(1,4),"CrackerNuts",10),

    DORITOS(new Index(2,0),"Doritos",100),
    LAYS(new Index(2,1),"LAYS",100),
    FRITOS(new Index(2,2),"Fritos",100),
    PRINGLES(new Index(2,3),"Prengles",100),
    RUFFLES(new Index(2,4),"Ruffles",100),

    OREO(new Index(3,0),"Oreo",100),
    TWIX(new Index(3,1),"Twix",100),
    MARS(new Index(3,2),"Mars",100),
    KINDER(new Index(3,3),"Kinder",100),
    SNICKERS(new Index(3,4),"Snickers",100),

    NUTS(new Index(4,0),"Nuts",50),
    CANDY(new Index(4,1),"Candy",10),
    INDOMIE(new Index(4,2),"Indomie",100),
    RAFFAELLO(new Index(4,3),"Raffaello",100),
    GALAXY(new Index(4,4),"Galaxy ",100);

    private Index index ;
    private String name ;
    private int price ;

    private Item(Index index ,String name ,int price ){
       this.index = index ;
        this.name =name ;
        this.price = price ;
    }

    public Index getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
