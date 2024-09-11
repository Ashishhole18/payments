pipeline {
    agent any
    stages {
        stage('GIT CLONE') {
            steps {
                git 'https://github.com/Ashishhole18/payments'
            }

        }
        stage('Package Application') {
            steps {
                Foo = tool name: 'maven', type: 'maven'
                sh "${Foo}/bin/mvn clean package"
            }
        }
        stage('Docker Image') {
            steps {
                sh 'docker build it payments-docker-image .'

            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d -p 9090:8080'
            }
        }

    }

}