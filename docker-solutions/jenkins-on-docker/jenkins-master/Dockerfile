FROM jenkins/jenkins:2.73.2-slim

# continue setup as root
USER root

# install packages
RUN apt-get update && \
    apt-get install --no-install-recommends -y vim iputils-ping procps telnet && \
    apt-get clean

# solve problem with trend charts
RUN sed -i 's/assistive_technologies.*/#assistive_technologies=org.GNOME.Accessibility.AtkWrapper/' /etc/java-8-openjdk/accessibility.properties

ENV JENKINS_OPTS --sessionTimeout=30 --prefix=/jenkins

# continue as jenkins
USER jenkins
