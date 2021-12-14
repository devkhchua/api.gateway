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
                    def mavenHome  = tool 'Maven3'
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
                sh 'ls -lrt'
                sh 'cd temp'
                sh 'pwd'
            }
        }
    }
}