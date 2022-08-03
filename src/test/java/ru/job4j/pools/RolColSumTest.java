package ru.job4j.pools;

import junit.framework.TestCase;
import org.junit.Test;
import  static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RolColSumTest {

    @Test
    public void whenNotAsyncWork() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = {
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        RolColSum.Sums[] result = RolColSum.sum(matrix);
        assertEquals(result[0], expected[0]);
    }

}