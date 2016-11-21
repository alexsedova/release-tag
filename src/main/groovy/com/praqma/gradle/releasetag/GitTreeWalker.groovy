package com.praqma.gradle.releasetag

import groovy.transform.CompileStatic

@CompileStatic
class GitTreeWalker {

    String getBottomTag() {
        // TODO: git magic
    }

    List<String> listCommitTitles(String bottomTag) {
        // TODO: git magic
    }

    String getReleaseTag() {
        def bottomTag = getBottomTag()
        def titles = listCommitTitles(bottomTag)
        def SemVerCalculator calculator = new SemVerCalculator()
        return calculator.next(Version.from(bottomTag), titles)
    }
}
