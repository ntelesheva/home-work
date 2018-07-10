package list;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class LinkedListTest {
    public static List<Integer> list;

    @Before
    public void fillTheList() {
        list = new LinkedList<>(asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void shouldAddNewObjectInMethodAdd() {
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    public void shouldReturnArrayInMethodToArray() {
        List<Integer> list = new LinkedList<>(asList(10, 10, 10));
        Object[] objects = list.toArray();
        assertThat(objects.length).isEqualTo(3);
    }


    @Test
    public void shouldRemoveNodeFromListAndReturnTrue() {
        assertThat(list.remove(new Integer(5))).isTrue();
        assertThat(list).contains(new Integer[]{1, 2, 3, 4, 6}).hasSize(5);
    }


    @Test
    @Parameters({
            "3|  2",
            "8| -1"
    })
    public void shouldReturnIndexForMethodIndexOf(Integer indexIn, Integer indexOut) {
        assertThat(list.indexOf(indexIn)).isEqualTo(indexOut);
    }


    @Test
    @Parameters(method = "addArray")
    public void shouldReturnArrayWhenUseMethodToArray(Object[] array, Object[] expectedArray) {
        assertThat(list.toArray(array)).isEqualTo(expectedArray);
    }

    private Object addArray() {
        return new Object[]{
                new Object[]{new Integer[6], new Integer[]{1, 2, 3, 4, 5, 6}},
                new Object[]{new Integer[1], new Integer[]{1, 2, 3, 4, 5, 6}},
                new Object[]{new Integer[8], new Integer[]{1, 2, 3, 4, 5, 6, null, null}}
        };
    }

    @Test
    @Parameters(method = "addList")
    public void shouldReturnTrue(List<Integer> array, boolean isContains) {
        assertThat(list.containsAll(array)).isEqualTo(isContains);
    }

    private Object addList() {
        return new Object[]{
                new Object[]{asList(1, 2, 3), true},
                new Object[]{asList(111, 2, 3), false},
        };
    }

    @Test
    public void shouldReturnTrueWhenRemoveAllCollection() {
        List<Integer> array = asList(111, 5, 2);
        assertThat(list.removeAll(array)).isTrue();
        assertThat(list).containsExactly(1, 3, 4, 6);
    }

    private Object putGet() {
        return new Object[]{
                new Object[]{5, 6, null},
                new Object[]{8, null, new IndexOutOfBoundsException("the index is out of range")}
        };

    }

    @Test
    @Parameters(method = "putGet")
    public void shouldReturnObjectByIndexInMethodGet(Integer index, Integer out, Exception err) {
        if (err == null) {
            assertThat(list.get(index)).isEqualTo(out);
        } else {
            assertThatThrownBy(() -> list.get(index))
                    .isInstanceOf(err.getClass())
                    .hasMessage(err.getMessage());
        }
    }

    @Test
    public void shouldRemoveElementById() {
        list.remove(2);
        assertThat(list).containsExactly(1, 2, 4, 5, 6);
    }

    @Test
    @Parameters(method = "addList2")
    public void shouldReturnTrueWhenAddCollection(List<Integer> newList, boolean rezult) {
        assertThat(list.addAll(newList)).isEqualTo(rezult);
    }

    private Object addList2() {
        return new Object[]{
                new Object[]{asList(11, 22, 33), true},
                new Object[]{asList(), false},
                new Object[]{null, false},
        };
    }


    @Test
    public void shouldReturnExpectedList() {
        list.addAll(new LinkedList<>(asList(11, 22, 33)));
        assertThat(list).isNotNull();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 11, 22, 33);
    }


    @Test
    public void shouldTestRetainAll() {
        List<Integer> listFirst = new LinkedList<>(asList(111, 222, 333));
        List<Integer> listSecond = new LinkedList<>(asList(111, 555, 333));
        Integer[] arrStr = new Integer[]{111, 333};
        listFirst.retainAll(listSecond);
        assertThat(listFirst).containsExactly(arrStr);
    }


    private Object dataForAddAll() {
        return new Object[]{
                new Object[]{new LinkedList<>(asList(111, 222, 555, 666)), 2, new LinkedList<>(asList(333, 444)), new Integer[]{111, 222, 333, 444, 555, 666}},
                new Object[]{new LinkedList<>(asList(111, 222, 555, 666)), 0, new LinkedList<>(asList(333, 444)), new Integer[]{333, 444, 111, 222, 555, 666}},
                new Object[]{new LinkedList<>(asList(111, 222, 555, 666)), 3, new LinkedList<>(asList(333, 444)), new Integer[]{111, 222, 555, 666, 333, 444}}
        };
    }

    @Test
    @Parameters(method = "dataForAddAll")
    public void shouldTestAddAllByIndex(List<Integer> listFirst, int index, List<Integer> listSecond, Integer[] result) {
        listFirst.addAll(index, listSecond);
        assertThat(listFirst).containsExactly(result);

    }


    @Test
    @Parameters({
            "10 | -1",
            "6  |  5",
            "88 |  4"
    })
    public void shouldReturnLastIndexOfList(Integer in, int result) {
        List<Integer> list = new LinkedList<>(asList(1, 6, 1, 6, 88, 6, 2));
        assertThat(list.lastIndexOf(in)).isEqualTo(result);
    }


    @Test
    public void shouldPrintAllElements() {
        List<Integer> list = new LinkedList<>(asList(1, 6, 1, 6, 88, 6, 2));
        for (Object object : list) {
            System.out.println("objects:" + object);
        }
    }
}