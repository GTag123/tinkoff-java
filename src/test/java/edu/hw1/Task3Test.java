package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Test nested array basic")
    public void testNestedArrayBasic() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {0, 6};
        assertThat(Task3.isNestable(arr1, arr2)).isTrue();
    }

    @Test
    @DisplayName("Test nested array with inverted arrays")
    public void testNestedArrayInverted() {
        int[] arr1 = {3, 1};
        int[] arr2 = {4, 0};
        assertThat(Task3.isNestable(arr1, arr2)).isTrue();
    }

    @Test
    @DisplayName("Test false nested array")
    public void testNestedArraysFalse() {
        int[] arr1 = {9, 9, 8};
        int[] arr2 = {8, 9};
        assertThat(Task3.isNestable(arr1, arr2)).isFalse();

        int[] arr3 = {1, 2, 3, 4};
        int[] arr4 = {2, 3};
        assertThat(Task3.isNestable(arr3, arr4)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test nested array with empty arrays and null")
    public void testNestedArrayEmptyArrays(int[] emptyArr) {
        int[] arr = {1, 2};
        assertThat(Task3.isNestable(arr, emptyArr)).isFalse();
        assertThat(Task3.isNestable(emptyArr, arr)).isFalse();
        assertThat(Task3.isNestable(emptyArr, emptyArr)).isFalse();
    }

}
