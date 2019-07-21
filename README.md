##Sample java springboot application with jenkins and docker.

##Installing Jenkins
1) Start a unbuntu server.
2) Install Docker, start jenkins with volume mounted to  /var/jenkins_home

https://raw.githubusercontent.com/wardviaene/jenkins-course/master/scripts/install_jenkins.sh
```
#!/bin/bash

# this script is only tested on ubuntu xenial

# install docker
apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
apt-add-repository 'deb https://apt.dockerproject.org/repo ubuntu-xenial main'
apt-get update
apt-get install -y docker-engine
systemctl enable docker
systemctl start docker
usermod -aG docker ubuntu

# run jenkins
mkdir -p /var/jenkins_home
chown -R 1000:1000 /var/jenkins_home/
docker run -p 8080:8080 -p 50000:50000 -v /var/jenkins_home:/var/jenkins_home -d --name jenkins jenkins/jenkins:lts

# show endpoint
echo 'Jenkins installed'
echo 'You should now be able to access jenkins at: http://'$(curl -s ifconfig.co)':8080'
```
3) Build a new docker image with jenkins and docker client
https://github.com/wardviaene/jenkins-docker/blob/master/Dockerfile
```
FROM jenkins/jenkins:lts
USER root

RUN mkdir -p /tmp/download && \
 curl -L https://download.docker.com/linux/static/stable/x86_64/docker-18.03.1-ce.tgz | tar -xz -C /tmp/download && \
 rm -rf /tmp/download/docker/dockerd && \
 mv /tmp/download/docker/docker* /usr/local/bin/ && \
 rm -rf /tmp/download && \
 groupadd -g 999 docker && \
 usermod -aG staff,docker jenkins

user jenkins
```


```
docker build -t jenkinsdocker .
```

docker run -p 8080:8080 -p 50000:50000 -v /var/jenkins_home:/var/jenkins_home -d --name jenkinsdocker jenkinsdocker


4) Remove the Jenkins image + container from step 2.
5) Launch Jekins container from image from step 3.

docker run -p 8080:8080 -p 50000:50000 -v /var/jenkins_home:/var/jenkins_home -d --name jenkins jenkins/jenkins:lts



##Jenkins setup.
1) Setup github credentials as github-creds
2) Setup docker hub credentials as dockerhub
3) Install Maven as maven-3 in jenkins.
4) Install 'SSH Agent' plugin (Jenkins > Manage Jenkins > Manage Plugins)
