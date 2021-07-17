# java-conversions-app

A Spring Boot REST application for various conversions, for example temperature conversions from Celsius to Fahrenheit.

This is a demonstration project for the [Kubernetes from scratch guide](https://github.com/nicc777/kubernetes-from-scratch).

Please refer to the guide for more details.

The initial state of this repository is in the branch called `initial_copy`. The intention is to introduce all changes in appropriate branches in order for users to track changes.

## Unit Tests

Running the unit test:

```shell
./mvnw clean && ./mvnw test
```

Generate the coverage report in HTML:

```shell
./mvnw jacoco:report
```

Expose the coverage report on a web server to view on a remote system:

```shell
cd target/site/jacoco
python3 -m http.server
```

Same as above, but on a custom port `9999`:

```shell
python3 -m http.server 9999
```

## Trigger a release build

Bump the version in `pom.xml`. Store this version value in the environment variable `VERSION`

Then commit and push.

Add a new tag:

```shell
git tag -a $VERSION -m "Release trigger for version ${VERSION}"

git push origin --tags
```
