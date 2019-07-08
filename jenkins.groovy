pipeline {
	agent {
		node { label 'docker-slave-cluster' }
	}
	options { buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2')) }


	stages {
		stage('Running unit & integration test') {
			steps {
				echo 'Testing..'
				script {

				}
			}
		}
		stage('Deploy') {
			steps {

				script {
					sh "echo 'Hello World'"

				}

			}
		}
	}
}