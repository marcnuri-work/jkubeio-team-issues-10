# Helm: Update chart's on-disk dependencies (k8s:helm-dependency-update)

https://github.com/eclipse/jkube/issues/2110

## Description

As an Eclipse JKube user, I want to be able to update the chart's on-disk dependencies, so that I can properly install charts with dependencies.

## Proposal

### JKube Kit

JKube Kit exposes a new method `dependencyUpdate` that updates the chart's on-disk dependencies.

The chart should have been previously generated using the `generateHelmCharts` method.

- The update is performed by executing [helm-java `dependency update` command](https://github.com/manusa/helm-java#dependency-update).
- At this point, there are no options to be provided through HelmConfig. However, the method accepts a `HelmConfg` argument to infer the chart's location.
- If the update succeeds. the output of the update command is logged in `white` (`[[W]]`).
- If the update fails, error message is logged.

### Maven plugins

The Kubernetes Maven Plugin and OpenShift Maven Plugin expose a new `helm-dependency-update` goal in a `HelmDependencyUpdateMojo` and `OpenshiftHelmDependencyUpdateMojo` that extends the basic `HelmMojo` (following a similar approach to that of `HelmPushMojo`).

The mojos are bound by default to the `LifecyclePhase.INSTALL` phase.

Tests should be added for each of the mojos and also for `KubernetesPluginTest`, `OpenShiftPluginTest`, and `GeneratedPluginDescriptorTest`.

### Gradle plugins

The Kubernetes Gradle Plugin and OpenShift Gradle Plugin expose a new `helmDependencyUpdate` task in a `KubernetesHelmDependencyUpdateTask` and `OpenShiftHelmDependencyUpdateTask` that extends the basic `AbstractJKubeTask` task.

Individual tests should be added for the new tasks and also for the plugins to verify task precedence.

An integration test proves that the task can be executed and required dependencies are downloaded.

### Documentation

The documentation exposes for each of the Maven, Gradle, Kubernetes, and OpenShift plugins the new dependency update goal/task.
