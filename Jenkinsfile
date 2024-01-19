pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                script {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build Docker image') {
            steps {
                script {
                    bat 'docker build -t rohitraman/master-server .'
                }
            }
        }
        stage('Docker push') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        bat 'docker push rohitraman/master-server'
                    }
                }
            }
        }
        stage ('Delete previous deployment') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                            bat 'kubectl delete deployment --all'
                        }
                    }
                }
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                            bat 'kubectl delete service backend-service'
                        }
                    }
                }
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                            bat 'kubectl delete service mysql'
                        }
                    }
                }

                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                            bat 'kubectl delete pvc --all'
                        }
                    }
                }

                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                            bat 'kubectl delete pv --all'
                        }
                    }
                }
            }
        }
        stage ('Run Kubernetes Cluster') {
            steps {
                script {
                    withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                        bat 'kubectl apply -f deployments/db-volume.yml'
                    }
                }

                script {
                    withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                        bat 'kubectl apply -f deployments/db-deployment.yml'
                    }
                }

                script {
                    withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                        bat 'kubectl apply -f deployments/db-service.yml'
                    }
                }

                script {
                    withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                        bat 'kubectl apply -f deployments/backend-deployment.yml'
                    }
                }

                script {
                    withCredentials([file(credentialsId : 'kubeconfig', variable : 'KUBECONFIG')]) {
                        bat 'kubectl apply -f deployments/backend-service.yml'
                    }
                }
            }
        }
    }

}