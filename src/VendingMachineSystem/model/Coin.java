package VendingMachineSystem.model;////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//
// File: Coin
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public enum Coin {
    ZERO(0),DIME(10),TWO_DIME(20),QUARTER(25),HALF_DOLLAR(50),DOLLAR(100);
    private int denomination ;

    private Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}