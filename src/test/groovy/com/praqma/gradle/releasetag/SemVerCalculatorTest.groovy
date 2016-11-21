package com.praqma.gradle.releasetag

class SemVerCalculatorTest extends GroovyTestCase {

    void testDummyVersion() {
        def calculator = new SemVerCalculator()
        def next = calculator.next(new Version(1, 0, 0), [
                'patch: Fix 0912',
                'minor: ... ',
                'major: ... ',
                'minor: ...',
                'patch: ...',
                'major: ...'
        ])
        assert next == new Version(2, 1, 1)
    }
    void testAnotherDummyVersion() {
        def calculator = new SemVerCalculator()
        def next = calculator.next(new Version(2, 0, 1), [
                'patch: Fix 0912',
                'minor: ... ',
                'major: ... ',
                'minor: ...',
                'patch: ...',
                'major: ...'
        ])
        assert next == new Version(3, 1, 1)
    }

    void testEmptyList() {
        def calculator = new SemVerCalculator()
        def next = calculator.next(new Version(2, 0, 1), [])
        assert next == new Version(2, 0, 1)
    }

    void testMinorPatchOnlyVersion() {
        def calculator = new SemVerCalculator()
        def next = calculator.next(new Version(1, 0, 0), [
                'patch: Fix 0912',
                'minor: ... ',
                'minor: ... ',
                'minor: ...',
                'patch: ...',
                'minor: ...'
        ])
        assert next == new Version(1, 1, 1)
    }

    void testPatchOnlyVersion() {
        def calculator = new SemVerCalculator()
        def next = calculator.next(new Version(1, 0, 0), [
                'patch: Fix 0912',
                'patch: ...',
        ])
        assert next == new Version(1, 0, 1)
    }
    void testMajorOnlyVersion() {
        def calculator = new SemVerCalculator()
        def next = calculator.next(new Version(4, 3, 2), [
                'major: Fix 0912',
                'minor: ... ',
                'minor: ... ',
                'minor: ...',
                'patch: ...',
                'minor: ...'
        ])
        assert next == new Version(5, 0, 0)
    }
}
