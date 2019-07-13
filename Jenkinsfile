node{
    stage('git checkout'){
		git credentialsId: 'github-creds', url: 'https://github.com/jmahendr/todo-api', branch: 'master'
    }
    stage('mvn build'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh label: 'mvnBuild', script: "${mvnCMD} clean package"
    }
    stage('Docker Push Image'){
        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
            def app = docker.build("jfrancis/todo-api:1.0.0", '.').push()
        }
    }
}