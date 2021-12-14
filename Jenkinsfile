pipeline {
    environment {
        imagename = "482680362026.dkr.ecr.ap-southeast-1.amazonaws.com/cdx-otp"
        //registryCredential = 'ecr_credentials'
        dockerImage = ''
     }

    agent any
    stages {
        stage('Initialize') {
            steps{
                script{
                    def dockerHome = tool 'Docker'
                    def mavenHome  = tool 'Maven'
                    env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
                }
            }
        }

        stage('CheckoutModule1') {
            steps {
                sh 'mkdir -p temp'
                dir("temp")
                {
                    git branch: "master",
                    credentialsId: 'GIT_CREDENTIAL',
                    url: 'https://github.com/devkhchua/config.service.git'
                }

                sh 'mvn install'
            }
        }

        stage('Building Package') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Running Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Building Docker Image') {
            steps{
                script {
                  dockerImage = docker.build imagename
                }
            }
        }
    }
}