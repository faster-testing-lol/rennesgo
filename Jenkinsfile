pipeline {
    agent any
    tools {
       maven 'Maven'
       jdk 'jdk11'
       nodejs 'node'
    }



    stages {
        stage('Build') {
            steps {
                sh './jenkins_build.sh'
                junit '*/build/test-results/*.xml'
                step( [ $class: 'JacocoPublisher' ] )
            }
        }
        stage('Build-back') {
            steps {
                sh '''
                   cd back-end
                   mvn clean compile
                '''
	        }
        }
        stage('Test-back') {
            steps {
                sh '''
                   cd back-end
                   mvn test
                '''
                jacoco(execPattern: 'target/jacoco.exec')
            }
            post {
                success {
                    junit 'back-end/target/surefire-reports/*.xml'
                }
            }
        }
	stage('Build-front') {
            steps {
                sh '''
                   cd front-end
                   npm i
                '''
            }
        }
        stage('Test-front') {
            steps {
                sh '''
                   cd front-end
                   npm run test-ci
                '''
            }
        }
    }
}
