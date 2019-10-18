pipeline {
    agent any
    tools {
       maven 'Maven'
       jdk 'JAVA_HOME'
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
