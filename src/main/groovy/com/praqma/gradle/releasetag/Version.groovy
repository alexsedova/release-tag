package com.praqma.gradle.releasetag

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class Version {

    int major
    int minor
    int patch

    static Version from(String tag) {
        // TODO: impl and unit test! (regex in groovy)
        new Version(1, 0, 0)
    }
}
