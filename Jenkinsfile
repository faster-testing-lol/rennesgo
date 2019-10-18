pipeline {
    agent any
    tools {
       maven 'Maven'
       jdk 'jdk11'
    }

    stages {
        stage('print_env') {
            steps {
                sh "printenv | sort"
            }
        }
        stage('Build') {
            steps {
                sh '''
                   cd back-end
                   mvn compile
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
