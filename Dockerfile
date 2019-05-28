FROM maven:3.6.0-jdk-8 AS build

COPY . /build
WORKDIR build

RUN --mount=type=cache,target=/root/.m2 \
    mvn verify

FROM registry.redhat.io/jboss-eap-7/eap72-openshift:1.0-19

COPY --from=build /build/target/ocp-eap7-mysql-sample-*.war /tmp/ocp-eap7-mysql-sample.war
RUN cp /tmp/ocp-eap7-mysql-sample.war /opt/eap/standalone/deployments/.
