# Secret - Is The Secret Function Additive #

## How To Run ##

```bash
$ mvn package exec:exec
```

## Table of contents ##

- [Output](#output)
- [Custom Runtime Config](#custom-runtime-config)
- [Unit Tests](#unit-tests)
- [Eclipse](#eclipse)
- [Technical Details](#technical-details)

---

## Output ##

```bash
...
[INFO] --- exec-maven-plugin:1.5.0:exec (default-cli) @ Secret ---
secret was additive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.581 s
...
```

OR

```bash
...
[INFO] --- exec-maven-plugin:1.5.0:exec (default-cli) @ Secret ---
secret was not additive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.718 s
...
```

## Custom Runtime Config ##

You can customize the class and method that are used for secret's implementation using the following system variable configurations.

```bash
$ mvn package
$ java -Dsecret.classname=com.example.codechallenge.utilities.SecretImplementations -Dsecret.methodname=secretFailure1 -jar target/Secret-1.0.jar 12
``` 

*secret() must be public and static.*

### Provided Implementations ###

Classes

- com.example.codechallenge.utilities.SecretImplementations

Methods

- secretSuccess1
- secretFailure1
- secretFailure2
- secretFailure3
- secretFailureEvil

## Unit Tests

```bash
$ mvn test
``` 

## Eclipse

You can build an ecplise project by issuing the following command:

```bash
$ mvn eclipse:eclipse
``` 

## Technical Details

jdk version: 1.7

Dependencies:

- Maven 3.3.9
- JUnit 4.11
