////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: Index
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
// Do not use, distribute, or copy without consent of Hedaya
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package VendingMachineSystem.model;

import java.util.Objects;

public class Index {
    public int row ;
    public int col ;

    public Index(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return row == index.row &&
                col == index.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
