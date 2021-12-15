pipeline {
    environment {
        imagename = "devkhchua/api.gateway"
        registryCredential = 'docker_credentials'
        dockerImage = ''
    }

    agent any
    stages {
        stage('Initialize') {
            steps {
                echo env.GIT_BRANCH
                script {
                    def dockerHome = tool 'Docker'
                    def mavenHome = tool 'Maven3'
                    env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
                }
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
            steps {
                script {
                    dockerImage = docker.build imagename
                }
            }
        }

        stage('Deploying Docker Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Removing Unused Docker Image') {
            steps {
                sh "docker rmi $imagename:latest"
            }
        }

        /* stage ('Deploy into Kubernetes') {
            steps{
                sshagent(credentials : ['KUBE_MACHINE']) {
                    sh 'ssh -v Jordan@192.168.0.100'
                    sh 'ssh Jordan@192.168.0.100 kubectl apply -f C:/Coding/projects/learning-java/k8s/api-gateway.yml'
                    sh 'ssh Jordan@192.168.0.100 kubectl delete pods -l app=api-gateway-app'
                    sh 'ssh Jordan@192.168.0.100 kubectl get all'
                }
            }
        } */
    }

    post {
        success {
            echo 'Build Succeed!'
            mail to: 'jordan.chuakenghui@appfuxion.com',
                subject: "${currentBuild.fullDisplayName} Pipeline Build Succeed",
                body: "The pipeline ${currentBuild.fullDisplayName} completed successfully."
            // slackSend channel: '#ewx-sba-fo-server',
            //     color: 'good',
            //     message: ":smile:The pipeline ${currentBuild.fullDisplayName} completed successfully."
        }

        failure {
            echo 'Build Failed!!'
            mail to: 'jordan.chuakenghui@appfuxion.com',
                subject: "${currentBuild.fullDisplayName} Pipeline Build Failed",
                body: "The pipeline ${currentBuild.fullDisplayName} exited with error in ${env.JOB_NAME} #${env.BUILD_NUMBER}."
            // slackSend channel: '#ewx-sba-fo-server',
            //     color: 'RED',
            //     message: ":rage:The pipeline ${currentBuild.fullDisplayName} exited with error in ${env.JOB_NAME} #${env.BUILD_NUMBER}."
        }
    }
}