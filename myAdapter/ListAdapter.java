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

    // Query Operations

    /**
     * Ritorna il numero degli elementi della lista.
     * Se la lista contiene più di n° Integer.MAX_VALUE elementi, il metodo 
     * ritorna automaticamente il numero Integer.MAX_VALUE
     * 
     * @return Il numero degli elementi della lista.
     */

    @Override
    public int size(){
        //Semplicemente chiamo il metodo della classe Vector che 
        //indica quanti elementi sono presenti nel Vector. Tale numero
        //corrisponde al numero degli elementi della lista
        return list.size();
    }
    /**
     * Ritorna vero se la lista non contiene elementi.
     * 
     * @return vero se la lista non contiene elementi
     */
    @Override
    public boolean isEmpty(){
        //Semplicemente chiamo il metodo della classe Vector che
        //indica se il Vector è vuoto.
        return list.isEmpty();
    }
    /**
     * Ritorna vero se la lista contiene l'elemento specificato.
     * 
     * @param obj è l'emento che deve essere trovato nella lista
     * @return true se la lista contiene l'elemento specificato
     */
    @Override
    public boolean contains(Object obj){
        //Riutilizzo il metodo della classe Vector per sapere se 
        //la collezione contiene l'elemento obj
        return list.contains(obj);
    }

    @Override
    public HIterator iterator(){
        return listIterator();
    }

    @Override
    public Object[] toArray(){
        return null;
    }

    @Override
    public Object[] toArray(Object arrayTarget[]){
        return null;
    }

    // Modification Operations

    /**
     * Aggiunge l'elemento specificato alla fine della lista
     * 
     * @param obj l'elemento da aggiungere alla lista
     * @return true se l'elemento non è presente nella lista
     */
    @Override 
    public boolean add(Object obj){
        if(list.contains(obj))
            return false;
        else{
            list.addElement(obj);
            return true;
        }
    }

    @Override
    public boolean remove(Object obj){
        return list.removeElement(obj);
    }

    // Bulk Operations

    @Override
    public boolean containsAll(HCollection coll){
        return false;
    }

    @Override
    public boolean addAll(HCollection coll){
        return false;
    }

    @Override
    public boolean addAll(int index, HCollection coll){
        return false;
    }

    @Override
    public boolean removeAll(HCollection coll){
        return false;
    }

    @Override
    public boolean retainAll(HCollection coll){
        return false;
    }

    @Override
    public void clear(){
        list.removeAllElements();
    }

    // Comparison and hashing

    @Override
    public boolean equals(Object obj){
        return list.equals(obj);
    }

    @Override
    public int hashCode(){
        return list.hashCode();
    }

    // Positional Access Operations

    @Override
    public Object get(int index){
        return list.elementAt(index);
    }

    @Override
    public Object set(int index, Object element){
        return null;
    }

    @Override
    public void add(int index, Object element){
        
    }

    @Override
    public Object remove(int index){
        return null; //removeElementAt(index);
    }

    @Override
    public int indexOf(Object obj){
        return list.indexOf(obj);
    }

    @Override
    public int lastIndexOf(Object obj){
        return list.lastIndexOf(obj);
    }

    // List Iterators

    @Override
    public HListIterator listIterator(){
        return null;
    }

    @Override
    public HListIterator listIterator(int index){
        return null;
    }

    // View

    @Override
    public HList subList(int fromIndex, int toIndex){
        return null;
    }

}
