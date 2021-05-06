pipeline {
    agent any 
    tools { 
        maven 'MAVEN_LOCAL' 
        jdk 'Java_Local' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        stage('Build Backend'){
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit tests'){
            steps {
                sh 'mvn test'
            }
        }
    }
}