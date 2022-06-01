/**
 * Class that implements {@link myAdapter.HList} and is the implementation of the sublist of
 * {@link myAdapter.ListAdapter}<br>
 * <p>
 * <p>
 * This class is only a representation of the main list bounded by two index
 *
 * @author Alessandro Marcassa
 * @see myAdapter.HList Interface: HList
 */
package myAdapter;

import java.util.NoSuchElementException;

public class ListAdapter implements HList, HCollection {
    private int from, to;
    private Vector list;
    boolean isFather;

    public ListAdapter() {
        from = 0;
        to = 0;
        list = new Vector();
    }
}
