package list;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LinkedListTest {
    public static List<Integer>  list;

    @BeforeClass
    public static  void initList() {
        list = new LinkedList<>();
    }

    @Before
    public void fillTheList(){
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
        assertThat(list.size()).isEqualTo(5);
    }


    @Test
    public void shouldReturnIndexForMethodIndexOf() {
        assertThat(list.indexOf(3)).isEqualTo(2);
    }
    @Test
    public void shouldReturnMinusOneForMethodIndexOf() {
        assertThat(list.indexOf(8)).isEqualTo(-1);
    }
}