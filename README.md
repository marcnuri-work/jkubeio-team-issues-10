# Eclipse JKube: Helm feature planning

https://github.com/jkubeio/team/issues/10

Analyze Eclipse JKube's current Helm support feature offering and propose new features to be implemented in the future.

## Current Eclipse JKube status

JKube provides support to generate Helm charts using its generated/managed Kubernetes resource manifests as input.

The current features include:
- Maven (OpenShift + Kubernetes)
  - [`k8s:helm`](https://eclipse.dev/jkube/docs/kubernetes-maven-plugin/#jkube:helm)
  - [`k8s:helm-push`](https://eclipse.dev/jkube/docs/kubernetes-maven-plugin/#jkube:helm-push)
- Gradle (OpenShift + Kubernetes)
  - [`k8sHelm`](https://eclipse.dev/jkube/docs/kubernetes-gradle-plugin/#jkubeHelm)
  - [`k8sHelmPush`](https://eclipse.dev/jkube/docs/kubernetes-gradle-plugin/#jkubeHelmPush)

## Proposed features

We can categorize the proposed features in two groups:
- Those that are related to improving the experience with the charts Eclipse JKube generates.
- Those that are related to improving the experience with the charts the user provides.

Common requirements:
- Helm: NFR add support to execute helm commands
  https://github.com/eclipse/jkube/issues/2392
  See current work on: https://github.com/manusa/helm-java

### Additional Kubernetes/OpenShift Maven/Gradle Plugin goals

Users have requested a variety of features on top of our current Helm support.

Some of them are:
- Helm: Store charts in chart repository (k8s:helm-repo-index)
  https://github.com/eclipse/jkube/issues/625
- Helm: Examine chart for possible issues (k8s:helm-lint)
  [K8S-HELM-LINT](K8S-HELM-LINT.md)
  https://github.com/eclipse/jkube/issues/2613

### Helm Maven/Gradle Plugin

[[EPIC] Helm Maven/Gradle Plugin](https://github.com/eclipse/jkube/issues/2394):

Seamless integration of Helm with your Java project.

**Charts are provided by the user.**

#### Goals

- `helm:lint`: Examine a chart for possible issues.

#### Similar or equivalent solutions

##### Kokuwa.io Helm Maven Plugin

https://github.com/kokuwaio/helm-maven-plugin

Provides a good amount of the Helm CLI functionality within a Maven Plugin.

It reuses whatever Helm CLI the user might have in the path.
Except for the `helm:upload` goal, it's just a Java wrapper around the Helm CLI.

Provided goals:

- `helm:init` initializes Helm by downloading a specific version
- `helm:dependency-build` resolves the chart dependencies
- `helm:dependency-update` verifies that the required chart dependencies are present
- `helm:package` packages the given charts (chart.tar.gz)
- `helm:lint` tests the given charts
- `helm:template` Locally render templates
- `helm:dry-run` simulates an install
- `helm:upload` upload charts via HTTP PUT
- `helm:registry-login` login into docker registry
- `helm:registry-logout` login from docker registry
- `helm:push` push charts to OCI (docker registry)
- `helm:upgrade` upgrade an already existing installation

##### Device Insight Helm Maven Plugin

https://github.com/deviceinsight/helm-maven-plugin

A Maven plugin that makes Maven properties available in your Helm Charts.

It reuses whatever Helm CLI the user might have in the path.

Provided goals:

- `helm:deploy` publishes the packaged chart in the configured chart repository
- `helm:lint` examines a chart for possible issues using the helm lint command
- `helm:package` packages a chart directory into a chart archive using the helm package command
- `helm:resolve` resolves the helm binary
- `helm:template`renders templates using the helm template command

## Related work

In order to be able to implement some of the proposed features, I started a parallel project during my free time to be able to accomplish Helm commands from Java.

[Helm Java](https://github.com/manusa/helm-java)


## Related links

- [Backlog for Helm features](https://github.com/jkubeio/team/issues/10)
- 
