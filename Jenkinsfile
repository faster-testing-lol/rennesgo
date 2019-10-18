pipeline {
    agent any
    tools {
       maven 'Maven'
       jdk 'jdk11'
    }

    environment {
        JAVA_HOME = "/var/jenkins_home/tools/hudson.model.JDK/jdk11"
    }

    stages {
        stage('Build') {
            steps {
                sh '''
                   cd back-end
                   mvn build
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''
                   cd back-end
                   mvn test
                '''
            }
            post {
                success {
                    junit 'back-end/target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}
