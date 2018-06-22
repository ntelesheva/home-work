package list;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {
    private List<Integer> list;


    @Before
    public void createListObject() {
        list = new ArrayList<>();
    }

    @Test
    public void shouldReturnZeroForEmptyList() {
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnSizeOfNotEmptyList() {
        list.add(56);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnSizeWithDoubleCapasity() {
        ArrayList<Integer> list = new ArrayList<>(1);
        list.add(5);
        list.add(8);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnTrueForEmptyList() {
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnFalseForEmptyList() {
        list.add(56);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    public void shouldReturnTrueIfListContainsObj() {
        list.add(45);
        assertThat(list.contains(45)).isTrue();
    }

    @Test
    public void shouldReturnTrueIfListContainsNull() {
        list.add(null);
        assertThat(list.contains(null)).isTrue();
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionInMethodGet() {
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("index number more then size of ArrayList");
    }

    @Test
    public void shouldReturnElementFromArrayListInMethodGet() {
        list.add(56);
        assertThat(list.get(0)).isEqualTo(56);
    }

    @Test
    public void shouldReturnElementThatWasDeleted() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.remove(1)).isEqualTo(2);
    }

    @Test
    public void shouldDecreaseSizeOfArrayAfterMethodRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnTrueMethodRemoveObject() {
        List<String> list = new java.util.ArrayList<>();
        list.add("test");
        list.add("method");
        assertThat(list.remove("method")).isTrue();
    }

    @Test
    public void shouldReturnFalseMethodRemoveObject() {
        List<String> list = new ArrayList<>();
        list.add("test");
        list.add("method");
        assertThat(list.remove("noString")).isFalse();
    }

    @Test
    public void shouldDecreaseSizeOfArrayAfterMethodRemoveObject() {
        List<String> list = new ArrayList<>();
        list.add("test");
        list.add("method");
        list.remove("method");
        assertThat(list).hasSize(1).first().isEqualTo("test");
    }

    @Test
    public void shouldDeleteFirstObject() {
        list.add(2);
        list.add(56);
        list.add(5);
        list.remove(new Integer(2));
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldDeleteMidlObject() {
        list.add(10);
        list.add(56);
        list.add(5);
        list.remove(new Integer(56));
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNewArray() {
        List<Integer> list = new ArrayList<>(10);
        list.add(555);
        Object[] objects = list.toArray();
        assertThat(objects.length).isEqualTo(1);
    }

    @Test
    public void shouldReturnIndexOfElementInList() {
        list.add(100);
        list.add(560);
        list.add(100);
        assertThat(list.indexOf(100)).isEqualTo(0);
    }

    @Test
    public void shouldReturnNegativeNumberOneAfterCallMethodIndexOf() {
        list.add(100);
        list.add(560);
        assertThat(list.indexOf(55)).isEqualTo(-1);
    }

    @Test
    public void shouldReturnLAstIndexInMethodLastIndex() {
        list.add(100);
        list.add(560);
        list.add(100);
        assertThat(list.lastIndexOf(100)).isEqualTo(2);
    }

    @Test
    public void shouldReturnNegativeNumberOneAfterCallMethodLastIndexOf() {
        list.add(100);
        list.add(560);
        assertThat(list.lastIndexOf(55)).isEqualTo(-1);
    }

    @Test
    public void shouldAddElementByIndex() {
        list.add(10);
        list.add(56);
        list.add(80);
        list.add(90);
        list.add(60);
        int size = list.size();
        list.add(3, 200);
        assertThat(list.indexOf(200)).isEqualTo(3);
        assertThat(list.size()).isEqualTo(size + 1);
    }

    @Test
    public void shouldReturnTrueAfterMethodContainsAll() {
        List<Integer> list = Arrays.asList(10, 56, 80, 90, 60);
        Collection<Integer> collections = new ArrayList<>();
        collections.add(56);
        collections.add(60);
        assertThat(list.containsAll(collections)).isTrue();
    }

    @Test
    public void shouldReturnFalseAfterMethodContainsAll() {
        list.add(10);
        list.add(56);
        list.add(80);
        list.add(90);
        list.add(60);
        Collection<Integer> collections = new ArrayList<>();
        collections.add(null);
        collections.add(6);
        assertThat(list.containsAll(collections)).isFalse();
    }

    @Test
    public void shouldReturnTrueAfterMethodAddAll() {
        list.add(10);
        list.add(56);
        list.add(80);
        list.add(90);
        list.add(60);
        List<Integer> list2 = new ArrayList<>();
        list2.add(25);
        list2.add(35);
        assertThat(list.addAll(list2)).isTrue();
    }

    @Test
    public void shouldDoEmptyArrayInMethodClear() {
        list.add(10);
        list.add(56);
        list.add(80);
        list.clear();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void shouldAddCollectionToArrayByIndex() {
        list.add(10);
        list.add(11);
        list.add(12);
        Collection<Integer> collections = new ArrayList<>();
        collections.add(55);
        collections.add(56);
        collections.add(57);
        list.addAll(2, collections);
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    public void checkElementOrderInElementList() {
        list.add(1);
        list.add(5);
        list.add(6);
        list.addAll(1, Arrays.asList(2, 3, 4));
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void checkElementOrderWhenAddFromFistIndex() {
        list.add(1);
        list.add(5);
        list.add(6);
        list.addAll(0, Arrays.asList(2, 3, 4));
        assertThat(list).containsExactly(2, 3, 4, 1, 5, 6);
    }

    @Test
    public void checkElementsOrderWhenAddFromLastIndex() {
        list.add(1);
        list.add(5);
        list.add(6);
        list.addAll(3, Arrays.asList(2, 3, 4));
        assertThat(list).containsExactly(1, 5, 6, 2, 3, 4);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionForAddAllByIndex() {
        list.add(2);
        assertThatThrownBy(() -> list.addAll(2, Arrays.asList(2, 3)))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("index number more then size of ArrayList");

    }

    @Test
    public void shouldReturnIterator(){
        list.add(5);
        list.add(5);
        assertThat(list.listIterator()).isNotNull();
    }

    @Test
    public void shouldReturnIteratorByIndex(){
        list.add(6);
        assertThat(list.listIterator(0)).isNotNull();
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionForListIteratorByIndex(){
        assertThatThrownBy(()->list.listIterator(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void shouldReturnArray(){
        Integer [] arrayInt = new Integer[]{10,15,20,25,30};
        list = Arrays.asList(arrayInt);
        Integer [] arrInt = new Integer[5];
        assertThat(list.toArray(arrInt)).hasSize(5).isEqualTo(arrayInt);
    }

    @Test
    public void shouldReturnNewBiggerArray(){
        Integer [] arrayInt = new Integer[]{5, 6, 7};
        list= Arrays.asList(arrayInt);
        Integer [] arrInt = new Integer[1];
        assertThat(list.toArray(arrInt)).hasSize(3).isEqualTo(arrayInt);
    }

    @Test
    public void shouldFillArrayNull(){
        Integer [] arrayInt = new Integer[]{5};
        Integer [] expectedArray = new Integer[]{5,null};
        list= Arrays.asList(arrayInt);
        Integer [] arrInt = new Integer[2];
        assertThat(list.toArray(arrInt)).hasSize(2).isEqualTo(expectedArray);
    }

}