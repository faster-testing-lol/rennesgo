pipeline {
    agent any
    tools {
       maven 'Maven'
       jdk 'jdk11'
       nodejs 'node'
    }

    stages {
        stage('Build-back') {
            steps {
                sh '''
                   cd back-end
                   mvn clean compile
                '''
								jacoco(execPattern: 'target/jacoco.exec')            
						}
        }
        stage('Test-back') {
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
	stage('Build-front') {
            steps {
                sh '''
                   cd front-end
                   npm i
                '''
            }
        }
        stage('Test-back') {
            steps {
                sh '''
                   cd front-end
                   npm run test-ci
                '''
            }
        }
    }
}
