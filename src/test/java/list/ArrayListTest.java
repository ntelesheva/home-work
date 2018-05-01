package list;

import org.junit.Before;
import org.junit.Test;

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
        list.add(new Integer(2));
        list.add(new Integer(56));
        list.add(new Integer(5));
        list.remove(new Integer(2));
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldDeleteMidlObject() {
        list.add(new Integer(10));
        list.add(new Integer(56));
        list.add(new Integer(5));
        list.remove(new Integer(56));
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNewArray() {
        List<Integer> list = new ArrayList<>(10);
        list.add(new Integer(555));
        Object[] objects = list.toArray();
        assertThat(objects.length).isEqualTo(1);
    }

    @Test
    public void shouldReturnIndexfElementInList(){
        list.add(new Integer(100));
        list.add(new Integer(560));
        list.add(new Integer(100));
        assertThat(list.indexOf(new Integer(100))).isEqualTo(0);
    }

    @Test
    public void shouldReturnNegativeNumberOneAfterCallMethodIndexOf(){
        list.add(new Integer(100));
        list.add(new Integer(560));
        assertThat(list.indexOf(new Integer(55))).isEqualTo(-1);
    }

    @Test
    public void shouldReturnLAstIndexInMethodLastIndex() {
        list.add(new Integer(100));
        list.add(new Integer(560));
        list.add(new Integer(100));
        assertThat(list.lastIndexOf(new Integer(100))).isEqualTo(2);
    }

    @Test
    public void shouldReturnNegativeNumberOneAfterCallMethodLastIndexOf(){
        list.add(new Integer(100));
        list.add(new Integer(560));
        assertThat(list.lastIndexOf(new Integer(55))).isEqualTo(-1);
    }

    @Test
    public void shouldAddElementByIndex(){
        list.add(new Integer(10));
        list.add(new Integer(56));
        list.add(new Integer(80));
        list.add(new Integer(90));
        list.add(new Integer(60));
        int size = list.size();
        list.add(3, 200);
        assertThat(list.indexOf(200)).isEqualTo(3);
        assertThat(list.size()).isEqualTo(size+1);
    }

    @Test
    public void shouldReturnTrueAfterMethodContainsAll(){
        list.add(new Integer(10));
        list.add(new Integer(56));
        list.add(new Integer(80));
        list.add(new Integer(90));
        list.add(new Integer(60));
        Collection<Integer> collections = new ArrayList<>();
        collections.add(new Integer(56));
        collections.add(new Integer(60));
        assertThat(list.containsAll(collections)).isTrue();
    }

    @Test
    public void shouldReturnFalseAfterMethodContainsAll(){
        list.add(new Integer(10));
        list.add(new Integer(56));
        list.add(new Integer(80));
        list.add(new Integer(90));
        list.add(new Integer(60));
        Collection<Integer> collections = new ArrayList<>();
        collections.add(null);
        collections.add(new Integer(6));
        assertThat(list.containsAll(collections)).isFalse();
    }

    @Test
    public void shouldReturnTrueAfterMethodAddAll(){
        list.add(new Integer(10));
        list.add(new Integer(56));
        list.add(new Integer(80));
        list.add(new Integer(90));
        list.add(new Integer(60));
        List<Integer> list2 = new ArrayList<>();
        list2.add(new Integer(25));
        list2.add(new Integer(35));
        assertThat(list.addAll(list2)).isTrue();
    }
}