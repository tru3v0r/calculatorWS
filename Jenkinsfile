pipeline {
    agent any

    tools {
        maven 'M3'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "-=- Checout project -=-"
                git branch: 'main', url: 'https://github.com/tru3v0r/calculatorWS'
            }
        }
        stage('Compile') {
            steps {
                echo "-=- Compile project -=-"
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                echo "-=- Test project -=-"
                sh 'mvn test'
            }

            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        } 

        stage ('Package') {
            steps {
                echo "-=- Package project -=-"
                sh 'mvn package -DskipTests'
            } 
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            } 
        }

        stage('SSH transfer') {
            steps {
                script {
                    sshPublisher(publishers: [
                        sshPublisherDesc(configName: 'docker-host', transfers:[
                            sshTransfer(
                                execCommand: '''
                                    sudo docker stop demo || true
                                    sudo docker rm demo || true
                                    sudo rmi demo || true
                                '''
                            ),
                            sshTransfer(
                                sourceFiles:"target/*.jar",
                                removePrefix: "target",
                                remoteDirectory: "/home/vagrant",
                                execCommand: "ls /home/vagrant"
                            ),
                            sshTransfer(
                                sourceFiles:"Dockerfile",
                                removePrefix: "",
                                remoteDirectory: "/home/vagrant",
                                execCommand: '''
                                    cd /home/vagrant
                                    sudo docker build -t demo .;
                                    sudo docker run -d --name demo -p 8080:8080 demo;
                                
                                '''
                            )
                        ])
                    ])                
                }
            }
        }

    }
}