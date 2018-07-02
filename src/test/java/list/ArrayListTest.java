package list;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class ArrayListTest {
    private List<Integer> list;


    @Before
    public void createListObject() {
        list = new ArrayList<>();
    }


    @Test
    @Parameters(method = "dataForSize")
    public void shouldReturnSizeForList(List<Integer> listIn, int expectedRez) {
        assertThat(listIn.size()).isEqualTo(expectedRez);
    }

    private Object dataForSize() {
        return new Object[]{
                new Object[]{Collections.emptyList(), 0},
                new Object[]{Collections.singletonList(56), 1},
                new Object[]{asList(5, 8), 2}
        };
    }

//    @Test
//    public void shouldReturnSizeOfNotEmptyList() {
//        list.add(56);
//        assertThat(list.size()).isEqualTo(1);
//    }
//
//    @Test
//    public void shouldReturnSizeWithDoubleCapacity() {
//        ArrayList<Integer> list = new ArrayList<>(1);
//        list.add(5);
//        list.add(8);
//        assertThat(list.size()).isEqualTo(2);
//    }

    @Test
    @Parameters(method = "dataForIsEmpty")
    public void shouldReturnTrueOrFalseForMethodIsEmpty(List<Integer> listIn, boolean expected) {
        assertThat(listIn.isEmpty()).isEqualTo(expected);
    }

    private Object dataForIsEmpty() {
        return new Object[]{
                new Object[]{Collections.emptyList(), true},
                new Object[]{asList(55, 88, 99), false}
        };
    }

//    @Test
//    public void shouldReturnFalseForEmptyList() {
//        list.add(56);
//        assertThat(list.isEmpty()).isFalse();
//    }


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

    private Object dataForLastIndexOf(){
        return new Object[]{
          new Object[]{ Arrays.asList(100, 560, 100), 100, 2},
          new Object[]{ Arrays.asList(100, 560, 100), 200, -1}
        };
    }

    @Test
    @Parameters(method = "dataForLastIndexOf")
    public void shouldReturnLastIndexInMethodLastIndexOf(List<Integer> listIn, Integer objToFind, Integer expected) {
        assertThat(listIn.lastIndexOf(objToFind)).isEqualTo(expected);
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

    private Object dataForContainsAll(){
        return new Object[]{
          new Object[]{ Arrays.asList(56,60), true},
          new Object[]{ Arrays.asList(556,60), false},
        };
    }

    @Test
    @Parameters(method = "dataForContainsAll")
    public void shouldReturnTrueAfterMethodContainsAll(List<Integer> listIn, boolean isContain) {
        List<Integer> list = asList(10, 56, 80, 90, 60);
       assertThat(list.containsAll(listIn)).isEqualTo(isContain);
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

    private Object dataForAddAll() {
        return new Object[]{
                new Object[]{new ArrayList<>(asList(1, 5, 6)), 1, asList(2, 3, 4), new Integer []{1, 2, 3, 4, 5, 6}, 6, null},
                new Object[]{new ArrayList<>(asList(1, 5, 6)), 0, asList(2, 3, 4), new Integer[]{2, 3, 4, 1, 5, 6}, 6, null},
                new Object[]{new ArrayList<>(asList(1, 5, 6)), 3, asList(2, 3, 4), new Integer[]{1, 5, 6, 2, 3, 4}, 6, null},
                new Object[]{new ArrayList<>(asList(2)), 2, asList(2, 3), null, null, new IndexOutOfBoundsException("index number more then size of ArrayList")}
        };
    }

    @Test
    @Parameters(method = "dataForAddAll")
    public void testMethodToArrayByIndex(List<Integer> listIn, Integer index, List<Integer> listAdd, Integer[] arrRez, Integer sizeRez, Exception err) {
        if (err != null) {
            assertThatThrownBy(() -> listIn.addAll(index, listAdd))
                    .isInstanceOf(err.getClass())
                    .hasMessage(err.getMessage());
        } else {
            listIn.addAll(index, listAdd);
            assertThat(listIn).containsExactly(arrRez);
        }
    }

    @Test
    public void shouldReturnIterator() {
        list.add(5);
        list.add(5);
        assertThat(list.listIterator()).isNotNull();
    }

    @Test
    public void shouldReturnIteratorByIndex() {
        list.add(6);
        assertThat(list.listIterator(0)).isNotNull();
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionForListIteratorByIndex() {
        assertThatThrownBy(() -> list.listIterator(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

//    private Object dataForToArray(){
//        return new Object[]{
//                new Object[]{asList({10, 15, 20, 25, 30),  asList( 3),}
//
//        };
//    }

//    @Test
//   @Parameters(method = "dataForToArray")
//    public void shouldReturnArray(List<Integer> listIn, Integer [] arrayIn, ) {
//      // Integer[] arrayInt = new Integer[]{10, 15, 20, 25, 30};
//        //list = asList(arrayInt);
//        Integer[] arrInt = new Integer[5];
//        assertThat(list.toArray(arrInt)).hasSize(5).isEqualTo(arrayInt);
//    }

    @Test
    public void shouldReturnNewBiggerArray() {
        Integer[] arrayInt = new Integer[]{5, 6, 7};
        list = asList(arrayInt);
        Integer[] arrInt = new Integer[1];
        assertThat(list.toArray(arrInt)).hasSize(3).isEqualTo(arrayInt);
    }

    @Test
    public void shouldFillArrayNull() {
        Integer[] arrayInt = new Integer[]{5};
        Integer[] expectedArray = new Integer[]{5, null};
        list = asList(arrayInt);
        Integer[] arrInt = new Integer[2];
        assertThat(list.toArray(arrInt)).hasSize(2).isEqualTo(expectedArray);
    }

}