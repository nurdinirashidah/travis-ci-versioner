# travis-ci-versioner

This is a gradle plugin which generates a version number using the Travis CI build number.

### Usage for modern gradle versions _(>= 2.1)_
```
plugins {
  id "com.bnc.gradle.travis-ci-versioner" version "1.0.0"
}
```

## Documentation

### Tasks

#### Version

The plugin automatically registers a task `version` which you can call to print out the calculated version of your project:

```
$ ./gradlew version
:version
Version: 0.0.2-SNAPSHOT

BUILD SUCCESSFUL

Total time: 1.769 secs
```

### Configuration

#### version >= 0.2.0

It is possible to configure the plugin inside the `build.gradle`.

~~~~
travis-versioner {
  major integer
  minor integer
  qualifiedBranch string (the release branch, it is expected only a single branch is used for releasing)
  ciEnvironmentVariable string (the environment variable which is set in the CI environment)
  postfix string (the string which is appended to the version when CI is not running against the release branch)
}

~~~~

If you do not provide such a configuration (or fill only partial configuration) the following defaults will be used
- _major_: `0`
- _minor_: `0`
- _qualifiedBranch_: `'master'`
- _ciEnvironmentVariable_: `'CI'`
- _postfix_: `'SNAPSHOT'`

## Local build & sample test project

- `$ ./gradlew install version` will install the current version inside the local maven repository and will print the published version
- minimal test project `build.gradle` file
  ````gradle
  buildscript {
    repositories {
    mavenLocal()
    }
    dependencies {
      classpath "com.bnc.gradle.travis-ci-versioner:1.0.0"
    }
  }
  apply plugin: 'com.bnc.gradle.travis-ci-versioner'
  ````
