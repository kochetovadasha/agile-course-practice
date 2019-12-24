package ru.unn.agile.crosssections.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CrossCheckerTest {

    @Test
    public void canInitPoint() {
        int x = 3;
        int y = 2;
        Dot point = new Dot(x, y);
        assertNotNull(point);
    }

    @Test
    public void canInitPointNegative() {
        int x = -3;
        int y = -2;
        Dot point = new Dot(x, y);
        assertNotNull(point);
    }

    @Test
    public void canCreateSection() {
        Dot a = new Dot(1, 2);
        Dot b = new Dot(1, 3);
        Section section = new Section(a, b);
        assertNotNull(section);
    }

    @Test
    public void canCreateSectionNegative() {
        Dot a = new Dot(-1, -2);
        Dot b = new Dot(-1, -3);
        Section section = new Section(a, b);
        assertNotNull(section);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateSectionWithTheSamePoints() {
        Dot a = new Dot(-1, -2);
        Dot b = new Dot(-1, -2);
        Section section = new Section(a, b);
    }

    @Test
    public void canCheckCrossOnTheSameSections() {
        Dot a = new Dot(0, 2);
        Dot b = new Dot(2, 2);
        Section section1 = new Section(a, b);
        Section section2 = new Section(a, b);
        assertTrue(CrossChecker.check(section1, section2));
    }

    @Test
    public void canCheckCrossOnTheSameNegativeSections() {
        Dot a = new Dot(-1, -2);
        Dot b = new Dot(-2, -2);
        Section section1 = new Section(a, b);
        Section section2 = new Section(a, b);
        assertTrue(CrossChecker.check(section1, section2));
    }

    @Test
    public void canCheckCrossOnTheSameStartSections() {
        Dot a = new Dot(0, 0);
        Dot b = new Dot(2, 2);
        Section section1 = new Section(a, b);
        Dot c = new Dot(0, 0);
        Dot d = new Dot(0, 2);
        Section section2 = new Section(c, d);
        assertTrue(CrossChecker.check(section1, section2));
    }

    @Test
    public void canCheckCrossOnTheSameEndSections() {
        Dot a = new Dot(0, 0);
        Dot b = new Dot(2, 2);
        Section section1 = new Section(a, b);
        Dot c = new Dot(0, 2);
        Dot d = new Dot(2, 2);
        Section section2 = new Section(c, d);
        assertTrue(CrossChecker.check(section1, section2));
    }

    @Test
    public void canCheckCrossInTheMiddlePositive() {
        Dot a = new Dot(2, 2);
        Dot b = new Dot(0, 0);
        Section section1 = new Section(a, b);
        Dot c = new Dot(2, 0);
        Dot d = new Dot(0, 2);
        Section section2 = new Section(c, d);
        assertTrue(CrossChecker.check(section1, section2));
    }

    @Test
    public void canCheckCrossInTheMiddleNegative() {
        Dot a = new Dot(0, 0);
        Dot b = new Dot(-2, -2);
        Section section1 = new Section(a, b);
        Dot c = new Dot(0, -2);
        Dot d = new Dot(-2, 0);
        Section section2 = new Section(c, d);
        assertTrue(CrossChecker.check(section1, section2));
    }

    @Test
    public void canCheckNotCross() {
        Dot a = new Dot(0, 2);
        Dot b = new Dot(2, 2);
        Section section1 = new Section(a, b);
        Dot c = new Dot(0, 0);
        Dot d = new Dot(2, 0);
        Section section2 = new Section(c, d);
        assertFalse(CrossChecker.check(section1, section2));
    }

}
