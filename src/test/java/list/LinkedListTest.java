package list;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class LinkedListTest {
    public static List<Integer> list;

    @Before
    public void fillTheList() {
        list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
    }

    @Test
    public void shouldAddNewObjectInMethodAdd() {
        assertThat(list.size()).isEqualTo(6);
    }

    @Test
    public void shouldReturnArrayInMethodToArray() {
        List<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(10);
        list.add(10);
        Object[] objects = list.toArray();
        assertThat(objects.length).isEqualTo(3);
    }


    @Test
    public void shouldRemoveNodeFromListAndReturnTrue() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        assertThat(list.remove(new Integer(5))).isTrue();
        Integer [] result = new Integer[] {1,2,3,4,6};
        assertThat(list).contains(result).hasSize(5);
    }


    @Test
    @Parameters({
            "3|  2",
            "8| -1"
    })
    public void shouldReturnIndexForMethodIndexOf(Integer indexIn,Integer indexOut ) {
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
    public void shouldReturnTrue(List<Integer>array, boolean isContains) {
        assertThat(list.containsAll(array)).isEqualTo(isContains);
    }

    private Object addList(){
        return new Object[]{
          new Object[]{asList(1, 2 , 3), true},
          new Object[]{asList(111, 2 , 3), false},
        };
    }

    @Test
    public void shouldReturnTrueWhenRemoveAllCollection() {
        List<Integer> array = asList(111, 5, 2);
        assertThat(list.removeAll(array)).isTrue();
    }

    @Test
    @Parameters(method = "putGet")
    public void shouldReturnObjectByIndexInMethodGet(Integer index, Integer out, Exception err) {
        if(err == null) {
            assertThat(list.get(index)).isEqualTo(out);
        }
        else{
            assertThatThrownBy(() -> list.get(index))
                    .isInstanceOf(err.getClass())
                    .hasMessage(err.getMessage());
        }
    }

    private Object putGet(){
        return new Object[]{
                new Object[]{5 , 6,null},
                new Object[]{8,null, new IndexOutOfBoundsException("the index is out of range")}
        };

    }

    @Test
    public void shouldRemoveElementById() {
        list.remove(2);
        assertThat(list.get(2)).isEqualTo(4);//TODO :
    }

    @Test
    @Parameters(method = "addList2")
    public void shouldReturnTrueWhenAddCollection(List<Integer> newList, boolean rezult) {
        assertThat(list.addAll(newList)).isEqualTo(rezult);
    }

    private Object addList2(){
        return new Object[]{
          new Object[]{ asList(11, 22 ,33), true},
          new Object[] {asList(), false},
          new Object[] {null, false},
        };
    }


    @Test
    public void shouldReturnExpectedList() { //TODO:
        List<Integer> listInt = asList(11, 22, 33);
        //List<Integer> listExpected = Arrays.asList(1,2,3,4,5,6,11,22,33);
        list.addAll(listInt);
        assertThat(list).isNotNull();
    }




    @Test
    public void shouldTestRetainAll(){
        List<Integer> listFirst = new LinkedList<>(Arrays.asList(111, 222, 333));
        List<Integer> listSecond = new LinkedList<>(Arrays.asList(111, 555, 333));
        Integer [] arrStr = new Integer[]{111, 333};
        listFirst.retainAll(listSecond);
        assertThat(listFirst).containsExactly(arrStr);
    }


private Object dataForAddAll(){
        return new Object[]{
          new Object[]{asList(111,222,555,666), 2 ,asList(333,444), new Integer[]{111,222,333,444,555,666}}
        };
}

    @Test
   @Parameters(method = "dataForAddAll")
    public void shouldTestAddAllByIndex(List<Integer> listFirst,int index,  List<Integer>listSecond, Integer [] result){
//        int index = 2;
//        List<Integer> listFirst = new LinkedList<>();
//        listFirst.add(111);
//        listFirst.add(222);
//        listFirst.add(555);
//        listFirst.add(666);
//
//        List<Integer> listSecond = new LinkedList<>();
//        listSecond.add(333);
//        listSecond.add(444);
//
//        Integer [] result = new Integer[]{111,222,333,444,555,666};
        listFirst.addAll(index, listSecond);
        assertThat(listFirst).containsExactly(result);

    }
}