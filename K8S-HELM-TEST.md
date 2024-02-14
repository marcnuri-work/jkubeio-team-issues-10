# Helm: helm-test task/goal

https://github.com/eclipse/jkube/issues/2667

Blocked by https://github.com/eclipse/jkube/issues/2375

## Description

As an Eclipse JKube user, I want to be able to run tests for a Helm chart release, so that I can verify that the release is working as expected.

## Proposal

### JKube Kit

JKube Kit exposes a new method `test` that runs the tests for the chart release with the name provided in the `HelmConfig` argument (or the one inferred from the project's configuration).

The chart should have been previously installed using the `install` method.

- The tests are run by executing [helm-java `test` command](https://github.com/manusa/helm-java#test).
- The user is able to provide the following test options through the HelmConfig:
  - `releaseName`: `jkube.helm.release.name`<br/>
    Optional, if not specified, the release name should be inferred following the same procedure used to compute default image and resource names.
  - `timeout`: `jkube.helm.test.timeout`<br/>
    Optional, timeout in seconds for the tests to complete.
- If the tests succeed a message `Tests for ${installName} passed` is logged.
- If the tests fail, error message is logged.

### Maven plugins

The Kubernetes Maven Plugin and OpenShift Maven Plugin expose a new `helm-test` goal in a `HelmTestMojo` and `OpenshiftHelmTestMojo` that extends the basic `HelmMojo` (following a similar approach to that of `HelmPushMojo` and `HelmLintMojo`).

The mojos are bound by default to the `LifecyclePhase.INSTALL` phase.

Tests should be added for each of the mojos and also for `KubernetesPluginTest`, `OpenShiftPluginTest`, and `GeneratedPluginDescriptorTest`.

### Gradle plugins

The Kubernetes Gradle Plugin and OpenShift Gradle Plugin expose a new `helmTest` task in a `KubernetesHelmTestTask` and `OpenShiftHelmTestTask` that extends the basic `AbstractJKubeTask` task.

Individual tests should be added for the new tasks and also for the plugins to verify task precedence.

An [e2e test](https://github.com/jkubeio/jkube-integration-tests) proves that the task can be executed.

### Documentation

The documentation exposes for each of the Maven, Gradle, Kubernetes, and OpenShift plugins the new test goal/task.

It includes a section with the test options that can be provided in the `HelmConfig` argument and properties.
