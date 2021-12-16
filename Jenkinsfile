

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

        //         stage('CheckoutModule1') {
        //             steps {
        //                 sh 'mkdir -p temp'
        //                 dir("temp") {
        //                     git branch: "${BRANCH_NAME}",
        //                         credentialsId: 'GIT_CREDENTIAL',
        //                         url: 'https://github.com/devkhchua/config.service.git'
        //
        //                     sh 'mvn install'
        //                 }
        //             }
        //         }

        //         stage('Building Package') {
        //             steps {
        //                 sh 'mvn clean package'
        //             }
        //         }
        //
        //         stage('Running Tests') {
        //             steps {
        //                 sh 'mvn test'
        //
        //                 echo sh(script: 'env|sort', returnStdout: true)
        //             }
        //         }

                 stage('Building Docker Image') {
                    steps {
                        script {
                            TAG = "1.1.0"
                            echo TAG
                             TAG = sh (
                              script: '''$TAG''',
                              returnStdout: true
                              )
                            dockerImage = docker.build imagename + ":${TAG}"
                        }
                    }
                }

        /*         stage('Deploying Docker Image') {
                    steps {
                        script {
                            docker.withRegistry('', registryCredential) {
                                dockerImage.push('latest')
                            }
                        }
                    }
                } */

        /*         stage('Removing Unused Docker Image') {
                    steps {
                        sh "docker rmi $imagename:latest"
                    }
                } */

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
}