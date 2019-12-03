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
	        }
        }
        stage('Test-back') {
            steps {
                sh '''
                   cd back-end
                   mvn test
                '''
                jacoco(execPattern: 'back-end/target/jacoco.exec')
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
            post {
                success {
                    junit 'front-end/src/junit-test-result/*.xml'
                }
            }
        }
	stage('Docker build') {
	    steps {
                sh '''
                    docker build -t rennesgo-backend back-end
                    docker build -t rennesgo-frontend front-end
                    docker build -t rennesgo-proxy haproxy
                '''
            }
	}
        stage('Docker deploy') {
            steps {
               sh '''

               '''
            }
        }
    }
}
